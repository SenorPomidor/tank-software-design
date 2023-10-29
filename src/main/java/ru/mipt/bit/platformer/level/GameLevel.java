package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.controller.DirectionKeyBoardAction;
import ru.mipt.bit.platformer.entity.Bullet;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;
import ru.mipt.bit.platformer.entity.interfces.ObstacleEntity;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;
import ru.mipt.bit.platformer.level.interfaces.GameObjectListener;
import ru.mipt.bit.platformer.level.strategy.TankBotCreationStrategy;
import ru.mipt.bit.platformer.level.strategy.TankCreationStrategy;
import ru.mipt.bit.platformer.level.strategy.TreeCreationStrategy;
import ru.mipt.bit.platformer.level.strategy.interfaces.EntityCreationStrategy;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.mipt.bit.platformer.common.CommonVariables.*;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public class GameLevel {

    private static final float MOVEMENT_SPEED = 0.4f;
    private static final float MOVEMENT_SPEED_BULLET = 0.25f;

    private final TiledMap level;
    private final TileMovement tileMovement;
    private final TiledMapTileLayer groundLayer;

    private final List<GameEntity> gameEntities = new ArrayList<>();

    private final List<PlayerEntity> playerEntities = new ArrayList<>();
    private final List<PlayerEntity> playerBotEntities = new ArrayList<>();
    private final List<ObstacleEntity> obstacleEntities = new ArrayList<>();
    private final List<Bullet> bullets = new ArrayList<>();

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

    public void createTankBot(GridPoint2 coordinates) {
        PlayerEntity player = createPlayer(coordinates, new TankBotCreationStrategy());
        notifyListeners(player, TANK_IMAGE);
    }

    public void createTree(GridPoint2 coordinates) {
        ObstacleEntity obstacle = createObstacle(coordinates, new TreeCreationStrategy());
        notifyListeners(obstacle, TREE_IMAGE);
    }

    public void createBullet(PlayerEntity playerEntity) {
        Bullet bullet = new Bullet(
                DirectionKeyBoardAction.applyCoordinatesByRotation(playerEntity.getCoordinates(), playerEntity.getRotation()),
                playerEntity.getRotation()
        );
        bullets.add(bullet);
        notifyListeners(bullet, BULLET_IMAGE);
    }

    public void updateGameState(float deltaTime) {
        updateStateForTanks(deltaTime);
        updateStateForBullets(deltaTime);
    }

    private void updateStateForTanks(float deltaTime) {
        List<PlayerEntity> tanks = Stream.concat(playerEntities.stream(), playerBotEntities.stream()).collect(Collectors.toList());

        tanks.forEach(playerEntity -> {
            playerEntity.updateState(continueProgress(playerEntity.getMovementProgress(), deltaTime, MOVEMENT_SPEED));

            if (playerEntity.isShoot() && !isObjectInFront(playerEntity)) {
                createBullet(playerEntity);
                playerEntity.setRecharge();
            } else if (playerEntity.isShoot()) {
                playerEntity.setRecharge();
            }
        });

        List<PlayerEntity> tanksToRemove = new ArrayList<>();
        tanks.stream()
                .filter(tank -> tank.getHealth() <= 0)
                .forEach(tank -> {
                    tanksToRemove.add(tank);
                    notifyDeleteListeners(tank);
                });
        gameEntities.removeAll(tanksToRemove);
        playerEntities.removeAll(tanksToRemove);
        playerBotEntities.removeAll(tanksToRemove);
    }

    private void updateStateForBullets(float deltaTime) {
        List<Bullet> bulletsToRemove = new ArrayList<>();
        bullets.forEach(bullet -> {
            bullet.updateBulletMovement(gameEntities);
            bullet.updateState(continueProgress(bullet.getMovementProgress(), deltaTime, MOVEMENT_SPEED_BULLET));
            if (!bullet.encountered()) {
                bulletsToRemove.add(bullet);
                notifyDeleteListeners(bullet);
            }
        });
        bullets.removeAll(bulletsToRemove);
    }

    private boolean isObjectInFront(PlayerEntity playerEntity) {
        GridPoint2 gridPoint2 = DirectionKeyBoardAction.applyCoordinatesByRotation(playerEntity.getCoordinates(), playerEntity.getRotation());

        List<PlayerEntity> players = Stream.concat(playerEntities.stream(), playerBotEntities.stream()).collect(Collectors.toList());

        boolean firstСondition = players.stream()
                .anyMatch(entity -> entity.getCoordinates().equals(gridPoint2));
        boolean secondCondition = obstacleEntities.stream()
                .anyMatch(entity -> entity.getCoordinates().equals(gridPoint2));

        return firstСondition || secondCondition;
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

    public List<PlayerEntity> getPlayerBotEntities() {
        return playerBotEntities;
    }

    public List<ObstacleEntity> getObstacleEntities() {
        return obstacleEntities;
    }

    public List<GameEntity> getGameEntities() {
        return gameEntities;
    }

    private PlayerEntity createPlayer(GridPoint2 coordinates, EntityCreationStrategy<PlayerEntity> entityStrategy) {
        PlayerEntity playerEntity = entityStrategy.createEntity(coordinates);

        if (entityStrategy instanceof TankBotCreationStrategy) {
            playerBotEntities.add(playerEntity);
        } else {
            playerEntities.add(playerEntity);
        }

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

    private void notifyListeners(Bullet bullet, String texture) {
        for (GameObjectListener listener : listeners) {
            listener.onBulletAdded(bullet, texture);
        }
    }

    private void notifyDeleteListeners(GameEntity bullet) {
        for (GameObjectListener listener : listeners) {
            listener.onBulletDelete(bullet);
        }
    }

    private void notifyDeleteListeners(PlayerEntity playerEntity) {
        for (GameObjectListener listener : listeners) {
            listener.onPlayerDelete(playerEntity);
        }
    }
}
