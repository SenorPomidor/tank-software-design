package ru.mipt.bit.platformer.enums;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.GridPoint2;

public enum Direction {

    UP(new GridPoint2(0, 1), 90f, Keys.UP, Keys.W),
    DOWN(new GridPoint2(0, -1), -90f, Keys.DOWN, Keys.D),
    LEFT(new GridPoint2(-1, 0), 180f, Keys.LEFT, Keys.A),
    RIGHT(new GridPoint2(1, 0), 0f, Keys.RIGHT, Keys.D);

    private final float rotation;
    private final GridPoint2 coordinates;
    private final int[] keys;

    Direction(GridPoint2 coordinates, float rotation, int... keys) {
        this.coordinates = coordinates;
        this.rotation = rotation;
        this.keys = keys;
    }

    public int[] getKeys() {
        return keys;
    }

    public GridPoint2 apply(GridPoint2 point) {
        return point.cpy().add(coordinates);
    }

    public float getRotation() {
        return rotation;
    }

}
