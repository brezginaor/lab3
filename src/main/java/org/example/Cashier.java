package org.example;
public class Cashier {

    private String name; // Human should have a name, I guess.

    Cashier(String name) {
        this.name = name;
    }

    /*
     * Cashier should calculate demand on gas be each car.
     * Cashier should make sure that demand could be devided by pump speed.
     * Example:
     * assert car.capacity - car.remaining == 12.34;
     * assert demand == 12.3 // Which could be divided by 0.1 and not greater than
     * 12.34
     * 
     * Args:
     * Car car: car which need to be refuel;
     * Float price: today's price of gas in 1 unit volume.
     * 
     * Returns:
     * Float demand: how many gas car needed.
     */
    public Float charge(Car car, Float price) {
        Float unit = Pump.speed;
        Float demand = (float) (Math.floor(car.demand() / unit) * unit);
        System.out.println(String.format("%s is going to refuel car with %.2f volume of gas", this.name, demand));
        car.pay(demand * price);
        return demand;
    }
}
