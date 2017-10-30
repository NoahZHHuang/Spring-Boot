package com.noah.services.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

	@Autowired
	SimpleDateFormat formatter;

	@Scheduled(initialDelay = 2000, fixedDelay = 5000)
	public void runInFixedPeriod() {
		System.out.println("runInFixedPeriod, Current time is " + formatter.format(new Date()));
	}

	@Scheduled(cron = "8 * * ? * *")
	public void runWhenCronTrigger() {
		System.out.println("runWhenCronTrigger, Current time is " + formatter.format(new Date()));
	}

}
