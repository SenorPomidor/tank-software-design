package ru.mipt.bit.platformer.entity.enums;

public enum Filed {

    LEVEL_WIDTH(10),
    LEVEL_HEIGHT(6);

    private final int field;

    Filed(int field) {
        this.field = field;
    }

    public int getField() {
        return field;
    }
}
