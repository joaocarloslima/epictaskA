package br.com.fiap.epictaskapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.repository.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    TaskRepository repository;
    
    public List<Task> listAll(){
        return repository.findAll();
    }

    public void save(Task task) {
        repository.save(task);
    }

    public Task getById(Long id) {
        return repository.getReferenceById(id);
    }

}
