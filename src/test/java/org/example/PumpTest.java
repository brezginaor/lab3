package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class PumpTest {

    @Test
    public void testRefuel() throws InterruptedException {
        // Создаем тестовый объект Pump
        Pump pump = new Pump(1);

        // Создаем тестовый объект Car
        Car car = new Car(100.0f, 50.0f);

        // Задаем требуемый объем топлива для заправки
        Float demand = 20.0f;

        // Вызываем метод bind, чтобы связать pump с car и передать ему demand
        pump.bind(car, demand);

        // Запускаем pump в отдельном потоке
        Thread thread = new Thread(() -> pump.refuel());
        thread.start();

        // Ждем, пока pump закончит работу
        thread.join();

        // После того, как pump закончит работу, проверяем, что остаток топлива у car увеличился на demand
        assertEquals(70.0f, car.getRemaining(), 0.001f);
    }
}

