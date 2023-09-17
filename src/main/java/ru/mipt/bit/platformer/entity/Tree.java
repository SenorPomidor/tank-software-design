package ru.mipt.bit.platformer.entity;

import com.badlogic.gdx.math.GridPoint2;

public class Tree extends GameObject {

    public Tree(String texturePath, GridPoint2 initialCoordinates) {
        super(texturePath);
        this.coordinates = initialCoordinates;
    }

}


