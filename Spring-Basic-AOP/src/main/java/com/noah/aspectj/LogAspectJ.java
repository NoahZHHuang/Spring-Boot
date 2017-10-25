package com.noah.aspectj;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect // means this class is an aspect (ÇÐÃæ), we can use an aspect to weave some logic into other class methods 
@Component //let spring can auto scan it
public class LogAspectJ {

	//define a cut point(ÇÐµã)
	@Pointcut("@annotation(com.noah.aspectj.Action)")  //"@annoation means we are going to catch a method with a specific annotaion "
	public void actionAnnoationPointcut(){}
	
	//The real aspect logic is in @After, @Before & @Around
	@After("actionAnnoationPointcut()")
	public void after(JoinPoint joinPoint){
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		Method method = signature.getMethod();
		Action action = method.getAnnotation(Action.class);
		System.out.println("After - the name in action is "+action.name());
	}
	
	//we can also use the execution expression directly in @After and @Before to define the aspect rule
	//don't need to encapsulate the rule in @Pointcut 
	@Before("execution(* com.noah.aspectj.DemoMethodService.*(..))")
	public void before(JoinPoint joinPoint){
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		Method method = signature.getMethod();
		System.out.println("Before - method name is "+method.getName());
	}
	
	
	@Around("execution(* com.noah.aspectj.DemoAroundService.*(..))")
	public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		System.out.println("Around before proceed");
		proceedingJoinPoint.proceed();
		System.out.println("Around after proceed");
	}
	
	
	//Please cautious , the expression need to be correct precisely, or any syntax error will make "Pointcut is not well-formed" error
	//detail can google "AspectJ Pointcut Execution"
	
	
}
