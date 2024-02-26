package com.srs.progresstracker.service;

import com.srs.progresstracker.document.DailyProgress;
import com.srs.progresstracker.document.Task;
import com.srs.progresstracker.dto.*;
import com.srs.progresstracker.repository.ProgressRepository;
import com.srs.progresstracker.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    protected ProgressRepository progressRepository;

    //DateTimeFomatter to format the date as 2021-12-31
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //Service method to create a new task
    public TaskDTO createTask(TaskDTO taskDTO) {
        checkIfTaskExists(taskDTO.getTaskName());
        final Task task = TaskDTO.toTask(taskDTO);
        task.setLastUpdated(null);
        task.setTimeSpent(BigDecimal.ZERO);
        task.setTaskStatus(TaskStatus.YET_TO_START);
        final Task savedTask = taskRepository.save(task);
        return TaskDTO.toTaskDTO(savedTask);
    }


    //Service method to fetch all the tasks
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream().map(TaskDTO::toTaskDTO).toList();
    }

    private void checkIfTaskExists(String taskName) {
        if (taskRepository.existsById(taskName)) {
            throw new RuntimeException("Task with name " + taskName + " already exists");
        }
    }

    //Service method to update the progress of a task
    public void addProgress(LocalDate date, String taskName, BigDecimal progressInHours) {
        final Task task = fetchTask(taskName);
        task.setTimeSpent(task.getTimeSpent().add(progressInHours));
        task.setTaskStatus(TaskStatus.IN_PROGRESS);
        task.setLastUpdated(date == null ? LocalDate.now() : date);
        taskRepository.save(task);
    }

    //Service method to mark a task as completed
    public TaskDTO markTaskAsCompleted(String taskName) {
        final Task task = fetchTask(taskName);
        task.setTaskStatus(TaskStatus.COMPLETED);
        task.setLastUpdated(LocalDate.now());
        final Task savedTask = taskRepository.save(task);
        return TaskDTO.toTaskDTO(savedTask);
    }

    private Task fetchTask(String taskName) {
        return taskRepository.findById(taskName).orElseThrow(() -> new RuntimeException("Task with name " + taskName + " does not exist"));
    }

    public List<TaskDTO> getTasksByStatus(String taskStatus) {
        return getAllTasks().stream().filter(taskDTO -> taskDTO.getTaskStatus().name().equals(taskStatus)).toList();
    }

    public List<MinimalTaskDTO> getMinimalTaskDetails() {
        return taskRepository.findAll().stream().map(MinimalTaskDTO::toMinimalTaskDTO).toList();
    }

    @Transactional
    public List<MinimalTaskDTO> addProgressToTasks(ProgressRequest progressRequest) {
        progressRequest.getProgessList().forEach(taskProgress -> addProgress(progressRequest.getProgressDate(),taskProgress.getTaskName(), taskProgress.getTimeInvested()));
        saveDailyProgress(progressRequest);
        return getMinimalTaskDetails();
    }

    private void saveDailyProgress(ProgressRequest progressRequest) {
        final LocalDate progressDate = progressRequest.getProgressDate() == null ? LocalDate.now() : progressRequest.getProgressDate();
        final DailyProgress dailyProgress = progressRepository.findById(progressDate.format(DATE_FORMATTER)).orElse(null);
        if (dailyProgress == null) {
            final DailyProgress newDailyProgress = new DailyProgress(progressDate.format(DATE_FORMATTER), progressRequest.getProgessList().stream().map(TaskProgressDTO::toTaskProgress).toList());
            progressRepository.save(newDailyProgress);
        } else {
            dailyProgress.getProgressList().addAll(progressRequest.getProgessList().stream().map(TaskProgressDTO::toTaskProgress).toList());
            progressRepository.save(dailyProgress);
        }
    }

    public ProgressRequest getProgressForDate(String date) {
        final LocalDate progressDate = date == null ? LocalDate.now() : LocalDate.parse(date);
        final DailyProgress dailyProgress = progressRepository.findById(progressDate.format(DATE_FORMATTER)).orElse(null);
        if (dailyProgress == null) {
            return new ProgressRequest(progressDate, List.of());
        } else {
            return new ProgressRequest(progressDate, dailyProgress.getProgressList().stream().map(TaskProgressDTO::toTaskProgressDTO).toList());
        }
    }
}
