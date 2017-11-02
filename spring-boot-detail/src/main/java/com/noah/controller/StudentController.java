package com.noah.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noah.domain.Student;
import com.noah.service.StudentService;


@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/all")
	private List<Student> getAllStudents(){

		return studentService.getallStudents();
	}
	
}
