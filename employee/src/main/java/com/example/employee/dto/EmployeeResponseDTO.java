package com.example.employee.dto;

public record EmployeeResponseDTO(
        Long id, String name, int age, int salary, String department
) {
}
