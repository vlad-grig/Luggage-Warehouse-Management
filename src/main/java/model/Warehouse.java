package model;

public class Warehouse {

    private StoragePlace storagePlace;

    public Warehouse(StoragePlace storagePlace) {
        this.storagePlace = storagePlace;
    }

    public StoragePlace getStoragePlace() {
        return storagePlace;
    }

    public void setStoragePlace(StoragePlace storagePlace) {
        this.storagePlace = storagePlace;
    }
}
