package com.wts.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wts.backend.dto.UHFReaderRequestDTO;
import com.wts.backend.entity.Department;
import com.wts.backend.entity.UHFReader;
import com.wts.backend.service.UHFReaderService;

import java.util.List;

/**
 * Controller to handle UHF reader-related requests.
 */
@RestController
@RequestMapping("/api/readers")
@RequiredArgsConstructor
public class UHFReaderController {

    private final UHFReaderService uhfReaderService;

    /**
     * GET /api/readers
     * Fetches all UHF readers.
     */
    @GetMapping
    public ResponseEntity<List<UHFReader>> getAllReaders() {
        return ResponseEntity.ok(uhfReaderService.getAllReaders());
    }

    /**
     * GET /api/readers/{readerId}
     * Fetch a specific reader by its ID (e.g., IP address).
     */
    @GetMapping("/{readerId}")
    public ResponseEntity<UHFReader> getReaderById(@PathVariable String readerId) {
        return ResponseEntity.ok(uhfReaderService.getReaderById(readerId));
    }

    /**
     * POST /api/readers
     * Adds a new UHF reader and assigns it to a department.
     */
    @PostMapping
    public ResponseEntity<UHFReader> addReader(@RequestBody UHFReaderRequestDTO dto) {
        // Look up department by name instead of ID
        Department department = departmentService.getDepartmentByName(dto.getDepartmentName());

        UHFReader reader = new UHFReader();
        reader.setReaderId(dto.getReaderId());
        reader.setDirection(dto.getDirection());
        reader.setDepartment(department);

        return ResponseEntity.ok(uhfReaderService.saveReader(reader));
    }
}
