package com.DeveloperTestAmarisBack.service.serviceImpl;

import com.DeveloperTestAmarisBack.configurate.ApiProperties;
import com.DeveloperTestAmarisBack.model.AnEmployee;
import com.DeveloperTestAmarisBack.model.Employee;
import com.DeveloperTestAmarisBack.model.EmployeesGroup;
import com.DeveloperTestAmarisBack.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private ApiProperties apiProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Employee> getAllEmployees() throws Exception {
        EmployeesGroup employeesGroup = new EmployeesGroup();
        List<Employee> employeeList = null;
        try {
            employeeList = restTemplate.getForObject(apiProperties.getDataUrl()+"employees", EmployeesGroup.class).getData();
            employeeList = employeeList.stream()
                    .map(entity -> modelMapper.map(entity, Employee.class))
                    .collect(Collectors.toList());
            employeesGroup.setStatus("200");
            employeesGroup.setData(employeeList);
            employeesGroup.setMessage("success request");
        }catch (HttpClientErrorException httpE){
            System.err.println(httpE.getStatusCode().toString());
            if(httpE.getStatusText().equals("Too Many Requests")){
                throw new Exception("Error 429 The service send too many requests");
            }else{
                throw new Exception("Error 500 Internal server error");
            }
        }catch (Exception e){
            throw new Exception("System Error: "+e);
        }

        return employeeList;
    }

    @Override
    public Employee getOneEmployee(Long idEmployee) throws Exception {
        AnEmployee anEmployee = new AnEmployee();
        Employee employee = null;
        try {
            employee = restTemplate.getForObject(apiProperties.getDataUrl()+"employee/"+idEmployee, AnEmployee.class).getData();
        }catch (HttpClientErrorException httpE){
            System.err.println(httpE.getStatusCode().toString());
            if(httpE.getStatusText().equals("Too Many Requests")){
                throw new Exception("Error 429 The service send too many requests");
            }else{
                throw new Exception("Error 500 Internal server error");
            }
        }catch (Exception e){
            throw new Exception("System Error: "+e);
        }
        return employee;
    }
}
