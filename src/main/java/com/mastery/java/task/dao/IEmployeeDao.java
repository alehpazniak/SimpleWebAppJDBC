package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeDao {

    Employee save(Employee employee);

    void deleteById(long id);

    Employee updateById(Employee employee, long id);

    List<Employee> findAll();

    Optional<Employee> findById(long id);
}
