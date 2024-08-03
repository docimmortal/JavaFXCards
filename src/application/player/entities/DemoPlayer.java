package application.player.entities;

import application.entities.Character;
import application.Main;
import application.buttons.StartButton;
import application.card.effects.StatType;
import application.card.entities.Card;
import application.entities.Enemy;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DemoPlayer extends Player {

	private Enemy enemy;
	private Enemy enemyClicked;
	private Character characterClicked;
	private boolean initialHandSet;
	private StartButton startButton;
	
	public DemoPlayer( Group group, Stage stage) {
		super(group, stage);
	}
	
	public DemoPlayer(Group group, Stage stage, Character character) {
		super(group, stage, character);
	}

	public final void setStartButton(StartButton startButton) {
		this.startButton = startButton;
	}

	public StartButton getStartButton() {
		return startButton;
	}

	public Enemy getEnemy(int index) {
		return enemy;
	}

	public void setEnemy(int index, Enemy enemy) {
		this.enemy = enemy;
	}
	
	public Enemy getEnemyClicked() {
		return enemyClicked;
	}

	public void setEnemyClicked(Enemy enemyClicked) {
		this.enemyClicked = enemyClicked;
	}
	
	public final Character getCharacterClicked() {
		return characterClicked;
	}

	public final void setCharacterClicked(Character characterClicked) {
		this.characterClicked = characterClicked;
	}

	// When you click on a card, you have not selected an enemy, yet.
	// If there was a enemy targeted by the previous card, we need to clear it out.
	@Override
	public void setCardClicked(Card cardClicked) {
		super.setCardClicked(cardClicked);
		enemyClicked=null;
		characterClicked=null;
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
		for(int i=0; i< absoluteHandSize(); i++) {
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
