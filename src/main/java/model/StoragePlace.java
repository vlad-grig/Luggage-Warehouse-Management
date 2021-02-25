package model;

import java.time.LocalDateTime;

public class StoragePlace {

    private int id;
    private LocalDateTime startTimer;
    private LocalDateTime endTimer;
    private Luggage luggage;

    public StoragePlace(int id, LocalDateTime startTimer, LocalDateTime endTimer, Luggage luggage) {
        this.id = id;
        this.startTimer = startTimer;
        this.endTimer = endTimer;
        this.luggage = luggage;
    }

    public StoragePlace() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Luggage getLuggage() {
        return luggage;
    }

    public void setLuggage(Luggage luggage) {
        this.luggage = luggage;
    }
}
