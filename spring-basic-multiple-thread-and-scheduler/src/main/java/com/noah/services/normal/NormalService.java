package com.noah.services.normal;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NormalService {

	public void executeNormalService(Integer i) {
		System.out.println("Running Normal Job " + i + ", it will still be run in async");
	}

}
