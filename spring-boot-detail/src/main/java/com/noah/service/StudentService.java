package com.noah.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noah.dao.AddressRepository;
import com.noah.dao.StudentRepository;
import com.noah.domain.Address;
import com.noah.domain.CountOfStudentByAddress;
import com.noah.domain.Student;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	public List<Student> getallStudents() {
		return (List<Student>) studentRepository.findAll();
	}

	public Student getStudentById(Integer id) {
		return studentRepository.findOne(id);
	}

	public List<Student> getStudentByIds(List<Integer> ids) {
		return (List<Student>) studentRepository.findAll(ids);
	}

	public List<Student> getStudentsByName(String name) {
		return studentRepository.findByName(name);
	}

	public List<Student> getStudentsByNameLike(String name) {
		return studentRepository.findByNameLike("%" + name + "%");
	}

	public List<Student> getStudentsByAddressIds(List<Integer> addressIds) {
		return studentRepository.findByAddressIn(addressIds);
	}

	public List<Student> getStudentByIdsAndNameNotLike(List<Integer> ids, String name) {
		return studentRepository.findByStudIdInAndNameNotLike(ids, "%" + name + "%");
	}

	public List<Student> getStudentsByNameAndEmail(String name, String email) {
		return studentRepository.findByNameAndEmail(name, email);
	}

	public List<CountOfStudentByAddress> getCountOfStudentByAddress() {
		List<CountOfStudentByAddress> counts = new ArrayList<>();
		List<Object[]> objectsList = studentRepository.findCountOfStudentsByAddress();
		objectsList.forEach(objects -> {
			CountOfStudentByAddress count = new CountOfStudentByAddress();
			count.setStudCount(Integer.valueOf(String.valueOf(objects[0])));
			count.setAddrId(objects[0] != null ? Integer.valueOf(String.valueOf(objects[0])) : null);
			counts.add(count);
		});
		return counts;
	}
	
	public Student saveStudent(Student studentToSave){
		return studentRepository.save(studentToSave);
	}
	
	public Student saveStudentWithAddress(Student studentToSave, Address addressToSave){
		Address addressSaved = addressRepository.save(addressToSave);
		studentToSave.setAddress(addressSaved);
		Student studentSaved = studentRepository.save(studentToSave);
		return studentSaved;
	}

}
