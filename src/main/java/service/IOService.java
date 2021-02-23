package service;

import model.StoragePlace;

import java.time.LocalDateTime;
import java.util.Scanner;

public class IOService {

    private Scanner scanner = new Scanner(System.in);


    public void displayMenu(StoragePlace storagePlace) {
        welcomeMessage();
        displayAvailableStoragePlaces(storagePlace);
        displayCostsInfo();
        displayInfo("Are you interested in accessing a baggage storage place?");
        displayInfo("Please make your choice: \n" +
                    "0 for no \n" +
                    "1 for yes \n" +
                    "2 for unlock your storage place");
    }

    public void displayInfo(String s) {
        System.out.println(s);
    }

    private void displayCostsInfo() {
        displayInfo("Costs are 10 lei for the first hour, and then 5 lei per hour.\n" +
                    "Rounding is done in addition, so\n" +
                    "50 minutes -> 1hour -> 10 lei\n" +
                    "61 minutes -> 2 hours -> 10 + 5 = 15 lei\n");
    }

    private void displayAvailableStoragePlaces(StoragePlace storagePlace) {
        displayLocalDateTime();
        displayInfo("At the moment there are " + storagePlace.getAvailableStoragePlace() + " places available!\n");
    }

    private void displayLocalDateTime() {
        System.out.println(LocalDateTime.now());
    }

    public String getUserInput() {
        displayInfo("Your answer is: ");
        return scanner.nextLine();
    }

    public void displayError(String error) {
        System.out.println("Error: " + error);
    }

    public void welcomeMessage() {
        System.out.println("Welcome to Luggage Warehouse!\n");
    }

    public void displayMessage(int assignedStoragePlaceId) {
        System.out.println("...... Read info: \n" +
                           "The storage place number " + assignedStoragePlaceId + " is now available to you. \n" +
                           "Place your luggage. \n" +
                           "Close the storage place. \n" +
                           "Confirm closure. \n" +
                           "Once the closure has been confirmed, a ticket will be generated. \n" +
                           "Present the unique code for the return of luggage.\n");
    }

    public String getUserConfirmation() {
        displayInfo("Type 'yes' for confirmation closure: ");
        return scanner.nextLine();
    }
}
