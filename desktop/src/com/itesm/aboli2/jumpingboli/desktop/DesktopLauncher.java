package com.itesm.aboli2.jumpingboli.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.itesm.aboli2.jumpingboli.GdXGame;
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280/2;
		config.height = 720/2;
		new LwjglApplication(new GdXGame(), config);
	}
}
