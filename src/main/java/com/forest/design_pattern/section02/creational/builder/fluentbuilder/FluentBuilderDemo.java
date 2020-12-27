package com.forest.design_pattern.section02.creational.builder.fluentbuilder;

/**
 * Fluent builder inheritance will go wrong without recursive generics.
 * If the parent class return itself, we cannot chain another method from child class.
 * Therefore, we need recursive generics.
 * We use recursive generics to pass child class to parent class. Then child method and parent method can chain together.
 */
public class FluentBuilderDemo {
    public static void main(String[] args) {
        PersonBuilder personBuilder = new PersonBuilder();
        Person john = personBuilder.withName("John").build();
        EmployeeBuilder employeeBuilder = new EmployeeBuilder();
        System.out.println(employeeBuilder.withName("Forest").worksAt("Guidewire").build());
    }
}

class Person {
    public String name;
    public String position;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }


}

// example of recursive generics
class PersonBuilder<SELF extends PersonBuilder<SELF>> {
    protected Person person = new Person();

    public SELF withName(String name) {
        person.name = name;
        return self();
    }

    public Person build() {
        return person;
    }

    protected SELF self(){
        return (SELF)this;
    }
}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {
    public EmployeeBuilder worksAt(String position) {
        person.position = position;
        return this;
    }

    @Override
    protected EmployeeBuilder self() {
        return this;
    }
}
