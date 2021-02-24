package util;

import model.StoragePlace;
import service.DelayService;
import service.IOService;
import service.ValidatorService;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class LuggageWarehouseManagementApp {

    private IOService ioService;
    private StoragePlace storagePlace;
    private ValidatorService validatorService;
    private Map <Integer, String> idAvailabilityMap;
    private Map <Integer, String> idCodeMap;
    private Map <Integer, String> idLocalDateTimeMap;
    private DelayService delayService;

    public LuggageWarehouseManagementApp() {
        this.ioService = new IOService();
        this.storagePlace = new StoragePlace();
        this.validatorService = new ValidatorService(ioService);
        this.idAvailabilityMap = new TreeMap <>();
        this.idCodeMap = new TreeMap <>();
        this.idLocalDateTimeMap = new TreeMap <>();
        this.delayService = new DelayService();
    }


    public void start() {
        while (true) {
            ioService.displayInfo("........................................................................");
            ioService.displayMenu(storagePlace);
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
                    unlockedStoragePlace();
                    start();
                    break;
                }
            }
        }
    }

    private void unlockedStoragePlace() {
        ioService.displayInfo("Please type the unique code: ");
        String userInputUniqueCode = ioService.getUserInput();
        boolean validateUniqueCode = validatorService.validateUniqueCode(userInputUniqueCode, idCodeMap);
        if (!validateUniqueCode) {
            start();
        }
        calculateTimeInMinute(userInputUniqueCode);

        ioService.displayInfo("inseamna ca s-a validat codul unic.");
        ioService.displayInfo("putem calcula timpul");
        ioService.displayInfo("putem calcula pretul");
        ioService.displayInfo("putem cere plata");
        ioService.displayInfo("putem incasa plata");
        ioService.displayInfo("putem afisa datele bagajului");
        ioService.displayInfo("putem elibera bagajul");
        ioService.displayInfo("putem actualiza datele din stoc disponibile, cod, si data");
        delayService.introduceDelay();
    }

    private void calculateTimeInMinute(String userInputUniqueCode) {
        ioService.displayInfo("calculez timpul");
    }

    private void accessStoragePlace() {
        if (storagePlace.getAvailableStoragePlace() == 0) {
            ioService.displayError("NO STORAGE PLACE IS AVAILABLE AT THIS MOMENT!");
            delayService.introduceDelay();
            start();
        }
        int assignedStoragePlaceId = assignStoragePlaceId();

        ioService.displayInfo("Acum ii poti lua date despre bagaj");
        delayService.introduceDelay();

        ioService.displayMessage(assignedStoragePlaceId);

        boolean confirmClosureStoragePlace = confirmClosureStoragePlace();
        if (!confirmClosureStoragePlace) {
            start();
        }
        idAvailabilityMap.replace(assignedStoragePlaceId, "unavailable");
        updateStoragePlacesAvailability();
        issueTicket(assignedStoragePlaceId);
        System.out.println(idAvailabilityMap);
        System.out.println(idCodeMap);
        System.out.println(idLocalDateTimeMap);
    }

    private void updateStoragePlacesAvailability() {
        int availableStoragePlace = storagePlace.getAvailableStoragePlace();
        availableStoragePlace -= 1;
        int occupiedStoragePlace = storagePlace.getOccupiedStoragePlace();
        occupiedStoragePlace += 1;
        storagePlace.setAvailableStoragePlace(availableStoragePlace);
        storagePlace.setOccupiedStoragePlace(occupiedStoragePlace);
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
                ioService.displayError(userConfirmation);
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
//            if (entry.getKey() == 1) {
//                String value = entry.getValue();
//                if (value.equals("available")) {
//                    idAvailabilityMap.replace(1, "unavailable");
//                }
//            }
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
        storagePlace.setTotalStoragePlace(4);
        storagePlace.setOccupiedStoragePlace(0);
        storagePlace.setAvailableStoragePlace(storagePlace.getTotalStoragePlace() - storagePlace.getOccupiedStoragePlace());
        return storagePlace.getTotalStoragePlace();
    }
}
