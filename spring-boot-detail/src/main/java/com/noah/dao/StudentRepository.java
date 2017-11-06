package com.noah.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.noah.domain.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

	List<Student> findByName(String name);

	List<Student> findByNameLike(String name);

	List<Student> findByNameAndEmail(String name, String email);

	List<Student> findByStudIdInAndNameNotLike(List<Integer> ids, String name); 

	// if here we don't use the @Query, then it will get error "Parameter value
	// element [1] did not match expected type [com.noah.domain.Address (n/a)]"
	@Query(value = "select * from students where addr_id in :addrIds", nativeQuery = true)
	List<Student> findByAddressIn(@Param("addrIds") List<Integer> addressIds);

	// @Query can be also used in situation "group by",
	// there is no Entity class for the result, so we use generic List<Object[]>
	@Query(value = "select count(stud_id), addr_id from students group by addr_id", nativeQuery = true)
	List<Object[]> findCountOfStudentsByAddress();

}
