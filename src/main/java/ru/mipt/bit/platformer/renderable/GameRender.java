package ru.mipt.bit.platformer.renderable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import ru.mipt.bit.platformer.renderable.interfaces.ObstacleRenderable;
import ru.mipt.bit.platformer.renderable.interfaces.PlayerRenderable;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;

public class GameRender {

    private final Batch batch;
    private final MapRenderer levelRenderer;

    private final List<PlayerRenderable> playerRenderables = new ArrayList<>();
    private final List<ObstacleRenderable> obstacleRenderables = new ArrayList<>();

    public GameRender(TiledMap level) {
        batch = new SpriteBatch();
        levelRenderer = createSingleLayerMapRenderer(level, batch);
    }

    public void addPlayerRenderable(PlayerRenderable playerRenderable) {
        playerRenderables.add(playerRenderable);
    }

    public void addObstacleRenderable(ObstacleRenderable obstacleRenderable) {
        obstacleRenderables.add(obstacleRenderable);
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    public void updateGameState(TileMovement tileMovement) {
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
}
