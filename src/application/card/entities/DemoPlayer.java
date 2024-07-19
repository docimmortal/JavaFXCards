package application.card.entities;

import application.Main;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DemoPlayer extends Player {

	private int points;
	private int health;
	private int maxHealth;
	private Text pointsText;
	private Enemy enemy;
	private Enemy enemyClicked;
	private Group group;
	private boolean initialHandSet;
	
	public DemoPlayer(Group group) {
		super();
		pointsText = new Text("Points: "+points);
		pointsText.setLayoutX(70);
		pointsText.setLayoutY(70);
		pointsText.setFont(new Font(20));
		pointsText.setFill(Color.WHITE);
		this.group=group;
		health=20;
		maxHealth=20;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void decrementHealth(int amount) {
		health-=amount;
	}
	
	public void incrementHealth(int amount) {
		health+=amount;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void healToFullHealth() {
		health=maxHealth;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
		pointsText.setText("Points: "+points);
	}
	
	public void subtract(int thesePoints) {
		if (thesePoints <= points) {
			points-=thesePoints;
			pointsText.setText("Points: "+points);
		}
	}
	
	public Text getSpellpointsText() {
		return pointsText;
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
		if (health <=0) {
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
