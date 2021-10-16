package com.acmecompany.todolist.controller;

import com.acmecompany.todolist.model.Task;
import com.acmecompany.todolist.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Creating a new Task")
    @ApiResponses(value ={
            @ApiResponse(code = 201, message = "Successfully created Task"),
            @ApiResponse(code = 500, message = "Could not create the Task, please check your information")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task){
        log.info("Creating a new task with the information: [{}]", task);
        return taskService.createTask(task);
    }

    @ApiOperation(value = "Listing all Tasks")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Tasks listed successfully"),
            @ApiResponse(code = 500, message = "Could not list all the Tasks")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks(){
        log.info("Listing all tasks created");
        return taskService.listAllTasks();
    }

    @ApiOperation(value = "Finding task by ID")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Task found successfully"),
            @ApiResponse(code = 404, message = "Could not found the Tasks")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getTaskBy(@PathVariable Long id){
        log.info("Finding task by ID: [{}]", id);
        return taskService.findTaskById(id);
    }

    @ApiOperation(value = "Updating the task by ID")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Task updated successfully"),
            @ApiResponse(code = 404, message = "Could not update the Tasks")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTaskBy(@PathVariable Long id, @RequestBody Task task){
        log.info("Updating the task by ID: [{}] with new informations", id);
        return taskService.updateTaskById(task, id);
    }

    @ApiOperation(value = "Deleting the task by ID")
    @ApiResponses(value ={
            @ApiResponse(code = 204, message = "Task deleted successfully"),
            @ApiResponse(code = 404, message = "Could not delete the Tasks")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteTask(@PathVariable Long id){
        log.info("Deleting the task by ID: [{}]", id);
        return taskService.deleteById(id);
    }
}
