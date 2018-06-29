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

import com.gubs.springboot.entity.ExpenseTracker;
import com.gubs.springboot.repository.ExpenseTrackerRepository;

@RestController
@RequestMapping(path="/expenseTracker")
public class ExpenseTrackerController {
	
	private ExpenseTrackerRepository expenseTrackerRepository;

	// http://localhost:8080/demo/add?name=gubs&email=gubs@jeanmartin.com
    public ExpenseTrackerController(ExpenseTrackerRepository expenseTrackerRepository) {
    	this.expenseTrackerRepository = expenseTrackerRepository;
	}
    
    
    @PostMapping
    public void addExpense(@RequestBody ExpenseTracker body) {
    	expenseTrackerRepository.save(body);
    }
    
    @GetMapping
    public List<ExpenseTracker> getExpenses() {
    	return expenseTrackerRepository.findAll();
    }
    
    @PutMapping("/{id}")
    public void editExpense(@PathVariable long id, @RequestBody ExpenseTracker body) {
    	ExpenseTracker expense = expenseTrackerRepository.getOne(id);
    	Assert.notNull(expense, "Task not found");
    	expense.setItemName(body.getItemName());
    	expense.setDescription(body.getDescription());
    	
    	expenseTrackerRepository.save(body);
    }
    
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable long id) {
    	expenseTrackerRepository.delete(id);
    }
	
}
