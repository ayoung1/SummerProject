package character;

public class Warrior extends PlayerFigure{

	private final static int health = 550;
	private final static int strength = 26;
	private final static int agility = 14;
	private final static int intelligence = 9;
	
	public Warrior(String name) {
		super(name, "Warrior", health, strength, agility, intelligence);
	}

	@Override
	protected void levelUp() {
		
	}

}
