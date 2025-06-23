package com.wts.backend.repository.custom;

import java.util.List;

import com.wts.backend.entity.HistoryTracker;

public interface HistoryTrackerCustomRepository {
    List<HistoryTracker> findLatestEntryPerWagon();
}

