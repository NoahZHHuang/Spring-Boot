package com.noah.aspectj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class LogAspectJTest {

	@Autowired
	private DemoMethodService demoMethodService;
	
	@Autowired
	private DemoAnnotationService demoAnnotationService;
	
	@Autowired
	private DemoAroundService demoAroundService;
	
	@Test
	public void testAspectJForAnnotationService(){
		demoAnnotationService.serve();
	}
	
	@Test
	public void testAspectJForMethodService(){
		demoMethodService.serve();
	}
	
	@Test
	public void testAspectJForAroundService(){
		demoAroundService.serve();
	}
	
}
