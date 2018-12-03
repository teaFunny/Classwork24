package com.alevel;

public class TestClass {
    private final String name;

    @FriendlyName(name = "Constructor with 1 parameter")
    public TestClass(String name) {
        this.name = name;
    }

    @FriendlyName(name = "Demo method")
    private String alwaysSayTrue(){
        return "true";
    }

    @Deprecated
    @FriendlyName
    public void sleep(){
        System.out.println("ZZZ");
    }
}
