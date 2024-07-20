package application.card.entities;

import entities.card.Target;
import javafx.scene.Group;

public class AnExtendedCard extends Card {

	private String cardName;
	private int cost;
	private Group group;
	private Target target;
	private int damage;
	private int block;
	private int cardNo;
	private static int staticNo=1;
	
	public AnExtendedCard(String filename, Target target, Player player, Group group, String cardName, int cost) {
		super(filename, player);
		this.cardName=cardName;
		this.target=target;
		this.cost=cost;
		this.group=group;
		cardNo=staticNo++;
	}

	public int getCardNo() {
		return cardNo;
	}
	
	public String getCardName() {
		return cardName;
	}
	
	public int getCost() {
		return cost;
	}
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	@Override
	public boolean checkUsability() {
		boolean validPlay=false;
		if (((DemoPlayer)getPlayer()).getCharacter().getPoints()>=cost) {
			validPlay=true;
			getPlayer().setCardClicked(this);
		}
		return validPlay;
	}
	
	public Enemy getEnemyClicked() {
		return ((DemoPlayer)getPlayer()).getEnemyClicked();
	}
	
	@Override
	public void useTheCard() {
		if (target!=Target.ENEMY || getEnemyClicked()!=null) {
			System.out.println("Using "+cardName+" on "+target);
			((DemoPlayer)getPlayer()).getCharacter().decrementPoints(cost);
			group.getChildren().set(1, ((DemoPlayer)getPlayer()).getCharacter().getSpellpointsText());
			getPlayer().setCardClicked(null);
			getPlayer().discardCardFromHand(this);
			if (target==Target.ENEMY) {
				if (damage!=0) {
					int damageMinusArmor=damage;
					getPlayer().getCharacter().incrementAttack(damageMinusArmor);
					System.out.println("ATTACK="+getPlayer().getCharacter().getAttack());
				}
			} else if (target==Target.SELF) {
				if (block!=0) {
					getPlayer().getCharacter().incrementArmor(block);
					System.out.println("ARMOR="+getPlayer().getCharacter().getArmor());
				}
			}
			// This will change based on index in goup for this text
			group.getChildren().set(9, ((DemoPlayer)getPlayer()).getCharacter().getStatsText());
		}
	}

	@Override
	public String toString() {
		return "AnExtendedCard [cardNo=" + cardNo + ", cardName=" + cardName + ", cost=" + cost + ", target=" + target + ", damage=" + damage
				+ ", block=" + block + "]";
	}
}
