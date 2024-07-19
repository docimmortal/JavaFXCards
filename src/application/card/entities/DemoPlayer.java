package application.card.entities;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DemoPlayer extends Player {

	private int points;
	private Text pointsText;
	private Enemy enemy;
	private Enemy enemyClicked;
	
	public DemoPlayer() {
		super();
		pointsText = new Text("Points: "+points);
		pointsText.setLayoutX(70);
		pointsText.setLayoutY(70);
		pointsText.setFont(new Font(20));
		pointsText.setFill(Color.WHITE);
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
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
	
}
