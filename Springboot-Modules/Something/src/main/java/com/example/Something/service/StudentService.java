package com.example.Something.service;

import com.example.Something.entity.Student;
import com.example.Something.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentService {

    private static final String FEMALE = "FEMALE";

    @Autowired
    private StudentRepository repository;


    public List<Student> getAllStudent(){
        List<Student> studentList = new ArrayList<>();
       Iterable<Student> studentIterable = repository.findAll();

       for(Student element : studentIterable){
           studentList.add(element);
       }
       return studentList;
    }

    public Iterable<Student> saveAllStudents(List<Student> studentList){
        return repository.saveAll(studentList);
    }


    public List<Student> getAllFemales() {
        Iterable<Student> studentIterable = repository.findAll();
        return StreamSupport.stream(studentIterable.spliterator(), false)
                .filter(student -> FEMALE.equalsIgnoreCase(student.getGender()))
                .toList();
    }
}
