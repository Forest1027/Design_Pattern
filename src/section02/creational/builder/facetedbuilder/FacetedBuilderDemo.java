package section02.creational.builder.facetedbuilder;

/**
 * Create multiple builders to work together.
 * All sub-builder should extend from the main builder. This allows you to switch from sub-builder to sub-builder, as you can share the super methods.
 */
public class FacetedBuilderDemo {
    public static void main(String[] args) {
        PersonBuilder personBuilder = new PersonBuilder();
        Person person = personBuilder
                .lives().at("123 London Road").in("London").withPostcode("SW12BC")
                .works().at("Fabrikam").as("Engineer").earning(123000).build();
        System.out.println(person);
    }
}

class Person {
    public String streetAddress, postcode, city;
    public String companyName, position;
    public int annualIncome;

    @Override
    public String toString() {
        return "FBPerson{" +
                "streetAddress='" + streetAddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", annualIncome=" + annualIncome +
                '}';
    }
}

// builder facade
class PersonBuilder {
    protected Person person = new Person();

    public PersonAddressBuilder lives() {
        return new PersonAddressBuilder(person);
    }

    public Person build() {
        return person;
    }

    public PersonJobBuilder works() {
        return new PersonJobBuilder(person);
    }
}

class PersonAddressBuilder extends PersonBuilder {
    public PersonAddressBuilder(Person person) {
        this.person = person;
    }

    public PersonAddressBuilder at(String streetAddress) {
        person.streetAddress = streetAddress;
        return this;
    }

    public PersonAddressBuilder in(String city) {
        person.city = city;
        return this;
    }

    public PersonAddressBuilder withPostcode(String postcode) {
        person.postcode = postcode;
        return this;
    }
}

class PersonJobBuilder extends PersonBuilder {
    public PersonJobBuilder(Person person) {
        this.person = person;
    }

    public PersonJobBuilder at(String companyName) {
        person.companyName = companyName;
        return this;
    }

    public PersonJobBuilder as(String position) {
        person.position = position;
        return this;
    }

    public PersonJobBuilder earning(int annualIncome) {
        person.annualIncome = annualIncome;
        return this;
    }
}
