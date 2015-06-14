package dungeon;
public class EndFloorPanel extends Panel{

	public EndFloorPanel(int id, char image) {
		super(id, image);
		// TODO Auto-generated constructor stub
	}

	public void onEnter(Object entering) {
		DungeonHandler.resetDungeon();
		DungeonHandler.GetInstanceOfDungeonHandler().init();
	}

	public boolean canEnter(Object entering) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String information() {
		// TODO Auto-generated method stub
		return "";
	}

}
