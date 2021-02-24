package service;

import model.StoragePlace;
import model.Warehouse;

public class UpdateService {

    private Warehouse warehouse;

    public UpdateService(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void updateDownAvailability() {
        int availableStoragePlace = warehouse.getAvailableStoragePlace();
        availableStoragePlace--;
        int occupiedStoragePlace = warehouse.getOccupiedStoragePlace();
        occupiedStoragePlace++;
        warehouse.setAvailableStoragePlace(availableStoragePlace);
        warehouse.setOccupiedStoragePlace(occupiedStoragePlace);
    }

    public void updateUpAvailability() {
        int availableStoragePlace = warehouse.getAvailableStoragePlace();
        availableStoragePlace++;
        int occupiedStoragePlace = warehouse.getOccupiedStoragePlace();
        occupiedStoragePlace--;
        warehouse.setAvailableStoragePlace(availableStoragePlace);
        warehouse.setOccupiedStoragePlace(occupiedStoragePlace);
    }
}
