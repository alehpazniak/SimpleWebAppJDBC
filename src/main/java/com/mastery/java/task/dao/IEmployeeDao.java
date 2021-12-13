package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeDao {

    Employee saveEmployee(Employee employee);

    void deleteDismissedEmployeeById(long id);

    Employee updateEmployeeById(Employee employee, long id);

    List<Employee> findAllEmployee();

    Optional<Employee> findById(long id);
}
