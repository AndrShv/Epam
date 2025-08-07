package org.example;

interface Animal {
    void speak();
}

class Dog implements Animal {
    public void speak() {
        System.out.println("Гав");
    }
}

class Cat implements Animal {
    public void speak() {
        System.out.println("Мяу");
    }
}

class AnimalFactory {
    public static Animal create(String type) {
        return switch (type) {
            case "dog" -> new Dog();
            case "cat" -> new Cat();
            default -> throw new IllegalArgumentException("Неизвестно");
        };
    }
}

public class FactoryPattern {
    public static void main(String[] args) {
        Animal a1 = AnimalFactory.create("dog");
        Animal a2 = AnimalFactory.create("cat");

        a1.speak(); // ➜ Гав
        a2.speak(); // ➜ Мяу
    }
}

