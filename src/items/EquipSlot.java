package items;

public enum EquipSlot {
	WEAPON("Weapon"), ARMOR("Armor");
	
	public final String name;
	
	private EquipSlot(String name)
	{
		this.name = name;
	}
}