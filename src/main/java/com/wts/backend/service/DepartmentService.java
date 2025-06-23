package com.wts.backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.wts.backend.entity.Department;
import com.wts.backend.repository.DepartmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor // Lombok: generates constructor for final fields
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    /**
     * Fetches all departments from the database.
     * @return List of Department entities
     */
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    /**
     * Fetch a department by its name.
     * Optional helper method in case you need it elsewhere.
     */
    public Department getDepartmentByName(String name) {
        return departmentRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Department not found: " + name));
    }

    /**
 * Saves a new department to the database.
 * @param department department to be added
 * @return the saved department entity
 */
public Department saveDepartment(Department department) {
    return departmentRepository.save(department); // Saves and returns the new record
}
}

