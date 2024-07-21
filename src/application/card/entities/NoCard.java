package application.card.entities;

import application.player.entities.Player;

// Used for when card was played and moved to the discard pile.
// Replace the card in hand with this one.
public final class NoCard extends Card {

	public NoCard(Player player) {
		super("\\images\\cards\\no-card.png", "No card", player);
	}
	
	@Override
	public String toString() {
		return getCardName();
	}

}
