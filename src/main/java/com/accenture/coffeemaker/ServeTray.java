package com.accenture.coffeemaker;

public class ServeTray {

    private boolean hasCup;

    public void availability() throws CoffeeMakerException {
        if (!hasCup) {
            throw new CoffeeMakerException("Server tray is empty, please place a cup on it!");
        }
    }

    public void placeCup() {
        hasCup = true;
    }

    public void removeCup() {
        hasCup = false;
    }
}
