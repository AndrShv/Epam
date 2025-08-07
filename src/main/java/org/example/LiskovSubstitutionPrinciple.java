package org.example;

interface Bird {}
interface FlyingBird extends Bird {
    void fly();
}

class Sparrow implements FlyingBird {
    public void fly() {}
}

class Ostrich implements Bird {
}

public class LiskovSubstitutionPrinciple {
    public static void main(String[] args) {
        FlyingBird sparrow = new Sparrow();
        sparrow.fly(); // Корректно, Sparrow реализует fly()

        // Ошибка компиляции, Ostrich не является FlyingBird
        // FlyingBird ostrich = new Ostrich();
    }
}
