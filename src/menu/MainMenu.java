package menu;

import java.util.ArrayList;

import Display.*;

public class MainMenu extends Menu implements Focusable{

	ArrayList<MenuOption> menu;
	
	public MainMenu(ArrayList<MenuOption> iterable) {
		super("Main Menu", iterable);
		this.menu = iterable;
	}

	@Override
	protected int getSize() {
		return this.menu.size();
	}

	@Override
	protected MenuOption getIndex(int index) {
		return this.menu.get(index);
	}

}
