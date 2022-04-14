package com.Brian.model;

import java.util.ArrayList;
import java.util.Arrays;

public class HotdogTemplate {
	protected String[] buns = {"plain","sesame","garlic","all-seasoned", "poppy seed", "brioche"};
	protected String[] meat = {"all beef", "standard", "foie grais", "rattlesnake", "elk", "venison", "chicken", "pork", "polish", 
			"bratwurst"}; 
	protected String[] condiments = {"ketchup", "yellow mustard", "celery salt",  "mayonnaise", "hollandaise", "dijon mustard",
			"asian zing", "sriracha"};
	protected String[] toppings = {"grilled onions", "raw onions", "steamed onions", "sports peppers", "coleslaw", 
			"sauerkraut", "grilled peppers",
			"relish", "chili", "jalepenos", "diced tomatoes"};
	protected String[] cheese = {"american", "swiss", "nacho"};
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(buns);
		result = prime * result + Arrays.hashCode(cheese);
		result = prime * result + Arrays.hashCode(condiments);
		result = prime * result + Arrays.hashCode(meat);
		result = prime * result + Arrays.hashCode(toppings);
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HotdogTemplate other = (HotdogTemplate) obj;
		return Arrays.equals(buns, other.buns) && Arrays.equals(cheese, other.cheese)
				&& Arrays.equals(condiments, other.condiments) && Arrays.equals(meat, other.meat)
				&& Arrays.equals(toppings, other.toppings);
	}
	
	

	@Override
	public String toString() {
		return "HotdogTemplate [buns=" + Arrays.toString(buns) + ", meat=" + Arrays.toString(meat) + ", condiments="
				+ Arrays.toString(condiments) + ", toppings=" + Arrays.toString(toppings) + ", cheese="
				+ Arrays.toString(cheese) + "]";
	}
}
