package com.wts.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wts.backend.entity.UHFReader;

@Repository
public interface UHFReaderRepository extends JpaRepository<UHFReader, Long> {
    Optional<UHFReader> findByReaderId(String readerId);
    
}
