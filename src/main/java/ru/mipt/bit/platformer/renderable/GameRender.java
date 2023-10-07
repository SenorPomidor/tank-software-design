package ru.mipt.bit.platformer.renderable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.interfces.ObstacleEntity;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;
import ru.mipt.bit.platformer.level.GameLevel;
import ru.mipt.bit.platformer.renderable.interfaces.ObstacleRenderable;
import ru.mipt.bit.platformer.renderable.strategy.TankCreationStrategy;
import ru.mipt.bit.platformer.renderable.strategy.TreeCreationStrategy;
import ru.mipt.bit.platformer.renderable.strategy.interfaces.EntityCreationStrategy;
import ru.mipt.bit.platformer.renderable.interfaces.PlayerRenderable;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;

public class GameRender {

    private final Batch batch;
    private final MapRenderer levelRenderer;
    private final GameLevel gameLevel;

    private final List<PlayerRenderable> playerRenderables = new ArrayList<>();
    private final List<ObstacleRenderable> obstacleRenderables = new ArrayList<>();

    public GameRender(TiledMap tiledMap, GameLevel gameLevel) {
        this.batch = new SpriteBatch();
        this.gameLevel = gameLevel;
        levelRenderer = createSingleLayerMapRenderer(tiledMap, batch);
    }

    public void createTank(GridPoint2 coordinates) {
        createPlayer(coordinates, new TankCreationStrategy());
    }

    public void createTree(GridPoint2 coordinates) {
        createObstacle(coordinates, new TreeCreationStrategy());
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    public void updateGameGraphics(TileMovement tileMovement) {
        for (PlayerRenderable tankRender : playerRenderables) {
            tankRender.updateGameGraphics(tileMovement);
        }
    }

    public void renderGame() {
        levelRenderer.render();
        batch.begin();

        for (PlayerRenderable player : playerRenderables) {
            player.drawTexture(batch);
        }

        for (ObstacleRenderable obstacle : obstacleRenderables) {
            obstacle.drawTexture(batch);
        }

        batch.end();
    }

    public void dispose() {
        for (PlayerRenderable player : playerRenderables) {
            player.dispose();
        }

        for (ObstacleRenderable obstacle : obstacleRenderables) {
            obstacle.dispose();
        }

        batch.dispose();
    }

    private void createPlayer(GridPoint2 coordinates, EntityCreationStrategy<PlayerRenderable> entityStrategy) {
        PlayerRenderable playerRenderable = entityStrategy.createEntity(coordinates);
        gameLevel.addPlayer((PlayerEntity) playerRenderable.getGameEntity());

        playerRenderables.add(playerRenderable);
    }

    private void createObstacle(GridPoint2 coordinates, EntityCreationStrategy<ObstacleRenderable> entityStrategy) {
        ObstacleRenderable obstacleRenderable = entityStrategy.createEntity(coordinates);
        gameLevel.addObstacle(obstacleRenderable, (ObstacleEntity) obstacleRenderable.getGameEntity());

        obstacleRenderables.add(obstacleRenderable);
    }
}
