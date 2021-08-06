package com.spring.security.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.entity.Student;

@RestController
@RequestMapping("/Api")
public class StudentController {

	List<Student> STUDENTS = Arrays.asList(new Student("xyz",1), new Student("abc",2));
	
	@GetMapping("/getStudents")
	public List<Student> getStudents()
	{
		return STUDENTS;
	}
	
	@GetMapping("/getStudent/{id}")
	public Optional<Student> getStudent(@PathVariable int id)
	{
		return STUDENTS.stream().filter(student -> id==student.getId()).findFirst();
				
	}
}
