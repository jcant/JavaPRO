package com.gmail.gm.jcant.javaPro;

import com.google.gson.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class JSONTargetWorker implements JsonSerializer<TargetClass>, JsonDeserializer<TargetClass> {

    @Override
    public TargetClass deserialize(JsonElement jsonElement, Type type,
                                   JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        TargetClass tg = new TargetClass();

        Class<?> cls = TargetClass.class;
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Save.class)) {
                field.setAccessible(true);
                if (field.getName().equals("fieldInt") && jsonObject.get("fieldInt") != null) {
                    tg.setFieldInt(jsonObject.get("fieldInt").getAsInt());
                }
                if (field.getName().equals("fieldDouble") && jsonObject.get("fieldDouble") != null) {
                    tg.setFieldDouble(jsonObject.get("fieldDouble").getAsDouble());
                }
                if (field.getName().equals("fieldString") && jsonObject.get("fieldString") != null) {
                    tg.setFieldString(jsonObject.get("fieldString").getAsString());
                }
                if (field.getName().equals("fieldIntArray") && jsonObject.get("fieldIntArray") != null) {
                    Gson gson = new Gson();
                    tg.setFieldIntArray(gson.fromJson(jsonObject.get("fieldIntArray").getAsString(), int[].class));
                }
            }
        }
        return tg;
    }

    @Override
    public JsonElement serialize(TargetClass targetClass, Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        JsonObject jObject = new JsonObject();

        Class<?> cls = targetClass.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Save.class)) {
                field.setAccessible(true);
                try {
                    if (field.getType() == int.class) {
                        jObject.addProperty(field.getName(), field.getInt(targetClass));
                    }
                    if (field.getType() == double.class) {
                        jObject.addProperty(field.getName(), field.getDouble(targetClass));
                    }
                    if (field.getType() == String.class) {
                        jObject.addProperty(field.getName(), (String) field.get(targetClass));
                    }
                    if (field.get(targetClass) instanceof int[]) {
                        Gson gson = new Gson();
                        jObject.addProperty(field.getName(), gson.toJson(field.get(targetClass)));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return jObject;
    }
}
