package gameEngine;

import menu.MenuOption;

public class ExitOption implements MenuOption{

	@Override
	public String menuString() {
		return "Exit Game";
	}

	@Override
	public void display() {
		System.exit(0);
	}

}
