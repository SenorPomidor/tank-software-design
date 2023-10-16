package ru.mipt.bit.platformer.level.generation;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.level.GameLevel;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import static ru.mipt.bit.platformer.common.CommonVariables.*;

public class RandomGeneration implements LevelGeneration {

    private final Random random = new Random();
    private final Set<GridPoint2> occupiedPositions = new HashSet<>();

    @Override
    public GameLevel generate(GameLevel gameLevel) {
        GridPoint2 startCoordinates = new GridPoint2(1, 1);
        gameLevel.createTank(startCoordinates);
        occupiedPositions.add(startCoordinates);

        IntStream.range(0, OBSTACLE_COUNT)
                .forEach(i -> {
                    GridPoint2 position;
                    do {
                        int x = random.nextInt(MAX_X);
                        int y = random.nextInt(MAX_Y);
                        position = new GridPoint2(x, y);
                    } while (occupiedPositions.contains(position));
                    occupiedPositions.add(position);

                    gameLevel.createTree(position);
                });

        return gameLevel;
    }
}
