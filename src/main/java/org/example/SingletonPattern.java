package org.example;

class PrintSpooler {
    private static PrintSpooler instance;

    // Приватный конструктор — нельзя создать объект напрямую
    private PrintSpooler() {
        System.out.println("📠 Диспетчер печати запущен");
    }

    // Глобальная точка доступа
    public static PrintSpooler getInstance() {
        if (instance == null) {
            instance = new PrintSpooler();
        }
        return instance;
    }

    public void printDocument(String documentName) {
        System.out.println("🖨️ Печать документа: " + documentName);
    }
}

public class SingletonPattern {
    public static void main(String[] args) {
        PrintSpooler spooler1 = PrintSpooler.getInstance();
        PrintSpooler spooler2 = PrintSpooler.getInstance();

        spooler1.printDocument("Документ_1.pdf");
        spooler2.printDocument("Документ_2.pdf");

        System.out.println(spooler1 == spooler2); // ➜ true — один и тот же диспетчер
    }
}



