package com.example.employee.controller;

import com.example.employee.dto.EmployeePatchDTO;
import com.example.employee.dto.EmployeeRequestDTO;
import com.example.employee.dto.EmployeeResponseDTO;
import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<EmployeeResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Long create(@RequestBody EmployeeRequestDTO requestDTO) {
        return service.create(requestDTO);
    }

    @PutMapping("/{id}")
    public EmployeeResponseDTO update(@PathVariable Long id, @RequestBody EmployeeRequestDTO requestDTO) {
        return service.update(id, requestDTO);
    }

    @PatchMapping("/{id}")
    public EmployeeResponseDTO update(@PathVariable Long id, @RequestBody EmployeePatchDTO patchDTO) {
        return service.update(id, patchDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
