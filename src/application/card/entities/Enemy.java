package application.card.entities;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Enemy extends Entity{

	private Enemy thisEnemy;
	
	public Enemy(String filename, Player player, int x, int y) {
		super(filename, player, x, y);
		thisEnemy=this;
		getImageView().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	    	@Override
	        public void handle(MouseEvent event) {
	    		if (canTarget()) {
	    			System.out.println("Clicked monster");
	    			((DemoPlayer)player).setEnemyClicked(thisEnemy);
	    			doTargetAction();
	    		}
	    	}
		});
	}
	
	/*
	 * Methods that can be overridden
	 */
	public boolean canTarget() {
		boolean canTarget=false;
		if (getPlayer().getCardClicked() != null) {
			System.out.println("We can target the enemy.");
			canTarget=true;
		} else {
			System.out.println("Click a valid card.");
		}
		return canTarget;
	}
	
	public void doTargetAction() {
		Card card=getPlayer().getCardClicked();
		if (card!=null) {
			System.out.println("Used the card.");
			card.useTheCard();
		}
		
	}

}
