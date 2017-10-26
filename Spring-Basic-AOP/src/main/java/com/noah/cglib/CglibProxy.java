package com.noah.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {
	
	private CglibProxy() {}
	
	private static CglibProxy instance = new CglibProxy();
	
	public static CglibProxy getInstance(){
		return instance;
	}

	//define a helper to gen the proxy of a class
	public Object getProxy(Class cls){
		return Enhancer.create(cls, this);
	}
	
	@Override
	public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		before();
		// please make sure, 
		// it is "proxy.invokeSuper(target,args);"
		// NOT "method.invoke(target,args);" or "proxy.invoke(target,args);"
		// because the proxy is a child class, need to invoke the method in super class
		// "method.invoke(target,args); and "proxy.invoke(target,args);" will trigger the method in proxy class
		// proxy will be wrapped in another proxy, so it will make dead loop
		Object result = proxy.invokeSuper(target,args);
		after();
		return result;
	}
	
	private void before(){
		System.out.println("Welcome..");
	}
	
	private void after(){
		System.out.println("GoodBye..");
	}

}
