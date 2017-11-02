package com.noah.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noah.dao.StudentRepository;
import com.noah.domain.Student;


@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getallStudents(){
		return (List<Student>) studentRepository.findAll();
	}
	
}
