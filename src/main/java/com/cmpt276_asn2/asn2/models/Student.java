package com.cmpt276_asn2.asn2.models;

import jakarta.persistence.*;

@Entity
@Table(name="Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String password;
    private String name;
    private Float gpa;
    private int year;
    private int rating;
    private String color;

    // Default constructor
    public Student() { 
    }

    // Paramerter constructor
    public Student(String name, String password, Float gpa, int year, int rating, String color) {
        this.name = name;
        this.password = password;
        this.gpa = gpa;
        this.year = year;
        this.rating = rating;
        this.color = color;
    }


    // getters for the Student entity
    public String getName() {
        return name;
    }

    public String getPassword(){
        return password;
    }

    public int getUid() {
        return uid;
    }

    public float getGpa() {
        return gpa;
    }

    public int getYear() {
        return year;
    }

    public int getRating(){
        return rating;
    }

    public String getColor(){
        return color;
    }

    // setters for the Student entity
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
    
    public void setGpa(Float gpa){
        this.gpa = gpa;
    }

    public void setYear(int year){
        this.year = year;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

    public void setColor(String color){
        this.color = color;
    }
}