package com.cmpt276_asn2.asn2.controllers;

import java.util.List;
import java.util.Map;

import org.aspectj.weaver.NewFieldTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cmpt276_asn2.asn2.models.Student;
import com.cmpt276_asn2.asn2.models.StudentRepository;

import jakarta.servlet.http.HttpServletMapping;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;




@Controller
public class StudentController {
    
    @Autowired
    private StudentRepository studRepo;

    // REDIRECT THE HOME PAGE TO THE ADD STUDENT PAGE
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String newStudent(){
        return "newStudent";
    }


    // SHOWING ALL THE STUDENTS FROM THE HOME PAGE BUTTON
    @GetMapping("/showAll")
    public String showAll(Model model){
        System.out.println("Showing all students");
        List<Student> Students = studRepo.findAll();
        model.addAttribute("students", Students);
        return "students/showAll/showAll";
    }


    // SHOW ALL THE STUDENTS FROM BUTTON AFTER ADDING NEW STUDENT
    @GetMapping("/studentList")
    public String showAall(Model model){
        System.out.println("Showing all students");
        List<Student> Students = studRepo.findAll();
        model.addAttribute("students", Students);
        return "students/showAll/showAll";
    }
    

    // GETTING ALL THE STUDENTS IN THE DB
    @GetMapping("/students/newStudent")
    public String getAllStudents(Model model){
        System.out.println("getting Students");
        List<Student> Students = studRepo.findAll();
        model.addAttribute("students", Students);
        return "students/showAll/showAll";
    }

    // REDIRECT TO THE EDIT FORM
    @GetMapping("/students/login")
    public String editStudent(){
        return "students/login/login";
    }

    @GetMapping("/students/addNew")
    public String addStudent(){
        return "students/addStudent/addStudent";
    }

    // REDIRECT TO THE DELETE FORM
    @GetMapping("/students/delete")
    public String deleteStudent(){
        return "students/delete/delete";
    }

    // GETTING ALL THE STUDENTS AFTER THE EDIT FORM
    @GetMapping("/edited")
    public String showAfterEdit(Model model){
        List<Student> students = studRepo.findAll();
        model.addAttribute("students", students);
        return "students/showAll/showAll";
    }
    

    // GET ALL THE STUDENTS AFTER THE DELETE
    @GetMapping("/deleted")
    public String showAfterDelete(Model model){
        List<Student> students = studRepo.findAll();
        model.addAttribute("students", students);
        return "students/showAll/showAll";
    }

    // GET THE NAME AND PASS OF THE STUDENT THAT WANTS TO BE DELETED
    @PostMapping("/students/deleting")
    public String deletingStudent(@RequestParam Map<String, String> deleteStudent, HttpServletResponse response){
        String name = deleteStudent.get("name");
        String pass = deleteStudent.get("password");
        Student student = studRepo.findByNameAndPassword(name, pass);
        if(student != null){
            studRepo.delete(student);
            response.setStatus(200);
            return "students/deleted/deleted";
        }
        else{
            response.setStatus(404);
            return "students/notFound/notFound";
        }
    }

    // GET THE NAME AND PASSWORD TO ACCESS THE EDIT FORM
    @PostMapping("/students/edit")
    public String editStudent(@RequestParam Map<String, String> editStudent, HttpServletResponse response){
        String name = editStudent.get("name");
        String pass = editStudent.get("password");
        Float newGPA = Float.parseFloat(editStudent.get("gpa"));
        int newYear = Integer.parseInt(editStudent.get("year"));
        int newRating = Integer.parseInt(editStudent.get("rating"));
        String newColor = editStudent.get("color");
        Student student = studRepo.findByNameAndPassword(name, pass);
        if(student != null){
            student.setGpa(newGPA);
            student.setYear(newYear);
            student.setRating(newRating);
            student.setColor(newColor);
            response.setStatus(200);
            studRepo.save(student);
            return "students/edited/edited";
        }
        else{
            response.setStatus(404);
            return "students/notFound/notFound";
        }
    }
    // ADDING NEW STUDENT TO THE DB
    @PostMapping("students/newStudent")
    public String addStudent(@RequestParam Map<String, String> newStudent, HttpServletResponse response) { 
        System.out.println("ADD student");
        String newName = newStudent.get("name");
        String newPass = newStudent.get("password");
        Float newGPA = Float.parseFloat(newStudent.get("gpa"));
        int newYear = Integer.parseInt(newStudent.get("year"));
        int newRating = Integer.parseInt(newStudent.get("rating"));
        String newColor = newStudent.get("color");
        studRepo.save(new Student(newName, newPass, newGPA, newYear, newRating, newColor));
        response.setStatus(201);
        return"students/added/added";
    }

    @PostMapping("/students/addStudent")
    public String newStudent(@RequestParam Map<String, String> newStudent, HttpServletResponse response) { 
        System.out.println("ADD student");
        String newName = newStudent.get("name");
        String newPass = newStudent.get("password");
        Float newGPA = Float.parseFloat(newStudent.get("gpa"));
        int newYear = Integer.parseInt(newStudent.get("year"));
        int newRating = Integer.parseInt(newStudent.get("rating"));
        String newColor = newStudent.get("color");
        studRepo.save(new Student(newName, newPass, newGPA, newYear, newRating, newColor));
        response.setStatus(201);
        return"students/added/added";
    }
}