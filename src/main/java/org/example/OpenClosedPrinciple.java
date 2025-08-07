package org.example;

interface PaymentMethod {
    void pay();
}

class PayPal implements PaymentMethod {
    public void pay() {
        System.out.println("Оплата через PayPal");
    }
}

class CreditCard implements PaymentMethod {
    public void pay() {
        System.out.println("Оплата через Кредитную карту");
    }
}

class PaymentProcessor {
    public void pay(PaymentMethod method) {
        method.pay();
    }
}
public class OpenClosedPrinciple {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        PaymentMethod paypal = new PayPal();
        PaymentMethod creditCard = new CreditCard();

        processor.pay(paypal);
        processor.pay(creditCard);
    }
}

