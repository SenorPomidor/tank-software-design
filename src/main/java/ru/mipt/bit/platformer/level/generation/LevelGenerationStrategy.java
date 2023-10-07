package ru.mipt.bit.platformer.level.generation;

public class LevelGenerationStrategy {

    public LevelGeneration createStrategy(String type) {
        switch (type) {
            case "RANDOM":
                return new RandomGeneration();
            case "FILE":
                return new FileGeneration();
            default:
                throw new IllegalArgumentException("Неизвестный тип генерации карты");
        }
    }
}

