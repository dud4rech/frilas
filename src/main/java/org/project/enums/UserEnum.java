package org.project.enums;

public enum UserEnum {
    FREELANCER(1),
    HIRER(2);

    private final int value;

    UserEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
