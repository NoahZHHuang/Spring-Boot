package com.noah.dao;

import org.springframework.data.repository.CrudRepository;

import com.noah.domain.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{

}
