package ru.mipt.bit.platformer.controller.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import ru.mipt.bit.platformer.controller.interfaces.ShootingAction;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;

import java.util.Arrays;

public enum ShootingActionImpl implements ShootingAction {

    SHOOT(Keys.SPACE);

    private final Integer[] keys;

    ShootingActionImpl(Integer... keys) {
        this.keys = keys;
    }

    @Override
    public boolean isTriggered() {
        return Arrays.stream(keys).anyMatch(key -> Gdx.input.isKeyJustPressed(key));
    }

    @Override
    public void execute(PlayerEntity playerEntity) {
        playerEntity.shoot(this);
    }
}
