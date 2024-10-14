package br.com.fiap.epictaska.task;

import br.com.fiap.epictaska.user.User;
import br.com.fiap.epictaska.user.UserRepository;
import br.com.fiap.epictaska.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public void delete(UUID id) {
        taskRepository.deleteById(id);
    }

    public void catchTask(UUID id, User user) {
        var task = taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Task not found")
        );

        if (task.getUser() != null) return;

        task.setUser(user);
        taskRepository.save(task);
    }

    public void releaseTask(UUID id, User user) {
        var task = taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Task not found")
        );

        if (!user.equals(task.getUser())) return;

        task.setUser(null);
        taskRepository.save(task);
    }

    public void incTask(UUID id, User user) {
        var task = taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Task not found")
        );

        if (task.getStatus() + 10 > 100) return;

        task.setStatus(task.getStatus() + 10);

        if (task.getStatus() == 100){
            userService.addScore(task.getScore(), user);
        }

        taskRepository.save(task);
    }

    public void decTask(UUID id, User user) {
        var task = taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Task not found")
        );

        if (task.getStatus() - 10 < 0) return;

        task.setStatus(task.getStatus() - 10);
        taskRepository.save(task);
    }

    public List<Task> findPending() {
        return taskRepository.findByStatusLessThan(100);
    }
}
