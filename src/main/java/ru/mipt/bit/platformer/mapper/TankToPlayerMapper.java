package ru.mipt.bit.platformer.mapper;

import org.awesome.ai.state.movable.Orientation;
import org.awesome.ai.state.movable.Player;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;
import ru.mipt.bit.platformer.mapper.interfaces.PlayerEntityMapper;

public class TankToPlayerMapper implements PlayerEntityMapper {

    @Override
    public Player mapToPlayer(PlayerEntity tank) {
        return Player.builder()
                .source(tank)
                .x(tank.getCoordinates().x)
                .y(tank.getCoordinates().y)
                .destX(tank.getDestinationCoordinates().x)
                .destY(tank.getDestinationCoordinates().y)
                .orientation(mapToOrientation(tank.getRotation()))
                .build();
    }

    private Orientation mapToOrientation(float rotation) {
        if (rotation == 90f) return Orientation.N;
        if (rotation == -90f) return Orientation.S;
        if (rotation == 180f) return Orientation.W;
        return Orientation.E;
    }
}

