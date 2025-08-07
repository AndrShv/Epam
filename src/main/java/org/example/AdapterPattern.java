package org.example;

interface UsbCCharger {
    void chargeViaUsbC();
}


class LightningCharger {
    public void chargeViaLightning() {
        System.out.println("Зарядка через Lightning-порт");
    }
}

class LightningToUsbCAdapter implements UsbCCharger {
    private LightningCharger lightningCharger;

    public LightningToUsbCAdapter(LightningCharger lightningCharger) {
        this.lightningCharger = lightningCharger;
    }

    public void chargeViaUsbC() {
        lightningCharger.chargeViaLightning();
    }
}


public class AdapterPattern {
    public static void main(String[] args) {
        LightningCharger iphone = new LightningCharger();
        UsbCCharger charger = new LightningToUsbCAdapter(iphone);


        charger.chargeViaUsbC(); // ➜ Выведет: Зарядка через Lightning-порт
    }
}

