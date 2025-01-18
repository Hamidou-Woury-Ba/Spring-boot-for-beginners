package com.hamidou.springdemo.student;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    Student Save(Student s);
    List<Student> findAllStudents();

    Student findByEmail(String email);

    Student update(Student s);

    void delete(String email);

}
