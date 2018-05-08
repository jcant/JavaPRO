package com.gmail.gm.jcant.javaPro;

public interface EntityDAO {
	Object get(int id);
	void add(Object entity);
	void update(Object entity);
	void delete(int id);
}
