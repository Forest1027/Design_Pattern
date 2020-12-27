package com.forest.design_pattern.section03.structural.facade;

import java.util.ArrayList;
import java.util.List;

/**
 * Facade: Provides a simple, easy to understand, user interface over a large and sophisticated body of code
 */
public class Demo {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(30, 20);
        Viewport viewport = new Viewport(buffer, 30,20,0,0);
        Console console = new Console(30, 20);
        console.addViewport(viewport);
        console.render();

        // use facade to provide simple interface for an easier use
        Console console2 = new Console(30,20);
        console2.render();
    }
}

class Buffer {
    private char[] characters;
    private int lineWidth;

    public Buffer(int lineHeight, int lineWidth) {
        this.lineWidth = lineWidth;
        characters = new char[lineWidth * lineHeight];
    }

    public char charAt(int x, int y) {
        return characters[y * lineWidth + x];
    }
}

class Viewport {
    private final Buffer buffer;
    private final int width;
    private final int height;
    private final int offsetX;
    private final int offsetY;

    public Viewport(Buffer buffer, int width, int height, int offsetX, int offsetY) {
        this.buffer = buffer;
        this.width = width;
        this.height = height;
        this.offsetX = offsetX;

        this.offsetY = offsetY;
    }

    public char charAt(int x, int y) {
        return buffer.charAt(x, y);
    }
}

class Console {
    private List<Viewport> viewports = new ArrayList<>();
    int width, height;

    public Console(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addViewport(Viewport viewport) {
        viewports.add(viewport);
    }

    public static Console newConsole(int width, int height) {
        Buffer buffer = new Buffer(30, 20);
        Viewport viewport = new Viewport(buffer, 30,20,0,0);
        Console console = new Console(30, 20);
        console.addViewport(viewport);
        return console;
    }

    public void render() {
        for(int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                for (Viewport vp : viewports)
                    System.out.println(vp.charAt(x,y));
            }
            System.out.println();
        }
    }
}
