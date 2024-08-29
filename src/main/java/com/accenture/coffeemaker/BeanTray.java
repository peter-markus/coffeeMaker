package com.accenture.coffeemaker;

public class BeanTray {
    private int beans = 10;

    public void availability() throws CoffeeMakerException {
        if (beans <= 0) {
            throw new CoffeeMakerException("Bean tray is empty, please fill it up!");
        }
    }

    public int getBeans() {
        return beans;
    }

    public void useBeans() {
        if (beans > 0) {
            beans--;
        }
    }

    public void refill() {
        beans = 10;
    }

}
