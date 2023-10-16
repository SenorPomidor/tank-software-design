package ru.mipt.bit.platformer.level.generation;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.level.GameLevel;
import ru.mipt.bit.platformer.map.MapLoader;

import static ru.mipt.bit.platformer.common.CommonVariables.*;

public class FileGeneration implements LevelGeneration {

    @Override
    public GameLevel generate(GameLevel gameLevel) {
        MapLoader mapLoader = new MapLoader(MAP_PATH);

        for (int y = 0; y < MAX_Y; y++) {
            for (int x = 0; x < MAX_X; x++) {
                char cell = mapLoader.getCell(x, y);
                switch (cell) {
                    case 'T':
                        gameLevel.createTree(new GridPoint2(x, y));
                        break;
                    case 'X':
                        gameLevel.createTank(new GridPoint2(x, y));
                        break;
                    case '_':
                        break;
                }
            }
        }

        return gameLevel;
    }
}
