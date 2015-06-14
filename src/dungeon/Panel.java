package dungeon;


public abstract class Panel {
	private int id;
	private char image;
	
	public Panel(int id, char image)
	{
		this.id = id;
		this.image = image;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public char getImage()
	{
		return this.image;
	}
	
	public void drawPanel()
	{
		System.out.print(this.image);
	}
	
	public abstract void onEnter(Object entering);
	public abstract boolean canEnter(Object entering);
	public abstract String information();
}
