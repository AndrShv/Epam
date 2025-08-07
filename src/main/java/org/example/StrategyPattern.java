package org.example;

interface Strategy {
    void execute();
}

class AggressiveStrategy implements Strategy {
    public void execute() {
        System.out.println("Атаковать!");
    }
}

class DefensiveStrategy implements Strategy {
    public void execute() {
        System.out.println("Защищаться!");
    }
}

class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void play() {
        strategy.execute();
    }
}

public class StrategyPattern {
    public static void main(String[] args) {
        Context game = new Context(new AggressiveStrategy());
        game.play(); // ➜ Выведет: Атаковать!

        game = new Context(new DefensiveStrategy());
        game.play(); // ➜ Выведет: Защищаться!
    }
}

