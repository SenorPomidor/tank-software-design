package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Disposable;
import ru.mipt.bit.platformer.renderable.interfaces.Renderable;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public class Level implements Disposable {

    private final TiledMap level;
    private final MapRenderer levelRenderer;
    private final TileMovement tileMovement;
    private final Batch batch;
    private final TiledMapTileLayer groundLayer;

    public Level() {
        this.batch = new SpriteBatch();
        this.level = new TmxMapLoader().load("level.tmx");
        this.levelRenderer = createSingleLayerMapRenderer(level, batch);
        this.groundLayer = getSingleLayer(level);
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }

    public Batch getBatch() {
        return batch;
    }

    public TiledMapTileLayer getGroundLayer() {
        return groundLayer;
    }

    public void renderGame(List<Renderable> objects) {
        levelRenderer.render();

        batch.begin();

        for (Renderable object : objects) {
            object.drawTexture(batch);
        }

        batch.end();
    }

    @Override
    public void dispose() {
        level.dispose();
    }

}
