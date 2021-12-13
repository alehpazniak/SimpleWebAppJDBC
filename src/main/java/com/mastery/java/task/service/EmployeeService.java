package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.mapper.EmployeeMapper;
import com.mastery.java.task.rest.protocol.EmployeeRequest;
import com.mastery.java.task.rest.protocol.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmployeeService {

    private EmployeeDao employeeDao;
    private EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao, EmployeeMapper employeeMapper) {
        this.employeeDao = employeeDao;
        this.employeeMapper = employeeMapper;
    }

    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee = employeeDao.saveEmployee(employeeMapper.mapToEmployee(employeeRequest));
        return employeeMapper.mapToResponse(employee);
    }

    public void deleteDismissedEmployeeById(long id) {
        employeeDao.deleteDismissedEmployeeById(id);
    }

    public EmployeeResponse updateEmployeeById(EmployeeRequest employeeRequest, long id) {
        Optional<Employee> employeeOptional = employeeDao.findById(id);
        if (employeeOptional.isPresent()) {
            Employee updateEmployee = employeeDao.updateEmployeeById(employeeMapper.mapToEmployee(employeeRequest), id);
            return employeeMapper.mapToResponse(updateEmployee);
        } else {
            throw new NoSuchElementException("Client with id: " + id + " wasn't found");
        }
    }

    public List<EmployeeResponse> findAllEmployee() {
        return StreamSupport.stream(employeeDao.findAllEmployee().spliterator(), false)
                .map(employeeMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public EmployeeResponse findById(long id) {
        Optional<Employee> employeeOptional = employeeDao.findById(id);
        if (employeeOptional.isPresent()) {
            return employeeMapper.mapToResponse(employeeOptional.get());
        } else {
            throw new NoSuchElementException("Client with id: " + id + " wasn't found");
        }
    }

}
