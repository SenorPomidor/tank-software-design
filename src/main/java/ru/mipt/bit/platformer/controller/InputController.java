package ru.mipt.bit.platformer.controller;

import ru.mipt.bit.platformer.adapter.AIAdapter;
import ru.mipt.bit.platformer.controller.interfaces.Action;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;

import java.util.*;

import static ru.mipt.bit.platformer.common.CommonVariables.RANDOM_ACTION_PERCENTAGE;

public class InputController {

    private final List<Action> actions = new ArrayList<>();
    private final Random random = new Random();

    private AIAdapter aiAdapter;

    public void setAIAdapter(AIAdapter aiAdapter) {
        this.aiAdapter = aiAdapter;
    }

    public void checkIsTriggeredKeyAndExecuteCommand(List<PlayerEntity> playerEntities, List<GameEntity> gameEntities, float deltaTime) {
        for (PlayerEntity playerEntity : playerEntities) {
            for (Action action : actions) {
                if (action.isTriggered()) {
                    action.execute(playerEntity, gameEntities, deltaTime);
                }
            }
        }
    }

    public void randomIsTriggeredKeyAndExecuteCommand(List<PlayerEntity> playerBotEntities, List<GameEntity> gameEntities, float deltaTime) {
        for (PlayerEntity playerEntity : playerBotEntities) {
            for (Action action : actions) {
                if (random.nextInt(RANDOM_ACTION_PERCENTAGE) == 0) {
                    action.execute(playerEntity, gameEntities, deltaTime);
                }
            }
        }
    }

    public void initActions() {
        actions.addAll(Arrays.asList(DirectionKeyBoardAction.values()));
        actions.addAll(Arrays.asList(ShootingAction.values()));
    }

    public List<Action> getActions() {
        return actions;
    }
}