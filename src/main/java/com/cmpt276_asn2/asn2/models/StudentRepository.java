package com.cmpt276_asn2.asn2.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByName(String name);
    List<Student> findById(int id);
    List<Student> findByGpa(float gpa);
    List<Student> findByYear(int year);
    List<Student> findByRating(int rating);
    List<Student> findByColor(String color);
    Student findByNameAndPassword(String name, String password);
}