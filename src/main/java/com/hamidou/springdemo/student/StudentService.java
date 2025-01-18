package com.hamidou.springdemo.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {
    public List<Student> findAllStudents() {
        return List.of(
                new Student(
                        "Hamidou",
                        "Ba",
                        LocalDate.now().toString(),
                        "hamidou@gmail.com",
                        23
                ),
                new Student(
                        "Aliou",
                        "Barry",
                        LocalDate.now().toString(),
                        "aliou@gmail.com",
                        25
                )
        );
    }

}
