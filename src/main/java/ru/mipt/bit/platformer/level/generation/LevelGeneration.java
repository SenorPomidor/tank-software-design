package ru.mipt.bit.platformer.level.generation;

import ru.mipt.bit.platformer.level.GameLevel;

public interface LevelGeneration {

    GameLevel generate(GameLevel gameLevel);
}
