package org.example;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Station {
    public static final Float price = 5.2f;

    private Cashier cashier;
    private ArrayBlockingQueue<Pump> pumps;
    private ExecutorService executor;

    Station(Integer pumpCount, String cashierName) {
        this.cashier = new Cashier(cashierName);
        this.pumps = new ArrayBlockingQueue<>(pumpCount);
        this.executor = Executors.newFixedThreadPool(pumpCount);
        for (int index = 0; index < pumpCount; ++index)
            this.pumps.add(new Pump(index));
    }

    /*
     * Serve car in process:
     * - Cashier will charge car for calulated demand of gas;
     * - Car should be bound to free pump;
     * - Car run away after refueling;
     * - Pump will be set free again for next service.
     */
    public void serve(Car car) {
        Runnable process = () -> {
            Float demand = this.cashier.charge(car, Station.price);
            try {
                Pump pump = this.pumps.take();
                pump.bind(car, demand);
                pump.refuel();
                this.pumps.put(pump);
            } catch (InterruptedException error) {
                throw new RuntimeException("Station could not serve car for an unknown reason.");
            }
        };
        this.executor.execute(process);
    }

    /* Shutdown current station. */
    public void shutdown() {
        this.executor.shutdown();
        while (!this.executor.isTerminated()) {
            System.out.println("Station is going to shutdown...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}