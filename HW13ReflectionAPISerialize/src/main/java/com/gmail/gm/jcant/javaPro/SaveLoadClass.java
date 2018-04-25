package com.gmail.gm.jcant.javaPro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Field;

public class SaveLoadClass {
    private TargetClass target;
    private String filename;

    public SaveLoadClass() {
        super();
        this.target = null;
        this.filename = null;
    }

    public SaveLoadClass(TargetClass target, String filename) {
        super();
        this.target = target;
        this.filename = filename;
    }

    public void saveClass() {
        if (target == null) {
            throw new IllegalArgumentException("Target is null");
        }
        if (filename == null) {
            throw new IllegalArgumentException("Filename not set");
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(TargetClass.class, new JSONTargetWorker());
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        try (PrintWriter pw = new PrintWriter(filename)) {
            gson.toJson(target, pw);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadClass() {
        if (filename == null) {
            throw new IllegalArgumentException("Filename not set");
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(TargetClass.class, new JSONTargetWorker());

        Gson gson = gsonBuilder.create();

        TargetClass tg = null;

        try (FileReader fr = new FileReader(new File(filename))) {
            tg = gson.fromJson(fr, TargetClass.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTargetFields(tg);
    }

    private void setTargetFields(TargetClass tg) {
        Class<?> cls = target.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Save.class)) {
                field.setAccessible(true);
                if (field.getName().equals("fieldInt") && tg.getFieldInt() != 0) {
                    target.setFieldInt(tg.getFieldInt());
                }
                if (field.getName().equals("fieldDouble") && tg.getFieldDouble() != 0) {
                    target.setFieldDouble(tg.getFieldDouble());
                }
                if (field.getName().equals("fieldString") && tg.getFieldString() != null) {
                    target.setFieldString(tg.getFieldString());
                }
                if (field.getName().equals("fieldIntArray") && tg.getFieldIntArray() != null) {
                    target.setFieldIntArray(tg.getFieldIntArray());
                }
            }
        }
    }

    public TargetClass getTarget() {
        return target;
    }

    public void setTarget(TargetClass target) {
        this.target = target;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "SaveLoadClass{" +
                "target=" + target +
                ", filename='" + filename + '\'' +
                '}';
    }
}
