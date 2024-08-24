package application.buttons;

import application.card.effects.StatType;
import application.card.entities.RPGCard;
import application.player.entities.RPGPlayer;
import javafx.scene.Group;

public class DiscardButton extends ImageButton {
	
	public DiscardButton(Group myParent, int x, int y) {
		super(myParent,"Button-Discard.png", x, y);
	}

	@Override
	public void doAction() {
		RPGCard card =(RPGCard)getPlayer().getCardClicked();
		if (card != null) {
			// Check if card is a cycle card
			int cycle = card.get(StatType.CYCLE);
			int index=-1;
			if (cycle==1) {
				index=getPlayer().getIndexOfCardInHand(card);
			}
			getPlayer().discardCardFromHand(card);
			if (index>-1) {
				getPlayer().replaceACard(index);
				((RPGPlayer)getPlayer()).addCardsToJavaFxDisplay(this);
			}
		}
	}
}
