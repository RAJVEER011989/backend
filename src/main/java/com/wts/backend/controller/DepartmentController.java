package com.wts.backend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wts.backend.entity.Department;
import com.wts.backend.service.DepartmentService;

import java.util.List;

/**
 * Controller to manage departments (viewing only for now).
 */
@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    /**
     * GET /api/departments
     * Returns all departments in the system.
     */
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    /**
     * GET /api/departments/{name}
     * (Optional) Get department by name.
     */
    @GetMapping("/{name}")
    public ResponseEntity<Department> getDepartmentByName(@PathVariable String name) {
        Department dept = departmentService.getDepartmentByName(name);
        return ResponseEntity.ok(dept);
    }

    /**
 * POST /api/departments
 * Adds a new department to the system.
 */
@PostMapping
public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
    Department saved = departmentService.saveDepartment(department); // Delegate to service
    return ResponseEntity.ok(saved); // Return saved department as response
}
}
