package section02.creational.singleton;

import java.io.*;

public class Demo {

    static void saveToFile(BasicSingleton singleton, String filename) throws Exception {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOutputStream)
        ) {
            out.writeObject(singleton);
        }
    }

    static BasicSingleton readFromFile(String filename) throws Exception {
        try (FileInputStream fileInputStream = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileInputStream)
        ) {
            return (BasicSingleton) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        BasicSingleton instance = BasicSingleton.getInstance();
        instance.setValue(123);
        String filename = "singleton.txt";
        saveToFile(instance, filename);
        instance.setValue(222);
        BasicSingleton basicSingleton = readFromFile(filename);
        System.out.println(instance == basicSingleton);
        System.out.println(instance.getValue());
        System.out.println(basicSingleton.getValue());
    }
}

class BasicSingleton implements Serializable {
    private BasicSingleton() {

    }

    private static final BasicSingleton INSTANCE = new BasicSingleton();

    public static BasicSingleton getInstance() {
        return INSTANCE;
    }

    private int value = 0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    protected Object readResolve() {
        return INSTANCE;
    }
}
