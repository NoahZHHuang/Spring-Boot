package com.noah;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class AppTest {
	
	@Autowired
	private DemoPublisher demoPublisher;
	
	@Autowired
	private DemoListener demoListener;
	
	@Test
	public void testPublishAndListen(){
		demoPublisher.publish("Heyo");
	}
	
	//the ApplicationEvent between listener and publisher is in sync
	//if want it async, please use the MQ.

}
