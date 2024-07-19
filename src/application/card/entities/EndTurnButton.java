package application.card.entities;

import java.util.List;

import application.Main;
import javafx.scene.Group;

public class EndTurnButton extends ImageButton {

	private Group group;
	
	public EndTurnButton(String filename, int x, int y, Player player, Group group) {
		super(filename, x, y, player);
		this.group=group;
	}
	
	public void doAction() {
		System.out.println("Ended turn. Do stuff.");
		((DemoPlayer)getPlayer()).setPoints(3);
		group.getChildren().set(1, ((DemoPlayer)getPlayer()).getSpellpointsText());

		// Enemy does action
		
		// Draw up cards if the game is not over.
		if (!getPlayer().isGameOver()) {
			List<Card> hand=getPlayer().getHand();
			for (int i=0; i < getPlayer().handSize(); i++) {
				if (hand.get(i) instanceof NoCard) {
					getPlayer().replaceACard(i);
				}
			}
			((DemoPlayer)getPlayer()).addItems(group);
		}

		// Debugging
		System.out.println("Updated points to "+((DemoPlayer)getPlayer()).getPoints());
		System.out.println("Discards:");
		for (Card card: getPlayer().getDiscard()) {
			System.out.println(card);
		}
	}

}
