package com.forest.design_pattern.section01.designprinciples.dip;

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * DIP: Dependency Inversion Principle
 * A. High-level modules should not depend on low-level modules. Both should depend on abstractions.
 * B. Abstractions should not depend on details. Details should depend on abstractions.
 * <p>
 * Abstraction: interface/ abstract class
 * High-level: Layer that is closer to end user.
 * Low-level: Layer that is closer to data storage.
 *
 * The example below Relationships is low-level, Research is high level. Research depends on the method of Relationships, which violated this principle.
 * In the future if you want to change the implementation of this method of lower-level, you will need to change everything.
 * Instead, create a abstraction and let low-level implement it, provide this method specifically for higher level
 */
public class Demo {
    public static void main(String[] args) {
        Person parent = new Person("John");
        Person child1 = new Person("Chris");
        Person child2 = new Person("Matt");

        Relationships relationships = new Relationships();
        relationships.addParentAndChild(parent, child1);
        relationships.addParentAndChild(parent, child2);

        Research research = new Research(relationships);

    }
}

enum Relationship {
    PARENT, CHILD, SIBLING
}

class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }
}

//low level, as it is related to data storage
class Relationships implements RelationshipBrowser {
    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    public void setRelations(List<Triplet<Person, Relationship, Person>> relations) {
        this.relations = relations;
    }

    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
        return relations.stream()
                .filter(x -> Objects.equals(x.getValue0().name, name) && x.getValue1() == Relationship.PARENT).map(Triplet::getValue2).collect(Collectors.toList());

    }
}

// high level: closer to end user
class Research {
//    public Research(Relationships relationships) {
//        List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
//        relations.stream().filter(x -> x.getValue0().name.equals("John") && x.getValue1() == Relationship.PARENT).forEach(child -> System.out.println("John has a child called " + child.getValue2().name));
//    }

    public Research(RelationshipBrowser browser) {
        List<Person> children = browser.findAllChildrenOf("John");
        for (Person child : children
        ) {
            System.out.println(child.name);
        }
    }
}

interface RelationshipBrowser {
    List<Person> findAllChildrenOf(String name);
}

