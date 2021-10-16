package com.acmecompany.todolist.controller;

import com.acmecompany.todolist.model.Task;
import com.acmecompany.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks(){
        return taskService.listAllTasks();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getTaskBy(@PathVariable Long id){
        return taskService.findTaskById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTaskBy(@PathVariable Long id, @RequestBody Task task){
        return taskService.updateTaskById(task, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteTask(@PathVariable Long id){
        return taskService.deleteById(id);
    }
}
