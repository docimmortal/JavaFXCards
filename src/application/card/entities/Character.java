package application.card.entities;

import application.utils.TextUtil;
import javafx.scene.text.Text;

public class Character extends Entity {

	private int points;
	private int health;
	private int maxHealth;
	private int attack;
	private int armor;
	private Text pointsText;
	private Text statsText;
	private int maxPoints;
	
	public Character(String filename, Player player, int health, int attack, int armor, int points, int x, int y) {
		super(filename, player, x, y);
		this.points=points;
		pointsText = TextUtil.initText("Spell points: "+points, 70, 70);
		setStatsText();
		this.health=health;
		maxHealth=health;
		maxPoints=points;
		this.attack=attack;
		this.armor=armor;
		points=maxPoints;
	}
	
	public final int getHealth() {
		return health;
	}
	
	public final void decrementHealth(int amount) {
		if (amount>0) {
			health-=amount;
			if (health<0) {
				health=0;
			}
			setStatsText();
		}
	}
	
	public final void incrementHealth(int amount) {
		if (amount>0) {
			health+=amount;
			if (health>maxHealth) {
				health=maxHealth;
			}
			setStatsText();
		}
	}
	
	public final int getMaxHealth() {
		return maxHealth;
	}
	
	public final void setMaxHealth(int maxHealth) {
		this.maxHealth=maxHealth;
	}
	
	public final void healToFullHealth() {
		health=maxHealth;
		setStatsText();
	}

	public final int getAttack() {
		return attack;
	}
	
	public final void incrementArmor(int thisValue) {
		if (thisValue>0) {
			armor+=thisValue;
			setStatsText();
		}
	}
	
	public final void decrementArmor(int thisValue) {
		if (thisValue>0) {
			armor-=thisValue;
			setStatsText();
		}
	}
	
	public final void setArmorZero() {
		armor=0;
		setStatsText();
	}
	
	public final void incrementAttack(int thisValue) {
		if (thisValue>0) {
			attack+=thisValue;
			setStatsText();
		}
	}
	
	public final void decrementAttack(int thisValue) {
		if (thisValue>0) {
			attack-=thisValue;
			setStatsText();
		}
	}
	
	public final void setAttackZero() {
		attack=0;
		setStatsText();
	}

	public final int getArmor() {
		return armor;
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
	
	private void setStatsText() {
		 statsText = TextUtil.initText(health+"  "+armor+"  "+attack, 120, 120);
	}
	
	public final Text getStatsText() {
		return statsText;
	}

}
