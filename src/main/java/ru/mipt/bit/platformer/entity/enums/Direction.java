package ru.mipt.bit.platformer.entity.enums;

public enum Direction {
    UP(90f, 0, 1),
    DOWN(-90f, 0, -1),
    LEFT(-180f, -1, 0),
    RIGHT(0f, 1, 0);

    public final float rotation;
    public final int dx;
    public final int dy;

    Direction(float rotation, int dx, int dy) {
        this.rotation = rotation;
        this.dx = dx;
        this.dy = dy;
    }
}

