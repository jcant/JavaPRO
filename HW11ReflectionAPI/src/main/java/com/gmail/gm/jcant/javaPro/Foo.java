package com.gmail.gm.jcant.javaPro;

public class Foo {

    @Test(a = 3, b = 5)
    public static int sum(int x, int y) {
        return x + y;
    }

    @Test(a = 3, b = 5)
    public static int multiply(int x, int y) {
        return x * y;
    }

    public static int diff(int x, int y) {
        return x - y;
    }
}
