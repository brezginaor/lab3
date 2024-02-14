package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class CashierTest {

    @Test
    public void testCharge() {
        // Создаем тестовый автомобиль
        Car car = new Car(100.0f, 50.0f);

        // Создаем тестового кассира
        Cashier cashier = new Cashier("John");

        // Задаем цену за единицу топлива
        Float price = 2.0f;

        // Ожидаемый спрос
        Float expectedDemand = 50.0f;

        // Вызываем метод charge у кассира
        Float actualDemand = cashier.charge(car, price);

        // Проверяем, что спрос был рассчитан верно
        assertEquals(expectedDemand, actualDemand, 0.001f);

        // Проверяем, что метод pay был вызван у объекта Car
        // Для этого создадим mock объект класса Car
        class MockCar extends Car {
            boolean isPayCalled = false;

            MockCar(Float capacity, Float remaining) {
                super(capacity, remaining);
            }

            @Override
            public void pay(Float money) {
                // Устанавливаем флаг, что метод был вызван
                isPayCalled = true;
            }
        }

        MockCar mockCar = new MockCar(100.0f, 50.0f);
        cashier.charge(mockCar, price);

        // Проверяем, что метод pay был вызван
        assertTrue(mockCar.isPayCalled);
    }
}

