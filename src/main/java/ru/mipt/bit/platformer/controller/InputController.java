package ru.mipt.bit.platformer.controller;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.enums.Direction;

import java.util.HashMap;
import java.util.Map;

public class InputController {
    private final Map<Integer, Direction> keyToDirectionMap = new HashMap<>();

    public Direction getDirection() {
        for (Integer key : keyToDirectionMap.keySet()) {
            if (Gdx.input.isKeyPressed(key)) {
                return keyToDirectionMap.get(key);
            }
        }
        return null;
    }

    public void initKeyBoardMappings() {
        for (Direction direction : Direction.values()) {
            for (int key : direction.getKeys()) {
                keyToDirectionMap.put(key, direction);
            }
        }
    }
}