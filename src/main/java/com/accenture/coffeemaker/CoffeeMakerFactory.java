package com.accenture.coffeemaker;

public class CoffeeMakerFactory {

    public CoffeeMaker create() {
        BeanTray beanTray = new BeanTray();
        ServeTray serveTray = new ServeTray();
        Display display = new Display();

        return new CoffeeMaker(beanTray, serveTray, display);
    }
}
