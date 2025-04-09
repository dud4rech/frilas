package org.project.enums;

public enum ProposalStatusEnum {
    CREATED(1),
    ACCEPTED(2);

    private final int value;

    ProposalStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
