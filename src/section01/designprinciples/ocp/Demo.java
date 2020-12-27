package section01.designprinciples.ocp;

import java.util.List;
import java.util.stream.Stream;

/**
 * OCP: Open-Closed Principle
 * Code should be open for extension but close for modification
 * Open for extension: It is ok to do inheritance or implementation
 * Close for modification: Under no circumstance that you will need to modify the interface directly
 */
public class Demo {
    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
        Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
        Product house = new Product("House", Color.BLUE, Size.LARGE);
        List<Product> products = List.of(apple, tree, house);
        ProductFilter filter = new ProductFilter();
        System.out.println("Green products (old):");
        filter.filterByColor(products, Color.GREEN).forEach(p -> System.out.println(" - " + p.name + " is " + p.color));

        // implement using specification
        BetterFilter betterFilter = new BetterFilter();
        System.out.println("Green products (new):");
        betterFilter.filter(products, new ColorSpecification(Color.GREEN)).forEach(p -> System.out.println(" - " + p.name + " is " + p.color));

        // combing two specifications
        System.out.println("Large blue items:");
        betterFilter.filter(products, new AndSpecification<>(
                new ColorSpecification(Color.BLUE),
                new SizeSpecification(Size.LARGE)
        )).forEach(p -> System.out.println(" - " + p.name + " is " + p.color + " and " + p.size));
    }
}

interface Specification<T> {
    boolean isSatisfied(T item);
}

interface Filter<T> {
    Stream<T> filter(List<T> items, Specification<T> spec);
}

class ColorSpecification implements Specification<Product> {

    private Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.color == color;
    }
}

class SizeSpecification implements Specification<Product> {

    private Size size;

    public SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.size == size;
    }
}

class BetterFilter implements Filter<Product> {
    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
        return items.stream().filter(p -> spec.isSatisfied(p));
    }
}

class AndSpecification<T> implements Specification<T> {
    private Specification<T> first, second;

    public AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T item) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}

/**
 * Below is the old way of implementing
 */
enum Color {
    RED, GREEN, BLUE
}

enum Size {
    SMALL, MEDIUM, LARGE, YUGE
}

class Product {
    public String name;
    public Color color;
    public Size size;

    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }
}

class ProductFilter {
    public Stream<Product> filterByColor(List<Product> products, Color color) {
        return products.stream().filter(p -> p.color == color);
    }

    public Stream<Product> filterBySize(List<Product> products, Size size) {
        return products.stream().filter(p -> p.size == size);
    }

    public Stream<Product> filterByColorSize(List<Product> products, Color color, Size size) {
        return products.stream().filter(p -> p.color == color && p.size == size);
    }
}


