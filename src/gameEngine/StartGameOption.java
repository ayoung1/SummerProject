package gameEngine;

import battle.BattleHandler;
import dungeon.DungeonHandler;
import menu.MenuOption;

public class StartGameOption implements MenuOption{

	@Override
	public String menuString() {
		return "Start Game";
	}

	@Override
	public void display() {
		BattleHandler.instanceOf().startBattle(GameEngine.instanceOf().getParty());
		DungeonHandler.GetInstanceOfDungeonHandler().init();
	}

}
