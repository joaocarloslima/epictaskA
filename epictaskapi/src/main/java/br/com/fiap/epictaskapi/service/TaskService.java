package br.com.fiap.epictaskapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.epictaskapi.model.Task;

@Service
public class TaskService {
    
    public List<Task> listAll(){
        return List.of( 
            new Task("Modalagem do BD", "Modelagem das tabelas do banco de dados", 100, 0),
            new Task("Prototipação", "Prototipar a interface gráfica", 100, 0)
        );

    }

}
