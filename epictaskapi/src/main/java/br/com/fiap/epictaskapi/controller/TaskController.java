package br.com.fiap.epictaskapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService service;
    
    @GetMapping()
    public List<Task> index(){
        return service.listAll();
    }

    @PostMapping()
    public ResponseEntity<Task> create(@RequestBody @Valid Task task){
        service.save(task);
        
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(task);
    }

    @GetMapping("{id}")
    public Task show(@PathVariable Long id){
        Task task = service.getById(id);
        System.out.println(task);
        return task;
    }


}
