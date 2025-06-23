package com.wts.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO used to create new UHF readers via POST request.
 */
@Data
public class UHFReaderRequestDTO {

    @Schema(example = "192.168.1.110")
    private String readerId;

    @Schema(example = "IN", description = "Direction of the reader: IN or OUT")
    private String direction;

    @Schema(example = "1", description = "Name of the department to which the reader is assigned")
    private String departmentName;
}