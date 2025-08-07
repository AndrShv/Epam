package org.example;

import java.util.ArrayList;
import java.util.List;

interface WeatherObserver {
    void updateWeather(String weather);
}



class MobileApp implements WeatherObserver {
    private String username;

    public MobileApp(String username) {
        this.username = username;
    }

    @Override
    public void updateWeather(String weather) {
        System.out.println(username + " получил уведомление: Погода изменилась на " + weather);
    }
}


class WeatherStation {
    private List<WeatherObserver> observers = new ArrayList<>();

    public void subscribe(WeatherObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(WeatherObserver observer) {
        observers.remove(observer);
    }

    public void changeWeather(String newWeather) {
        System.out.println("Погодная станция: Новая погода - " + newWeather);
        notifyObservers(newWeather);
    }

    private void notifyObservers(String weather) {
        for (WeatherObserver observer : observers) {
            observer.updateWeather(weather);
        }
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();

        WeatherObserver user1 = new MobileApp("Андрей");
        WeatherObserver user2 = new MobileApp("Мария");

        station.subscribe(user1);
        station.subscribe(user2);

        station.changeWeather(" Солнечно");
        station.changeWeather("🌧 Дождь");

        // ➜ Погодная станция: Новая погода - Солнечно
        // ➜ Андрей получил уведомление: Погода изменилась на Солнечно
        // ➜ Мария получила уведомление: Погода изменилась на Солнечно
        // ➜ Погодная станция: Новая погода - Дождь
        // ➜ Андрей получил уведомление: Погода изменилась на Дождь
        // ➜ Мария получила уведомление: Погода изменилась на Дождь
    }
}
