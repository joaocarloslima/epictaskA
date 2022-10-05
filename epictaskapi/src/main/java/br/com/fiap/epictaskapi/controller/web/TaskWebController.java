package br.com.fiap.epictaskapi.controller.web;

import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.repository.TaskRepository;
import br.com.fiap.epictaskapi.service.TaskService;

@Controller
@RequestMapping("/task")
public class TaskWebController {

    @Autowired
    TaskService service;

    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("task/index").addObject("tasks", service.listAll());
    }

    @GetMapping("new")
    public String form(Task task){
        return "task/new";
    }

    @PostMapping
    public String create(
            @Valid Task task, 
            BindingResult binding, 
            RedirectAttributes redirect
        ){

        if (binding.hasErrors()) return "/task/new";
        System.out.println(task.getId());
        String message = (task.getId() == null)
                ?"Tarefa cadastrada com sucesso"
                :"Tarefa alterada com sucesso";

        service.save(task);
        redirect.addFlashAttribute("message", message);
        return "redirect:/task";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        service.deleteById(id);
        redirect.addFlashAttribute("message", "Tarefa apagada com sucesso");
        return "redirect:/task";
    }

    @GetMapping("{id}")
    public ModelAndView edit(@PathVariable Long id){
        Optional<Task> optional = service.getById(id);
        if (optional.isEmpty()) return new ModelAndView("task/index");
        
        return new ModelAndView("task/new")
            .addObject("task", optional.get());
    }
    
}
