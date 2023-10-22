package ru.mipt.bit.platformer.mapper.interfaces;

import org.awesome.ai.state.movable.Bot;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;

public interface BotEntityMapper {

    Bot mapToBot(PlayerEntity entity);
}
