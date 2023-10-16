package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;
import ru.mipt.bit.platformer.entity.interfces.ObstacleEntity;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;
import ru.mipt.bit.platformer.level.interfaces.GameObjectListener;
import ru.mipt.bit.platformer.level.strategy.TankCreationStrategy;
import ru.mipt.bit.platformer.level.strategy.TreeCreationStrategy;
import ru.mipt.bit.platformer.level.strategy.interfaces.EntityCreationStrategy;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.common.CommonVariables.TANK_IMAGE;
import static ru.mipt.bit.platformer.common.CommonVariables.TREE_IMAGE;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public class GameLevel {

    private static final float MOVEMENT_SPEED = 0.4f;

    private final TiledMap level;
    private final TileMovement tileMovement;
    private final TiledMapTileLayer groundLayer;

    private final List<GameEntity> gameEntities = new ArrayList<>();

    private final List<PlayerEntity> playerEntities = new ArrayList<>();
    private final List<ObstacleEntity> obstacleEntities = new ArrayList<>();

    private final List<GameObjectListener> listeners = new ArrayList<>();

    public GameLevel() {
        this.level = new TmxMapLoader().load("level.tmx");
        this.groundLayer = getSingleLayer(level);
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
    }

    public void createTank(GridPoint2 coordinates) {
        PlayerEntity player = createPlayer(coordinates, new TankCreationStrategy());
        notifyListeners(player, TANK_IMAGE);
    }

    public void createTree(GridPoint2 coordinates) {
        ObstacleEntity obstacle = createObstacle(coordinates, new TreeCreationStrategy());
        notifyListeners(obstacle, TREE_IMAGE);
    }

    public void updateGameState(float deltaTime) {
        for (PlayerEntity playerEntity : playerEntities) {
            playerEntity.updateState(continueProgress(playerEntity.getMovementProgress(), deltaTime, MOVEMENT_SPEED));
        }
    }

    public void addListener(GameObjectListener listener) {
        listeners.add(listener);
    }

    public TiledMap getTiledMap() {
        return level;
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }

    public TiledMapTileLayer getGroundLayer() {
        return groundLayer;
    }

    public List<PlayerEntity> getPlayerEntities() {
        return playerEntities;
    }

    public List<GameEntity> getGameEntities() {
        return gameEntities;
    }

    private PlayerEntity createPlayer(GridPoint2 coordinates, EntityCreationStrategy<PlayerEntity> entityStrategy) {
        PlayerEntity playerEntity = entityStrategy.createEntity(coordinates);

        playerEntities.add(playerEntity);
        gameEntities.add(playerEntity);

        return playerEntity;
    }

    private ObstacleEntity createObstacle(GridPoint2 coordinates, EntityCreationStrategy<ObstacleEntity> entityStrategy) {
        ObstacleEntity obstacleEntity = entityStrategy.createEntity(coordinates);

        obstacleEntities.add(obstacleEntity);
        gameEntities.add(obstacleEntity);

        return obstacleEntity;
    }

    private void notifyListeners(PlayerEntity playerEntity, String texture) {
        for (GameObjectListener listener : listeners) {
            listener.onPlayerAdded(playerEntity, texture);
        }
    }

    private void notifyListeners(ObstacleEntity obstacleEntity, String texture) {
        for (GameObjectListener listener : listeners) {
            listener.onObstacleAdded(obstacleEntity, texture);
        }
    }
}
