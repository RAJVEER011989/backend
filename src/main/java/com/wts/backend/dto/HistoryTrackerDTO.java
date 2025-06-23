package com.wts.backend.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryTrackerDTO {
    private String wagonName;
    private String tagId;
    private String departmentName;
    private String activity;
    private LocalDateTime createdAt;
}
