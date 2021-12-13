package com.mastery.java.task.mapper;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.rest.protocol.EmployeeRequest;
import com.mastery.java.task.rest.protocol.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class EmployeeMapper {

    private final ConversionService conversionService;

    @Autowired
    public EmployeeMapper(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public Employee mapToEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setDepartmentId(employeeRequest.getDepartmentId());
        employee.setGender(Gender.valueOf(employeeRequest.getGender().toUpperCase()));
        employee.setDateOfBirth(Objects
                .requireNonNull(conversionService.convert(employeeRequest.getDateOfBirth(), LocalDate.class)));
        employee.setJobTitle(employeeRequest.getJobTitle());
        return employee;
    }


    public EmployeeResponse mapToResponse(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setFirstName(employee.getFirstName());
        employeeResponse.setLastName(employee.getLastName());
        employeeResponse.setDepartmentId(employee.getDepartmentId());
        employeeResponse.setGender(employee.getGender());
        employeeResponse.setDateOfBirth(employee.getDateOfBirth());
        employeeResponse.setJobTitle(employee.getJobTitle());
        return employeeResponse;
    }
}
