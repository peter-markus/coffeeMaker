package com.accenture.coffeemaker;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ServeTrayTest {
    public static final int EMPTY_TRAY = 0;

    private ServeTray underTest;

    @BeforeMethod
    public void setUp() {
        underTest = new ServeTray();
    }

    @Test(expectedExceptions = CoffeeMakerException.class)
    public void testAvailabilityShouldThrowExceptionWhenTrayIsEmpty() throws CoffeeMakerException {
        underTest.removeCup();

        underTest.availability();
    }

    @Test
    public void testAvailabilityShouldNotThrowExceptionWhenTrayIsNotEmpty() throws CoffeeMakerException {
        underTest.placeCup();

        underTest.availability();
    }

}