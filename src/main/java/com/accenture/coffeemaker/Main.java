package com.accenture.coffeemaker;

import java.util.Scanner;

public class Main {
    /*
    1. Create a class that represents a CoffeeMaker. Our CoffeeMaker should be built up as a modular system containing
        several submodules like BeanTray, ServeTray and Display.
    2. Create a class for every submodule and make them as a part of the CoffeeMaker. Every submodule must have a common
        function that performs an availability check. This common function never returns any value, it throws a custom
        exception instead in case of any error.
    3. Supplement the CoffeeMaker with the newly created modules.
    4. Perform availability check every time on each submodule when a coffee is requested. Display a meaningful message if
        an error occurred and handle it properly.
    5. Implement the coffee making process. (Decrease the BeanTray amount and inform the user once the coffee is ready.)
    6. Let the user to fill the BeanTray.

    Submodules:
        - BeanTray: Handles the amount of coffee beans can be used for making coffee. The initial amount of coffee beans
                    on program start is always eligible for making 10 cups of coffee. The amount is decreases by 1 per
                    coffee.
                    The availability check method throws a custom exception if the tray is empty.

        - ServeTray: Checks if there is a cup on the tray.
                    The availability check method throws a custom exception if the tray is empty.

        - Display: Displays string message on an LCD display (use console log for testing)
                    The availability check method shouldn't throw any exception for now.
     */

    public static void main(String[] args) {
        printMenu();
        CoffeeMaker coffeeMaker = new CoffeeMaker();

        while (true) {
            int input = readInput();
            if (input == 1) {
                coffeeMaker.placeCupOnTray();
                coffeeMaker.makeCoffee();
            } else if (input == 2) {
                coffeeMaker.refillBeanTray();
            } else if (input == 3) {
                break;
            } else {
                System.out.println("Input not valid");
            }

            printMenu();
        }
    }

    private static void printMenu() {
        System.out.println("(1) Make a coffee");
        System.out.println("(2) Fill bean tray");
        System.out.println("(3) Shut down coffee machine");
        System.out.println("\nWhat do you want to do?");
    }

    private static int readInput() {
        Scanner scanner = new Scanner(System.in);

        int input;
        try {
            input = scanner.nextInt();
        } catch (Exception e) {
            input = 0;
        }

        return input;
    }
}
