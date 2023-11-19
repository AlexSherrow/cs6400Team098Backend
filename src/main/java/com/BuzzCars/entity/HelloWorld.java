package com.BuzzCars.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HelloWorld {
	private String message;

	public HelloWorld() {
		// TODO Auto-generated constructor stub
	}

	public HelloWorld(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
