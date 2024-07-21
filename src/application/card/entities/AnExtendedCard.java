package application.card.entities;

import application.card.effects.StatType;
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
		int totalPoints=((DemoPlayer)getPlayer()).getCharacter().get(StatType.POINTS);
		System.out.print(cardName+" cost:"+cost+", total points:"+totalPoints);
		if (totalPoints >= cost) {
			validPlay=true;
			getPlayer().setCardClicked(this);
		}
		System.out.println("  "+validPlay);
		return validPlay;
	}
	
	public Enemy getEnemyClicked() {
		return ((DemoPlayer)getPlayer()).getEnemyClicked();
	}
	
	@Override
	public void useTheCard() {
		if (target!=Target.ENEMY || getEnemyClicked()!=null) {
			System.out.println("Using "+cardName+" on "+target);
			((DemoPlayer)getPlayer()).getCharacter().decrement(StatType.POINTS,cost,0); // lowest point value is 0.
			group.getChildren().set(1, ((DemoPlayer)getPlayer()).getCharacter().getSpellpointsText());
			getPlayer().setCardClicked(null);
			getPlayer().discardCardFromHand(this);
			if (target==Target.ENEMY) {
				if (damage!=0) {
					int damageMinusArmor=damage;
					getPlayer().getCharacter().increment(StatType.ATTACK,damageMinusArmor, 0); // min damage is 0 
					System.out.println("ATTACK="+getPlayer().getCharacter().get(StatType.ATTACK));
				}
			} else if (target==Target.SELF) {
				if (block!=0) {
					getPlayer().getCharacter().increment(StatType.ARMOR,block,-1); // no max armor amount
					System.out.println("ARMOR="+getPlayer().getCharacter().get(StatType.ARMOR));
				}
			}
			// This will change based on index in goup for this text
			group.getChildren().set(10, ((DemoPlayer)getPlayer()).getCharacter().getStatsText());
		}
	}

	@Override
	public String toString() {
		return "AnExtendedCard [cardNo=" + cardNo + ", cardName=" + cardName + ", cost=" + cost + ", target=" + target + ", damage=" + damage
				+ ", block=" + block + "]";
	}
}
