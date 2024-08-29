package com.accenture.coffeemaker;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.easymock.EasyMock.expectLastCall;

public class CoffeeMakerTest {

    public static final String BEAN_TRAY_HAS_BEEN_REFILLED = "Bean tray has been refilled!";
    public static final String BEAN_TRAY_IS_EMPTY_MESSAGE = "Bean tray is empty, please fill it up!";
    public static final String SERVER_TRAY_IS_EMPTY_MESSAGE = "Server tray is empty, please place a cup on it!";
    public static final String COFFEE_IS_READY = "Coffee is ready!";
    private static final String ERROR_MESSAGE_PREFIX = "Error: ";

    IMocksControl control;
    private BeanTray beanTray;
    private ServeTray serveTray;
    private Display display;

    private CoffeeMaker underTest;

    @BeforeMethod
    public void beforeMethod() {
        control = EasyMock.createStrictControl();
        beanTray = control.createMock(BeanTray.class);
        serveTray = control.createMock(ServeTray.class);
        display = control.createMock(Display.class);
        underTest = new CoffeeMaker(beanTray, serveTray, display);
    }

    @Test
    public void testPlaceCupOnTrayShouldCallServeTray() {
        serveTray.placeCup();
        control.replay();

        underTest.placeCupOnTray();

        control.verify();
    }

    @Test
    public void testRefillBeanTrayShouldCallBeanTrayAndDisplayCollaborator() {
        beanTray.refill();
        display.displayMessage(BEAN_TRAY_HAS_BEEN_REFILLED);
        control.replay();

        underTest.refillBeanTray();

        control.verify();
    }

    @Test()
    public void testMakeCoffeeShouldDisplayErrorMessageWhenBeanTrayAvailabilityThrowsException() throws CoffeeMakerException {
        beanTray.availability();
        expectLastCall().andThrow(new CoffeeMakerException(BEAN_TRAY_IS_EMPTY_MESSAGE));
        display.displayMessage(ERROR_MESSAGE_PREFIX + BEAN_TRAY_IS_EMPTY_MESSAGE);
        control.replay();

        underTest.makeCoffee();

        control.verify();
    }

    @Test()
    public void testMakeCoffeeShouldDisplayErrorMessageWhenServeTrayAvailabilityThrowsException() throws CoffeeMakerException {
        beanTray.availability();
        serveTray.availability();
        expectLastCall().andThrow(new CoffeeMakerException(SERVER_TRAY_IS_EMPTY_MESSAGE));
        display.displayMessage(ERROR_MESSAGE_PREFIX + SERVER_TRAY_IS_EMPTY_MESSAGE);
        control.replay();

        underTest.makeCoffee();

        control.verify();
    }

    @Test
    public void testMakeCoffeeShouldMakeCoffeeWhenCalledAndThereIsNoException() throws CoffeeMakerException {
        beanTray.availability();
        serveTray.availability();
        display.availability();
        beanTray.useBeans();
        display.displayMessage(COFFEE_IS_READY);
        serveTray.removeCup();
        control.replay();

        underTest.makeCoffee();

        control.verify();
    }

}