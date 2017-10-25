package com.noah.beans;

public class Bean_D {
	
	private String distinguisher;

	public Bean_D(String distinguisher) {
		this.distinguisher = distinguisher;
	}
	
	public void serve(){
		String msg = "Bean_D(" + distinguisher +") is serving you!";
		System.out.println(msg);
	}

}
