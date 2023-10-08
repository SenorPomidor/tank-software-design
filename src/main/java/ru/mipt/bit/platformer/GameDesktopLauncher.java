package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.controller.InputController;
import ru.mipt.bit.platformer.entity.Tank;
import ru.mipt.bit.platformer.entity.Tree;
import ru.mipt.bit.platformer.level.GameLevel;
import ru.mipt.bit.platformer.renderable.GameRender;
import ru.mipt.bit.platformer.renderable.interfaces.ObstacleRenderable;
import ru.mipt.bit.platformer.renderable.interfaces.PlayerRenderable;

public class GameDesktopLauncher implements ApplicationListener {

    private GameLevel gameLevel;
    private GameRender gameRender;

    @Override
    public void create() {
        InputController inputController = new InputController();
        inputController.initActions();

        gameLevel = new GameLevel(inputController);

        PlayerRenderable playerRenderable = gameLevel.addPlayer(new Tank(new GridPoint2(1, 1)));
        ObstacleRenderable obstacleRenderable = gameLevel.addObstacle(new Tree(new GridPoint2(3, 3)));

        gameRender = new GameRender(gameLevel.getLevel());

        gameRender.addPlayerRenderable(playerRenderable);
        gameRender.addObstacleRenderable(obstacleRenderable);
    }

    @Override
    public void render() {
        gameRender.clearScreen();

        float deltaTime = Gdx.graphics.getDeltaTime();

        gameLevel.checkIsTriggeredKeyAndExecuteCommand(deltaTime);
        gameLevel.updateGameState(deltaTime);
        gameRender.updateGameGraphics(gameLevel.getTileMovement());

        gameRender.renderGame();
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
        gameLevel.getLevel().dispose();
        gameRender.dispose();
    }
}
