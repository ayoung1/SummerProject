package menu;

import java.awt.event.KeyEvent;

import Display.*;

public abstract class Menu implements Focusable{

	private int index;
	private MenuOption selected;
	
	private Iterable<MenuOption> iterable;
	private String title;
	
	public Menu(String title, Iterable<MenuOption> iterable)
	{
		this.iterable = iterable;
		this.title = title;
		
		this.index = 0;
		this.selected = this.iterable.iterator().next();
	}
	
	@Override
	public void open() {
		Display.getInstanceOfDisplay().setFocus(this);
	}

	@Override
	public String displayString() {
		String string = this.title + "\n\n";
		
		for(MenuOption option : this.iterable)
		{
			string += this.selected == option ? "*" : " ";
			
			string += option.menuString() + '\n';
		}
		
		return string;
	}

	@Override
	public String eventString() {
		return "";
	}

	@Override
	public String getName() {
		return this.title;
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
				this.selected.display();
			
			if(Keys.keyEscape(e))
				return true;
		}
		return false;
	}

	protected void changeSelection(int direction)
	{
		this.index += direction;
		
		if(direction > 0 && this.index >= this.getSize())
			this.index = 0;
		else if(direction < 0 && this.index < 0)
			this.index = this.getSize()-1;
		
		this.selected = this.getIndex(this.index);
	}
	
	protected MenuOption getSelected()
	{
		return this.selected;
	}
	
	protected abstract int getSize();
	protected abstract MenuOption getIndex(int index);
}
