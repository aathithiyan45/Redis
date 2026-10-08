package com.example.demo.controller;

import com.example.demo.service.StudentService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public String getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }
    @PutMapping("/{id}")
    public String updateStudent(@PathVariable Long id) {
        return studentService.updateStudent(id);
    }
}