package com.uttesh.exude;

import com.uttesh.exude.exception.InvalidDataException;

public class SimpleData {

	String sampleSwearData = "testing the swearing words tit";
	static String sampleData = "testing testing testing the keep keep the the duplicate data data in result";
	
	static String sampleData1 = "asfaf the asfasf asfasfasfasfasfaf";
	
	static String inputData = "https://en.wikipedia.org/wiki/Rama";
	
	static String inputData1 = "https://en.wikipedia.org/wiki/Ayodhya";

	public static void main(String[] args) throws InvalidDataException {
		ExudeData exudeData = ExudeData.getInstance();
		String output = exudeData.filterStoppings(sampleData);
		System.out.println("output 1:: "+output);
		output = exudeData.filterStoppings(sampleData1);
		System.out.println("output 2:: "+output);
		output = exudeData.filterStoppings(inputData);
		System.out.println("output 3:: "+output);
		output = exudeData.filterStoppings(inputData1);
		System.out.println("output 4:: "+output);
	}

}
