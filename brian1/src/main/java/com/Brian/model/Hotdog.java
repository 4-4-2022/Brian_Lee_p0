package com.Brian.model;

public class Hotdog {
	private int hotdogId;
	private String hotdogStyle;
	private int calories;
	private float cost;
	private String description;
	private static int hotdogCount;
	
	public Hotdog() {
		super();
		hotdogCount++;
	}
	
	public Hotdog(int hotdogId, String hotdogStyle, int calories, float cost, String description) {
		this.hotdogId = hotdogId;
		this.hotdogStyle = hotdogStyle;
		this.calories = calories;
		this.cost = cost;
		this.description = description;
		
		hotdogCount++;
	}

	public int getHotdogId() {
		return hotdogId;
	}

	public void setHotdogId(int hotdogId) {
		this.hotdogId = hotdogId;
	}

	public String getHotdogStyle() {
		return hotdogStyle;
	}

	public void setHotdogStyle(String hotdogStyle) {
		this.hotdogStyle = hotdogStyle;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static int getHotdogCount() {
		return hotdogCount;
	}

	public static void setHotdogCount(int hotdogCount) {
		Hotdog.hotdogCount = hotdogCount;
	}



	@Override
	public String toString() {
		return "Hotdog [Id= " + hotdogId + ", " + hotdogStyle + ", " + calories+" calories" + ", price= "
				+ cost + ", " + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + calories;
		result = prime * result + Float.floatToIntBits(cost);
		result = prime * result + hotdogId;
		result = prime * result + ((hotdogStyle == null) ? 0 : hotdogStyle.hashCode());
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
		Hotdog other = (Hotdog) obj;
		if (calories != other.calories)
			return false;
		if (Float.floatToIntBits(cost) != Float.floatToIntBits(other.cost))
			return false;
		if (hotdogId != other.hotdogId)
			return false;
		if (hotdogStyle == null) {
			if (other.hotdogStyle != null)
				return false;
		} else if (!hotdogStyle.equals(other.hotdogStyle))
			return false;
		return true;
	}
	
	
	
}


