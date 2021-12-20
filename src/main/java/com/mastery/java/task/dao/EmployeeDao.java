package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.mapper.EmployeeRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class EmployeeDao implements IEmployeeDao {

    @Value("${app.simplewebapp.database.sql.UPDATE_SQL}")
    private String UPDATE_SQL;
    @Value("${app.simplewebapp.database.sql.DELETE_SQL}")
    private String DELETE_SQL;
    @Value("${app.simplewebapp.database.sql.FIND_ALL_SQL}")
    private String FIND_ALL_SQL;
    @Value("${app.simplewebapp.database.sql.FIND_BY_ID_SQL}")
    private String FIND_BY_ID_SQL;

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

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
        jdbcTemplate.update(DELETE_SQL, id);
    }

    @Override
    public Employee updateById(Employee employee, long id) {
        jdbcTemplate.update(UPDATE_SQL,
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
        return jdbcTemplate.query(FIND_ALL_SQL, new EmployeeRowMapper());
    }

    @Override
    public Optional<Employee> findById(long id) {
        return Optional.of(jdbcTemplate.query(FIND_BY_ID_SQL, new EmployeeRowMapper(), new Object[]{id}).get(0));
    }
}
