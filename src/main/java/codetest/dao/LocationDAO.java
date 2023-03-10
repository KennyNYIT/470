package codetest.dao;

import codetest.PO.Location;

public interface LocationDAO {
	public Location getById(int id) ;
	public Location getByName(String name);
}