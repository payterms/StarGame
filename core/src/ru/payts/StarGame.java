package ru.payts;

import com.badlogic.gdx.Game;

import ru.payts.screen.MenuScreen;

public class StarGame extends Game {

	@Override
	public void create() {
		setScreen(new MenuScreen(this));
	}

}