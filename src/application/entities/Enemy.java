package application.entities;

import application.card.entities.Card;
import application.player.entities.DemoPlayer;
import application.player.entities.Player;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Enemy extends Entity{

	private Enemy thisEnemy;
	private int health;
	private int maxHealth;
	
	public Enemy(String filename, Player player, int x, int y, int health) {
		super(filename, player, x, y);
		thisEnemy=this;
		this.health=health;
		this.maxHealth=health;
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
	
	public final int getHealth() {
		return health;
	}

	public final int getMaxHealth() {
		return maxHealth;
	}

	public final void decrementHealth(int thisAmount) {
		if (thisAmount>0) {
			health-=thisAmount;
			if (health<0) {
				health=0;
			}
		}
	}
	
	public final void incrementHealth(int thisAmount) {
		if (thisAmount>0) {
			health-=thisAmount;
			if (health>maxHealth) {
				health=maxHealth;
			}
		}
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
