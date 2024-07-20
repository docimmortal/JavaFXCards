package application.card.entities;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Character extends Entity {

	private int points;
	private int health;
	private int maxHealth;
	private Text pointsText;
	private int maxPoints;
	
	public Character(String filename, Player player, int x, int y) {
		super(filename, player, x, y);
		pointsText = new Text("Points: "+points);
		pointsText.setLayoutX(70);
		pointsText.setLayoutY(70);
		pointsText.setFont(new Font(20));
		pointsText.setFill(Color.WHITE);
		health=20;
		maxHealth=20;
		maxPoints=3;
		points=maxPoints;
	}
	
	public final int getHealth() {
		return health;
	}
	
	public final void decrementHealth(int amount) {
		health-=amount;
	}
	
	public final void incrementHealth(int amount) {
		health+=amount;
	}
	
	public final int getMaxHealth() {
		return maxHealth;
	}
	
	public final void setMaxHealth(int maxHealth) {
		this.maxHealth=maxHealth;
	}
	
	public final void healToFullHealth() {
		health=maxHealth;
	}

	public final int getPoints() {
		return points;
	}

	public final void setPoints(int points) {
		this.points = points;
		pointsText.setText("Points: "+points);
	}
	
	public final void decrementPoints(int thesePoints) {
		if (thesePoints <= points) {
			points-=thesePoints;
			pointsText.setText("Points: "+points);
		}
	}
	
	public final void incrementPoints(int thesePoints) {
		if (thesePoints > 0) {
			points+=thesePoints;
			pointsText.setText("Points: "+points);
		}
	}
	
	public final void resetPoints() {
		points=maxPoints;
	}
	
	public final int getMaxPoints() {
		return maxPoints;
	}

	public final Text getSpellpointsText() {
		return pointsText;
	}

}
