package application.card.entities;

// Used for when card was played and moved to the discard pile.
// Replace the card in hand with this one.
public final class NoCard extends Card {

	public NoCard(Player player) {
		super("\\images\\cards\\no-card.png", player);
	}
	
	@Override
	public String toString() {
		return "no card";
	}

}
