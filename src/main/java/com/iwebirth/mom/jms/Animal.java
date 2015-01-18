package com.iwebirth.mom.jms;

import java.io.Serializable;

public class Animal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Animal(int height,int weight) {
		// TODO Auto-generated constructor stub
		this.height = height;
		this.weight = weight;
	}
	private int height;
	private int weight;
	
	
	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "呵呵";
	}

}
