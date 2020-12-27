package com.forest.design_pattern.section03.structural.bridge.exercise;

public class Demo {

}

interface Renderer {
    String whatToRenderAs(String name);
}

abstract class Shape {
    protected Renderer renderer;

    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }

    public abstract String getName();
}

class Triangle extends Shape {


    public Triangle(Renderer renderer) {
        super(renderer);
    }

    @Override
    public String getName() {
        return "Triangle";
    }

    @Override
    public String toString() {
        return super.renderer.whatToRenderAs(getName());
    }
}

class Square extends Shape {

    public Square(Renderer renderer) {
        super(renderer);
    }

    @Override
    public String getName() {
        return "Square";
    }

    @Override
    public String toString() {
        return super.renderer.whatToRenderAs(getName());
    }

}

class VectorRenderer implements Renderer {
    @Override
    public String whatToRenderAs(String name) {
        return String.format("Drawing %s as lines", name);
    }
}

class RasterRenderer implements Renderer {

    @Override
    public String whatToRenderAs(String name) {
        return String.format("Drawing %s as pixels", name);
    }
}

// imagine VectorTriangle and RasterTriangle are here too
