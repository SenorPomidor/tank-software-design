package ru.mipt.bit.platformer.map;

public class GameMap {
    private final char[][] mapData;
    private final int width;
    private final int height;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.mapData = new char[height][width];
    }

    public void setCell(int x, int y, char value) {
        mapData[y][x] = value;
    }

    public char getCell(int x, int y) {
        return mapData[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}


