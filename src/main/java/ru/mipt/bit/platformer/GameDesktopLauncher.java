package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.controller.InputController;
import ru.mipt.bit.platformer.entity.Tank;
import ru.mipt.bit.platformer.entity.Tree;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;
import ru.mipt.bit.platformer.entity.interfces.ObstacleEntity;
import ru.mipt.bit.platformer.entity.interfces.TankEntity;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.renderable.ObstacleRenderableImpl;
import ru.mipt.bit.platformer.renderable.TankRenderableImpl;
import ru.mipt.bit.platformer.renderable.interfaces.ObstacleRenderable;
import ru.mipt.bit.platformer.renderable.interfaces.TankRenderable;
import ru.mipt.bit.platformer.renderable.interfaces.Renderable;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameDesktopLauncher implements ApplicationListener {

    private final static String TANK_IMAGE = "images/tank_blue.png";
    private final static String TREE_IMAGE = "images/greenTree.png";

    private Level level;

    private InputController inputController;

    private final List<Renderable> objectRenderable = new ArrayList<>();
    private final List<GameEntity> gameEntities = new ArrayList<>();

    @Override
    public void create() {
        level = new Level();

        initGameEntities();

        inputController = new InputController();
        inputController.initKeyBoardMappings();
    }

    @Override
    public void render() {
        clearScreen();

        float deltaTime = Gdx.graphics.getDeltaTime();

        updateGameState(deltaTime);
        level.renderGame(objectRenderable);
    }

    private void initGameEntities() {
        TankEntity tank = new Tank(new GridPoint2(1, 1));
        TankRenderable tankGraphics = new TankRenderableImpl(TANK_IMAGE, tank);
        gameEntities.add(tank);
        objectRenderable.add(tankGraphics);

        ObstacleEntity tree = new Tree(new GridPoint2(1, 3));
        ObstacleRenderable obstacleGraphics = new ObstacleRenderableImpl(TREE_IMAGE, tree);
        obstacleGraphics.moveObstacle(level.getGroundLayer());
        gameEntities.add(tree);
        objectRenderable.add(obstacleGraphics);
    }

    private void updateGameState(float deltaTime) {
        for (Renderable object : objectRenderable) {
            // Не знаю как иначе без этого...
            if ("TankRenderableImpl".equals(object.getClass().getSimpleName())) {
                ((TankRenderable) object).updateTankState(inputController.getDirection(), gameEntities, deltaTime);
                ((TankRenderable) object).updateGameGraphics(level.getTileMovement());
            }
        }
    }

    private static void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        for (Renderable object : objectRenderable) {
            object.dispose();
        }
        level.dispose();
        level.getBatch().dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
