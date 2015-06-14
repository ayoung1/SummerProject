package dungeon;



import java.awt.Dimension;
import Display.Display;

public class DungeonHandler {
	private Room[][] map;
	private Room currentRoom;
	private static int rooms;
	private static int height;
	private static int width;
	private MiniMap miniMap;
	private Dimension position;
	private PlacementAlgorithm roomPlacer;

	private static DungeonHandler dungeonHandler;
	
	private DungeonHandler(int height, int width, int rooms)
	{
		DungeonHandler.height = height;
		DungeonHandler.width = width;
		DungeonHandler.rooms = rooms;
		this.map = new Room[DungeonHandler.height][DungeonHandler.width];
		this.miniMap = new MiniMap(this.map, DungeonHandler.height, DungeonHandler.width);
	}
	
	public static void resetDungeon()
	{
		int roomnum = rooms+1;
		int increase = 0;
		
		if(height * width < roomnum*1.3)
			increase++;
		
		Display.getInstanceOfDisplay().removeFocus(DungeonHandler.GetInstanceOfDungeonHandler().getCurrentRoom());
		dungeonHandler = new DungeonHandler(height+increase, width+increase, rooms+1);
	}
	
	public static DungeonHandler GetInstanceOfDungeonHandler()
	{
		if(dungeonHandler == null)
			dungeonHandler = new DungeonHandler(3, 3, 6);
		return dungeonHandler;
	}
	
	public void init()
	{
		int posX = DungeonHandler.height / 2;
		int posY = DungeonHandler.width / 2;
		
		this.position = new Dimension(posX, posY);
		
		this.roomPlacer = new PlacementAlgorithm();
	}
	
	public MiniMap getMap(){
		return this.miniMap;
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}

	public Room[][] getMapArray(){
		return this.map;
	}
	
	public PlacementAlgorithm getPlacement(){
		return this.roomPlacer;
	}
	
	public Dimension getPosition(){return this.position;}
	public Dimension getSize(){return new Dimension(DungeonHandler.height, DungeonHandler.width);}
	
	public void setPosition(Dimension d)
	{
		this.position = d;
	}

	public void setCurrentRoom(Room room) {
		// TODO Auto-generated method stub
		this.currentRoom = room;
	}

	public int getRooms() {
		// TODO Auto-generated method stub
		return this.rooms;
	}
}
