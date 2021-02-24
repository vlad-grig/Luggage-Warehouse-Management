package model;

import java.time.LocalDateTime;

public class StoragePlace {

    private int id;
    private LocalDateTime startTimer;
    private LocalDateTime endTimer;

    public StoragePlace(int id, LocalDateTime startTimer, LocalDateTime endTimer) {
        this.id = id;
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
