package ru.mipt.bit.platformer.controller;

import ru.mipt.bit.platformer.controller.interfaces.Action;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;

import java.util.*;

public class InputController {

    private final List<Action> actions = new ArrayList<>();

    public void checkIsTriggeredKeyAndExecuteCommand(PlayerEntity playerEntity, List<GameEntity> gameEntities, float deltaTime) {
        for (Action action : actions) {
            if (action.isTriggered()) {
                action.execute(playerEntity, gameEntities, deltaTime);
            }
        }
    }

    public void initActions() {
        actions.addAll(Arrays.asList(DirectionKeyBoardAction.values()));
        actions.addAll(Arrays.asList(ShootingAction.values()));
    }
}