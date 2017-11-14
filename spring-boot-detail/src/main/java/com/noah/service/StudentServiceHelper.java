package com.noah.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.noah.dao.StudentRepository;
import com.noah.domain.Student;

@Service
public class StudentServiceHelper {

	@Autowired
	private StudentRepository studentRepository;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<Student> getAllStudents() {
		return (List<Student>) studentRepository.findAll();
	}
	
	
}
