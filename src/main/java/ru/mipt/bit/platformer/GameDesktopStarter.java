package ru.mipt.bit.platformer;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import static ru.mipt.bit.platformer.common.CommonVariables.MAX_X;
import static ru.mipt.bit.platformer.common.CommonVariables.MAX_Y;

public class GameDesktopStarter {

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(MAX_X * 128, MAX_Y * 128);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
