package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.entity.GameField;
import ru.mipt.bit.platformer.entity.Tank;
import ru.mipt.bit.platformer.entity.Tree;
import ru.mipt.bit.platformer.entity.enums.Direction;
import ru.mipt.bit.platformer.service.DrawService;
import ru.mipt.bit.platformer.service.InputService;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.entity.enums.Filed.LEVEL_HEIGHT;
import static ru.mipt.bit.platformer.entity.enums.Filed.LEVEL_WIDTH;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.moveRectangleAtTileCenter;

public class GameDesktopLauncher implements ApplicationListener {

    private static final int PIXELS = 128;

    private Batch batch;

    private GameField gameField;
    private Tank tank;
    private Tree tree;

    private TileMovement tileMovement;

    private final InputService inputService = new InputService();
    private final DrawService drawService = new DrawService();

    @Override
    public void create() {
        batch = new SpriteBatch();

        TiledMap level = new TmxMapLoader().load("level.tmx");
        gameField = new GameField(level, batch);

        TiledMapTileLayer groundLayer = getSingleLayer(level);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);

        tank = new Tank("images/tank_blue.png", new GridPoint2(1, 1));
        tree = new Tree("images/greenTree.png", new GridPoint2(3, 3));

        moveRectangleAtTileCenter(groundLayer, tree.getRectangle(), tree.getCoordinates());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        float deltaTime = Gdx.graphics.getDeltaTime();

        for (Direction direction : Direction.values()) {
            inputService.handleUserInput(tank, tree, tileMovement, direction, deltaTime);
        }

        gameField.render();

        batch.begin();

        drawService.drawGameObject(batch, tank);
        drawService.drawGameObject(batch, tree);

        batch.end();
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void dispose() {
        tree.dispose();
        tank.dispose();
        gameField.dispose();
        batch.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(LEVEL_WIDTH.getField() * PIXELS, LEVEL_HEIGHT.getField() * PIXELS);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}