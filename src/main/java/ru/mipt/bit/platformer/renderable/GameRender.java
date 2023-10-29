package ru.mipt.bit.platformer.renderable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.mipt.bit.platformer.entity.Bullet;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;
import ru.mipt.bit.platformer.entity.interfces.ObstacleEntity;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;
import ru.mipt.bit.platformer.level.interfaces.GameObjectListener;
import ru.mipt.bit.platformer.renderable.interfaces.ObstacleRenderable;
import ru.mipt.bit.platformer.renderable.interfaces.MovingObjectRenderable;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;

public class GameRender implements GameObjectListener {

    private final Batch batch;
    private final MapRenderer levelRenderer;
    private final TiledMapTileLayer groundLayer;

    private final List<MovingObjectRenderable> movingObjectRenderables = new ArrayList<>();
    private final List<ObstacleRenderable> obstacleRenderables = new ArrayList<>();

    public GameRender(TiledMap tiledMap, TiledMapTileLayer groundLayer) {
        this.batch = new SpriteBatch();
        this.groundLayer = groundLayer;
        levelRenderer = createSingleLayerMapRenderer(tiledMap, batch);
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    public void updateGameGraphics(TileMovement tileMovement) {
        for (MovingObjectRenderable tankRender : movingObjectRenderables) {
            tankRender.updateGameGraphics(tileMovement);
        }
    }

    @Override
    public void onPlayerAdded(PlayerEntity playerEntity, String texture) {
        movingObjectRenderables.add(new PlayerRenderableImpl(playerEntity, texture));
    }

    @Override
    public void onObstacleAdded(ObstacleEntity obstacleEntity, String texture) {
        ObstacleRenderable obstacleRenderable = new ObstacleRenderableImpl(obstacleEntity, texture);

        obstacleRenderables.add(obstacleRenderable);
        obstacleRenderable.moveObstacle(groundLayer);
    }

    @Override
    public void onBulletAdded(Bullet bullet, String texture) {
        BulletRenderableImpl bulletRenderable = new BulletRenderableImpl(bullet, texture);

        movingObjectRenderables.add(bulletRenderable);
    }

    @Override
    public void onBulletDelete(GameEntity gameEntity) {
        Optional<MovingObjectRenderable> deletedEntity = movingObjectRenderables.stream()
                .filter(movingObjectRenderable -> movingObjectRenderable.getGameEntity().equals(gameEntity))
                .findFirst();

        deletedEntity.ifPresent(movingObjectRenderables::remove);
    }

    @Override
    public void onPlayerDelete(PlayerEntity gameEntity) {
        Optional<MovingObjectRenderable> deletedEntity = movingObjectRenderables.stream()
                .filter(movingObjectRenderable -> movingObjectRenderable.getGameEntity().equals(gameEntity))
                .findFirst();

        deletedEntity.ifPresent(movingObjectRenderables::remove);
    }

    public void renderGame() {
        levelRenderer.render();
        batch.begin();

        for (MovingObjectRenderable player : movingObjectRenderables) {
            player.drawTexture(batch);
        }

        for (ObstacleRenderable obstacle : obstacleRenderables) {
            obstacle.drawTexture(batch);
        }

        batch.end();
    }

    public void dispose() {
        for (MovingObjectRenderable player : movingObjectRenderables) {
            player.dispose();
        }

        for (ObstacleRenderable obstacle : obstacleRenderables) {
            obstacle.dispose();
        }

        batch.dispose();
    }
}
