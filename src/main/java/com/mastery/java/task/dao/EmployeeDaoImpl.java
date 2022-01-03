package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.mapper.EmployeeRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Value("${app.simplewebapp.database.sql.UPDATE_SQL}")
    private String UPDATE_SQL;
    @Value("${app.simplewebapp.database.sql.DELETE_SQL}")
    private String DELETE_SQL;
    @Value("${app.simplewebapp.database.sql.FIND_ALL_SQL}")
    private String FIND_ALL_SQL;
    @Value("${app.simplewebapp.database.sql.FIND_BY_ID_SQL}")
    private String FIND_BY_ID_SQL;
    @Value("${app.simplewebapp.database.sql.INSERT_SQL}")
    private String INSERT_SQL;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Employee> findById(long id) {
        return Optional.of(jdbcTemplate.query(FIND_BY_ID_SQL, new EmployeeRowMapper(), new Object[]{id}).get(0));
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, new EmployeeRowMapper());
    }

    @Override
    public Employee updateById(Employee employee, long id) {
        jdbcTemplate.update(UPDATE_SQL,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender().name(),
                Date.valueOf(employee.getDateOfBirth()),
                id);
        employee.setEmployeeId(id);
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        jdbcTemplate.update(INSERT_SQL,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender().name(),
                Date.valueOf(employee.getDateOfBirth()));
        return employee;
    }

    @Override
    public void deleteById(long id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }
}
