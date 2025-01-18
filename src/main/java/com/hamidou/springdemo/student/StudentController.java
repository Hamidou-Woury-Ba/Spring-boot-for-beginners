package com.hamidou.springdemo.student;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private StudentService service;

    public StudentController(StudentService service){
        this.service = service;
    }
    @PostMapping
    public Student Save(@RequestBody Student student){
        return service.Save(student);
    }
    @GetMapping
    public List<Student> findAllStudents() {
       return service.findAllStudents();
    }
    @GetMapping("/{email}")
    public Student findByEmail (@PathVariable("email") String email){
        return service.findByEmail(email);
    }
    @PutMapping
    public Student updateStudent(@RequestBody Student student){
        return service.update(student);
    }
    @DeleteMapping("/{email}")
    public void delete(@PathVariable("email") String email){
        service.delete(email);
    }

}
