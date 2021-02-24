package model;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private int totalStoragePlace;
    private int availableStoragePlace;
    private int occupiedStoragePlace;
    List<StoragePlace> storagePlaceList = new ArrayList <>();

    public Warehouse(int totalStoragePlace, int availableStoragePlace, int occupiedStoragePlace, List <StoragePlace> storagePlaceList) {
        this.totalStoragePlace = totalStoragePlace;
        this.availableStoragePlace = availableStoragePlace;
        this.occupiedStoragePlace = occupiedStoragePlace;
        this.storagePlaceList = storagePlaceList;
    }

    public Warehouse() {
    }

    public int getTotalStoragePlace() {
        return totalStoragePlace;
    }

    public void setTotalStoragePlace(int totalStoragePlace) {
        this.totalStoragePlace = totalStoragePlace;
    }

    public int getAvailableStoragePlace() {
        return availableStoragePlace;
    }

    public void setAvailableStoragePlace(int availableStoragePlace) {
        this.availableStoragePlace = availableStoragePlace;
    }

    public int getOccupiedStoragePlace() {
        return occupiedStoragePlace;
    }

    public void setOccupiedStoragePlace(int occupiedStoragePlace) {
        this.occupiedStoragePlace = occupiedStoragePlace;
    }

    public List <StoragePlace> getStoragePlaceList() {
        return storagePlaceList;
    }

    public void setStoragePlaceList(List <StoragePlace> storagePlaceList) {
        this.storagePlaceList = storagePlaceList;
    }
}
