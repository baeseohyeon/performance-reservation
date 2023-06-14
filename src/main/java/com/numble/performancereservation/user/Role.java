package com.numble.performancereservation.user;

public enum Role {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");
    final String value;

    public String value() {
        return value;
    }

    Role(String value) {
        this.value = value;
    }
}
