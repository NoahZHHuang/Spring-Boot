package com.noah.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.noah.config.DynamicDataSourceHolder;
import com.noah.domain.Address;
import com.noah.domain.CountOfStudentByAddress;
import com.noah.domain.Student;
import com.noah.domain.StudentVO;
import com.noah.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	
	@RequestMapping(value = "/all", method=RequestMethod.GET)
	private List<Student> getAllStudents(){
		return studentService.getallStudents();
	}
	
	@RequestMapping(value = "/all/allDB", method=RequestMethod.GET)
	private List<Student> getAllStudentsInAllDB(){
		
		List<Student> studentsInSlave = studentService.getAllStudentsInSlaveDB();
		List<Student> studentsInMaster = studentService.getAllStudentsInMasterDB();
		studentsInMaster.addAll(studentsInSlave);
		return studentsInMaster;
	}
	
	@RequestMapping(value = "/id/{id}", method=RequestMethod.GET)
	private Student getStudentById(@PathVariable("id") Integer id){
		return studentService.getStudentById(id);
	}
	
	@RequestMapping(value = "/{db}/id/{id}", method=RequestMethod.GET)
	private Student getStudentByIdInSpecificDB(@PathVariable("db") String db, @PathVariable("id") Integer id){
		if(db.equalsIgnoreCase("slave")){
			DynamicDataSourceHolder.setDataSource("slave");
		}else{
			DynamicDataSourceHolder.setDataSource("master");
		}
		return studentService.getStudentById(id);
	}
	
	@RequestMapping(value = "/ids/{id}", method=RequestMethod.GET)
	private List<Student> getStudentByIds(@PathVariable("id") List<Integer> ids){
		return studentService.getStudentByIds(ids);
	}
	
	@RequestMapping(value = "/name/{name}", method=RequestMethod.GET)
	private List<Student> getStudentByName(@PathVariable("name") String name){
		return studentService.getStudentsByName(name);
	}
	
	@RequestMapping(value = "/name/fuzzy/{name}", method=RequestMethod.GET)
	private List<Student> getStudentByNameFuzzy(@PathVariable("name") String name){
		return studentService.getStudentsByNameLike(name);
	}
	
	//URL pattern example
	//http://localhost:8888/root/students/name/Allie/email?email=Allie@****.com
	//We use query parameter instead of the path variable. It looks strange, usually, we should map it as /name/{name}/email/{email}
	//But Spring MVC will take the part after last "." as the domain, so the email will be probably cut accidentally
	//like if the url is http:~/name/Allie/email/Allie@****.com, then the email param will be only "Allie@****"
	@RequestMapping(value = "/name/{name}/email", method=RequestMethod.GET)
	private List<Student> getStudentByNameAndEmail(@PathVariable("name") String name, @RequestParam("email") String email){
		return studentService.getStudentsByNameAndEmail(name, email);
	}
	
	@RequestMapping(value = "/ids/{ids}/name/not/{name}", method=RequestMethod.GET)
	private List<Student> getStudentByIdsAndNameNotLike(@PathVariable("ids") List<Integer> ids, @PathVariable("name") String name){
		return studentService.getStudentByIdsAndNameNotLike(ids, name);
	}
	
	@RequestMapping(value = "/addresses/{addressIds}", method=RequestMethod.GET)
	private List<Student> getStudentByAddressIds(@PathVariable("addressIds") List<Integer> addressIds){
		return studentService.getStudentsByAddressIds(addressIds);
	} 
	
	@RequestMapping(value = "/count/byAddress", method=RequestMethod.GET)
	private List<CountOfStudentByAddress> getCountOfStudentByAddress(){
		return studentService.getCountOfStudentByAddress();		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="", method=RequestMethod.POST)
	private JSONObject saveStudent(@RequestBody StudentVO studentVo){
		
		Address addressToSave = new Address();
		addressToSave.setCity(studentVo.getCity());
		addressToSave.setStreet(studentVo.getStreet());
		addressToSave.setZipCode(studentVo.getZipCode());
		
		Student studentToSave = new Student();
		studentToSave.setName(studentVo.getName());
		studentToSave.setEmail(studentVo.getEmail());
		studentToSave.setAddress(addressToSave);
		
		Student studentSaved = studentService.saveStudentWithAddress(studentToSave, addressToSave);
		
		JSONObject result = new JSONObject();
		result.put("message", "save good!");
		result.put("The Student Saved", studentSaved);
		return result;
	}
	
	
	
	
	
	
}
