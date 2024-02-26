package com.srs.progresstracker.dto;

import com.srs.progresstracker.document.TaskProgress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskProgressDTO {
    private String taskName;
    private BigDecimal timeInvested;

    //Method to convert TaskProgressDTO to TaskProgress
    public static TaskProgress toTaskProgress(final TaskProgressDTO taskProgressDTO) {
        TaskProgress taskProgress = new TaskProgress();
        taskProgress.setTaskName(taskProgressDTO.getTaskName());
        taskProgress.setTimeInvested(taskProgressDTO.getTimeInvested());
        return taskProgress;
    }

    //Method to convert TaskProgress to TaskProgressDTO
    public static TaskProgressDTO toTaskProgressDTO(final TaskProgress taskProgress) {
        TaskProgressDTO taskProgressDTO = new TaskProgressDTO();
        taskProgressDTO.setTaskName(taskProgress.getTaskName());
        taskProgressDTO.setTimeInvested(taskProgress.getTimeInvested());
        return taskProgressDTO;
    }

}
