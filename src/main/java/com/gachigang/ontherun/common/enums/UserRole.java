package com.gachigang.ontherun.common.enums;

/**
 * Roles for the User class
 */
public enum UserRole {
    STAFF,
    MANAGER,
    USER;

    public String getRole() {
        return "ROLE_" + name();
    }
}
