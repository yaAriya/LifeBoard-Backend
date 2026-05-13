package entity;

import enums.DeviceStatus;
import enums.DeviceType;

import java.time.LocalDateTime;
import java.util.Objects;

public class Device {
    private long id;
    private String name;
    private DeviceType type;
    private DeviceStatus status;
    private LocalDateTime lastActive;
    private User user;

    public Device() {
    }

    public Device(long id, String name, DeviceType type, DeviceStatus status, LocalDateTime lastActive, User user) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.lastActive = lastActive;
        this.user = user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || !(obj instanceof Device)) {
            return false;
        }
        Device device = (Device) obj;
        return device.id == id;
    }

    @Override
    public String toString() {
        return name + "," + name + "," + type + "," + status + "," + lastActive + "," + user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }

    public LocalDateTime getLastActive() {
        return lastActive;
    }

    public void setLastActive(LocalDateTime lastActive) {
        this.lastActive = lastActive;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
