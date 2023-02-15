package com.gachigang.ontherun.common.enums;

public enum UserRole {
    STAFF,
    MANAGER,
    USER;

    public String getRole() {
        return "ROLE_" + name();
    }
}
