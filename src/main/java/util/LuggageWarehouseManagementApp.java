package util;

import model.StoragePlace;
import model.Warehouse;
import service.IOService;
import service.ValidatorService;

public class LuggageWarehouseManagementApp {

    private IOService ioService;
    private Warehouse warehouse;
    private StoragePlace storagePlace;
    private ValidatorService validatorService;

    public LuggageWarehouseManagementApp() {
        this.ioService = new IOService();
        this.storagePlace = new StoragePlace();
        this.warehouse = new Warehouse(storagePlace);
        this.validatorService = new ValidatorService(warehouse, ioService);
    }


    public void start() {
        while (true) {
            ioService.displayMenu(warehouse);
            String userInput = ioService.getUserInput();
            processUserInput(userInput);
            break;
        }
    }

    private void processUserInput(String userInput) {
        boolean successfulValidation = validatorService.validateUserInput(userInput);
        if (!successfulValidation) {
            start();
        } else {
            ioService.displayInfo("Continue process!");
        }
    }


    public void load() {
        storagePlace.setTotalStoragePlace(25);
        warehouse.getStoragePlace().setOccupiedStoragePlace(0);
        warehouse.getStoragePlace().setAvailableStoragePlace(warehouse.getStoragePlace().getTotalStoragePlace() - warehouse.getStoragePlace().getOccupiedStoragePlace());
        if (warehouse.getStoragePlace().getAvailableStoragePlace() == 0) {
            ioService.displayInfo("Sorry, but at the moment all storage places are occupied!");
        }
    }
}
