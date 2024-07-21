package application.buttons;

import java.util.List;

import application.card.entities.Card;
import application.card.entities.NoCard;
import application.player.entities.DemoPlayer;
import application.player.entities.Player;
import javafx.scene.Group;

public class EndTurnButton extends ImageButton {

	private Group group;
	
	public EndTurnButton(String filename, int x, int y, Player player, Group group) {
		super(filename, x, y, player);
		this.group=group;
	}
	
	public void doAction() {
		System.out.println("Ended turn. Do stuff.");
		// Player does action
		
		// Enemy does action
		
		// Player reset
		((DemoPlayer)getPlayer()).getCharacter().resetAll();
		group.getChildren().set(1, ((DemoPlayer)getPlayer()).getCharacter().getSpellpointsText());
		group.getChildren().set(5, ((DemoPlayer)getPlayer()).getCharacter().getStatsText());

		// Enemy reset
		
		// If Enemy is dead, flag new button to display ("hunt") and do not draw cards
		
		// Draw up cards if the game is not over.
		if (!getPlayer().isGameOver()) {
			List<Card> hand=getPlayer().getHand();
			for (int i=0; i < getPlayer().handSize(); i++) {
				if (hand.get(i) instanceof NoCard) {
					getPlayer().replaceACard(i);
				}
			}
			((DemoPlayer)getPlayer()).addCardsToJavaFxDisplay(group);
		}

		/* Debugging
		System.out.println("Updated points to "+((DemoPlayer)getPlayer()).getCharacter().get(StatType.POINTS));
		System.out.println("Discards:");
		for (Card card: getPlayer().getDiscard()) {
			System.out.println(card);
		}*/
	}

}
