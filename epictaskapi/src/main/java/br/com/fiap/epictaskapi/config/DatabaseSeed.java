package br.com.fiap.epictaskapi.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.repository.TaskRepository;

@Configuration
public class DatabaseSeed implements CommandLineRunner {

    @Autowired
    TaskRepository repository;

    @Override
    public void run(String... args) throws Exception {

        repository.saveAll(List.of(
            new Task("Modelar o BD", "Modelar as tabelas do banco", 100 , 0), 
            new Task("Bug", "Modelar as tabelas do banco", 50 , 0), 
            new Task("Prototipo", "Modelar as tabelas do banco", 30 , 0), 
            new Task("Deploy", "Modelar as tabelas do banco", 10 , 0), 
            new Task("Login", "Modelar as tabelas do banco", 40 , 0), 
            new Task("Criar token", "Modelar as tabelas do banco", 55 , 0), 
            new Task("Analise de dados", "Modelar as tabelas do banco", 5 , 0), 
            new Task("Testes unitários", "Modelar as tabelas do banco", 200 , 0), 
            new Task("Configurar ambiente", "Modelar as tabelas do banco", 210 , 0), 
            new Task("Prototipo", "prototipar as telas", 190 , 0)
        ));
        
    }
    
}
