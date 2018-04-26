package com.gmail.gm.jcant.javaPro;


public class App {
    public static void main(String[] args){
        TargetClass a = new TargetClass();
        a.setFieldInt(111);
        a.setFieldDouble(111.111);
        a.setFieldString("111");
        a.setFieldIntArray(new int[]{11,22,33,44,55});

        System.out.println(a);

        SaveLoadClass saver = new SaveLoadClass(a);
        saver.saveClass("./target_class.json");

 
        saver.setTarget(null);
        saver.loadClass("./target_class.json");

        System.out.println(saver.getTarget());

    }
}
