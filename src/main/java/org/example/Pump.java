package org.example;
import java.lang.Thread;

public class Pump {

    public static final Float speed = 1.f; // Refuel 1 volume unit in <gap> second
    public static final Integer gap = 10; // Thread sleep for 0.5 second after each refuel operation

    private Integer index = 0;

    private Car car = null;
    private Float demand = 0.f;

    Pump(Integer index) {
        this.index = index;
    }

    public void refuel() {
        // Refuel car for a while and make sure the car could not been opreated by any
        // other threads.
        // Example: car will NOT been allowed to drive away when process refueling going
        // :)
        System.out.println(String.format("Pump-%d start working...", this.index));
        synchronized (this.car) {
            while (this.demand > 0) {
                this.car.refuel(speed);
                this.demand -= speed;
                try {
                    Thread.sleep(gap);
                } catch (InterruptedException error) {
                    break;
                }
            }
        }

        // Car ran away
        this.car = null;
        this.demand = 0.f;
        System.out.println(String.format("Pump-%d stopped working...", this.index));
    }

    /* Bind a car for refueling. */
    public void bind(Car car, Float demand) {
        this.car = car;
        this.demand = demand;
    }

    /* Check if current pump is working. */
    public boolean working() {
        return this.car == null;
    }
}
