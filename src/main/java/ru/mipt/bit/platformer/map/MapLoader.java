package ru.mipt.bit.platformer.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MapLoader {

    private GameMap gameMap;

    public MapLoader(String filename) {
        loadMapFromFile(filename);
    }

    private void loadMapFromFile(String filename) {
        InputStream resourceStream = MapLoader.class.getClassLoader().getResourceAsStream(filename);

        if (resourceStream != null) {
            List<String> lines;

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream))) {
                lines = reader.lines().collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            gameMap = new GameMap(lines.get(0).length(), lines.size());

            IntStream.range(0, lines.size())
                    .forEach(y -> {
                        String line = lines.get(lines.size() - y - 1);
                        IntStream.range(0, line.length())
                                .forEach(x -> gameMap.setCell(x, y, line.charAt(x)));
                    });
        } else {
            throw new IllegalArgumentException("Файл " + filename + " не найден!");
        }
    }

    public char getCell(int x, int y) {
        return gameMap.getCell(x, y);
    }

    public int getWidth() {
        return gameMap.getWidth();
    }

    public int getHeight() {
        return gameMap.getHeight();
    }
}
