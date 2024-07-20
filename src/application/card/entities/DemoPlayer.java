package application.card.entities;

import application.Main;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class DemoPlayer extends Player {

	private Enemy enemy;
	private Enemy enemyClicked;
	private Group group;
	private boolean initialHandSet;
	
	public DemoPlayer(Group group) {
		super();
		this.group=group;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
	
	public Enemy getEnemyClicked() {
		return enemyClicked;
	}

	public void setEnemyClicked(Enemy enemyClicked) {
		this.enemyClicked = enemyClicked;
	}
	
	// When you click on a card, you have not selected an enemy, yet.
	// If there was a enemy targeted by the previous card, we need to clear it out.
	public void setCardClicked(Card cardClicked) {
		super.setCardClicked(cardClicked);
		enemyClicked=null;
	}
	
	@Override
	public void updateDiscardCardImage(int index) {
		group.getChildren().set(Main.FIRST_CARD_INDEX+index, getHand().get(index).getImageView());
	}
	
	@Override
	public boolean isGameOver() {
		boolean gameOver=false;
		if (getCharacter().getHealth() <=0) {
			gameOver=true;
		}
		return gameOver;
	}
	
	public void addItems(Group group) {
		for(int i=0; i< handSize(); i++) {
			ImageView imageView = viewCardInHand(i).getImageView();
			imageView.setLayoutX(50+200*i);
			imageView.setLayoutY(600);
			if (initialHandSet) {
				System.out.println(">>>>Update "+((AnExtendedCard)viewCardInHand(i)).getCardName());
				group.getChildren().set(Main.FIRST_CARD_INDEX+i, imageView);
			} else {
				System.out.println(">>>>>Add "+((AnExtendedCard)viewCardInHand(i)).getCardName());
				group.getChildren().add(imageView);
			}
		}
		initialHandSet=true;
	}
	
}
