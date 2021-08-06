package com.spring.security.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.entity.Student;

@RestController
@RequestMapping("/Management/Api")
public class StudentAdminController {
	
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
	
	@PostMapping("/postStudents")
	public void postStudent(@RequestBody Student student)
	{
		System.out.println("posting student");
	}
	
	@PutMapping("/putStudents")
	public void putStudent(@RequestBody Student student)
	{
		System.out.println("updating student");
	}
	
	@DeleteMapping("/putStudents")
	public void deleteStudent(@RequestBody Student student)
	{
		System.out.println("Deletings student student");
	}


}
