package ru.mipt.bit.platformer.mapper;

import org.awesome.ai.state.movable.Bot;
import org.awesome.ai.state.movable.Orientation;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;
import ru.mipt.bit.platformer.mapper.interfaces.BotEntityMapper;

public class TankToBotMapper implements BotEntityMapper {

    @Override
    public Bot mapToBot(PlayerEntity botTank) {
        return Bot.builder()
                .source(botTank)
                .x(botTank.getCoordinates().x)
                .y(botTank.getCoordinates().y)
                .destX(botTank.getDestinationCoordinates().x)
                .destY(botTank.getDestinationCoordinates().y)
                .orientation(mapToOrientation(botTank.getRotation()))
                .build();
    }

    private Orientation mapToOrientation(float rotation) {
        if (rotation == 90f) return Orientation.N;
        if (rotation == -90f) return Orientation.S;
        if (rotation == 180f) return Orientation.W;
        return Orientation.E;
    }
}
