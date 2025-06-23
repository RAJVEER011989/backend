package com.wts.backend.controller;

import com.wts.backend.dto.HistoryTrackerDTO;
import com.wts.backend.service.HistoryTrackerService;
import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * REST controller for logging and retrieving wagon movement history.
 */
@RestController
@RequestMapping("/api/history-tracker")
@RequiredArgsConstructor
public class HistoryTrackerController {

    private final HistoryTrackerService historyService;

    /**
     * Endpoint to log a new history tracker record.
     * Called by hardware integration or backend when a tag is scanned.
     */
    @PostMapping("/log")
    public ResponseEntity<String> logHistory(
            @RequestParam String tagId,
            @RequestParam String readerId,
            @RequestParam String activity
    ) {
        historyService.logActivity(tagId, readerId, activity);
        return ResponseEntity.ok("History entry logged successfully.");
    }

    /**
     * Endpoint to retrieve all history tracker records in DTO format.
     * Called by frontend to display readable data.
     */
    @GetMapping
    public ResponseEntity<List<HistoryTrackerDTO>> getAllHistory() {
        return ResponseEntity.ok(historyService.getAllHistory());
    }

    @GetMapping("/filter")
public ResponseEntity<List<HistoryTrackerDTO>> getFilteredHistory(
        @RequestParam(required = false) String wagonName,
        @RequestParam(required = false) String departmentName,
        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
) {
    return ResponseEntity.ok(historyService.getFilteredHistory(wagonName, departmentName, from, to));
}

@GetMapping("/latestByWagon")
public ResponseEntity<List<HistoryTrackerDTO>> getLatestPerWagon() {
    return ResponseEntity.ok(historyService.getMostRecentLocationPerWagon());
}
}
