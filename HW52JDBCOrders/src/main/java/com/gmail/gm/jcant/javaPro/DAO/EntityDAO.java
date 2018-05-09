package com.gmail.gm.jcant.javaPro.DAO;

import java.util.List;

public interface EntityDAO<T>{
	T get(int id);
	List<T> getAll();
	void add(T entity);
	void update(T entity);
	void delete(int id);
	void createTable();
	List<T> getBy(String condition);
}
