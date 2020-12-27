package com.forest.design_pattern.section02.creational.singleton.testabilityissue;

import com.google.common.collect.Iterables;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Demo {
    @Test
    public void dependentPopulationTest() {
        DummyDatabase dummyDatabase = new DummyDatabase();
        ConfigurableRecordFinder configurableRecordFinder = new ConfigurableRecordFinder(dummyDatabase);
        Assert.assertEquals(4, configurableRecordFinder.getTotalPopulation(List.of("alpha","beta")));
    }

    public static void main(String[] args) {
//        SingletonRecordFinder singletonRecordFinder = new SingletonRecordFinder();
//        List<String> names = List.of("Seoul", "Mexico City");
//        int tp = singletonRecordFinder.getTotalPopulation(names);
//        System.out.println(tp == (17500000+17400000));
        DummyDatabase dummyDatabase = new DummyDatabase();
        SingletonDatabase singletonDatabase = SingletonDatabase.getInstance();
        ConfigurableRecordFinder configurableRecordFinder = new ConfigurableRecordFinder(singletonDatabase);
        System.out.println(configurableRecordFinder.getTotalPopulation(List.of("Seoul", "Mexico City"))==2);
    }

}

class SingletonDatabase implements Database{
    private Dictionary<String, Integer> capitals = new Hashtable<>();

    private static int instanceCount = 0;

    public static int getCount() {
        return instanceCount;
    }

    private SingletonDatabase() {
        instanceCount++;
        System.out.println("Initializing database");
        try {
            File f = new File(SingletonDatabase.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            Path fullPath = Paths.get("/home/forest/Projects/Udemy/Design_Pattern/capitals.txt");
            Files.readAllLines(fullPath);
            List<String> lines = Files.readAllLines(fullPath);
            Iterables.partition(lines, 2).forEach(kv -> capitals.put(kv.get(0).trim(), Integer.parseInt(kv.get(1))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final SingletonDatabase INSTANCE = new SingletonDatabase();

    public static SingletonDatabase getInstance() {
        return INSTANCE;
    }

    public int getPopulation(String name) {
        return capitals.get(name);
    }
}



class SingletonRecordFinder {
    public int getTotalPopulation(List<String> names) {
        int result = 0;
        for (String name : names)
            result += SingletonDatabase.getInstance().getPopulation(name);
        return result;
    }
}

class ConfigurableRecordFinder {
    private Database database;

    public ConfigurableRecordFinder(Database database) {
        this.database = database;
    }

    public int getTotalPopulation(List<String> names) {
        int result = 0;
        for (String name : names)
            // use interface to abstract the specific database that is using
            result += database.getPopulation(name);
        return result;
    }
}

interface Database {
    int getPopulation(String name);
}

class DummyDatabase implements Database {
    private Dictionary<String, Integer> data = new Hashtable<>();

    public DummyDatabase() {
        data.put("alpha", 1);
        data.put("beta", 1);
        data.put("gamma", 3);
    }

    @Override
    public int getPopulation(String name) {
        return data.get(name);
    }
}

