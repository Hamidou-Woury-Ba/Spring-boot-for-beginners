package com.hamidou.springdemo.student;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Student {

    @Id
    @GeneratedValue
    private Integer id;
    private String firstname;

    private String lastname;

    private String dateOfBirth;

    @Column(unique = true)
    private String email;

    @Transient
    private int age;


    public Student(String firstname, String lastname, String dateOfBirth, String email, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.age = age;
    }

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String dateOfBirth() {
        return dateOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return Period.between(LocalDate.parse(dateOfBirth), LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }
}
