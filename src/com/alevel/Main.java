package com.alevel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    static Field privateStringField;//класс с приватным final полем name

    public static void main(String[] args) {


        TestClass testObject = new TestClass("not changing");
        try {
            runAllMethods(testObject);
            String name = getValue("name", testObject);
            System.out.println("name = " + name);

            name = setValue(testObject);
            System.out.println("name = " + name);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String setValue(TestClass testObject) throws IllegalAccessException {
        String name;
        privateStringField.set(testObject, "Новое значение");
        name = (String) privateStringField.get(testObject);
        return name;
    }

    private static void runAllMethods(TestClass testObject) throws IllegalAccessException, InvocationTargetException {
        Method[] methods = testObject.getClass().getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            Annotation annotation = method.getAnnotation(FriendlyName.class);
            if (annotation != null) {
                String name = ((FriendlyName) annotation).name();
                System.out.println(name + " will be run now");
            }
            System.out.println(method.invoke(testObject));
        }

    }

    private static String getValue(String name, Object object) throws NoSuchFieldException, IllegalAccessException {
        privateStringField = object.getClass().getDeclaredField("name");
        privateStringField.setAccessible(true);
        return (String) privateStringField.get(object);
    }

}
