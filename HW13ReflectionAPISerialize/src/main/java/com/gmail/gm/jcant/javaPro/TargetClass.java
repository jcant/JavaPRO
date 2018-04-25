package com.gmail.gm.jcant.javaPro;

import java.util.Arrays;

public class TargetClass {
    @Save
    private int fieldInt;

    private double fieldDouble;

    private String fieldString;
    @Save
    private int[] fieldIntArray;

    public TargetClass() {
        super();
    }

    public TargetClass(int fieldInt, double fieldDouble, String fieldString, int[] fieldIntArray) {
        super();
        this.fieldInt = fieldInt;
        this.fieldDouble = fieldDouble;
        this.fieldString = fieldString;
        this.fieldIntArray = fieldIntArray;
    }

    public int getFieldInt() {
        return fieldInt;
    }

    public void setFieldInt(int fieldInt) {
        this.fieldInt = fieldInt;
    }

    public double getFieldDouble() {
        return fieldDouble;
    }

    public void setFieldDouble(double fieldDouble) {
        this.fieldDouble = fieldDouble;
    }

    public String getFieldString() {
        return fieldString;
    }

    public void setFieldString(String fieldString) {
        this.fieldString = fieldString;
    }

    public int[] getFieldIntArray() {
        return fieldIntArray;
    }

    public void setFieldIntArray(int[] fieldIntArray) {
        this.fieldIntArray = fieldIntArray;
    }

    @Override
    public String toString() {
        return "TargetClass{" +
                "fieldInt=" + fieldInt +
                ", fieldDouble=" + fieldDouble +
                ", fieldString='" + fieldString + '\'' +
                ", fieldIntArray=" + Arrays.toString(fieldIntArray) +
                '}';
    }
}
