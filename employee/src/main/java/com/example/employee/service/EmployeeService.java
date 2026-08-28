package com.example.employee.service;


import com.example.employee.dto.EmployeePatchDTO;
import com.example.employee.dto.EmployeeRequestDTO;
import com.example.employee.dto.EmployeeResponseDTO;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    EmployeeRepository repository;
    private Long nextId = 1L;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<EmployeeResponseDTO> getAll() {

        List<Employee> employees = repository.findAll();
        List<EmployeeResponseDTO> response = new ArrayList<>();
        for (Employee emp: employees) {
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO(
                    emp.getId(),
                    emp.getName(),
                    emp.getAge(),
                    emp.getSalary(),
                    emp.getDepartment()
            );
            response.add(responseDTO);
        }
        return response;
    }

    public EmployeeResponseDTO getById(Long id) {
        Employee employee = repository.findById(id);
        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getAge(),
                employee.getSalary(),
                employee.getDepartment()
        );
    }

    public Long create(@Valid EmployeeRequestDTO request) {
        repository.create(new Employee(
                nextId++,
                request.name(),
                request.age(),
                request.salary(),
                request.department()
        ));
        return nextId - 1;
    }

    public EmployeeResponseDTO update(Long id, EmployeeRequestDTO request) {
        Employee employee = new Employee(id, request.name(), request.age(), request.salary(), request.department());
        repository.update(id, employee);
        return new EmployeeResponseDTO(
                id,
                employee.getName(),
                employee.getAge(),
                employee.getSalary(),
                employee.getDepartment()
        );
    }

    public EmployeeResponseDTO update(Long id, EmployeePatchDTO request) {
        if (request.name() == null &&
                request.age() == null &&
                request.salary() == null &&
                request.department() == null) {
            throw new IllegalArgumentException(
                    "At least one field must be provided"
            );
        }
        Employee emp = repository.update(id, request);
        return new EmployeeResponseDTO(
                emp.getId(),
                emp.getName(),
                emp.getAge(),
                emp.getSalary(),
                emp.getDepartment()
        );
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}
