package com.srs.progresstracker.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskProgress {
    private String taskName;
    private BigDecimal timeInvested;
}
