package util;

import model.StoragePlace;
import model.Warehouse;
import service.DelayService;
import service.IOService;
import service.UpdateService;
import service.ValidatorService;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class LuggageWarehouseManagementApp {

    private Warehouse warehouse;
    private IOService ioService;
    private StoragePlace storagePlace;
    private ValidatorService validatorService;
    private Map <Integer, String> idAvailabilityMap;
    private Map <Integer, String> idCodeMap;
    private Map <Integer, String> idLocalDateTimeMap;
    private DelayService delayService;
    private UpdateService updateService;

    public LuggageWarehouseManagementApp() {
        this.warehouse = new Warehouse();
        this.ioService = new IOService();
        this.storagePlace = new StoragePlace();
        this.validatorService = new ValidatorService(ioService);
        this.idAvailabilityMap = new TreeMap <>();
        this.idCodeMap = new TreeMap <>();
        this.idLocalDateTimeMap = new TreeMap <>();
        this.delayService = new DelayService();
        this.updateService = new UpdateService(warehouse);
    }


    public void start() {
        while (true) {
            ioService.displayInfo("........................................................................");
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
            switch (userInput) {
                case "1": {
                    accessStoragePlace();
                    start();
                    break;
                }
                case "2": {
                    unlockStoragePlace();
                    start();
                    break;
                }
            }
        }
    }

    private void unlockStoragePlace() {
        ioService.displayInfo("Please type the unique code: ");
        String userInputUniqueCode = ioService.getUserInput();
        boolean validateUniqueCode = validatorService.validateUniqueCode(userInputUniqueCode, idCodeMap);
        if (!validateUniqueCode) {
            ioService.displayError(userInputUniqueCode + " is not a valid code!");
            delayService.introduceDelay();
            start();
        }
        int timeInMinute = calculateTimeInMinute(userInputUniqueCode);
        int price = calculatePrice(timeInMinute);
        ioService.displayInfo("TO BE PAID: " + price + "LEI\n" +
                              "Touch card here!");

        ioService.displayInfo("valideaza cardul, valideaza plata");
        ioService.displayInfo("putem incasa plata");
        ioService.displayInfo("putem afisa datele bagajului");
        ioService.displayInfo("putem elibera bagajul");

        restoreAvailabilityAfterStoragePlaceRelease(userInputUniqueCode);
        delayService.introduceDelay();
    }

    private void restoreAvailabilityAfterStoragePlaceRelease(String userInputUniqueCode) {
        for(Map.Entry <Integer, String> entry : idCodeMap.entrySet()) {
            if (entry.getValue().equals(userInputUniqueCode)) {
                Integer key = entry.getKey();
                idAvailabilityMap.replace(key, "available");
                idCodeMap.replace(key, "");
                idLocalDateTimeMap.replace(key, "");
                updateService.updateUpAvailability();
                break;
            }
        }

        System.out.println(idAvailabilityMap);
        System.out.println(idCodeMap);
        System.out.println(idLocalDateTimeMap);

        start();
    }

    private int calculatePrice(int timeInMinute) {
        int price = 0;
        ioService.displayInfo("calculez pretul");
        return price;
    }

    private int calculateTimeInMinute(String userInputUniqueCode) {
        int timeToBeCalculate = 0;
        ioService.displayInfo("calculez timpul");
        return timeToBeCalculate;
    }

    private void accessStoragePlace() {
        checkStoragePlaceAvailability();
        int assignedStoragePlaceId = assignStoragePlaceId();
        getDetailsAboutLuggage();
        ioService.displayMessage(assignedStoragePlaceId);
        boolean confirmClosureStoragePlace = confirmClosureStoragePlace();
        if (!confirmClosureStoragePlace) {
            start();
        }
        idAvailabilityMap.replace(assignedStoragePlaceId, "unavailable");
        updateService.updateDownAvailability();
        issueTicket(assignedStoragePlaceId);
//        System.out.println(idAvailabilityMap);
//        System.out.println(idCodeMap);
//        System.out.println(idLocalDateTimeMap);
    }

    private void getDetailsAboutLuggage() {
        ioService.getField("owner first name and last name");
        ioService.getField("weight");
        ioService.getField("length");
        ioService.getField("width");
        ioService.getField("height");
        ioService.getField("color");

        delayService.introduceDelay();
    }

    private void checkStoragePlaceAvailability() {
        if (warehouse.getAvailableStoragePlace() == 0) {
            ioService.displayError("NO STORAGE PLACE IS AVAILABLE AT THIS MOMENT!");
            delayService.introduceDelay();
            start();
        }
    }


    private void issueTicket(int assignedStoragePlaceId) {
        String uniqueCode = generateCode();
        assignStoragePlaceUniqueCode(uniqueCode, assignedStoragePlaceId);
        String localDateTime = assignLocalDateTime(assignedStoragePlaceId);
        ioService.displayInfo("Issue Ticket: ");
        ioService.displayInfo("Storage place id assigned: " + assignedStoragePlaceId);
        ioService.displayInfo("Unique code assigned: " + uniqueCode);
        ioService.displayInfo("Time in: " + localDateTime);
        ioService.displayInfo("Info phone number: 0232/209880 \n");
    }

    private boolean confirmClosureStoragePlace() {
        String userConfirmation = ioService.getUserConfirmation();
        switch (userConfirmation) {
            case "yes": {
                ioService.displayInfo("Closure confirmed!\n");
                return true;
            }
            case "cancel":
                start();
                return false;
            default:
                ioService.displayError(userConfirmation + " is not a valid option!");
                delayService.introduceDelay();
                return false;
        }
    }

    private String assignLocalDateTime(int assignedStoragePlaceId) {
        String localDateTime = "";
        for(Map.Entry <Integer, String> entry : idLocalDateTimeMap.entrySet()) {
            if (entry.getKey().equals(assignedStoragePlaceId)) {
                idLocalDateTimeMap.replace(assignedStoragePlaceId, LocalDateTime.now().toString());
                localDateTime = entry.getValue();
                break;
            }
        }
        return localDateTime;
    }

    private void assignStoragePlaceUniqueCode(String uniqueCode, int assignedStoragePlaceId) {
        for(Map.Entry <Integer, String> entry : idCodeMap.entrySet()) {
            if (entry.getKey().equals(assignedStoragePlaceId)) {
                idCodeMap.replace(assignedStoragePlaceId, uniqueCode);
            }
        }
    }

    private int assignStoragePlaceId() {
        int assignedStoragePlaceId = 0;

        for(Map.Entry <Integer, String> entry : idAvailabilityMap.entrySet()) {
            if (entry.getValue().equals("available")) {
                assignedStoragePlaceId = entry.getKey();
                break;
            }
        }
        return assignedStoragePlaceId;
    }


    private String generateCode() {
        StringBuilder uniqueCode = new StringBuilder();
        Random random = new Random();
        for(int index = 0; index < 4; index++) {
            int randomCod = random.nextInt(10);
            char c = (char) (random.nextInt(26) + 'A');
            uniqueCode.append(randomCod);
            uniqueCode.append(c);
        }
        return uniqueCode.toString();
    }

    public void load() {
        int totalStoragePlaces = initialLoad();
        warehouseAvailabilityInitialSet(totalStoragePlaces);
        warehouseCodeInitialSet(totalStoragePlaces);
        warehouseLocalDateTimeInitialSet(totalStoragePlaces);
    }

    private void warehouseLocalDateTimeInitialSet(int totalStoragePlaces) {
        for(int index = 1; index <= totalStoragePlaces; index++) {
            storagePlace.setId(index);
            String localDateTime = "";
            idLocalDateTimeMap.put(storagePlace.getId(), localDateTime);
        }
        System.out.println(idLocalDateTimeMap);
    }

    private void warehouseCodeInitialSet(int totalStoragePlaces) {
        for(int index = 1; index <= totalStoragePlaces; index++) {
            storagePlace.setId(index);
            String code = "";
            idCodeMap.put(storagePlace.getId(), code);
        }
        System.out.println(idCodeMap);
    }

    private void warehouseAvailabilityInitialSet(int totalStoragePlaces) {
        for(int index = 1; index <= totalStoragePlaces; index++) {
            storagePlace.setId(index);
            String avaialble = "available";
            idAvailabilityMap.put(storagePlace.getId(), avaialble);
        }
        System.out.println(idAvailabilityMap);
    }

    private int initialLoad() {
        warehouse.setTotalStoragePlace(4);
        warehouse.setOccupiedStoragePlace(0);
        warehouse.setAvailableStoragePlace(warehouse.getTotalStoragePlace() - warehouse.getOccupiedStoragePlace());
        return warehouse.getTotalStoragePlace();
    }
}
