package com.srs.progresstracker.document;

import com.srs.progresstracker.dto.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

// Add the required annotations to make this class a document class.

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Task {
    @Id
    private String taskName;
    private String taskDescription;
    private TaskStatus taskStatus;
    private BigDecimal estimatedTime;
    private BigDecimal timeSpent;
    private LocalDate startDate;
    private LocalDate lastUpdated;
    private LocalDate dueDate;
}
