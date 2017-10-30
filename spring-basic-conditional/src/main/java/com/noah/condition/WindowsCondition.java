package com.noah.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WindowsCondition implements Condition{

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String os = context.getEnvironment().getProperty("os.name");
		System.out.println("WindowsCondition :");
		System.out.println("OS :" + os);
		boolean flag = os.contains("Windows");
		System.out.println("Condition :" + flag);
		return flag;
	}

	
	
}
