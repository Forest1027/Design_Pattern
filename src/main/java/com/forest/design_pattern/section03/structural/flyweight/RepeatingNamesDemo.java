package com.forest.design_pattern.section03.structural.flyweight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class RepeatingNamesDemo {
    public static void main(String[] args) {
        User user1 = new User("John Smith");
        User user2 = new User("Jane Smith");
        System.out.println(user1);
        System.out.println(user2);
        User2 user21 = new User2("John Smith");
        User2 user22 = new User2("Jane Smith");
        System.out.println(user21);
        System.out.println(user22);
    }
}

class User {
    private String fullName;

    public User(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                '}';
    }
}

class User2 {
    static List<String> strings = new ArrayList<>();
    private int[] names;

    public User2(String fullName) {
        Function<String, Integer> getOrAdd = (String s) -> {
            int idx = strings.indexOf(s);
            if (idx != -1) return idx;
            else {
                strings.add(s);
                return strings.size() - 1;
            }
        };
        names = Arrays.stream(fullName.split(" ")).mapToInt(s -> getOrAdd.apply(s)).toArray();
    }

    @Override
    public String toString() {
        return "User2{"+strings.get(names[0])+" "+strings.get(names[1])+"}";
    }
}
