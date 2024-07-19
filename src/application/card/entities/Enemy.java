package application.card.entities;

import application.fxcomponents.ImageLoader;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Enemy {

	private ImageView imageView;
	private Player player;
	private Enemy thisEnemy;
	
	public Enemy(String filename, Player player, int x, int y) {
		thisEnemy=this;
		imageView = ImageLoader.load(filename,false);
		imageView.setLayoutX(x);
		imageView.setLayoutY(y);
		imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	    	@Override
	        public void handle(MouseEvent event) {
	    		if (canTarget()) {
	    			System.out.println("Clicked monster");
	    			((DemoPlayer)player).setEnemyClicked(thisEnemy);
	    			doTargetAction();
	    		}
	    	}
		});
		this.player=player;
	}
	
	public ImageView getImageView() {
		return imageView;
	}
	
	public boolean canTarget() {
		boolean canTarget=false;
		if (player.getCardClicked() != null) {
			System.out.println("We can target the enemy.");
			canTarget=true;
		} else {
			System.out.println("Click a valid card.");
		}
		return canTarget;
	}
	
	public void doTargetAction() {
		Card card=player.getCardClicked();
		if (card!=null) {
			System.out.println("Used the card.");
			card.setUsed(true);
			card.useTheCard();
			card.hideCardImage();
		}
		
	}

	public Player getPlayer() {
		return player;
	}
}
