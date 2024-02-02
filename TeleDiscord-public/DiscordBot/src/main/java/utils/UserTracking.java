package utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class UserTracking {
    private String username;
    private String id;
    private long timeAccumulated;
    private LocalDateTime timeJoined;

    public UserTracking(String username, String id, LocalDateTime timeJoined){
        this.username = username;
        this.id = id;
        this.timeJoined = timeJoined;
        this.timeAccumulated = 0;
    }

    public void sumTimeAccumulated(){
        this.timeAccumulated += Duration.between(this.timeJoined, LocalDateTime.now()).toSeconds();
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public long getTimeAccumulated() {
        return timeAccumulated;
    }

    public void setTimeAccumulated(long timeAccumulated) {
        this.timeAccumulated = timeAccumulated;
    }

    public LocalDateTime getTimeJoined() {
        return timeJoined;
    }

    public void setTimeJoined(LocalDateTime timeJoined) {
        this.timeJoined = timeJoined;
    }
}
