package br.com.fiap.epictaskapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.service.TaskService;

@RestController
public class TaskController {

    @Autowired
    private TaskService service;
    
    @GetMapping("/api/task")
    public List<Task> index(){
        return service.listAll();
    }

}
