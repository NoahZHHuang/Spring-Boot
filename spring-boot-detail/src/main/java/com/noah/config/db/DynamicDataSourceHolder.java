package com.noah.config.db;

public class DynamicDataSourceHolder {
	
	// put it into ThreadLocal, make it thread safe in multiple threading env
	private static final ThreadLocal<Object> THREAD_DATA_SOURCE = new ThreadLocal<>();
	
	public static void setDataSource(Object dataSource){
		THREAD_DATA_SOURCE.set(dataSource);
	}
	
	public static Object getDataSource(){
		return THREAD_DATA_SOURCE.get();
	}
	
	//cleaning up the ThreadLocal is very important, especially the thread is used in a Thread Pool 
	public static void clearDataSource(){
		THREAD_DATA_SOURCE.remove();
	}
	
}
