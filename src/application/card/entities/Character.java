package application.card.entities;

import application.fxcomponents.ImageLoader;
import application.utils.TextUtil;

import java.util.HashMap;
import java.util.Map;

import application.card.effects.StatType;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Character extends Entity {

	private Text pointsText;
	private Text statsText;
	private Map<StatType,Integer> statMap;

	private ImageView statsImage;
	
	public Character(String filename, Player player, int health, int attack, int armor, int points, int x, int y) {
		super(filename, player, x, y);
		statMap = new HashMap<>();
		set(StatType.POINTS, points);
		set(StatType.MAX_POINTS, points);
		pointsText = TextUtil.initText("Spell points: "+points, 70, 70);
		set(StatType.HEALTH, health);
		set(StatType.MAX_HEALTH, health);
		set(StatType.ATTACK, attack);
		set(StatType.ARMOR, armor);
		setStatsText();
		
		statsImage = ImageLoader.load("images//characters//stats-images.png",false);
		statsImage.setLayoutX(140);
		statsImage.setLayoutY(getLowerYPlusOffset()-22);
	}
	
	public final int get(StatType statType) {
		return statMap.get(statType);
	}
	
	public final void set(StatType statType, int value) {
		statMap.put(statType, value);
	}
	
	public final void resetToZero(StatType statType) {
		set(statType, 0);
		setStatsText();
		setPointsText();
	}
	
	public final void resetTo(StatType statType, StatType newValue) {
		set(statType, get(newValue));
		setStatsText();
		setPointsText();
	}
	
	// set minAmount to -1 if there is no minAmount.
	public final void decrement(StatType statType, int amount, int minAmount) {
		if (amount>0) {
			int value=get(statType);
			value-=amount;
			if (minAmount>=0 && value<minAmount) {
				value=minAmount;
			}
			statMap.put(statType, value);
			setStatsText();
			setPointsText();
		}
	}
	
	// set maxAmount to -1 if there is no maxAmount.
	public final void increment(StatType statType, int amount, int maxAmount) {
		if (amount>0) {
			int value=get(statType);
			value+=amount;
			if (maxAmount>0 && amount>maxAmount) {
				value=maxAmount;
			}
			statMap.put(statType, value);
			setStatsText();
			setPointsText();
		}
	}
	
	public final void resetAll() {
		resetTo(StatType.POINTS, StatType.MAX_POINTS);
		resetToZero(StatType.ATTACK);
		resetToZero(StatType.ARMOR);
	}
	
	public final Text getSpellpointsText() {
		return pointsText;
	}

	public void setPointsText() {
		pointsText.setText("Spell points: "+statMap.get(StatType.POINTS));
	}
	
	private void setStatsText() {
		int lHealth=get(StatType.HEALTH);
		String healthStr=String.format("%3d",lHealth);
		int hpLen=8;
		if (lHealth>0)
			hpLen-=(int)Math.log(lHealth);
		String spacing1=String.format("%"+hpLen+"s", "");
		int lAttack=get(StatType.ATTACK);
		String attackStr=String.format("%3d",lAttack);
		int attLen=7;
		if (lAttack>9)
			attLen-=1;
		if (lAttack>99)
			attLen-=1;
		System.out.println("--------------->AttLen:"+attLen);
		String spacing2=String.format("%"+attLen+"s", "");
		int lArmor=get(StatType.ARMOR);
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
