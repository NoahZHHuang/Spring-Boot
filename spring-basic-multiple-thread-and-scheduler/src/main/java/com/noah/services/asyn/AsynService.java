package com.noah.services.asyn;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
//here should define the scope as prototype
//because in task executor, every time it launch a task
//it should be an independent task
//prototype can make sure no impact between each other
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AsynService {

	//if @Async is on class level, then all the methods are async
	@Async
	public void executeAsynService(Integer i) {
		System.out.println("Running Asyn Job " + i);
	}

}
