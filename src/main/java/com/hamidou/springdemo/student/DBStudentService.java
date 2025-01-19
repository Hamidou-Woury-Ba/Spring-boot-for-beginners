package com.hamidou.springdemo.student;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBStudentService implements StudentService{

    private final StudentRepository repository;

    public DBStudentService(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student Save(Student s) {
        return repository.save(s);
    }

    @Override
    public List<Student> findAllStudents() {
        return repository.findAll();
    }

    @Override
    public Student findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Student update(Student s) {
        return repository.save(s);
    }

    @Override
    public void delete(String email) {
        repository.deleteByEmail(email);
    }
}
