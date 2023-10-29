package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import org.awesome.ai.Action;
import org.awesome.ai.Recommendation;
import org.awesome.ai.state.movable.Actor;
import ru.mipt.bit.platformer.adapter.AIAdapter;
import ru.mipt.bit.platformer.controller.InputController;
import ru.mipt.bit.platformer.mapper.GameStateMapper;
import ru.mipt.bit.platformer.level.GameLevel;
import ru.mipt.bit.platformer.level.InitialLevel;
import ru.mipt.bit.platformer.level.generation.LevelGeneration;
import ru.mipt.bit.platformer.level.generation.LevelGenerationStrategy;
import ru.mipt.bit.platformer.renderable.GameRender;

import java.util.List;

import static ru.mipt.bit.platformer.common.CommonVariables.*;

public class GameDesktopLauncher implements ApplicationListener {

    private GameLevel gameLevel;
    private GameRender gameRender;
    private InputController inputController;
    private AIAdapter aiAdapter;

    @Override
    public void create() {
        gameLevel = new GameLevel();

        gameRender = new GameRender(gameLevel.getTiledMap(), gameLevel.getGroundLayer());

        gameLevel.addListener(gameRender);

        LevelGenerationStrategy factory = new LevelGenerationStrategy();
        LevelGeneration levelGeneration = factory.createStrategy(GENERATE_FROM);
        InitialLevel initialLevel = new InitialLevel(levelGeneration);
        gameLevel = initialLevel.initLevelMethod(gameLevel);

        inputController = new InputController();
        inputController.initActions();

        aiAdapter = new AIAdapter(inputController, gameLevel.getPlayerEntities());
    }

    @Override
    public void render() {
        gameRender.clearScreen();

        float deltaTime = Gdx.graphics.getDeltaTime();

        inputController.checkIsTriggeredKeyAndExecuteCommand(gameLevel.getPlayerEntities(), gameLevel.getGameEntities(), deltaTime);
        inputController.randomIsTriggeredKeyAndExecuteCommand(gameLevel.getPlayerBotEntities(), gameLevel.getGameEntities(), deltaTime);

//        List<Recommendation> aiRecommendations = aiAdapter.recommend(
//                GameStateMapper.mapToGameState(
//                        gameLevel.getObstacleEntities(),
//                        gameLevel.getPlayerEntities().get(0),
//                        gameLevel.getPlayerBotEntities(),
//                        MAX_X,
//                        MAX_Y));

        // Какие-либо действия с aiRecommendations...

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
        gameLevel.getTiledMap().dispose();
        gameRender.dispose();
    }
}
