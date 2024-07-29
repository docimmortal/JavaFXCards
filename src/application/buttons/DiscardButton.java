package application.buttons;

import application.card.effects.StatType;
import application.card.entities.AnExtendedCard;
import application.player.entities.DemoPlayer;
import application.player.entities.Player;
import javafx.scene.Group;

public class DiscardButton extends ImageButton {

	private Group group;
	
	public DiscardButton(int x, int y, Player player, Group group) {
		super("Button-Discard.png", x, y, player);
		this.group=group;
	}

	@Override
	public void doAction() {
		AnExtendedCard card =(AnExtendedCard)getPlayer().getCardClicked();
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
				((DemoPlayer)getPlayer()).addCardsToJavaFxDisplay(group);
			}
		}
	}
}
