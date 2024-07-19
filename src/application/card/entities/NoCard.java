package application.card.entities;

// Used for when card was played and moved to the discard pile.
// Replace the card in hand with this one.
public class NoCard extends Card {

	public NoCard(Player player) {
		super("/images/no-card.png", player);
	}
	
	@Override
	public String toString() {
		return "no card";
	}

}
