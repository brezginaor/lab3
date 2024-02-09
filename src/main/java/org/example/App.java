package org.example;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        Random generator = new Random();
        Station station = new Station(3, "Doge");
        while (true) {
            Car randomCar = Car.generateRandomCar(generator);
            station.serve(randomCar);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException error) {
                break;
            }
        }
        station.shutdown();
    }
}
