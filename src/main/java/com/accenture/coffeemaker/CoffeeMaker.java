package com.accenture.coffeemaker;

public class CoffeeMaker {

    private final BeanTray beanTray;
    private final ServeTray serveTray;
    private final Display display;

    public CoffeeMaker(final BeanTray beanTray, final ServeTray serveTray, final Display display) {
        this.beanTray = beanTray;
        this.serveTray = serveTray;
        this.display = display;
    }

    public void makeCoffee() {
        try {
            runAvailabilityChecksOnAllModule();

            beanTray.useBeans();
            display.displayMessage("Coffee is ready!");
            serveTray.removeCup();
        } catch (CoffeeMakerException e) {
            display.displayMessage("Error: " + e.getMessage());
        }

    }

    public void refillBeanTray() {
        beanTray.refill();
        display.displayMessage("Bean tray has been refilled!");
    }

    public void placeCupOnTray() {
        serveTray.placeCup();
    }

    private void runAvailabilityChecksOnAllModule() throws CoffeeMakerException {
        beanTray.availability();
        serveTray.availability();
        display.availability();
    }
}
