package application.card.entities;

import java.util.HashMap;
import java.util.Map;

import application.card.effects.StatType;
import application.card.effects.Target;
import application.entities.Enemy;
import application.entities.Character;
import application.player.entities.DemoPlayer;
import application.utils.UseCardUtil;
import javafx.scene.Group;

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
	
	public Map<StatType,Integer>  getStatMap() {
		return statMap;
	}
	
	public DemoPlayer getPlayer() {
		return (DemoPlayer)myParent.lookup("#Player");
	}
	
	public String getFilename() {
		return filename;
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
		UseCardUtil.useTheCard(this);
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
