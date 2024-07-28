package application.player.entities;

import application.Main;
import application.card.effects.StatType;
import application.card.entities.Card;
import application.entities.Enemy;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DemoPlayer extends Player {

	private Enemy enemy;
	private Enemy enemyClicked;
	private boolean initialHandSet;
	
	public DemoPlayer(Group group, Stage stage) {
		super(group, stage);
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
	@Override
	public void setCardClicked(Card cardClicked) {
		super.setCardClicked(cardClicked);
		enemyClicked=null;
	}
	
	@Override
	public void updateDiscardCardImage(int index) {
		getGroup().getChildren().set(Main.FIRST_CARD_INDEX+index, getHand().get(index).getImageView());
	}
	
	@Override
	public boolean isGameOver() {
		boolean gameOver=false;
		if (getCharacter().get(StatType.HEALTH) <=0) {
			gameOver=true;
		}
		return gameOver;
	}
	
	public void addCardsToJavaFxDisplay(Group group) {
		for(int i=0; i< handSize(); i++) {
			ImageView imageView = viewCardInHand(i).getImageView();
			imageView.setLayoutX(50+200*i);
			imageView.setLayoutY(600);
			if (initialHandSet) {
				group.getChildren().set(Main.FIRST_CARD_INDEX+i, imageView);
			} else {
				group.getChildren().add(imageView);
			}
		}
		initialHandSet=true;
	}
	
}
