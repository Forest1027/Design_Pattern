package com.forest.design_pattern.section02.creational.singleton.enumbasic;


import java.io.*;


enum EnumBasedSingleton implements Serializable {
    INSTANCE;

    EnumBasedSingleton() {
        value = 42;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

public class Demo {
    static void saveToFile(EnumBasedSingleton singleton, String filename) throws Exception {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOutputStream)
        ) {
            out.writeObject(singleton);
        }
    }

    static EnumBasedSingleton readFromFile(String filename) throws Exception {
        try (FileInputStream fileInputStream = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileInputStream)
        ) {
            return (EnumBasedSingleton) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        String filename = "myfile.txt";
//        EnumBasedSingleton singleton = EnumBasedSingleton.INSTANCE;
//        singleton.setValue(111);
//        saveToFile(singleton, filename);
        EnumBasedSingleton singleton1 = readFromFile(filename);
        System.out.println(singleton1.getValue());
    }
}
