package ru.mipt.bit.platformer.renderable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;
import ru.mipt.bit.platformer.entity.interfces.TankEntity;
import ru.mipt.bit.platformer.enums.Direction;
import ru.mipt.bit.platformer.renderable.interfaces.TankRenderable;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.List;
import java.util.Optional;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class TankRenderableImpl implements TankRenderable {

    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;
    private final TankEntity tankEntity;

    public TankRenderableImpl(String fileNameTexture, TankEntity gameEntity) {
        texture = new Texture(fileNameTexture);
        textureRegion = new TextureRegion(texture);
        rectangle = createBoundingRectangle(textureRegion);
        this.tankEntity = gameEntity;

    }

    @Override
    public void drawTexture(Batch batch) {
        drawTextureRegionUnscaled(batch, textureRegion, rectangle, tankEntity.getRotation());
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public void updateGameGraphics(TileMovement tileMovement) {
        tileMovement.moveRectangleBetweenTileCenters(
                rectangle,
                tankEntity.getCurrentCoordinates(),
                tankEntity.getDestinationCoordinates(),
                tankEntity.getMovementProgress()
        );
    }

    @Override
    public void updateTankState(Direction direction, List<GameEntity> gameFields, float deltaTime) {
        if (!tankEntity.isMoving() && direction != null) {
            GridPoint2 destinationCoordinates = direction.apply(tankEntity.getCurrentCoordinates());
            Optional<GameEntity> anyObject = gameFields.stream()
                    .filter(object -> collides(destinationCoordinates, object.getCoordinates()))
                    .findAny();
            if (anyObject.isEmpty()) {
                tankEntity.moveTo(destinationCoordinates);
            }
            tankEntity.setRotation(direction.getRotation());
        }
        tankEntity.updateMovementState(deltaTime);
    }

}
