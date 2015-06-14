package party;

import java.util.ArrayList;
import java.util.Iterator;
import menu.EmptyOption;
import menu.MenuOption;
import character.Figure;

public class Party implements Iterable<MenuOption>{
	
	public static final int MAX_MEMBERS = 4;
	
	private ArrayList<Figure> party;
	
	private class PartyIterator implements Iterator<MenuOption>
	{
		private int position = 0;
		private final int end = party.size();
		
		@Override
		public boolean hasNext() {
			return position < end;
		}

		@Override
		public MenuOption next() {
			if(!this.hasNext())
			{
				return new EmptyOption();
			}
			MenuOption current = party.get(position);
			position++;
			return current;
		}
		
	}
	
	public Party(Figure ... party)
	{
		this.party = new ArrayList<Figure>();
		
		for(Figure figure : party)
			this.addMember(figure);
	} 
	
	public boolean addMember(Figure figure)
	{
		if(this.party.size() < MAX_MEMBERS)
		{
			this.party.add(figure);
			return true;
		}
		return false;
	}
	
	public Figure removeMember(int index)
	{
		Figure figure = this.party.get(index);
		
		this.party.remove(index);
		
		return figure;
	}
	
	public int size()
	{
		return this.party.size();
	}
	
	public Figure getPartyMember(int index)
	{
		return this.party.get(index);
	}

	@Override
	public Iterator<MenuOption> iterator() {
		return new PartyIterator();
	}
}
