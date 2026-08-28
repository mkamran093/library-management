package com.example.employee.repository;

import com.example.employee.dto.EmployeePatchDTO;
import com.example.employee.exception.ResourceNotFoundException;
import com.example.employee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();

    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }

    public Employee findById(Long id) {
        Employee employee = employees.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElse(null);

        return employee;
    }

    public void create(Employee employee) {
        employees.add(employee);
    }

    public void update(Long id, Employee employee) {
        Employee emp = employees.stream()
                .filter(employee1 -> employee1.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (emp == null) {
            throw new ResourceNotFoundException("Employee with id = " + id + " not found");
        }
        emp.setName(employee.getName());
        emp.setAge(employee.getAge());
        emp.setSalary(employee.getSalary());
        emp.setDepartment(employee.getDepartment());
    }

    public Employee update(Long id, EmployeePatchDTO request) {
        Employee employee = employees.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (employee == null) {
            throw new ResourceNotFoundException("Employee with id = " + id + " not found");
        }

        if (request.name() != null) {
            employee.setName(request.name());
        }
        if (request.age() != null) {
            employee.setAge(request.age());
        }
        if (request.salary() != null) {
            employee.setSalary(request.salary());
        }
        if (request.department() != null) {
            employee.setDepartment(request.department());
        }

        return employee;
    }

    public void delete(Long id) {
        Employee employee = employees.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (employee == null) {
            throw new ResourceNotFoundException("Employee with id = " + id + " not found");
        }
        employees.remove(employee);
    }
}
