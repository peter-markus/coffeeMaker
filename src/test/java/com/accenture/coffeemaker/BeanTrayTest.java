package com.accenture.coffeemaker;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BeanTrayTest {

    public static final int EMPTY_TRAY = 0;

    private BeanTray underTest;

    @BeforeMethod
    public void setUp() {
        underTest = new BeanTray();
    }

    @Test(expectedExceptions = CoffeeMakerException.class)
    public void testAvailabilityShouldThrowExceptionWhenTrayIsEmpty() throws CoffeeMakerException {
        int defaultBeanNumber = underTest.getBeans();
        for (int i = 0; i < defaultBeanNumber; i++) {
            underTest.useBeans();
        }

        underTest.availability();
    }

    @Test
    public void testAvailabilityShouldNotThrowExceptionWhenTrayIsNotEmpty() throws CoffeeMakerException {
        underTest.availability();
    }

    @Test
    public void testRefillShouldSetBeanValueToDefaultWhenCalled() {
        int defaultBeanNumber = underTest.getBeans();
        for (int i = 0; i < defaultBeanNumber; i++) {
            underTest.useBeans();
        }

        underTest.refill();

        assertEquals(underTest.getBeans(), defaultBeanNumber);
    }

    @Test
    public void testUseBeansShouldDecreaseBeansWhenThereAreBeansOnTheTray() {
        int defaultBeanNumber = underTest.getBeans();

        underTest.useBeans();

        assertEquals(underTest.getBeans(), defaultBeanNumber - 1);
    }

    @Test
    public void testUseBeansShouldNotDecreaseBeansWhenThereAreNoBeansOnTheTray() {
        int defaultBeanNumber = underTest.getBeans();
        for (int i = 0; i < defaultBeanNumber; i++) {
            underTest.useBeans();
        }

        assertEquals(underTest.getBeans(), EMPTY_TRAY);
        underTest.useBeans();
        assertEquals(underTest.getBeans(), EMPTY_TRAY);
    }
}