package menu;

import party.Party;
import Display.*;

public class PartyMenu extends Menu implements Focusable, MenuOption{

	private Party party;
	
	public PartyMenu(Party party) {
		super("Party", party);

		this.party = party;
	}

	@Override
	protected int getSize() {
		return this.party.size();
	}

	@Override
	protected MenuOption getIndex(int index) {
		return this.party.getPartyMember(index);
	}

	@Override
	public String menuString() {
		return this.getName();
	}

	@Override
	public void display() {
		Display.getInstanceOfDisplay().setFocus(this);		
	}

	
}
