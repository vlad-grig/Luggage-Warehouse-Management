package service;

public class ValidatorService {

    private IOService ioService;

    public ValidatorService(IOService ioService) {
        this.ioService = ioService;
    }

    public boolean validateUserInput(String userInput) {
        try {
            if (Integer.parseInt(userInput) == 1 || Integer.parseInt(userInput) == 2) {
                return true;
            } else if (Integer.parseInt(userInput) == 0) {
                ioService.displayInfo("Thank you! Have a nice day!");
                return false;
            } else {
                ioService.displayError(userInput + " is not a valid option!");
                return false;
            }
        }
        catch( NumberFormatException numberFormatException ) {
            ioService.displayError(userInput + " is not a valid option!");
            return false;
        }
    }
}