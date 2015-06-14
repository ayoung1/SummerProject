package Display;

import java.awt.event.KeyEvent;

public class Keys {

	public static boolean keyW(KeyEvent e)
	{
		return e.getKeyCode() == KeyEvent.VK_W;
	}
	
	public static boolean keyA(KeyEvent e)
	{
		return e.getKeyCode() == KeyEvent.VK_A;
	}
	
	public static boolean keyS(KeyEvent e)
	{
		return e.getKeyCode() == KeyEvent.VK_S;
	}
	
	public static boolean keyD(KeyEvent e)
	{
		return e.getKeyCode() == KeyEvent.VK_D;
	}
	
	public static boolean keyEnter(KeyEvent e)
	{
		return e.getKeyCode() == KeyEvent.VK_ENTER;
	}
	
	public static boolean keyEscape(KeyEvent e)
	{
		return e.getKeyCode() == KeyEvent.VK_ESCAPE;
	}
}
