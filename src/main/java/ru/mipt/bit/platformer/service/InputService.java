package ru.mipt.bit.platformer.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.Tank;
import ru.mipt.bit.platformer.entity.Tree;
import ru.mipt.bit.platformer.entity.enums.Direction;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.entity.enums.Filed.LEVEL_HEIGHT;
import static ru.mipt.bit.platformer.entity.enums.Filed.LEVEL_WIDTH;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class InputService {

    private static final float MOVEMENT_SPEED = 1.2f;

    public void handleUserInput(Tank tank, Tree tree, TileMovement tileMovement, Direction direction, float deltaTime) {
        boolean keyJustPressed;

        keyJustPressed = isKeyJustPressed(direction);
        newTankParams(tank, tree, direction, keyJustPressed);
        moveRectangle(tank, tileMovement);
        movementProgress(tank, deltaTime);

        if (isEqual(tank.getMovementProgress(), 1f)) {
            tank.setCoordinates(tank.getDestinationCoordinates());
        }
    }

    private void movementProgress(Tank tank, float deltaTime) {
        tank.setMovementProgress(
                continueProgress(
                        tank.getMovementProgress(),
                        deltaTime,
                        MOVEMENT_SPEED
                )
        );
    }

    private void moveRectangle(Tank tank, TileMovement tileMovement) {
        tileMovement.moveRectangleBetweenTileCenters(
                tank.getRectangle(),
                tank.getCoordinates(),
                tank.getDestinationCoordinates(),
                tank.getMovementProgress()
        );
    }

    private void newTankParams(Tank tank, Tree tree, Direction direction, boolean keyJustPressed) {
        if (keyJustPressed && isEqual(tank.getMovementProgress(), 1f)) {
            GridPoint2 newCoords = new GridPoint2(
                    tank.getDestinationCoordinates().x + direction.dx,
                    tank.getDestinationCoordinates().y + direction.dy
            );

            if (!newCoords.equals(tree.getCoordinates()) &&
                    newCoords.x >= 0 && newCoords.x < LEVEL_WIDTH.getField() &&
                    newCoords.y >= 0 && newCoords.y < LEVEL_HEIGHT.getField()) {
                tank.setDestinationCoordinates(newCoords);
                tank.setMovementProgress(0f);
            }

            tank.setRotation(direction.rotation);
        }
    }

    private boolean isKeyJustPressed(Direction direction) {
        if (direction != null) {
            int key = switch (direction) {
                case UP -> Input.Keys.UP;
                case DOWN -> Input.Keys.DOWN;
                case LEFT -> Input.Keys.LEFT;
                case RIGHT -> Input.Keys.RIGHT;
            };

            return Gdx.input.isKeyJustPressed(key);
        }

        return false;
    }
}
