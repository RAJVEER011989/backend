package com.wts.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wts.backend.entity.Wagons;

@Repository
public interface WagonsRepository extends JpaRepository<Wagons, Long> {
    Optional<Wagons> findByTagId(String tagId);
}

