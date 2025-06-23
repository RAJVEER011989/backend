package com.wts.backend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wts.backend.entity.Wagons;
import com.wts.backend.service.WagonsService;

import java.util.List;

/**
 * Controller to manage wagon-related endpoints.
 */
@RestController
@RequestMapping("/api/wagons") // All endpoints will start with /api/wagons
@RequiredArgsConstructor // Injects WagonService via constructor
public class WagonsController {
    private final WagonsService wagonService;

    /**
     * GET /api/wagons
     * Fetches all wagons from the system.
     */
    @GetMapping
    public ResponseEntity<List<Wagons>> getAllWagons() {
        List<Wagons> wagons = wagonService.getAllWagons();
        return ResponseEntity.ok(wagons); // 200 OK with list of wagons
    }

    /**
     * GET /api/wagons/tag/{tagId}
     * Fetches a single wagon by its tag ID.
     */
    @GetMapping("/tag/{tagId}")
    public ResponseEntity<Wagons> getWagonByTagId(@PathVariable String tagId) {
        Wagons wagon = wagonService.getWagonByTagId(tagId);
        return ResponseEntity.ok(wagon); // 200 OK with wagon object
    }

    /**
     * PUT /api/wagons/{wagonId}/tag
     * Updates the tag ID of a wagon.
     * Used when a tag gets corrupted or replaced.
     */
    @PutMapping("/{wagonId}/tag")
    public ResponseEntity<Wagons> updateWagonTag(
            @PathVariable Long wagonId,
            @RequestParam String newTagId
    ) {
        Wagons updated = wagonService.updateWagonTag(wagonId, newTagId);
        return ResponseEntity.ok(updated); // 200 OK with updated wagon
    }

    /**
 * POST /api/wagons
 * Adds a new wagon to the system.
 */
@PostMapping
public ResponseEntity<Wagons> addWagon(@RequestBody Wagons wagon) {
    Wagons saved = wagonService.saveWagon(wagon); // Call service to save wagon
    return ResponseEntity.ok(saved); // Return saved wagon in response
}
}
