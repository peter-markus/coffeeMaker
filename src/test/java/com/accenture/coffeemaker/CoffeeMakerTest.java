package com.accenture.coffeemaker;

import org.easymock.Mock;
import org.easymock.TestSubject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.easymock.EasyMock.*;
import static org.easymock.EasyMockSupport.injectMocks;

public class CoffeeMakerTest {

    @Mock
    private BeanTray beanTray;
    @Mock
    private ServeTray serveTray;
    @Mock
    private Display display;

    @TestSubject
    private CoffeeMaker underTest;

    @BeforeMethod
    public void beforeClass() {
        underTest = new CoffeeMaker();
        injectMocks(this);
    }

    @Test
    public void testPlaceCupOnTrayShouldCallServeTray() {
        serveTray.placeCup();
        replay(serveTray);

        underTest.placeCupOnTray();

        verify(serveTray);
    }

    @Test
    public void testRefillBeanTrayShouldCallBeanTrayAndDisplayCollaborator() {
        beanTray.refill();
        display.displayMessage("Bean tray has been refilled!");
        replay(beanTray, display);

        underTest.refillBeanTray();

        verify(beanTray, display);
    }

    @Test()
    public void testMakeCoffeeShouldDisplayErrorMessageWhenBeanTrayAvailabilityThrowsException() throws CoffeeMakerException {
        beanTray.availability();
        expectLastCall().andThrow(new CoffeeMakerException("Bean tray is empty, please fill it up!"));
        display.displayMessage("Error: Bean tray is empty, please fill it up!");
        replay(beanTray, display);

        underTest.makeCoffee();

        verify(beanTray, display);
    }

    @Test()
    public void testMakeCoffeeShouldDisplayErrorMessageWhenServeTrayAvailabilityThrowsException() throws CoffeeMakerException {
        beanTray.availability();
        serveTray.availability();
        expectLastCall().andThrow(new CoffeeMakerException("Server tray is empty, please place a cup on it!"));
        display.displayMessage("Error: Server tray is empty, please place a cup on it!");
        replay(beanTray, serveTray, display);

        underTest.makeCoffee();

        verify(beanTray, serveTray, display);
    }

    @Test
    public void testMakeCoffeeShouldMakeCoffeeWhenCalledAndThereIsNoException() throws CoffeeMakerException {
        beanTray.availability();
        serveTray.availability();
        display.availability();
        beanTray.useBeans();
        display.displayMessage("Coffee is ready!");
        serveTray.removeCup();
        replay(beanTray, serveTray, display);

        underTest.makeCoffee();

        verify(beanTray, serveTray, display);
    }

}