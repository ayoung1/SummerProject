package dungeon;

import Display.*;

import java.awt.event.KeyEvent;


public class MiniMap implements Focusable{
	private Room[][] miniMap;
	private int height;
	private int width;
	
	public MiniMap(Room[][] map, int height, int width)
	{
		this.miniMap = map;
		this.height = height;
		this.width = width;		
	}
	
	public String displayString()
	{
		DungeonHandler handler = DungeonHandler.GetInstanceOfDungeonHandler();
		String str = "";
		
		for(int i = 0; i < this.height; i++)
		{
			for(int j = 0; j < this.width; j++)
			{
				if(i == handler.getPosition().width && j == handler.getPosition().height)
					str = str + "@ ";
				else if(this.miniMap[i][j] == null)
					str = str + "- ";
				else if(this.miniMap[i][j].isEndRoom())
					str = str + "^ ";
				else
					str = str + "# ";
			}
			str = str + "\n";
		}
		
		return str;
	}

	public boolean update(KeyEvent e, boolean focused) {
		if(focused){
			if(e.getKeyCode() == KeyEvent.VK_M || e.getKeyCode() == KeyEvent.VK_ESCAPE)
				return true;
			return false;
		}
		return true;
	}

	public String eventString() {
		// TODO Auto-generated method stub
		return "";
	}

	public void open() {
		// TODO Auto-generated method stub
		Display.getInstanceOfDisplay().setFocus(this);
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "Map";
	}
}
