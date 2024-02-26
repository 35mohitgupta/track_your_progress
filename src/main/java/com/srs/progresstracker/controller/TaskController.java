package com.srs.progresstracker.controller;

import com.srs.progresstracker.dto.ProgressRequest;
import com.srs.progresstracker.dto.TaskDTO;
import com.srs.progresstracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    //Endpoint to create a new task
    @PostMapping()
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.createTask(taskDTO));
    }

    //Endpoint to get the list of all tasks
    @GetMapping("/all")
    public ResponseEntity<?> getMinimalTasksDetails() {
        return ResponseEntity.ok(taskService.getMinimalTaskDetails());
    }

    @GetMapping("/{taskStatus}")
    public ResponseEntity<?> getTasksByStatus(@PathVariable String taskStatus) {
        return ResponseEntity.ok(taskService.getTasksByStatus(taskStatus));
    }

    @PutMapping()
    public ResponseEntity<?> addProgressToTasks(@RequestBody ProgressRequest progressRequest) {
        return ResponseEntity.ok(taskService.addProgressToTasks(progressRequest));
    }

    @DeleteMapping("/{taskName}")
    public ResponseEntity<?> closeTask(@PathVariable String taskName) {
        return ResponseEntity.ok(taskService.markTaskAsCompleted(taskName));
    }

    @GetMapping("/progress/{date}")
    public ResponseEntity<?> getProgressForDate(@PathVariable String date) {
        return ResponseEntity.ok(taskService.getProgressForDate(date));
    }



}
