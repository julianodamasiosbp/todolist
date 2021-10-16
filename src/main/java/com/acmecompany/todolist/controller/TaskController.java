package com.acmecompany.todolist.controller;

import com.acmecompany.todolist.model.Task;
import com.acmecompany.todolist.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
@Slf4j
public class TaskController {

    TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task){
        log.info("Creating a new task with the information: [{}]", task);
        return taskService.createTask(task);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks(){
        log.info("Listing all tasks created");
        return taskService.listAllTasks();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getTaskBy(@PathVariable Long id){
        log.info("Finding task by ID: [{}]", id);
        return taskService.findTaskById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTaskBy(@PathVariable Long id, @RequestBody Task task){
        log.info("Updating the task by ID: [{}] with new informations", id);
        return taskService.updateTaskById(task, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteTask(@PathVariable Long id){
        log.info("Deleting the task by ID: [{}]", id);
        return taskService.deleteById(id);
    }
}
