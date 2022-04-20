package com.brian1.hotdogtest;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Brian.model.Hotdog;

@TestInstance(Lifecycle.PER_CLASS)
public class HotdogTest {
	
	private Hotdog hotdog;
	private Hotdog hotdog2;
	
	@Mock
	private ArrayList<Hotdog> hotdogs;
	
	@BeforeAll
	public void setup() {
		MockitoAnnotations.openMocks(this);
		this.hotdog = new Hotdog(1, "CHICAGO", 400, 4.75f, "Very delicious");
		this.hotdog2 = new Hotdog(2, "NEW YORK", 400, 3.25f, "Delicious");
		hotdogs = new ArrayList();
		hotdogs.add(new Hotdog(1, "CHICAGO", 400, 4.75f, "Very delicious"));
		hotdogs.add(new Hotdog(2, "NEW YORK", 400, 3.25f, "Delicious"));
		hotdogs.add(new Hotdog(3, "NEW ENGLAND", 400, 4.00f, "Not delicious"));
	}
	
	@Test
	public void testChangePrice() {
		this.hotdog.changePrice(hotdog, 2.00f);
		Assertions.assertEquals(2.00f, hotdog.getCost());
	}
	
	@Test
	public void testChangeCalories() {
		this.hotdog.changeCalories(hotdog, 10);
		Assertions.assertEquals(10, hotdog.getCalories());
	}
	
	@Test
	public void testChangeDescription() {
		this.hotdog.changeDescription(hotdog, "best hotdog ever!");
		Assertions.assertEquals("best hotdog ever!", hotdog.getDescription());
	}
	
	@Test
	public void testChangeStyle() {
		this.hotdog.changeStyle(hotdog, "LA DOG");
		Assertions.assertEquals("LA DOG", hotdog.getHotdogStyle());
	}
	
	@Test
	public void testToString() {
		String testString = this.hotdog2.toString();
		Assertions.assertEquals("Hotdog [Id= " + "2" + ", " + "NEW YORK" + ", " + "400"+" calories" + ", price= "
				+ 3.25f + ", " + "Delicious" + "]", testString);
	}

	
	
}
