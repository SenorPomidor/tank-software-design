package ru.mipt.bit.platformer.controller;

import ru.mipt.bit.platformer.controller.impl.DirectionKeyBoardActionImpl;
import ru.mipt.bit.platformer.controller.impl.ShootingActionImpl;
import ru.mipt.bit.platformer.controller.interfaces.DirectionKeyBoardAction;
import ru.mipt.bit.platformer.controller.interfaces.ShootingAction;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;

import java.util.*;

public class InputController {

    private final List<DirectionKeyBoardAction> directionKeyBoardActions = new ArrayList<>();
    private final List<ShootingAction> shootingActions = new ArrayList<>();

    public void executeTriggeredDirection(PlayerEntity playerEntity, List<GameEntity> gameEntities, float deltaTime) {
        for (DirectionKeyBoardAction action : directionKeyBoardActions) {
            if (action.isTriggered()) {
                action.execute(playerEntity, gameEntities, deltaTime);
            }
        }
    }

    public void executeTriggeredShooting(PlayerEntity playerEntity) {
        for (ShootingAction action : shootingActions) {
            if (action.isTriggered()) {
                action.execute(playerEntity);;
            }
        }
    }

    public void initActions() {
        directionKeyBoardActions.addAll(Arrays.asList(DirectionKeyBoardActionImpl.values()));
        shootingActions.addAll(Arrays.asList(ShootingActionImpl.values()));
    }
}