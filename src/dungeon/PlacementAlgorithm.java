package dungeon;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import Display.Display;

public class PlacementAlgorithm {
	private ArrayList<Room> roomCollection;
	private int placedRooms;
	private int emptyDoors;
	private boolean placedEndRoom;
	private boolean shortcircit;
	
	public PlacementAlgorithm()
	{
		this.placedRooms = 0;
		this.emptyDoors = 0;
		this.placedEndRoom = false;
		this.shortcircit = false;
		this.roomSetup();
		this.startingRoom();
	}
	
	private void startingRoom()
	{
		DungeonHandler handler = DungeonHandler.GetInstanceOfDungeonHandler();
		Random random = new Random();
		int posX = handler.getPosition().height;
		int posY = handler.getPosition().width;
		
		do{
			handler.getMapArray()[posX][posY] = this.roomCollection.get(random.nextInt(this.roomCollection.size()));
		}while(handler.getMapArray()[posX][posY].getDoorNumber() < 3);
		
		handler.setCurrentRoom(handler.getMapArray()[posX][posY]);
		handler.getCurrentRoom().enterRoom(-1);
		this.placedRooms++;
		this.emptyDoors = handler.getCurrentRoom().getDoorNumber();
	}
	
	private void roomSetup()
	{
		RoomFactory factory = new RoomFactory();

		this.roomCollection = factory.Run();
	}
	
	private boolean canMove(DungeonHandler handler, int direction)
	{
		return handler.getCurrentRoom().getDoors()[direction] == 1;
	}
	
	public void generateNextRoom()
	{
		DungeonHandler handler = DungeonHandler.GetInstanceOfDungeonHandler();
		int posX = handler.getPosition().width;
		int posY = handler.getPosition().height;
		int direction;
		Random i = new Random();
		Dimension position = handler.getCurrentRoom().getPosition();
		
		if(position.width == 0)
			direction = 0;
		else if(position.width == handler.getCurrentRoom().getHeight() - 1)
			direction = 1;
		else if(position.height == 0)
			direction = 3;
		else if(position.height == handler.getCurrentRoom().getWidth() - 1)
			direction = 2;
		else
			direction = -1;
		
		if(!canMove(handler, direction))
			return;
		
		this.placeRoom(posX, posY, direction);
		Display.getInstanceOfDisplay().removeFocus(handler.getCurrentRoom());
		handler.setCurrentRoom(handler.getMapArray()[handler.getPosition().width][handler.getPosition().height]);
		
		if((!this.placedEndRoom && (this.placedRooms / handler.getRooms())*100 >= i.nextInt(100)) || (this.shortcircit && !this.placedEndRoom))
			this.placedEndRoom = handler.getCurrentRoom().setEndRoom();
		
		handler.getCurrentRoom().enterRoom(direction);
	}
	
	private boolean placeRoom(int posX, int posY, int dir) {
		// TODO Auto-generated method stub	
		switch(dir)
		{
			case 0:
				posX--;
				break;
			case 1:
				posX++;
				break;
			case 2:
				posY++;
				break;
			case 3:
				posY--;
		}
		
		DungeonHandler.GetInstanceOfDungeonHandler().setPosition(new Dimension(posX, posY));
		
		if(DungeonHandler.GetInstanceOfDungeonHandler().getMapArray()[posX][posY] != null)
			return false;
		
		this.placementAlgorithm(this.doorCheck(posX, posY, dir), posX, posY);
		this.placedRooms++;
		return true;
	}
	
	private void placementAlgorithm(int[] doorCheck, int posX, int posY)
	{
		Random randomGenerator = new Random();
		boolean found = false;
		boolean roomCondition = true;
		Room check = null;
		DungeonHandler handler = DungeonHandler.GetInstanceOfDungeonHandler();
		int loop = 0;
		
		while(!found)
		{
			roomCondition = true;
			check = this.roomCollection.get(randomGenerator.nextInt(this.roomCollection.size()));
			
			for(int i = 0; i < 4 && roomCondition; i++)
			{
				if(doorCheck[i] == 1)
				{
					if(check.getDoors()[i] != 1)
						roomCondition = false;
				}
				else if(doorCheck[i] == -1)
				{
					if(check.getDoors()[i] == 1)
						roomCondition = false;
				}
			}
			
			if(check.getDoorNumber() == 1 && this.emptyDoors == 1)
			{
				if(this.emptyDoors + this.placedRooms != handler.getRooms())
					roomCondition = false;
			}else{
				if((check.getDoorNumber() - 1) + (this.emptyDoors - 1) + this.placedRooms >= handler.getRooms())
				{
					if(loop < 750)
						roomCondition = false;
					else
					{
						this.shortcircit = true;
					}
				}
			}
			
			if(roomCondition)
			{
				DungeonHandler.GetInstanceOfDungeonHandler().getMapArray()[posX][posY] = check;
				found = true;
				this.emptyDoors--;
				this.emptyDoors += check.getDoorNumber() - 1;
			}
			loop++;
		}
	}
	
	private int[] doorCheck(int posX, int posY, int dir)
	{
		int[] doorCheck = {0,0,0,0};
		DungeonHandler handler = DungeonHandler.GetInstanceOfDungeonHandler();
		Dimension size = handler.getSize();
		
		//North
		if(posX - 1 > -1 && handler.getMapArray()[posX - 1][posY] != null)
		{
			if(handler.getMapArray()[posX - 1][posY].getDoors()[1] > 0)
				doorCheck[0] = 1;
			else
				doorCheck[0] = -1;
		}else if(posX - 1 < 0)
			doorCheck[0] = -1;
		//South
		if(posX + 1 < size.height && handler.getMapArray()[posX + 1][posY] != null)
		{
			if(handler.getMapArray()[posX + 1][posY].getDoors()[0] > 0)
				doorCheck[1] = 1;
			else
				doorCheck[1] = -1;
		}else if(posX + 1 == size.height)
			doorCheck[1] = -1;
		//East
		if(posY + 1 < size.height && handler.getMapArray()[posX][posY + 1] != null)
		{
			if(handler.getMapArray()[posX][posY + 1].getDoors()[3] > 0)
				doorCheck[2] = 1;
			else
				doorCheck[2] = -1;
		}else if(posY + 1 == size.width)
			doorCheck[2] = -1;
		//West
		if(posY - 1 > -1 && handler.getMapArray()[posX][posY - 1] != null)
		{
			if(handler.getMapArray()[posX][posY - 1].getDoors()[2] > 0)
				doorCheck[3] = 1;
			else
				doorCheck[3] = -1;
		}else if(posY - 1 == -1)
			doorCheck[3] = -1;
		
		switch(dir)
		{
			case 0:
				doorCheck[1] = 1;
				break;
			case 1:
				doorCheck[0] = 1;
				break;
			case 2:
				doorCheck[3] = 1;
				break;
			case 3:
				doorCheck[2] = 1;
		}
		
		return doorCheck;
	}
}
