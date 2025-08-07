package org.example;

interface Animal {
    void makeSound();
}

class Dog implements Animal {
    public void makeSound() {
        System.out.println("Woof");
    }
}

class AnimalTrainer {
    private Animal animal;

    public AnimalTrainer(Animal animal) {
        this.animal = animal;
    }

    public void train() {
        animal.makeSound();
    }
}

public class ProgramToInterfaces {
    public static void main(String[] args) {
        Animal dog = new Dog();
        AnimalTrainer trainer = new AnimalTrainer(dog);
        trainer.train(); // Выведет: Woof
    }
}
