package model;

import java.time.LocalDateTime;

public class StoragePlace {

    private int id;
    private int totalStoragePlace;
    private int availableStoragePlace;
    private int occupiedStoragePlace;
    private LocalDateTime startTimer;
    private LocalDateTime endTimer;

    public StoragePlace(int id, int totalStoragePlace, int availableStoragePlace, int occupiedStoragePlace, LocalDateTime startTimer, LocalDateTime endTimer) {
        this.id = id;
        this.totalStoragePlace = totalStoragePlace;
        this.availableStoragePlace = availableStoragePlace;
        this.occupiedStoragePlace = occupiedStoragePlace;
        this.startTimer = startTimer;
        this.endTimer = endTimer;
    }

    public StoragePlace() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDateTime getStartTimer() {
        return startTimer;
    }

    public void setStartTimer(LocalDateTime startTimer) {
        this.startTimer = startTimer;
    }

    public LocalDateTime getEndTimer() {
        return endTimer;
    }

    public void setEndTimer(LocalDateTime endTimer) {
        this.endTimer = endTimer;
    }
}
