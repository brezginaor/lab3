package org.example;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Random;

public class CarTest {

    @Test
    public void testDemand() {

        // Создаем объект Car с определенной вместимостью бака и оставшимся топливом
        Car car = new Car(100.0f, 30.0f);

        Float capacity = car.getCapacity();
        Float remaining = car.getRemaining();

        // Ожидаемый результат - разница между вместимостью бака и оставшимся топливом
        Float expectedDemand = capacity - remaining;

        // Вызываем метод demand() и проверяем, что результат соответствует ожидаемому
        assertEquals(expectedDemand, car.getCapacity() - car.getRemaining(), 0.001f);
    }

    @Test
    public void testRefuel() {
        // Создаем объект Car с нулевым остатком топлива
        Car car = new Car(100.0f, 0.0f);

        // Добавляем некоторый объем топлива
        Float refuelVolume = 50.0f;
        car.refuel(refuelVolume);

        // Проверяем, что остаток топлива равен объему дозаправки
        assertEquals(refuelVolume, car.getRemaining(), 0.001f);

        // Добавляем еще топлива, чтобы заполнить бак полностью
        car.refuel(50.0f);

        // Проверяем, что остаток топлива равен вместимости бака
        assertEquals(100.0f, car.getRemaining(), 0.001f);
    }

    @Test(expected = RuntimeException.class)
    public void testRefuelOverCapacity() {
        // Создаем объект Car с полным баком
        Car car = new Car(100.0f, 100.0f);

        // Пытаемся добавить еще топлива, чем вмещает бак
        car.refuel(1.0f); // Должно быть выброшено исключение RuntimeException
    }

    @Test
    public void testGenerateRandomCar() {
        // Создаем генератор случайных чисел
        Random generator = new Random();

        // Создаем случайный автомобиль
        Car car = Car.generateRandomCar(generator);

        // Проверяем, что вместимость бака и остаток топлива находятся в допустимых пределах
        assertTrue(car.getCapacity() >= 50.0f && car.getCapacity() <= 100.0f);
        assertTrue(car.getRemaining() >= 0.0f && car.getRemaining() <= car.getCapacity());
    }
}
