package application.card.entities;

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
		System.out.println("Updated points to "+((DemoPlayer)getPlayer()).getPoints());
		group.getChildren().set(1, ((DemoPlayer)getPlayer()).getSpellpointsText());
		System.out.println("Discards:");
		for (Card card: getPlayer().getDiscard()) {
			System.out.println(card);
		}
	}

}
