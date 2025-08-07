package org.example;

interface MessageSender {
    void send(String message);
}

class EmailSender implements MessageSender {
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

class SmsSender implements MessageSender {
    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}

class NotificationService {
    private MessageSender sender;

    public NotificationService(MessageSender sender) {
        this.sender = sender;
    }

    public void notifyUser() {
        sender.send("Добро пожаловать!");
    }
}


public class DependencyInversionPrinciple {
    public static void main(String[] args) {
        MessageSender emailSender = new EmailSender();
        NotificationService emailService = new NotificationService(emailSender);
        emailService.notifyUser(); // Выведет: Email: Добро пожаловать!

        MessageSender smsSender = new SmsSender();
        NotificationService smsService = new NotificationService(smsSender);
        smsService.notifyUser(); // Выведет: SMS: Добро пожаловать!
    }
}
