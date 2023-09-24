package ru.mipt.bit.platformer.renderable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.entity.Tree;
import ru.mipt.bit.platformer.entity.interfces.ObstacleEntity;
import ru.mipt.bit.platformer.renderable.interfaces.ObstacleRenderable;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class ObstacleRenderableImpl implements ObstacleRenderable {

    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;
    private final ObstacleEntity tree;

    public ObstacleRenderableImpl(String fileNameTexture, ObstacleEntity tree) {
        texture = new Texture(fileNameTexture);
        textureRegion = new TextureRegion(texture);
        rectangle = createBoundingRectangle(textureRegion);
        this.tree = tree;
    }

    @Override
    public void drawTexture(Batch batch) {
        drawTextureRegionUnscaled(batch, textureRegion, rectangle, 0f);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public void moveObstacle(TiledMapTileLayer tileLayer) {
        moveRectangleAtTileCenter(
                tileLayer,
                this.rectangle,
                tree.getCurrentCoordinates()
        );
    }
}
