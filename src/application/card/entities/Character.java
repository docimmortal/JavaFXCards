package application.card.entities;

import application.fxcomponents.ImageLoader;
import application.utils.TextUtil;

import java.util.HashMap;
import java.util.Map;

import application.card.effects.StatType;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Character extends Entity {

	//private int points;
	//private int health;
	//private int maxHealth;
	//private int attack;
	//private int armor;
	private Text pointsText;
	private Text statsText;
	//private int maxPoints;
	private Map<StatType,Integer> statMap;

	private ImageView statsImage;
	
	public Character(String filename, Player player, int health, int attack, int armor, int points, int x, int y) {
		super(filename, player, x, y);
		statMap = new HashMap<>();
		//this.points=points;
		statMap.put(StatType.POINTS, points);
		pointsText = TextUtil.initText("Spell points: "+points, 70, 70);
		//this.health=health;
		statMap.put(StatType.HEALTH, health);
		//maxHealth=health;
		statMap.put(StatType.MAX_HEALTH, health);
		//maxPoints=points;
		statMap.put(StatType.MAX_POINTS, points);
		//this.attack=attack;
		statMap.put(StatType.ATTACK, attack);
		//this.armor=armor;
		statMap.put(StatType.ARMOR, armor);
		//points=maxPoints;
		setStatsText();
		
		statsImage = ImageLoader.load("images//characters//stats-images.png",false);
		statsImage.setLayoutX(140);
		statsImage.setLayoutY(getLowerYPlusOffset()-22);
	}
	
	public final int getHealth() {
		//return health;
		return statMap.get(StatType.HEALTH);
	}
	
	public final void decrementHealth(int amount) {
		if (amount>0) {
			int lHealth=statMap.get(StatType.HEALTH);
			/*health-=amount;
			 * if (health<0) {
				health=0;
			}*/
			lHealth-=amount;
			if (lHealth<0) {
				lHealth=0;
			}
			statMap.put(StatType.HEALTH, lHealth);
			setStatsText();
		}
	}
	
	public final void incrementHealth(int amount) {
		if (amount>0) {
			int lHealth=statMap.get(StatType.HEALTH);
			/*
			health+=amount;
			if (health>maxHealth) {
				health=maxHealth;
			}*/
			lHealth+=amount;
			int lMaxHealth=statMap.get(StatType.MAX_HEALTH);
			if (lHealth>lMaxHealth) {
				lHealth=lMaxHealth;
			}
			statMap.put(StatType.HEALTH, lHealth);
			setStatsText();
		}
	}
	
	public final int getMaxHealth() {
		//return maxHealth;
		return statMap.get(StatType.MAX_HEALTH);
	}
	
	public final void setMaxHealth(int maxHealth) {
		//this.maxHealth=maxHealth;
		statMap.put(StatType.MAX_HEALTH,maxHealth);
	}
	
	public final void healToFullHealth() {
		statMap.put(StatType.HEALTH,statMap.get(StatType.MAX_HEALTH));
		//health=maxHealth;
		setStatsText();
	}
	
	public final int getArmor() {
		//return armor;
		return statMap.get(StatType.ARMOR);
	}
	
	public final void incrementArmor(int thisValue) {
		if (thisValue>0) {
			int lArmor=statMap.get(StatType.ARMOR);
			//armor+=thisValue;
			lArmor+=thisValue;
			statMap.put(StatType.ARMOR, lArmor);
			setStatsText();
		}
	}
	
	public final void decrementArmor(int thisValue) {
		if (thisValue>0) {
			int lArmor=statMap.get(StatType.ARMOR);
			//armor-=thisValue;
			lArmor-=thisValue;
			statMap.put(StatType.ARMOR, lArmor);
			setStatsText();
		}
	}
	
	public final void resetArmor() {
		//armor=0;
		statMap.put(StatType.ARMOR, 0);
		setStatsText();
	}
	

	public final int getAttack() {
		//return attack;
		return statMap.get(StatType.ATTACK);
	}
	
	public final void incrementAttack(int thisValue) {
		int lAttack=statMap.get(StatType.ATTACK);
		if (thisValue>0) {
			//attack+=thisValue;
			lAttack+=thisValue;
			statMap.put(StatType.ATTACK, lAttack);
			setStatsText();
		}
	}
	
	public final void decrementAttack(int thisValue) {
		if (thisValue>0) {
			int lAttack=statMap.get(StatType.ATTACK);
			//attack-=thisValue;
			lAttack-=thisValue;
			statMap.put(StatType.ATTACK, lAttack);
			setStatsText();
		}
	}
	
	public final void resetAttack() {
		//attack=0;
		statMap.put(StatType.ATTACK, 0);
		setStatsText();
	}

	public final int getPoints() {
		//return points;
		return statMap.get(StatType.POINTS);
	}

	public final void setPoints(int points) {
		//this.points = points;
		statMap.put(StatType.POINTS, points);
		setPointsText();
	}
	
	public final void decrementPoints(int thesePoints) {
		int lPoints=statMap.get(StatType.POINTS);
		//if (thesePoints <= points) {
		if (thesePoints <= lPoints) {
			//points-=thesePoints;
			lPoints-=thesePoints;
			statMap.put(StatType.POINTS, lPoints);
			setPointsText();
		}
	}
	
	public final void incrementPoints(int thesePoints) {
		if (thesePoints > 0) {
			int lPoints=statMap.get(StatType.POINTS);
			//points+=thesePoints;
			lPoints+=thesePoints;
			statMap.put(StatType.POINTS, lPoints);
			setPointsText();
		}
	}
	
	public final void resetPoints() {
		statMap.put(StatType.POINTS, statMap.get(StatType.MAX_POINTS));
		setPointsText();
	}
	
	public final void resetAll() {
		//points=maxPoints;
		resetPoints();
		//armor=0;
		 resetAttack();
		//attack=0;
		resetArmor();
		//setPointsText();
		//setStatsText();
	}
	
	public final int getMaxPoints() {
		//return maxPoints;
		return statMap.get(StatType.MAX_POINTS);
	}

	public final Text getSpellpointsText() {
		return pointsText;
	}

	public void setPointsText() {
		//pointsText.setText("Spell points: "+points);
		pointsText.setText("Spell points: "+statMap.get(StatType.POINTS));
	}
	
	private void setStatsText() {
		//String healthStr=String.format("%3d",health);
		int lHealth=statMap.get(StatType.HEALTH);
		String healthStr=String.format("%3d",lHealth);
		int hpLen=8;
		if (lHealth>0)
			hpLen-=(int)Math.log(lHealth);
			//hpLen-=(int)Math.log(health);
		String spacing1=String.format("%"+hpLen+"s", "");
		//String attackStr=String.format("%3d",attack);
		int lAttack=statMap.get(StatType.ATTACK);
		String attackStr=String.format("%3d",lAttack);
		int attLen=7;
		if (lAttack>9)
			attLen-=1;
		if (lAttack>99)
			attLen-=1;
		System.out.println("--------------->AttLen:"+attLen);
		String spacing2=String.format("%"+attLen+"s", "");
		int lArmor=statMap.get(StatType.ARMOR);
		//String armorStr=String.format("%3d",armor);
		String armorStr=String.format("%3d",lArmor);
		String msg=healthStr+spacing1+attackStr+spacing2+armorStr;
		statsText = TextUtil.initText(msg, 140, getLowerYPlusOffset());
	}
	
	private int getLowerYPlusOffset() {
		return (int)getImageView().boundsInParentProperty().get().getMaxY()+25;
	}
	
	/*
	private int getLowerXCenterMinusOffset() {
		int maxX= (int)getImageView().boundsInParentProperty().get().getMaxX();
		int minX= (int)getImageView().boundsInParentProperty().get().getMinX();
		return minX+(maxX-minX)/2;
	}*/
	
	public final Text getStatsText() {
		return statsText;
	}
	
	public ImageView getStatsImage() {
		return statsImage;
	}
}
