package com.wts.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wts.backend.dto.HistoryTrackerDTO;
import com.wts.backend.entity.Department;
import com.wts.backend.entity.HistoryTracker;
import com.wts.backend.entity.UHFReader;
import com.wts.backend.entity.Wagons;
import com.wts.backend.repository.HistoryTrackerRepository;
import com.wts.backend.repository.UHFReaderRepository;
import com.wts.backend.repository.WagonsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoryTrackerService {

     private final WagonsRepository wagonRepository;
    private final UHFReaderRepository uhfReaderRepository;
    private final HistoryTrackerRepository historyRepository;

    public void logActivity(String tagId, String readerId, String activity) {
        Wagons wagon = wagonRepository.findByTagId(tagId)
            .orElseThrow(() -> new RuntimeException("Wagon not found for tag: " + tagId));
    
        UHFReader reader = uhfReaderRepository.findByReaderId(readerId)
            .orElseThrow(() -> new RuntimeException("UHF Reader not found for readerId: " + readerId));
    
        Department department = reader.getDepartment();
    
        // Debounce logic: check for duplicate log in last 10 seconds
        LocalDateTime debounceThreshold = LocalDateTime.now().minusSeconds(10);
        boolean recentlyLogged = historyRepository
            .existsByWagonAndDepartmentAndActivityAndCreatedAfter(
                wagon, department, activity, debounceThreshold
            );
    
        if (recentlyLogged) {
            // Optional: log debug or info
            System.out.println("Debounced repeated log for wagon " + tagId);
            return; // Don't save duplicate
        }
    
        // Log history
        HistoryTracker history = new HistoryTracker();
        history.setWagon(wagon);
        history.setDepartment(department);
        history.setActivity(activity);
        history.setCreated(LocalDateTime.now());
    
        historyRepository.save(history);
    }

    public List<HistoryTrackerDTO> getAllHistory() {
    List<HistoryTracker> historyList = historyRepository.findAll();

    return historyList.stream().map(history -> {
        HistoryTrackerDTO dto = new HistoryTrackerDTO();
        dto.setWagonName(history.getWagon().getWagonName());
        dto.setTagId(history.getWagon().getTagId());
        dto.setDepartmentName(history.getDepartment().getName());
        dto.setActivity(history.getActivity());
        dto.setCreatedAt(history.getCreated());
        return dto;
    }).collect(Collectors.toList());
}

public List<HistoryTrackerDTO> getFilteredHistory(String wagonName, String departmentName,
                                           LocalDateTime from, LocalDateTime to) {
    List<HistoryTracker> results = historyRepository.findAll().stream()
        .filter(h -> wagonName == null || h.getWagon().getWagonName().equalsIgnoreCase(wagonName))
        .filter(h -> departmentName == null || h.getDepartment().getName().equalsIgnoreCase(departmentName))
        .filter(h -> from == null || !h.getCreated().isBefore(from))
        .filter(h -> to == null || !h.getCreated().isAfter(to))
        .collect(Collectors.toList());

    return results.stream().map(h -> {
        HistoryTrackerDTO dto = new HistoryTrackerDTO();
        dto.setWagonName(h.getWagon().getWagonName());
        dto.setTagId(h.getWagon().getTagId());
        dto.setDepartmentName(h.getDepartment().getName());
        dto.setActivity(h.getActivity());
        dto.setCreatedAt(h.getCreated());
        return dto;
    }).collect(Collectors.toList());
}

public List<HistoryTrackerDTO> getMostRecentLocationPerWagon() {
    return historyRepository.findLatestEntryPerWagon().stream()
        .map(h -> {
            HistoryTrackerDTO dto = new HistoryTrackerDTO();
            dto.setWagonName(h.getWagon().getWagonName());
            dto.setTagId(h.getWagon().getTagId());
            dto.setDepartmentName(h.getDepartment().getName());
            dto.setActivity(h.getActivity());
            dto.setCreatedAt(h.getCreated());
            return dto;
        })
        .collect(Collectors.toList());
}
    
}
