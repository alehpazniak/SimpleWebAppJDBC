package com.mastery.java.task.mapper;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employeeEntity = new Employee();
        employeeEntity.setEmployeeId(rs.getLong("employee_id"));
        employeeEntity.setFirstName(rs.getString("first_name"));
        employeeEntity.setLastName(rs.getString("last_name"));
        employeeEntity.setDepartmentId(rs.getInt("department_id"));
        employeeEntity.setGender(Gender.valueOf(rs.getString("gender").toUpperCase()));
        employeeEntity.setDateOfBirth(rs.getTimestamp("date_of_birth").toLocalDateTime().toLocalDate());
        employeeEntity.setJobTitle(rs.getString("job_title"));
        return employeeEntity;
    }
}
