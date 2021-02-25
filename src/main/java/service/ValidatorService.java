package service;

import java.util.Map;

public class ValidatorService {

    private IOService ioService;
    private DelayService delayService;

    public ValidatorService(IOService ioService) {
        this.ioService = ioService;
        this.delayService = new DelayService();
    }

    public boolean validateUserInput(String userInput) {
        try {
            if (Integer.parseInt(userInput) == 1 || Integer.parseInt(userInput) == 2) {
                return true;
            } else if (Integer.parseInt(userInput) == 0) {
                ioService.displayInfo("Thank you! Have a nice day!");
                delayService.introduceDelay();
                return false;
            } else {
                ioService.displayError(userInput + " is not a valid option!");
                delayService.introduceDelay();
                return false;
            }
        }
        catch( NumberFormatException numberFormatException ) {
            ioService.displayError(userInput + " is not a valid option!");
            delayService.introduceDelay();
            return false;
        }
    }

    public boolean validateUniqueCode(String userInputUniqueCode, Map <Integer, String> idCodeMap) {
        for(Map.Entry <Integer, String> entry : idCodeMap.entrySet()) {
            if (entry.getValue().equals(userInputUniqueCode)) {
                return true;
            }
        }
        return false;
    }
}