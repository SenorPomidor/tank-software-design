package ru.mipt.bit.platformer.mapper.interfaces;

import org.awesome.ai.state.movable.Player;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;

public interface PlayerEntityMapper {

    Player mapToPlayer(PlayerEntity entity);
}
