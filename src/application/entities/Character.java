package application.entities;

import application.fxcomponents.ImageLoader;
import application.player.entities.Player;
import application.utils.TextUtil;

import java.util.ArrayList;
import java.util.List;

import application.card.effects.StatType;
import application.card.entities.Card;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Character extends Entity {

	private Text pointsText;
	private Text statsText;

	private ImageView statsImage;
	
	public Character(String filename, Player player, int health, int attack, int armor, int points, int x, int y) {
		super(filename, player, x, y);
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
	
	public final void resetAll() {
		resetTo(StatType.POINTS, StatType.MAX_POINTS);
		resetToZero(StatType.ATTACK);
		resetToZero(StatType.ARMOR);
	}
	
	public final Text getSpellpointsText() {
		return pointsText;
	}

	public void setPointsText() {
		pointsText.setText("Spell points: "+get(StatType.POINTS));
	}
	
	@Override
	public void updateScreenText() {
		setStatsText();
		setPointsText();
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
	
	/*
	 * Methods that can be overridden
	 */
	
	public List<Card> getInitialDeck(){
		System.out.println("===========[ NO INITIAL DECK ]===========");
		return new ArrayList<>();
	}
}
