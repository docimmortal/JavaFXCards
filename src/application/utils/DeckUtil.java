package application.utils;

import java.util.ArrayList;
import java.util.List;

import application.card.effects.StatType;
import application.card.effects.Target;
import application.card.entities.RPGCard;
import javafx.scene.Group;
import application.card.entities.Card;

public class DeckUtil {

	public static List<Card> getInitialDeck(Group myParent){
		
		List<Card> cards = new ArrayList<>();
		
		System.out.println("===========[ DUMMY INITIAL DECK ]===========");
		RPGCard block = new RPGCard(myParent, "images\\cards\\block.jpg", Target.SELF, "Block", 1);
		block.set(StatType.ARMOR,5);
		cards.add(block);
		
		RPGCard punch = new RPGCard(myParent,"images\\cards\\lg-punch.jpg",Target.ENEMY, "Punch", 1);
		punch.set(StatType.ATTACK,5);
		cards.add(punch);
	
		RPGCard punchPlus = new RPGCard(myParent,"images\\cards\\punch-plus.jpg",Target.ENEMY, "Punch+", 1);
		punchPlus.set(StatType.ATTACK,5);
		punchPlus.set(StatType.CYCLE, 1);
		cards.add(punchPlus);
		
		RPGCard blockPlus = new RPGCard(myParent,"images\\cards\\block-plus.jpg",Target.SELF, "Block+", 1);
		blockPlus.set(StatType.ARMOR,6);
		blockPlus.set(StatType.CYCLE, 1);
		cards.add(blockPlus);
		
		RPGCard puncha = new RPGCard(myParent,"images\\cards\\puncha.jpg",Target.ENEMY, "Punch A", 1);
		puncha.set(StatType.ATTACK,5);
		cards.add(puncha);
		
		cards.add(new RPGCard(block));
		
		RPGCard punchb = new RPGCard(myParent,"images\\cards\\punchb.jpg",Target.ENEMY, "Punch B", 1);
		punchb.set(StatType.ATTACK,5);
		cards.add(punchb);
		
		RPGCard punchc = new RPGCard(myParent,"images\\cards\\punchc.jpg",Target.ENEMY, "Punch C", 1);
		punchc.set(StatType.ATTACK,5);
		cards.add(punchc);
		
		cards.add(new RPGCard(block));
		
		RPGCard punchd = new RPGCard(myParent,"images\\cards\\punchd.jpg",Target.ENEMY, "Punch D", 1);
		punchd.set(StatType.ATTACK,5);
		cards.add(punchd);
		
		RPGCard punche = new RPGCard(myParent,"images\\cards\\punche.jpg",Target.ENEMY, "Punch E", 1);
		punche.set(StatType.ATTACK,5);
		cards.add(punche);
		
		return cards;
	}
}
