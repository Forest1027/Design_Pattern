package com.forest.design_pattern.section03.structural.proxy;

import java.util.Objects;

/**
 * logging the value whenever the value is changed.
 */
public class PropertyProxyDemo {
    public static void main(String[] args) {
        Creature creature = new Creature();
        creature.setAgility(10);
    }
}

class Creature {
    private Property<Integer> agility = new Property<>(0);

    public int getAgility() {
        return this.agility.getValue();
    }

    public void setAgility(int agility) {
        this.agility.setValue(agility);
    }

}

class Property<T> {
    private T value;

    public Property(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        // logging
        System.out.println("logging value: "+value);
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property<?> property = (Property<?>) o;
        return Objects.equals(value, property.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
