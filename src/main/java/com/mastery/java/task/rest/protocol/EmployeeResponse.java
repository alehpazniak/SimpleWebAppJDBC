package com.mastery.java.task.rest.protocol;

import com.mastery.java.task.dto.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeResponse {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private int departmentId;
    private String jobTitle;
    private Gender gender;
    private LocalDate dateOfBirth;

}
