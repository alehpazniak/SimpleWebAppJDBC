package com.mastery.java.task.rest.protocol;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private int departmentId;
    private String jobTitle;
    private String gender;
    private LocalDate dateOfBirth;
}
