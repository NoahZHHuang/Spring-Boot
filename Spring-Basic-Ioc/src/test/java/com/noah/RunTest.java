package com.noah;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.noah.beans.Bean_A;
import com.noah.beans.Bean_B;
import com.noah.beans.Bean_C;
import com.noah.beans.Bean_D;
import com.noah.beans.Bean_Profile;
import com.noah.services.Service_A;
import com.noah.services.Service_B;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
//in Junit, use the @ActiveProfiles to specify which env you are testing against, very convenient.
@ActiveProfiles("Dev")
public class RunTest {
	
	// if not using @ActiveProfiles, 
	//we can also specify the env by system property "spring.profiles.active"
    /*	
    @BeforeClass
	public static void setUpClass(){
		System.setProperty("spring.profiles.active","Prd");
	}
	*/

	@Autowired
	private Bean_A beanA;
	
	@Autowired
	private Bean_B beanB;
	
	@Autowired
	private Bean_C beanC;
	
	@Autowired
	@Qualifier("second_bean_d")
	private Bean_D beanD;
	
	@Autowired
	private Service_A serviceA;
	
	@Autowired
	@Qualifier("first_service_b")
	private Service_B serviceB;
	
	@Autowired
	private Bean_Profile beanProfile;
	
	@Test
	public void testIoC(){
		beanA.serve();
		System.out.println("");
		beanB.serve();
		System.out.println("");
		beanC.serve();
		System.out.println("");
		beanD.serve();
		System.out.println("");
		serviceA.serve();
		System.out.println("");
		serviceB.serve();
		System.out.println("");
		beanProfile.serve();
		System.out.println("");
	}
	
	
}
