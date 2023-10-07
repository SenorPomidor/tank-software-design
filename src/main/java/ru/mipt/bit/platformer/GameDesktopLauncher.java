package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.controller.InputController;
import ru.mipt.bit.platformer.level.GameLevel;
import ru.mipt.bit.platformer.level.InitialLevel;
import ru.mipt.bit.platformer.level.generation.LevelGeneration;
import ru.mipt.bit.platformer.level.generation.LevelGenerationStrategy;
import ru.mipt.bit.platformer.renderable.GameRender;

import static ru.mipt.bit.platformer.common.CommonVariables.GENERATE_FROM;

public class GameDesktopLauncher implements ApplicationListener {

    private GameLevel gameLevel;
    private GameRender gameRender;

    @Override
    public void create() {
        InputController inputController = new InputController();
        inputController.initActions();

        gameLevel = new GameLevel(inputController);
        gameRender = new GameRender(gameLevel.getLevel(), gameLevel);

        LevelGenerationStrategy factory = new LevelGenerationStrategy();
        LevelGeneration levelGeneration = factory.createStrategy(GENERATE_FROM);
        InitialLevel initialLevel = new InitialLevel(levelGeneration);
        initialLevel.initLevelMethod(gameRender);
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
