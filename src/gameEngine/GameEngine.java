package gameEngine;

import inventory.Inventory;

import java.util.ArrayList;

import character.Warrior;
import menu.*;
import party.Party;

public class GameEngine {
	
	private static GameEngine gameEngine;
	private MainMenu menu;
	private Party party;
	private Inventory inventory;
	
	private GameEngine()
	{
		this.party = new Party(new Warrior("Eric"));
		this.inventory = new Inventory();
		
		this.menuSetUp();
	}
	
	public static GameEngine instanceOf()
	{
		if(gameEngine == null)
			gameEngine = new GameEngine();
		return gameEngine;
	}
	
	private void menuSetUp()
	{
		ArrayList<MenuOption> menu = new ArrayList<MenuOption>();
		
		menu.add(new PartyMenu(this.party));
		menu.add(new InventoryMenu(this.inventory));
		
		this.menu = new MainMenu(menu);
	}
	
	public void Run()
	{
		ArrayList<MenuOption> menu = new ArrayList<MenuOption>();
		
		menu.add(new StartGameOption());
		menu.add(new ExitOption());
		
		StartMenu start = new StartMenu(menu);
		
		start.open();
	}
	
	public Party getParty()
	{
		return this.party;
	}
	
	public MainMenu getMenu()
	{
		return this.menu;
	}
	
}
