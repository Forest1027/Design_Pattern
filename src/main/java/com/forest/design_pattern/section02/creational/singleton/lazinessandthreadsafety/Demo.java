package com.forest.design_pattern.section02.creational.singleton.lazinessandthreadsafety;

public class Demo {
    public static void main(String[] args) {
        EagerSingleton.helper();
        InnerStaticSingleton.helper();
        InnerStaticSingleton.getInstance();
        System.out.println("done");
    }
}

class EagerSingleton {

    private static EagerSingleton instance = new EagerSingleton();

    public EagerSingleton() {
        System.out.println("I'm eager singleton, initializing.");
    }

    public EagerSingleton getInstance() {
        return instance;
    }

    public static void helper() {
        System.out.println("helping loading EagerSingleton class");
    }
}

class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("initializing a lazy singleton");
    }

//    public static LazySingleton getInstance() {
//        if (instance == null) {
//            instance = new LazySingleton();
//        }
//        return instance;
//    }

    //double-checked locking, outdated
    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}

class InnerStaticSingleton {

    private InnerStaticSingleton() {
        System.out.println("InnerStaticSingleton is initializing");
    }

    // Java guarantees that a class is only loaded when it's used the first time.
    // So the inner class won't be loaded twice and consequently the singleton will never get constructed twice
    private static class Impl {
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }

    public static InnerStaticSingleton getInstance() {
        return Impl.INSTANCE;
    }

    public static void helper() {
        System.out.println("helping loading InnerStaticSingleton class.");
    }
}
