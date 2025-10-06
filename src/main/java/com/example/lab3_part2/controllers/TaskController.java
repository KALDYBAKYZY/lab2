package com.example.lab3_part2.controllers;
import com.example.lab3_part2.class2.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@Controller
public class TaskController {

    private ArrayList<Task> tasks = new ArrayList<>();
    private Long counter = 1L;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("task", new Task());
        return "tasks";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam String name,
                          @RequestParam String deadlineDate,
                          @RequestParam(required = false) String description,
                          @RequestParam(required = false, defaultValue = "false") boolean isCompleted) {
        Task task = new Task();
        task.setId(counter++);
        task.setName(name);
        task.setDeadlineDate(deadlineDate);
        task.setDescription(description);
        task.setCompleted(isCompleted);
        tasks.add(task);
        return "redirect:/";
    }


    @GetMapping("/task")
    public String details(@RequestParam Long id, Model model) {
        Task foundTask = null;
        for (Task t : tasks) {
            if (t.getId().equals(id)) {
                foundTask = t;
                break;
            }
        }

        if (foundTask != null) {
            model.addAttribute("task", foundTask);
            return "task-details";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/edit")
    public String editTask(@RequestParam Long id,
                           @RequestParam String name,
                           @RequestParam String deadlineDate,
                           @RequestParam(required = false, defaultValue = "false") boolean completed) {
        for (Task t : tasks) {
            if (t.getId().equals(id)) {
                t.setName(name);
                t.setDeadlineDate(deadlineDate);
                t.setCompleted(completed);
                break;
            }
        }
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteTask(@RequestParam Long id) {
        tasks.removeIf(t -> t.getId().equals(id));
        return "redirect:/";
    }

}
