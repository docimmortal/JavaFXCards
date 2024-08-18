package application.player.entities;

import application.entities.Character;
import application.card.effects.StatType;
import application.card.entities.Card;
import application.entities.Enemy;
import application.screens.MapScreen;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DemoPlayer extends Player {

	private Enemy enemy;
	private Enemy enemyClicked;
	private Character characterClicked;
	private boolean initialHandSet;
	private MapScreen mapScreen;
	
	public DemoPlayer(Group myParent, Stage stage) {
		super(myParent, stage);
		setId("Player");
		initialHandSet=false;
	}

	public final MapScreen getMapScreen() {
		return mapScreen;
	}

	public final void setMapScreen(MapScreen mapScreen) {
		this.mapScreen = mapScreen;
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
	public boolean isGameOver() {
		boolean gameOver=false;
		if (getCharacter().get(StatType.HEALTH) <=0) {
			gameOver=true;
		}
		return gameOver;
	}
	
	public void setNoInitialHandSet() {
		initialHandSet=false;
	}
	
	public void addCardsToJavaFxDisplay(Group group) {
		int firstCardIndex=0;
		if (initialHandSet) {
			Node node=myParent.lookup("#Card1");
			if (node==null) {
				node=myParent.lookup("#NoCard1");
			}
			firstCardIndex=myParent.getChildren().indexOf(node);
		}
		for(int i=0; i< absoluteHandSize(); i++) {
			ImageView imageView = viewCardInHand(i).getImageView();
			imageView.setLayoutX(50+200*i);
			imageView.setLayoutY(600);
			imageView.setId("Card"+(i+1)+"-Image");
			if (initialHandSet) {
				myParent.getChildren().set(firstCardIndex+i*2, viewCardInHand(i));
				myParent.getChildren().set(firstCardIndex+i*2+1, imageView);
			} else {
				myParent.getChildren().add(viewCardInHand(i));
				myParent.getChildren().add(imageView);
			}
		}
		initialHandSet=true;
	}
	
}
