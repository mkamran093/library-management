package com.example.employee.dto;

import jakarta.validation.constraints.NotBlank;

public record EmployeeRequestDTO (
        @NotBlank(message = "Name is required") String name,
        int age,
        int salary,
        String department
) {
}
