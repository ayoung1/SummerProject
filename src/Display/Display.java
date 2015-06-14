package Display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/*
*Display Class v.1.01
*
*Changes - Added a very poor workaround to modification of an iterating collection
*
*Takes a focusable object and displays its content to the screen
*
*Supports two display types, a main display and a smaller description display
*
*	getInstanceOfDisplay()
*		Returns the singleton instance of the display or creates the singletion
*		if no display exisits
*
*	setFocus(Focusable)
*		Takes in a focusable object and places it 'in charge' of the display
*
*	Key events shouldn't be called by any outside class, unpredictable results
*/

public class Display extends JFrame implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 32272931641610214L;
	/**
	 * 
	 */
	public final int HEIGHT = 500;
	public final int WIDTH = 750;
	
	private static Display gameDisplay;
	private Stack<Focusable> focus;
	
	private JTextPane display;
	private JTextPane eventDisplay;
	private JPanel contentPane;
	
	private Display()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
		this.createContentPane(5, 5, 5, 5);
		this.focus = new Stack<Focusable>();
		
		this.display = this.createTextPane(this.createDefaultStyle(), 10, 10, 720, 350);
		this.eventDisplay = this.createTextPane(this.createDefaultStyle(), 10, 370, 720, 90);
		
		this.display.addKeyListener(this);
		this.eventDisplay.addKeyListener(this);
		this.setResizable(false);
		this.setTitle("Game");
		this.pack();
		this.setVisible(true);		
	}
	
	private StyledDocument createDefaultStyle()
	{
		StyledDocument temp = new DefaultStyledDocument();
		Style tempStyle = temp.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setAlignment(tempStyle, StyleConstants.ALIGN_CENTER);
		
		return temp;
	}
	
	private JTextPane createTextPane(StyledDocument document, int x, int y, int h, int w)
	{
		JTextPane temp = new JTextPane(document);
		temp.setForeground(Color.WHITE);
		temp.setBackground(Color.BLACK);
		temp.setBounds(x,y,h,w);
		temp.setFont(new Font("Courier", Font.PLAIN, 16));
		temp.setEditable(false);
		this.contentPane.add(temp);
		
		return temp;
	}
	
	private void createContentPane(int x, int y, int h, int w)
	{
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(x,y,h,w));
		this.setContentPane(contentPane);
		this.contentPane.setLayout(null);
	}
	
	public static Display getInstanceOfDisplay(){
		if(gameDisplay == null)
			gameDisplay = new Display();
		return gameDisplay;
	}

	private void toScreen(){
		this.display.setText(this.focus.firstElement().displayString());
		this.eventDisplay.setText(this.focus.firstElement().eventString());
	}
	
	public void setFocus(Focusable focus)
	{
		
		this.focus.add(0, focus);
		
		this.toScreen();
	}
	
	public void removeFocus(Focusable focusable)
	{
		this.focus.remove(focusable);
	}
	
	@SuppressWarnings("unchecked")
	public void keyPressed(KeyEvent arg0) 
	{
		Stack<Focusable> toBeUpdated = (Stack<Focusable>) this.focus.clone();
		Stack<Focusable> toBeRemoved = new Stack<Focusable>();
		for(Focusable focusable : toBeUpdated)
		{
			if(focusable.update(arg0, focusable == this.focus.firstElement()) && this.focus.size() > 1)
				toBeRemoved.add(focusable);
		}
		
		for(Focusable focusable : toBeRemoved)
		{
			this.removeFocus(focusable);
		}
		this.toScreen();
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
