package com.Brian.respository;

import java.util.ArrayList;
import java.util.Set;

import com.Brian.model.Hotdog;

public interface HotdogRepository {
	public ArrayList<Hotdog> findAllHotdogs();
	public Hotdog findHotdogByStyle(String flavor);
	public Set<Hotdog> findHotdogsByStyle(String...flavors);
	public void save(Hotdog hotdog);
	public void update(Hotdog hotdog);
	public void delete(Hotdog cupcake);
}
