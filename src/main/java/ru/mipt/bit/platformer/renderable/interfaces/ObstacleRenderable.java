package ru.mipt.bit.platformer.renderable.interfaces;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public interface ObstacleRenderable extends Renderable {

    void moveObstacle(TiledMapTileLayer tileLayer);

}
