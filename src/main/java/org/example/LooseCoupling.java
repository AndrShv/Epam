package org.example;

interface InputDevice {
    void input();
}

class Keyboard implements InputDevice {
    public void input() {
        System.out.println("Keyboard input");
    }
}

class Mouse implements InputDevice {
    public void input() {
        System.out.println("Mouse input");
    }
}

class Computer {
    private InputDevice device;

    public Computer(InputDevice device) {
        this.device = device;
    }

    public void start() {
        device.input();
    }
}

public class LooseCoupling {
    public static void main(String[] args) {
        InputDevice keyboard = new Keyboard();
        Computer comp1 = new Computer(keyboard);
        comp1.start(); // Выведет: Keyboard input
        InputDevice mouse = new Mouse();
        Computer comp2 = new Computer(mouse);
        comp2.start(); // Выведет: Mouse input
    }
}

