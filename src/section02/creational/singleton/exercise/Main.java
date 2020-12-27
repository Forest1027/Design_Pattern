package section02.creational.singleton.exercise;

import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

    }
}

class SingletonTester
{
    public static boolean isSingleton(Supplier<Object> func)
    {
        Object object1 = func.get();
        Object object2 = func.get();

        return object1==object2;
    }
}
