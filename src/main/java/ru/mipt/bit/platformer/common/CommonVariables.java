package ru.mipt.bit.platformer.common;

public class CommonVariables {

    public static final String TANK_IMAGE = System.getProperty("sprite.tank.path");
    public static final String TREE_IMAGE = System.getProperty("sprite.tree.path");
    public static final String BULLET_IMAGE = System.getProperty("sprite.bullet.path");

    public final static Integer TREE_COUNT = Integer.valueOf(System.getProperty("generate.random.obstacle.tree.count"));
    public final static Integer TANK_BOT_COUNT = Integer.valueOf(System.getProperty("generate.random.player.tank.count"));
    public final static String MAP_PATH = System.getProperty("generate.file.path");
    public final static String GENERATE_FROM = System.getProperty("generate.from");

    public final static Integer MAX_X = Integer.valueOf(System.getProperty("map.max.x"));
    public final static Integer MAX_Y = Integer.valueOf(System.getProperty("map.max.y"));

    public final static Integer RANDOM_ACTION_PERCENTAGE = Integer.valueOf(System.getProperty("random.action.percentage"));

}
