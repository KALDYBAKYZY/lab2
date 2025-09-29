package com.example.lab3_part2.controllers;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.lab3_part2.class2.Task;
import java.util.HashMap;

@Controller
public class TaskController{
    private HashMap<Long, Task> tasks = new HashMap<>();
    private HashMap<Long, String> descriptions = new HashMap<>();
    private Long counter = 1L;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", tasks.values());
        return "tasks";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("task", new Task());
        return "addtask";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task, @RequestParam("description") String description) {
        task.setId(counter++);
        task.setCompleted(false);
        tasks.put(task.getId(), task);
        descriptions.put(task.getId(), description);
        return "redirect:/";
    }

    @GetMapping("/task/{id}")
    public String details(@PathVariable Long id, Model model) {
        Task task = tasks.get(id);
        model.addAttribute("task", task);
        model.addAttribute("description", descriptions.get(id));
        return "task-details";
    }

    @PostMapping("/edit/{id}")
    public String editTask(@PathVariable Long id,
                           @ModelAttribute Task updatedTask,
                           @RequestParam("description") String description) {
        Task task = tasks.get(id);
        if (task != null) {
            task.setName(updatedTask.getName());
            task.setDeadlineDate(updatedTask.getDeadlineDate());
            task.setCompleted(updatedTask.isCompleted());
            descriptions.put(id, description);
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        tasks.remove(id);
        descriptions.remove(id);
        return "redirect:/";
    }
}

