package com.noah;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.noah.services.asyn.AsynService;
import com.noah.services.normal.NormalService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class AppTest {
	
	@Autowired
	private AsynService asynService;
	
	@Autowired
	private NormalService normalService;
	
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	@Autowired
	private SimpleDateFormat formatter;
		
	@Test
	public void testAsynTaskInNewWay() throws InterruptedException{
		System.out.println("New way...");
		for(int i =0; i<10; i++){
			asynService.executeAsynService(i);
		}
		Thread.currentThread().join();
	}
	
	@Test
	public void testAsynTaskInOldWay() throws InterruptedException{
		System.out.println("Old way...");
		AtomicInteger atomic = new AtomicInteger(0);
		for(int i =0; i<20; i++){
			threadPoolTaskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					normalService.executeNormalService(atomic.getAndIncrement());
					
				}
			});
		}
		Thread.currentThread().join();
	}
	
	//compare two ways to run the async task in a multiple thread pool, apparently the new way is more simple
	
	
	@Test
	public void testScheduleService() throws InterruptedException{
		System.out.print("Start time :" + formatter.format(new Date()));
		Thread.currentThread().join();
	}
	

	
}
