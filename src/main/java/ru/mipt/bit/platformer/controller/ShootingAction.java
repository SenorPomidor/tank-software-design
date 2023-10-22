package ru.mipt.bit.platformer.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import ru.mipt.bit.platformer.controller.interfaces.Action;
import ru.mipt.bit.platformer.controller.interfaces.ActionVisitor;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;

import java.util.Arrays;
import java.util.List;

public enum ShootingAction implements Action {

    SHOOT(Keys.SPACE);

    private final Integer[] keys;

    ShootingAction(Integer... keys) {
        this.keys = keys;
    }

    @Override
    public boolean isTriggered() {
        return Arrays.stream(keys).anyMatch(key -> Gdx.input.isKeyJustPressed(key));
    }

    @Override
    public void execute(PlayerEntity playerEntity, List<GameEntity> gameEntities, float deltaTime) {
        playerEntity.shoot(this);
    }

    @Override
    public org.awesome.ai.Action accept(ActionVisitor visitor) {
        return null;
    }
}
