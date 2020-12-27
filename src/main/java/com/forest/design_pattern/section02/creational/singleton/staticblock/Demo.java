package com.forest.design_pattern.section02.creational.singleton.staticblock;

import java.io.File;
import java.io.IOException;

public class Demo {

}

class StaticBlockSingleton {
    private StaticBlockSingleton() throws IOException {
        System.out.println("Singleton is initializing");
        File.createTempFile(".", ".");
    }

    private static StaticBlockSingleton instance;

    // use static block to handle exception
    static {
        try {
            instance = new StaticBlockSingleton();
        }catch (Exception e) {
            System.err.println("failed to create singleton");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}
