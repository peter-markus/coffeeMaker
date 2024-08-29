package com.accenture.coffeemaker;

public class CoffeeMaker {

    private BeanTray beanTray = new BeanTray();
    private ServeTray serveTray = new ServeTray();
    private Display display = new Display();

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
