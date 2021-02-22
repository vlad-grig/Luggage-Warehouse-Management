package service;

import model.Warehouse;
import util.LuggageWarehouseManagementApp;

public class ValidatorService {

    private Warehouse warehouse;
    private IOService ioService;

    public ValidatorService(Warehouse warehouse, IOService ioService) {
        this.warehouse = warehouse;
        this.ioService = ioService;
    }

    public boolean validateUserInput(String userInput) {
        try {
            if (Integer.parseInt(userInput) == 1) {
                return true;
            } else if (Integer.parseInt(userInput) == 0) {
                ioService.displayInfo("Thank you! Have a nice day!\n\n");
                return false;
            } else {
                ioService.displayError(userInput + " is not a valid option!\n\n");
                return false;
            }
        }
        catch( NumberFormatException numberFormatException ) {
            ioService.displayError(userInput + " is not a valid option!\n\n");
            return false;
        }
    }
}