package org.example;

interface Workable { void work(); }
interface Eatable { void eat(); }

class Human implements Workable, Eatable {
    public void work() {
        System.out.println("Human is working");

    }
    public void eat() {
        System.out.println("Human is eating");
    }
}

class Robot implements Workable {
    public void work() {
        System.out.println("Robot is working");
    }
}

public class InterfaceSegregationPrinciple {
    public static void main(String[] args) {
        Workable human = new Human();
        human.work(); // Human is working

        Eatable humanEater = new Human();
        humanEater.eat(); // Human is eating

        Workable robot = new Robot();
        robot.work(); // Robot is working


    }
}

