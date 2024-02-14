package org.example;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StationTest {

    @Test
    public void testServe() throws InterruptedException {
        // Создаем станцию с одним насосом и одним кассиром
        Station station = new Station(1, "John");

        // Создаем тестовый объект Car
        Car car = new Car(100.0f, 50.0f);

        // Создаем новый поток для запуска метода serve
        station.serve(car);

            Thread.sleep(1000);


        // Проверяем, что автомобиль успешно заправлен
        assertTrue(car.getRemaining() == 100.0f);

        // Проверяем, что станция закрыта
        station.shutdown();
    }
}
