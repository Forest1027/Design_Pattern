package com.forest.design_pattern.section01.designprinciples.lsp;

import org.w3c.dom.css.Rect;

/**
 * LSP: Liskov Substitution Principle
 * A sub class should be able to substitute a base class.
 * For example, if an API is taking a base class, you should be able to pass in a subclass without any issue.
 * If this principle is not satisfied, there may be issue during inheritance in the future.
 */
public class Demo {

    static void useIt(Rectangle r) {
        int width = r.getWidth();
        r.setHeight(10);
        //area = width * 10
        System.out.println(
                "Expected area of " + (width * 10) + ", got " + r.getArea()
        );
    }

    public static void main(String[] args) {
        Rectangle rc = new Rectangle(2, 3);
        useIt(rc);//Expected area of 20, got 20

        Square square = new Square();
        square.setHeight(5);
        useIt(square);//Expected area of 50, got 100
        // As Suare has its own way of setting width and height, the Square objects cannot be used for place that takes Rectangle anymore.
        // A design pattern way of improving it is to use Factory. See implementation in the end of the class
    }
}

class Rectangle {
    protected int width, height;

    public Rectangle() {
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    public boolean isSquare() {
        return width == height;
    }
}

class Square extends Rectangle {
    public Square() {
    }

    public Square(int size) {
        width = height = size;
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }
}

class RectangleFactory {
    public static Rectangle newRectangle(int width, int height) {
        return new Rectangle(width, height);
    }

    public static Rectangle newSquare(int side) {
        return new Rectangle(side, side);
    }
}
