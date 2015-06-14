package dungeon;


import gameEngine.GameEngine;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.Random;
import Display.*;

public class Room implements Focusable{
	private int height;
	private int width;
	private int doors[];
	private int doorNumber;
	private boolean isEndRoom;
	private Dimension position;
	private Panel panels[][];
	
	public Room(int height, int width, int doorNumber, int[] doors)
	{
		this.height = height;
		this.width = width;
		this.doorNumber = doorNumber;
		this.doors = doors;
		this.isEndRoom = false;
		this.panels = new Panel[this.height][this.width];
		
		this.generateRoom();
	}
	
	private void generateRoom()
	{
		for(int i = 0; i < this.height; i++)
		{
			for(int j = 0; j < this.width; j++)
			{
				if(i == this.height / 2 && j == 0 && this.doors[3] == 1)
					this.panels[i][j] = new DoorPanel(0, 'D');
				else if(i == this.height / 2 && j == this.width - 1 && this.doors[2] == 1)
					this.panels[i][j] = new DoorPanel(0, 'D');
				else if(i == 0 && j == this.width / 2 && this.doors[0] == 1)
					this.panels[i][j] = new DoorPanel(0, 'D');
				else if(i == this.height - 1 && j == this.width / 2 && this.doors[1] == 1)
					this.panels[i][j] = new DoorPanel(0, 'D');
				else if((i == this.height - 1) || (i == 0) || (j == this.width - 1) || (j == 0))
					this.panels[i][j] = new WallPanel(1, '#');
				else
					this.panels[i][j] = new FloorPanel(2, '.');
				
			}
		}
	}
	
	public Dimension getPosition(){return this.position;}
	public boolean isEndRoom(){return this.isEndRoom;}
	
	public boolean setEndRoom()
	{
		if(this.doorNumber == 4)
			return this.isEndRoom;
		this.isEndRoom = true;
		Random random = new Random();
		int i = random.nextInt(4);
		while(doors[i] == 1)
			i = random.nextInt(4);
		
		Dimension location = null;
		
		switch(i)
		{
		case 1:
			location = new Dimension(this.height - 1, this.width/2);
			break;
		case 0:
			location = new Dimension(0, this.width/2);
			break;
		case 3:
			location = new Dimension(this.height/2, 0);
			break;
		case 2:
			location = new Dimension(this.height/2, this.width - 1);
		}
		
		this.panels[location.width][location.height] = new EndFloorPanel(3, '^');
		return this.isEndRoom;
	}
	
	public void enterRoom(int direction)
	{
		
		if(direction < 0)
			this.position = new Dimension(this.height/2, this.width/2);
		else
		{
			switch(direction)
			{
			case 0:
				this.position = new Dimension(this.height - 2, this.width/2);
				break;
			case 1:
				this.position = new Dimension(1, this.width/2);
				break;
			case 2:
				this.position = new Dimension(this.height/2, 1);
				break;
			case 3:
				this.position = new Dimension(this.height/2, this.width - 2);
			}
		}
		this.open();
	}
	
	public int getDoorNumber(){return this.doorNumber;}
	
	public int getHeight(){return this.height;}
	public int getWidth(){return this.width;}
	
	public int[] getDoors()
	{
		return this.doors;
	}
	
	public void setPanels(Panel[][] room)
	{
		this.panels = room;
	}
	
	public String displayString()
	{
		String room = "";
		
		for(int i = 0; i < this.height; i++)
		{
			for(int j = 0; j < this.width; j++)
			{
				if(position.width == i && position.height == j)
					room += '@';
				else
					room += panels[i][j].getImage();
			}
			room += "\n";
		}
		
		return room;
	}
	
	public boolean update(KeyEvent e, boolean focused)
	{
		if(focused){
			DungeonHandler handler = DungeonHandler.GetInstanceOfDungeonHandler();
			Dimension position = new Dimension(this.position.width, this.position.height);
			
			if(e.getKeyCode() == KeyEvent.VK_W)
				position.width--; 
			else if(e.getKeyCode() == KeyEvent.VK_S)
				position.width++;
			else if(e.getKeyCode() == KeyEvent.VK_D)
				position.height++;
			else if(e.getKeyCode() == KeyEvent.VK_A)
				position.height--;
			else if(e.getKeyCode() == KeyEvent.VK_M)
				handler.getMap().open();
			else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				return true;
			else if(e.getKeyCode() == KeyEvent.VK_ENTER)
				GameEngine.instanceOf().getMenu().open();
			
			if(this.panels[position.width][position.height].canEnter(this))
			{
				this.position = position;
				this.panels[position.width][position.height].onEnter(this);
			}
		}
		return false;
	}

	public String eventString() {
		return "";//this.panels[position.width][position.height].information();
	}

	public void open() {
		Display.getInstanceOfDisplay().setFocus(this);		
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "Room";
	}
}
