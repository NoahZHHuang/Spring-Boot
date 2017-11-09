package com.noah.config;

public class DynamicDataSourceHolder {
	
	// put it into ThreadLocal, make it thread safe in multiple threading env
	private static final ThreadLocal<String> THREAD_DATA_SOURCE = new ThreadLocal<>();
	
	public static void setDataSource(String dataSource){
		THREAD_DATA_SOURCE.set(dataSource);
	}
	
	public static String getDataSource(){
		return THREAD_DATA_SOURCE.get();
	}
	
	//cleaning up the ThreadLocal is very important, especially the thread is used in a Thread Pool 
	public static void clearDataSource(){
		THREAD_DATA_SOURCE.remove();
	}
	
}
