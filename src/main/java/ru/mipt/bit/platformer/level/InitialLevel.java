package ru.mipt.bit.platformer.level;

import ru.mipt.bit.platformer.level.generation.LevelGeneration;

public class InitialLevel {

    private final LevelGeneration strategy;

    public InitialLevel(LevelGeneration strategy) {
        this.strategy = strategy;
    }

    public GameLevel initLevelMethod(GameLevel gameLevel) {
        return strategy.generate(gameLevel);
    }

}
