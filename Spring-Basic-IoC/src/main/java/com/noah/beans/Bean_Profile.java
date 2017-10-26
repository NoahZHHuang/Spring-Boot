package com.noah.beans;

public class Bean_Profile {
	
	private String name;
	
	public Bean_Profile(String name) {
		this.name = name;
	}
	
	public void serve(){
		System.out.println("This is " + name + " profile serving");
	};

}
