package ru.mipt.bit.platformer.controller.interfaces;

import ru.mipt.bit.platformer.entity.interfces.GameEntity;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;

import java.util.List;

public interface DirectionKeyBoardAction extends Action {

    void execute(PlayerEntity playerEntity, List<GameEntity> gameEntities, float deltaTime);

}
