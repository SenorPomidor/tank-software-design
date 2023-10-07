package ru.mipt.bit.platformer.level.generation;

import ru.mipt.bit.platformer.renderable.GameRender;

public interface LevelGeneration {

    void generate(GameRender gameRender);
}
