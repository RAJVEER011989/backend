package com.wts.backend.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wts.backend.entity.Department;
import com.wts.backend.entity.HistoryTracker;
import com.wts.backend.entity.Wagons;
import com.wts.backend.repository.custom.HistoryTrackerCustomRepository;

@Repository
public interface HistoryTrackerRepository extends JpaRepository<HistoryTracker, Long>, HistoryTrackerCustomRepository   {
    
    boolean existsByWagonAndDepartmentAndActivityAndCreatedAfter(
    Wagons wagon,
    Department department,
    String activity,
    LocalDateTime created
);
}


