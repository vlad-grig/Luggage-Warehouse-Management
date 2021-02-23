package util;

import model.StoragePlace;
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

    public LuggageWarehouseManagementApp() {
        this.ioService = new IOService();
        this.storagePlace = new StoragePlace();
        this.validatorService = new ValidatorService(ioService);
        this.idAvailabilityMap = new TreeMap <>();
        this.idCodeMap = new TreeMap <>();
        this.idLocalDateTimeMap = new TreeMap <>();
    }


    public void start() {
        while (true) {
            ioService.displayMenu(storagePlace);
            String userInput = ioService.getUserInput();
            processUserInput(userInput);
            break;
        }
    }

    private void processUserInput(String userInput) {
        boolean successfulValidation = validatorService.validateUserInput(userInput);
        if (!successfulValidation) {
            ioService.displayInfo("........................................................................");
            start();
        } else {
            switch (userInput) {
                case "1": {
                    if (storagePlace.getAvailableStoragePlace() == 0 ){
                        ioService.displayError("No storage place is available at the moment!\n");
                        start();
                    }
                    int assignedStoragePlaceId = assignStoragePlaceId();
                    ioService.displayMessage(assignedStoragePlaceId);
                    if(confirmClosureStoragePlace()){
                        int availableStoragePlace = storagePlace.getAvailableStoragePlace();
                        availableStoragePlace -= 1;
                        storagePlace.setAvailableStoragePlace(availableStoragePlace);
                    }
                    //vezi daca e nevoie sa actualizezi si locuri totale sau locuri ocupate
                    //daca se razagandeste sa iasa din app si sa se reia menu
                    //daca da sa se actualizeze numarul de locuri disponibile dupa accesare - REZOLVAT
                    //daca nu mai sunt locuri disponibile trebuie sa iesi din metoda - REZOLVAT
                    //nu uita sa actualizezi si dupa eliberarea locului
                    String uniqueCode = generateCode();
                    assignStoragePlaceUniqueCode(uniqueCode, assignedStoragePlaceId);
                    String localDateTime = assignLocalDateTime(assignedStoragePlaceId);
                    issueTicket(assignedStoragePlaceId, uniqueCode, localDateTime);


                    System.out.println(idAvailabilityMap);
                    System.out.println(idCodeMap);
                    System.out.println(idLocalDateTimeMap);
                    ioService.displayInfo("........................................................................");
                    start();
                }
                case "2": {
                    System.out.println("inseamna ca vrea sa ridice bagajul!");
                    ioService.displayInfo("........................................................................");
                    start();
                }
            }
        }
    }

    private void issueTicket(int assignedStoragePlaceId, String uniqueCode, String localDateTime) {
        ioService.displayInfo("Here is your ticket: ");
        ioService.displayInfo("Storage place id assigned: " + assignedStoragePlaceId);
        ioService.displayInfo("Unique code assigned: " + uniqueCode);
        ioService.displayInfo("Time in: " + localDateTime);
        ioService.displayInfo("Info phone number: 0232/209880 \n");
    }

    private boolean confirmClosureStoragePlace() {
        String userConfirmation = ioService.getUserConfirmation();
        if (!userConfirmation.equals("yes")) {
            ioService.displayError(userConfirmation);
            confirmClosureStoragePlace();
            return false;
        } else ioService.displayInfo("Closure confirmed!\n");
        return true;
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
                idAvailabilityMap.replace(assignedStoragePlaceId, "unavailable");
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
//        System.out.println(idLocalDateTimeMap);
    }

    private void warehouseCodeInitialSet(int totalStoragePlaces) {
        for(int index = 1; index <= totalStoragePlaces; index++) {
            storagePlace.setId(index);
            String code = "";
            idCodeMap.put(storagePlace.getId(), code);
        }
//        System.out.println(idCodeMap);
    }

    private void warehouseAvailabilityInitialSet(int totalStoragePlaces) {
        for(int index = 1; index <= totalStoragePlaces; index++) {
            storagePlace.setId(index);
            String avaialble = "available";
            idAvailabilityMap.put(storagePlace.getId(), avaialble);
        }
//        System.out.println(idAvailabilityMap);
    }

    private int initialLoad() {
        storagePlace.setTotalStoragePlace(3);
        storagePlace.setOccupiedStoragePlace(0);
        storagePlace.setAvailableStoragePlace(storagePlace.getTotalStoragePlace() - storagePlace.getOccupiedStoragePlace());
        if (storagePlace.getAvailableStoragePlace() == 0) {
            ioService.displayInfo("Sorry, but at the moment all storage places are occupied!");
        }
        return storagePlace.getTotalStoragePlace();
    }
}
