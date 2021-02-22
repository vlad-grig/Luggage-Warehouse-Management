package service;

import model.Warehouse;

import java.util.Scanner;

public class IOService {

    private Scanner scanner = new Scanner(System.in);

    public void welcomeMessage() {
        System.out.println("Welcome to Luggage Warehouse!\n");
    }

    public void displayInfo(String s) {
        System.out.println(s);
    }

    public void displayMenu(Warehouse warehouse) {
        welcomeMessage();
        displayAvailableStoragePlaces(warehouse);
        displayCostsInfo();
        displayInfo("Are you interested in accessing a baggage storage place?");
        displayInfo("Please make your choice: \n" +
                    "1 for yes \n" +
                    "0 for no");
    }

    private void displayCostsInfo() {
        displayInfo("Costs are 10 lei for the first hour, and then 5 lei per hour.\n" +
                    "Rounding is done in addition, so\n" +
                    "50 minutes -> 1hour -> 10 lei\n" +
                    "61 minutes -> 2 hours -> 10 + 5 = 15 lei\n");
    }

    private void displayAvailableStoragePlaces(Warehouse warehouse) {
        displayInfo("At the moment there are " + warehouse.getStoragePlace().getAvailableStoragePlace() + " places available!\n");

    }

    public String getUserInput() {
        displayInfo("Your answer is: ");
        return scanner.nextLine();
    }

    public void displayError(String error) {
        System.out.println("Error: " + error);
    }
}
