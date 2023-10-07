package ru.mipt.bit.platformer.level;

import ru.mipt.bit.platformer.level.generation.LevelGeneration;
import ru.mipt.bit.platformer.renderable.GameRender;

public class InitialLevel {

    private final LevelGeneration strategy;

    public InitialLevel(LevelGeneration strategy) {
        this.strategy = strategy;
    }

    public void initLevelMethod(GameRender gameRender) {
        strategy.generate(gameRender);
    }

}
