package dungeon;

public class DoorPanel extends Panel{

	public DoorPanel(int id, char image) {
		super(id, image);
		// TODO Auto-generated constructor stub
	}

	public void onEnter(Object entering) {
		DungeonHandler.GetInstanceOfDungeonHandler().getPlacement().generateNextRoom();
	}

	public boolean canEnter(Object entering) {
		return true;
	}

	@Override
	public String information() {
		return "";
	}

}
