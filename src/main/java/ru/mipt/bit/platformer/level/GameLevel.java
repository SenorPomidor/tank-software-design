package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.controller.InputController;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;
import ru.mipt.bit.platformer.entity.interfces.ObstacleEntity;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;
import ru.mipt.bit.platformer.renderable.impl.ObstacleRenderableImpl;
import ru.mipt.bit.platformer.renderable.impl.PlayerRenderableImpl;
import ru.mipt.bit.platformer.renderable.interfaces.ObstacleRenderable;
import ru.mipt.bit.platformer.renderable.interfaces.PlayerRenderable;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public class GameLevel {

    private static final float MOVEMENT_SPEED = 0.4f;

    private static final String TANK_IMAGE = "images/tank_blue.png";
    private static final String TREE_IMAGE = "images/greenTree.png";

    private final TiledMap level;
    private final TileMovement tileMovement;
    private final TiledMapTileLayer groundLayer;
    private final InputController inputController;

    private final List<GameEntity> gameEntities = new ArrayList<>();

    private final List<PlayerEntity> playerEntities = new ArrayList<>();
    private final List<ObstacleEntity> obstacleEntities = new ArrayList<>();

    public GameLevel(InputController inputController) {
        this.level = new TmxMapLoader().load("level.tmx");
        this.groundLayer = getSingleLayer(level);
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
        this.inputController = inputController;
    }

    public PlayerRenderable addPlayer(PlayerEntity playerEntity) {
        PlayerRenderable playerGraphics = new PlayerRenderableImpl(TANK_IMAGE, playerEntity);

        playerEntities.add(playerEntity);
        gameEntities.add(playerEntity);

        return playerGraphics;
    }

    public ObstacleRenderable addObstacle(ObstacleEntity obstacle) {
        ObstacleRenderable obstacleGraphics = new ObstacleRenderableImpl(TREE_IMAGE, obstacle);
        obstacleGraphics.moveObstacle(groundLayer);

        obstacleEntities.add(obstacle);
        gameEntities.add(obstacle);

        return obstacleGraphics;
    }

    public void checkIsTriggeredKeyAndExecuteCommand(float deltaTime) {
        for (PlayerEntity playerEntity : playerEntities) {
            inputController.checkIsTriggeredKeyAndExecuteCommand(playerEntity, gameEntities, deltaTime);
        }
    }

    public void updateGameState(float deltaTime) {
        for (PlayerEntity playerEntity : playerEntities) {
            playerEntity.updateState(continueProgress(playerEntity.getMovementProgress(), deltaTime, MOVEMENT_SPEED));
        }
    }

    public TiledMap getLevel() {
        return level;
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }
}
