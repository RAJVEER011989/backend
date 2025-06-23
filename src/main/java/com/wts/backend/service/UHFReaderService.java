package com.wts.backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.wts.backend.entity.UHFReader;
import com.wts.backend.repository.UHFReaderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor // Auto-generates constructor for final fields
public class UHFReaderService {

    private final UHFReaderRepository uhfReaderRepository;

    /**
     * Fetch all UHF readers from the database.
     * @return List of UHFReader entities
     */
    public List<UHFReader> getAllReaders() {
        return uhfReaderRepository.findAll();
    }

    /**
     * Find a reader by its readerId (e.g., IP address or serial ID).
     * @param readerId the ID of the reader
     * @return the UHFReader entity if found
     */
    public UHFReader getReaderById(String readerId) {
        return uhfReaderRepository.findByReaderId(readerId)
                .orElseThrow(() -> new RuntimeException("UHF Reader not found with ID: " + readerId));
    }

    /**
 * Saves a new UHFReader to the database.
 * @param reader the new UHFReader to create
 * @return the saved UHFReader
 */
public UHFReader saveReader(UHFReader reader) {
    return uhfReaderRepository.save(reader);
}

}
