package ru.mipt.bit.platformer.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.controller.interfaces.Action;
import ru.mipt.bit.platformer.controller.interfaces.ActionVisitor;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;

import java.util.Arrays;
import java.util.List;

public enum DirectionKeyBoardAction implements Action {

    UP(new GridPoint2(0, 1), 90f, Keys.UP, Keys.W),
    DOWN(new GridPoint2(0, -1), -90f, Keys.DOWN, Keys.S),
    LEFT(new GridPoint2(-1, 0), 180f, Keys.LEFT, Keys.A),
    RIGHT(new GridPoint2(1, 0), 0f, Keys.RIGHT, Keys.D);

    private final float rotation;
    private final GridPoint2 coordinates;
    private final Integer[] keys;

    DirectionKeyBoardAction(GridPoint2 coordinates, float rotation, Integer... keys) {
        this.coordinates = coordinates;
        this.rotation = rotation;
        this.keys = keys;
    }

    public GridPoint2 apply(GridPoint2 point) {
        return point.cpy().add(coordinates);
    }

    public float getRotation() {
        return rotation;
    }

    @Override
    public boolean isTriggered() {
        return Arrays.stream(keys).anyMatch(key -> Gdx.input.isKeyPressed(key));
    }

    @Override
    public void execute(PlayerEntity currentPlayerEntity, List<GameEntity> gameEntities, float deltaTime) {
        currentPlayerEntity.updatePlayerMovement(this, gameEntities, deltaTime);
    }

    @Override
    public org.awesome.ai.Action accept(ActionVisitor visitor) {
        return visitor.visit(this);
    }

}
