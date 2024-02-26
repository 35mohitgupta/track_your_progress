package com.srs.progresstracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProgressRequest {
    private LocalDate progressDate;
    private List<TaskProgressDTO> progessList;


}
