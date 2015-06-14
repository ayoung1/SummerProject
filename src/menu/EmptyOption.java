package menu;

public class EmptyOption implements MenuOption{

	@Override
	public String menuString() {
		return "";
	}

	@Override
	public void display() {}

}
