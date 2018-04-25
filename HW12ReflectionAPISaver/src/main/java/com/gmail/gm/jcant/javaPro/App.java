package com.gmail.gm.jcant.javaPro;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		TextContainer txt = new TextContainer("Some text to write...");

		Class<?> cls = txt.getClass();
		String file = null;
		if (cls.isAnnotationPresent(SaveTo.class)) {
			file = cls.getAnnotation(SaveTo.class).file();

			Method[] methods = cls.getDeclaredMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(Saver.class)) {
					try {
						method.invoke(txt, file);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
