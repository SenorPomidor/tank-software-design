package ru.mipt.bit.platformer.adapter;

import org.awesome.ai.AI;
import org.awesome.ai.Recommendation;
import org.awesome.ai.state.GameState;
import org.awesome.ai.state.movable.Actor;
import org.awesome.ai.state.movable.Orientation;
import org.awesome.ai.state.movable.Player;
import ru.mipt.bit.platformer.controller.DirectionKeyBoardAction;
import ru.mipt.bit.platformer.controller.InputController;
import ru.mipt.bit.platformer.controller.interfaces.Action;
import ru.mipt.bit.platformer.controller.interfaces.ActionVisitor;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class AIAdapter implements AI, ActionVisitor {

    private final InputController inputController;
    private final List<PlayerEntity> playerEntities;

    public AIAdapter(InputController inputController, List<PlayerEntity> playerEntities) {
        this.inputController = inputController;
        this.playerEntities = playerEntities;
    }

    @Override
    public List<Recommendation> recommend(GameState gameState) {
        List<Recommendation> recommendations = new ArrayList<>();

        for (PlayerEntity playerEntity : playerEntities) {
            for (Action action : inputController.getActions()) {
                if (action.isTriggered()) {
                    Actor actor = convertToActor(playerEntity);
                    Recommendation recommendation = new Recommendation(actor, action.accept(this));
                    recommendations.add(recommendation);
                }
            }
        }

        return recommendations;
    }


    private Actor convertToActor(PlayerEntity playerEntity) {
        return Player.builder()
                .source(playerEntity)
                .x(playerEntity.getCoordinates().x)
                .y(playerEntity.getCoordinates().y)
                .destX(playerEntity.getDestinationCoordinates().x)
                .destY(playerEntity.getDestinationCoordinates().y)
                .orientation(convertToOrientation(playerEntity.getRotation()))
                .build();
    }

    @Override
    public org.awesome.ai.Action visit(DirectionKeyBoardAction directionAction) {
        switch (directionAction) {
            case UP:
                return org.awesome.ai.Action.MoveNorth;
            case DOWN:
                return org.awesome.ai.Action.MoveSouth;
            case LEFT:
                return org.awesome.ai.Action.MoveWest;
            case RIGHT:
                return org.awesome.ai.Action.MoveEast;
            default:
                throw new IllegalArgumentException("Unknown action type");
        }
    }

    private Orientation convertToOrientation(float rotation) {
        if (rotation == 90f) return Orientation.N;
        if (rotation == -90f) return Orientation.S;
        if (rotation == 180f) return Orientation.W;
        return Orientation.E;
    }

}

