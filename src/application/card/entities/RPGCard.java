package application.card.entities;

import java.util.HashMap;
import java.util.Map;

import application.buttons.ImageButton;
import application.buttons.RestartButton;
import application.card.effects.StatType;
import application.entities.Enemy;
import application.entities.Character;
import application.fxcomponents.EraseUtil;
import application.fxcomponents.ScreenUtil;
import application.fxcomponents.UpdateUtil;
import application.player.entities.DemoPlayer;
import entities.card.Target;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class RPGCard extends Card {

	private Target target;
	private Map<StatType,Integer> statMap;
	private String filename;
	
	public RPGCard(Group myParent, String filename, Target target, String cardName, int cost) {
		super(myParent, filename, cardName);
		this.filename=filename;
		statMap = new HashMap<>();
		this.target=target;
		statMap.put(StatType.COST, cost);
	}
	
	// Copy an extended card from another extended card
	// This will be a new object in a new memory location.
	public RPGCard(RPGCard eCard) {
		super(eCard.myParent, eCard.filename, eCard.getCardName());
		statMap = new HashMap<>(eCard.statMap);
		this.target=eCard.target;
		statMap.put(StatType.COST, eCard.get(StatType.COST));
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
	
	private DemoPlayer getPlayer() {
		return (DemoPlayer)myParent.lookup("#Player");
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
			//this.getChildren().set(1, ((DemoPlayer)getPlayer()).getCharacter().getSpellpointsText());
			Text text=((DemoPlayer)getPlayer()).getCharacter().getSpellpointsText();
			UpdateUtil.updateGroupText(myParent, "#PointsText", text);
			
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
			//this.getChildren().set(5, ((DemoPlayer)getPlayer()).getCharacter().getStatsText());
			getPlayer().getCharacter().updateScreenText();
			text=getPlayer().getCharacter().getStatsText();
			System.out.println("Player stats text: "+text.getText());
			UpdateUtil.updateGroupText(myParent, "#PlayerStatsText", text);
			
			if (enemy != null && enemy.get(StatType.HEALTH)>0) {
				// hard coded to currently work with one enemy
				int no=enemy.getEnemyNumber();
				for (Node node:myParent.getChildren()) {
					System.out.println(node.getId());
				}
				int enemyIndex=ScreenUtil.getIndexOfId(myParent,"#Enemy1");
				Group enemyGroup = (Group) myParent.getChildren().get(enemyIndex+no-1);
				enemyGroup.getChildren().set(2, enemy.getStatsText());
			} else if (enemy!=null){
				EraseUtil.eraseEnemy(enemy, myParent, 0);
				getPlayer().clearHand();
				((DemoPlayer)getPlayer()).addCardsToJavaFxDisplay(myParent);
				EraseUtil.erase("#DiscardButton",myParent);
				EraseUtil.erase("#EndTurnButton",myParent);
				ImageButton leaveButton = new RestartButton(myParent,"Leave.png",1200,700);
 				myParent.getChildren().add(leaveButton.getImageView());
				for (Node node:myParent.getChildren()) {
					System.out.println("==========>"+node.getId());
				}
				System.out.println("=========[END RPGCard enemy dead]=====");
				EraseUtil.redraw(myParent, getPlayer());
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
		builder.append(", target=");
		builder.append(target);
		builder.append(", statMap=");
		builder.append(statMap);
		builder.append("]");
		return builder.toString();
	}

	
}
