package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.mapper.EmployeeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class EmployeeDao implements IEmployeeDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public EmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("employee")
                .usingGeneratedKeyColumns("employee_id");
    }

    @Override
    public Employee save(Employee employee) {
        Map<String, Object> params = new HashMap<>();
        params.put("employee_id", null);
        params.put("first_name", employee.getFirstName());
        params.put("last_name", employee.getLastName());
        params.put("department_id", employee.getDepartmentId());
        params.put("job_title", employee.getJobTitle());
        params.put("gender", employee.getGender().toString().toUpperCase());
        params.put("date_of_birth", employee.getDateOfBirth());

        long generateId = simpleJdbcInsert.executeAndReturnKey(params).longValue();
        employee.setEmployeeId(generateId);
        return employee;
    }

    @Override
    public void deleteById(long id) {
        String sql = "DELETE FROM employee WHERE employee_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Employee updateById(Employee employee, long id) {
        jdbcTemplate.update("UPDATE employee SET " +
                        "first_name = ?, " +
                        "last_name = ?, " +
                        "department_id = ?, " +
                        "job_title = ?, " +
                        "gender = ?, " +
                        "date_of_birth = ? " +
                        "WHERE employee_id = ?",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender().name(),
                employee.getDateOfBirth(),
                id);
        employee.setEmployeeId(id);
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employee ORDER BY employee_id";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    @Override
    public Optional<Employee> findById(long id) {
        String sql = "SELECT *  FROM employee WHERE employee_id = ?";
        return Optional.of(jdbcTemplate.query(sql, new EmployeeRowMapper(), new Object[]{id}).get(0));
    }
}
