package com.accenture.coffeemaker;

public class Display {

    public void availability() {
    }

    public void displayMessage(final String message) {
        if (!message.isEmpty()) {
            System.out.println("LCD display: " + message);
        }
    }
}
