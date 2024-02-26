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
public class TaskDTO {
    private String taskName;
    private String taskDescription;
    private TaskStatus taskStatus;
    private BigDecimal estimatedTime;
    private BigDecimal timeSpent;
    private BigDecimal percentageCompleted;
    private LocalDate startDate;
    private LocalDate lastUpdated;
    private LocalDate dueDate;


    //Method to convert TaskDTO to Task
    public static Task toTask(final TaskDTO taskDTO) {
        Task task = new Task();
        task.setTaskName(taskDTO.getTaskName());
        task.setTaskDescription(taskDTO.getTaskDescription());
        task.setTaskStatus(taskDTO.getTaskStatus());
        task.setStartDate(taskDTO.getStartDate());
        task.setEstimatedTime(taskDTO.getEstimatedTime());
        task.setTimeSpent(taskDTO.getTimeSpent());
        task.setLastUpdated(taskDTO.getLastUpdated());
        task.setDueDate(taskDTO.getDueDate());
        return task;
    }

    //Method to convert Task to TaskDTO
    public static TaskDTO toTaskDTO(final Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskName(task.getTaskName());
        taskDTO.setTaskDescription(task.getTaskDescription());
        taskDTO.setTaskStatus(task.getTaskStatus());
        taskDTO.setEstimatedTime(task.getEstimatedTime());
        taskDTO.setTimeSpent(task.getTimeSpent());
        taskDTO.setLastUpdated(task.getLastUpdated());
        taskDTO.setStartDate(task.getStartDate());
        taskDTO.setDueDate(task.getDueDate());
        taskDTO.setPercentageCompleted(taskDTO.timeSpent.multiply(BigDecimal.valueOf(100)).divide(taskDTO.estimatedTime, 2, RoundingMode.HALF_UP));
        return taskDTO;
    }

}
