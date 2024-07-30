package application.card.entities;

import java.util.HashMap;
import java.util.Map;

import application.Main;
import application.buttons.ImageButton;
import application.buttons.LeaveButton;
import application.card.effects.StatType;
import application.entities.Enemy;
import application.entities.Character;
import application.fxcomponents.EraseUtil;
import application.player.entities.DemoPlayer;
import application.player.entities.Player;
import entities.card.Target;
import javafx.scene.Group;
import javafx.scene.Node;

public class AnExtendedCard extends Card {

	private Group group;
	private Target target;
	private Map<StatType,Integer> statMap;
	private String filename;
	
	public AnExtendedCard(String filename, Target target, Player player, Group group, String cardName, int cost) {
		super(filename, cardName, player);
		this.filename=filename;
		statMap = new HashMap<>();
		this.target=target;
		statMap.put(StatType.COST, cost);
		this.group=group;
	}
	
	// Copy an extended card from another extended card
	// This will be a new object in a new memory location.
	public AnExtendedCard(AnExtendedCard eCard) {
		super(eCard.filename, eCard.getCardName(), eCard.getPlayer());
		statMap = new HashMap<>(eCard.statMap);
		this.target=eCard.target;
		statMap.put(StatType.COST, eCard.get(StatType.COST));
		this.group=eCard.group;
	}
	
	public int get(StatType statType) {
		Integer stat = statMap.get(statType);
		if (stat==null) {
			stat = Integer.valueOf(0);
		}
		return stat;
	}
	
	public void set(StatType statType, int value) {
		statMap.put(statType, value);
	}

	@Override
	public boolean checkUsability() {
		boolean validPlay=false;
		int cost=statMap.get(StatType.COST);
		int totalPoints=((DemoPlayer)getPlayer()).getCharacter().get(StatType.POINTS);
		//System.out.print(getCardName()+" cost:"+cost+", total points:"+totalPoints);
		if (totalPoints >= cost) {
			validPlay=true;
			getPlayer().setCardClicked(this);
		}
		return validPlay;
	}
	
	public Enemy getEnemyClicked() {
		return ((DemoPlayer)getPlayer()).getEnemyClicked();
	}
	
	public Character getCharacterClicked() {
		return ((DemoPlayer)getPlayer()).getCharacterClicked();
	}
	
	@Override
	public void useTheCard() {
		Enemy enemy=getEnemyClicked();
		Character character=getCharacterClicked();
		if ((character!=null && target==Target.SELF) || (enemy!=null && target==Target.ENEMY)) {
			System.out.println("Using "+getCardName()+" on "+target);
			int cost=statMap.get(StatType.COST);
			Integer damage=statMap.get(StatType.ATTACK);
			Integer block=statMap.get(StatType.ARMOR);
			
			// spend points on using card
			((DemoPlayer)getPlayer()).getCharacter().decrement(StatType.POINTS,cost,0); // lowest point value is 0.
			group.getChildren().set(1, ((DemoPlayer)getPlayer()).getCharacter().getSpellpointsText());
			
			// Discard card
			getPlayer().setCardClicked(null);
			getPlayer().discardCardFromHand(this);
			
			// Do card actions based on target
			if (target==Target.ENEMY) {
				if (damage != null && damage!=0) {
					enemy.decrementHealth(damage);
					enemy.updateScreenText();
				}
			} else if (target==Target.SELF) {
				if (block != null && block!=0) {
					getPlayer().getCharacter().increment(StatType.ARMOR,block,-1); // no max armor amount
				}
			}
			
			// Update character stats.
			// This will change based on index in group for this text
			group.getChildren().set(5, ((DemoPlayer)getPlayer()).getCharacter().getStatsText());
			
			if (enemy != null && enemy.get(StatType.HEALTH)>0) {
				// hard coded to currently work with one enemy
				int no=enemy.getEnemyNumber();
				Group enemyGroup = (Group) group.getChildren().get(Main.ENEMY1_INDEX+no-1);
				enemyGroup.getChildren().set(2, enemy.getStatsText());
			} else if (enemy!=null){
				EraseUtil.eraseEnemy(enemy, group, 0);
				getPlayer().clearHand();
				((DemoPlayer)getPlayer()).addCardsToJavaFxDisplay(group);
				EraseUtil.eraseDiscard(7,group);
				ImageButton leaveButton = new LeaveButton("Leave.png",1200,700, getPlayer());
				group.getChildren().set(2, leaveButton.getImageView());
				EraseUtil.redraw(group, getPlayer());
			}
		}
	}

	public final Target getTarget() {
		return target;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AnExtendedCard [cardName=");
		builder.append(getCardName());
		builder.append(", group=");
		builder.append(group);
		builder.append(", target=");
		builder.append(target);
		builder.append(", statMap=");
		builder.append(statMap);
		builder.append("]");
		return builder.toString();
	}

	
}
