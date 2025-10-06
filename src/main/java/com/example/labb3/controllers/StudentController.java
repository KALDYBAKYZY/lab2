package com.example.labb3.controllers;
import com.example.labb3.class1.Student;
import org.springframework.stereotype.Controller;
import java.util.ArrayList;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
public class StudentController {
    private ArrayList<Student> students = new ArrayList<>();
    private Long id_counter = 1L;
    @GetMapping("/")
    public String IndexPage(Model model){
        model.addAttribute("students", students);
        return "home";
    }
    @GetMapping("/add")
    public String AddPage(){
        return "add";
    }
    @PostMapping("/add")
    public String AddStudent(@RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam int exam){
        Student student = new Student();
        student.setId(id_counter++);
        student.setName(name);
        student.setSurname(surname);
        student.setExam(exam);
        if (exam >= 90)
            student.setMark("A");
        else if (exam >= 75)
            student.setMark("B");
        else if (exam >= 60)
            student.setMark("C");
        else if (exam >= 50)
            student.setMark("D");
        else
            student.setMark("F");

        students.add(student);
        return "redirect:/";
    }
}
