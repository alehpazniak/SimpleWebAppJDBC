package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDaoImpl;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.mapper.EmployeeMapper;
import com.mastery.java.task.rest.protocol.EmployeeRequest;
import com.mastery.java.task.rest.protocol.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeDaoImpl employeeDao;
    private final EmployeeMapper employeeMapper;

    public EmployeeResponse save(EmployeeRequest employeeRequest) {
        Employee employee = employeeDao.save(employeeMapper.mapToEmployee(employeeRequest));
        return employeeMapper.mapToResponse(employee);
    }

    public void deleteById(long id) {
        employeeDao.deleteById(id);
    }

    public EmployeeResponse updateById(EmployeeRequest employeeRequest, long id) {
        employeeDao.findById(id).orElseThrow(NoSuchElementException::new);
        return employeeMapper.mapToResponse(employeeDao.updateById(
                employeeMapper.mapToEmployee(employeeRequest), id));
    }

    public List<EmployeeResponse> findAll() {
        return employeeDao.findAll().stream()
                .map(employeeMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public EmployeeResponse findById(long id) {
        Employee employee = employeeDao.findById(id)
                .orElseThrow(NoSuchElementException::new);
        return employeeMapper.mapToResponse(employee);
    }

}
