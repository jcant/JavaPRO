package com.gmail.gm.jcant.javaPro;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Annotations and Reflections
 *
 */
public class App {
	public static void main(String[] args) {
		Class<?> cls = Foo.class;

		Method[] methods = cls.getDeclaredMethods();

		for (Method method : methods) {
			if (method.isAnnotationPresent(Test.class)) {
				Test annotation = method.getAnnotation(Test.class);
				int result = 0;
				try {
					result = (Integer) method.invoke(null, annotation.a(), annotation.b());

					System.out.println("Method: " + method.getName() + "(" + annotation.a() + "," + annotation.b()
							+ ") = " + result);

				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
