package org.project.enums;

public enum ProjectStatusEnum {
    START(1),
    FINISHED(2);

    private final int value;

    ProjectStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}