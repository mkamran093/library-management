package com.example.employee.dto;

import jakarta.validation.constraints.NotBlank;

public record EmployeePatchDTO(
        String name,
        Integer age,
        Integer salary,
        String department
) {
}
