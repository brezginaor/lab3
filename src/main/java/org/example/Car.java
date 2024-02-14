package org.example;
import java.util.Random;

public class Car {
    private Integer number;
    private Float remaining;
    private final Float capacity;

    private static Integer count = 0;

    static Car generateRandomCar(Random generator) {
        Float capacity = 50 + 50 * generator.nextFloat();
        Float remaining = capacity * generator.nextFloat();
        return new Car(capacity, remaining);
    }

    Car(Float capacity, Float remaining) {
        this.capacity = capacity;
        this.remaining = remaining;

        // Give this car a number by index
        this.number = Car.count;
        Car.count += 1;
    }

    /* Cashier will call this. */
    public void pay(Float money) {
        System.out.println(String.format("Car-%d has been charged for %.2f$", this.number, money));
    }

    /* Calculate refueling demand. */
    public Float demand() {
        return this.capacity - this.remaining;
    }

    /* Refuel car for a given volume of gas. */
    public void refuel(Float volume) {
        if (this.remaining + volume <= this.capacity)
            this.remaining += volume;
        else
            throw new RuntimeException("cannot refuel more than capacity");
    }

    public Float getCapacity() {
        return capacity;
    }

    public Float getRemaining() {
        return remaining;
    }
}
