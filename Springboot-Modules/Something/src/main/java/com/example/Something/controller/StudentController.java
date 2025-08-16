package com.example.Something.controller;

import com.example.Something.entity.Student;
import com.example.Something.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/all")
    public ResponseEntity<List<Student>> findAllStudents(){
        return ResponseEntity.ok(service.getAllStudent());
    }

    @PostMapping("/saveAll")
    public ResponseEntity<String> persistAllStudents(@RequestBody List<Student> studentList){
        Iterable<Student> studentIterable = service.saveAllStudents(studentList);
        String json = StreamSupport.stream(studentIterable.spliterator(), false)
                .map(Student::toString)
                .collect(Collectors.joining(", " , "[", "]"));

        return ResponseEntity.ok(json);
    }

    @GetMapping("/getAllFemale")
    public ResponseEntity<List<Student>> getAllFemaleStudents(){
        return ResponseEntity.ok(service.getAllFemales());
    }

}
