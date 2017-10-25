package com.noah;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.noah.beans.Bean_A;
import com.noah.beans.Bean_B;
import com.noah.beans.Bean_C;
import com.noah.beans.Bean_D;
import com.noah.services.Service_A;
import com.noah.services.Service_B;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class RunTest {
	
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
	private Service_B ServiceB;
	
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
		ServiceB.serve();
		System.out.println("");
	}
	
	
}
