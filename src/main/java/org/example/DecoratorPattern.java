package org.example;

interface Coffee {
    String getDescription();
    double cost();
}


class BasicCoffee implements Coffee {
    public String getDescription() { return "Кофе"; }
    public double cost() { return 5.0; }
}


class MilkCoffee implements Coffee {
    private Coffee coffee;

    public MilkCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public String getDescription() {
        return coffee.getDescription() + ", с молоком";
    }

    public double cost() {
        return coffee.cost() + 1.5;
    }
}
class CaremelCoffee implements Coffee {
    private Coffee coffee;

    public CaremelCoffee(Coffee coffee){
        this.coffee = coffee;
    }

    public String getDescription() {
        return coffee.getDescription() + ", с каарамелью";
    }

    public double cost() {
        return coffee.cost() + 2.5;
    }

}


public class DecoratorPattern {
    public static void main(String[] args) {
        Coffee coffee1 = new MilkCoffee(new BasicCoffee());
        System.out.println(coffee1.getDescription()); // ➜ Кофе, с молоком
        System.out.println(coffee1.cost());           // ➜ 6.5
        Coffee coffee2 = new CaremelCoffee(new BasicCoffee());
        System.out.println(coffee2.getDescription()); // ➜ Кофе, с карамеью
        System.out.println(coffee2.cost());           // ➜ 7.5

    }
}

