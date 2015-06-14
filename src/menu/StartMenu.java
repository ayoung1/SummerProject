package menu;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Display.*;

public class StartMenu extends Menu implements Focusable{

	private ArrayList<MenuOption> list;
	
	public StartMenu(ArrayList<MenuOption> iterable) {
		super("Start Menu", iterable);
		this.list = iterable;
	}

	@Override
	protected int getSize() {
		return this.list.size();
	}

	@Override
	protected MenuOption getIndex(int index) {
		return this.list.get(index);
	}
	
	@Override
	public boolean update(KeyEvent e, boolean focused) {
		if(focused)
		{
			if(Keys.keyW(e))
				changeSelection(-1);
			if(Keys.keyS(e))
				changeSelection(1);
			
			if(Keys.keyEnter(e))
				this.getSelected().display();
		}
		return true;
	}

}
