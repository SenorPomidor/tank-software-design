package ru.mipt.bit.platformer.renderable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.mipt.bit.platformer.entity.interfces.ObstacleEntity;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;
import ru.mipt.bit.platformer.level.interfaces.GameObjectListener;
import ru.mipt.bit.platformer.renderable.interfaces.ObstacleRenderable;
import ru.mipt.bit.platformer.renderable.interfaces.PlayerRenderable;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;

public class GameRender implements GameObjectListener {

    private final Batch batch;
    private final MapRenderer levelRenderer;
    private final TiledMapTileLayer groundLayer;

    private final List<PlayerRenderable> playerRenderables = new ArrayList<>();
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
        for (PlayerRenderable tankRender : playerRenderables) {
            tankRender.updateGameGraphics(tileMovement);
        }
    }

    @Override
    public void onPlayerAdded(PlayerEntity playerEntity, String texture) {
        playerRenderables.add(new PlayerRenderableImpl(playerEntity, texture));
    }

    @Override
    public void onObstacleAdded(ObstacleEntity obstacleEntity, String texture) {
        ObstacleRenderable obstacleRenderable = new ObstacleRenderableImpl(obstacleEntity, texture);

        obstacleRenderables.add(obstacleRenderable);
        obstacleRenderable.moveObstacle(groundLayer);
    }

    public void renderGame() {
        System.out.println("playerRenderables " + playerRenderables.size());
        System.out.println("obstacleRenderables " + obstacleRenderables.size());

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
}
