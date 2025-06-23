package com.wts.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wts.backend.entity.Wagons;
import com.wts.backend.repository.WagonsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Lombok: automatically generates constructor for final fields
public class WagonsService {
    // Injecting WagonRepository for database access
    private final WagonsRepository wagonRepository;

    /**
     * Fetch all wagons from the database.
     * @return List of all wagons
     */
    public List<Wagons> getAllWagons() {
        return wagonRepository.findAll(); // Uses built-in JPA method
    }

    /**
     * Find a wagon by its tag ID.
     * @param tagId the RFID tag attached to the wagon
     * @return Wagon if found
     * @throws RuntimeException if no wagon with this tag exists
     */
    public Wagons getWagonByTagId(String tagId) {
        return wagonRepository.findByTagId(tagId)
                .orElseThrow(() -> new RuntimeException("Wagon not found for tag: " + tagId));
    }

    /**
     * Update the tag ID of a wagon (used when a tag is corrupted/replaced).
     * @param wagonId ID of the wagon to update
     * @param newTagId the new tag ID to associate with the wagon
     * @return updated Wagon object
     * @throws RuntimeException if the wagon is not found
     */
    public Wagons updateWagonTag(Long wagonId, String newTagId) {
        Wagons wagon = wagonRepository.findById(wagonId)
                .orElseThrow(() -> new RuntimeException("Wagon not found with ID: " + wagonId));
        
        wagon.setTagId(newTagId); // Set the new tag
        return wagonRepository.save(wagon); // Save and return updated wagon
    }

    /**
 * Saves a new wagon to the database.
 * @param wagon the wagon to save
 * @return the saved wagon entity
 */
public Wagons saveWagon(Wagons wagon) {
    wagon.setId(null); 
    return wagonRepository.save(wagon); // Save new wagon with name and tag ID
}
}
