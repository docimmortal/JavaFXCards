package application.card.entities;

import javafx.scene.Group;

// Used for when card was played and moved to the discard pile.
// Replace the card in hand with this one.
public final class NoCard extends Card {

	public NoCard(Group myParent, String num) {
		super(myParent, "\\images\\cards\\no-card.png", "No card");
		setId("NoCard"+num);
	}
	
	@Override
	public String toString() {
		return getCardName();
	}

}
