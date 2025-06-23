package com.wts.backend.repository.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wts.backend.entity.HistoryTracker;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class HistoryTrackerCustomRepositoryImpl implements HistoryTrackerCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<HistoryTracker> findLatestEntryPerWagon() {
        String nativeQuery = """
            SELECT *
            FROM (
                SELECT *, 
                       ROW_NUMBER() OVER (PARTITION BY wagon_id ORDER BY created DESC) AS rn
                FROM history_tracker
            ) AS ranked
            WHERE rn = 1
            """;

        return entityManager.createNativeQuery(nativeQuery, HistoryTracker.class)
                            .getResultList();
    }
}

