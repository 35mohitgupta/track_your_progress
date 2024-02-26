package com.srs.progresstracker.dto;

import com.srs.progresstracker.document.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MinimalTaskDTO {
    private String taskName;
    private TaskStatus taskStatus;
    private BigDecimal percentageCompleted;
    private LocalDate lastUpdated;



    //Method to convert Task to TaskDTO
    public static MinimalTaskDTO toMinimalTaskDTO(final Task task) {
        MinimalTaskDTO minimalTaskDTO = new MinimalTaskDTO();
        minimalTaskDTO.setTaskName(task.getTaskName());
        minimalTaskDTO.setTaskStatus(task.getTaskStatus());
        minimalTaskDTO.setLastUpdated(task.getLastUpdated());
        minimalTaskDTO.setPercentageCompleted(task.getTimeSpent().multiply(BigDecimal.valueOf(100)).divide(task.getEstimatedTime(), 2, RoundingMode.HALF_UP));
        return minimalTaskDTO;
    }

}
