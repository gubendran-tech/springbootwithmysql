package com.gubs.springboot.controller;

import java.util.List;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gubs.springboot.entity.Task;
import com.gubs.springboot.repository.TaskRepository;

// https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/
// https://docs.spring.io/spring-security/site/docs/current/guides/html5/helloworld-boot.html
@RestController    // This means that this class is a @Controller  + @ResponseBody
@RequestMapping(path="/tasks") // This means URL's start with /demo (after Application path)
public class TaskController {
	
	private TaskRepository taskRepository;

	// http://localhost:8080/demo/add?name=gubs&email=gubs@jeanmartin.com
    public TaskController(TaskRepository taskRepository) {
    	this.taskRepository = taskRepository;
	}
    
    
    @PostMapping
    public void addTask(@RequestBody Task task) {
    	taskRepository.save(task);
    }
    
    @GetMapping
    public List<Task> getTasks() {
    	return taskRepository.findAll();
    }
    
    @PutMapping("/{id}")
    public void editTask(@PathVariable long id, @RequestBody Task task) {
    	Task existingTask = taskRepository.getOne(id);
    	Assert.notNull(existingTask, "Task not found");
    	existingTask.setName(task.getName());
    	existingTask.setDescription(task.getDescription());
    	taskRepository.save(existingTask);
    }
    
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id) {
    	taskRepository.delete(id);
    }
	
}