package com.mastery.java.task.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Employee {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private int departmentId;
    private String jobTitle;
    private Gender gender;
    private LocalDate dateOfBirth;
}
