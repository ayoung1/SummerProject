package Display;



import java.awt.event.KeyEvent;

/*
 * Focusable Interface v.1.0
 * 
 * Required interface for any class wanting to interact with the display
 * 
 * 	open()
 * 		the way the object opens itsself into the Display
 * 
 * 	String displayString()
 * 		This method is called when the display updates or gets loaded a new Focusable
 * 		It returns the string to be displayed
 * 
 * 	String eventString()
 * 		This method is called when the display updates or gets loaded a new Focusable
 * 		It returns the string to be displayed to the lower display
 * 
 * 	boolean update(KeyEvent, boolean)
 * 		This method is called by the display to each object in the display stack. It passes
 * 		the key code of the keyboard button pressed as well as a boolean of if the object being
 * 		updated is currently being displayed to the user. This allows for any object to handle background
 * 		process differently than when displayed. The return value is true when the object wants to close from the 
 * 		display and false when it doesn't
 * 
 */

public interface Focusable {

	public void open();
	public String displayString();
	public String eventString();
	public String getName();
	public boolean update(KeyEvent e, boolean focused);
	
}
