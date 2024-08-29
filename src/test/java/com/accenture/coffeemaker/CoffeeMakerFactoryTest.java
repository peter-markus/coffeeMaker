package com.accenture.coffeemaker;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CoffeeMakerFactoryTest {

    private CoffeeMakerFactory underTest = new CoffeeMakerFactory();

    @Test
    public void testCreateShouldReturnCoffeeMakerInstanceWhenCalled() {
        CoffeeMaker result = underTest.create();

        assertNotNull(result);
    }

}