package com.noah.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class LinuxCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String os = context.getEnvironment().getProperty("os.name");
		System.out.println("LinuxCondition :");
		System.out.println("OS :" + os);
		boolean flag = os.contains("Linux");
		System.out.println("Condition :" + flag);
		return flag;
	}

}
