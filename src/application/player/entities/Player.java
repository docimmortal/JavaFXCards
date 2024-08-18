package application.player.entities;

import java.util.ArrayList;
import java.util.List;

import application.card.entities.Card;
import application.card.entities.NoCard;
import application.entities.Character;
import application.fxcomponents.ScreenUtil;
import javafx.scene.Group;
import javafx.stage.Stage;

public class Player extends Group{

	private Card cardClicked;
	private List<Card> deck;
	private List<Card> hand;
	private List<Card> discard;
	private Character character;
	protected Group myParent;

	private Stage stage;
	
	public Player(Group myParent, Stage stage) {
		deck = new ArrayList<>();
		hand = new ArrayList<>();
		discard = new ArrayList<>();
		this.stage=stage;
		this.myParent=myParent;
		setId("Player");
	}

	public Stage getStage() {
		return stage;
	}
	
	public Group myParent() {
		return myParent;
	}
	
	public void resetDeckHandDiscard() {
		// Add all cards that are not NoCard from hand to deck
		for (Card card: hand) {
			if (!(card instanceof NoCard)) {
				deck.add(card);
			}
		}
		// put all discards back in deck
		deck.addAll(discard);
		// clear hand and discard
		hand.clear();
		discard.clear();
		shuffleDeck();
	}
	
	public void clearHand() {
		for (int i=0; i < hand.size(); i++) {
			Card card = hand.get(i);
			int index=getHand().indexOf(card);
			String num=""+card.getId().charAt(card.getId().length()-1);
			hand.set(index, new NoCard(myParent,num));
		}
	}
	
	public final Card getCardClicked() {
		return cardClicked;
	}

	public final List<Card> getDeck() {
		return deck;
	}
	public final void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	public final void addCardToDeck(Card card) {
		deck.add(card);
	}
	
	public final int getIndexOfCardInHand(Card card) {
		return hand.indexOf(card);
	}
	
	public final int deckSize() {
		return deck.size();
	}
	public final void drawAnInitialHand() {
		for (int i=0; i<5; i++) {
			if (deck.size()==0) {
				putDiscardsInDeck();
			}
			Card card = deck.remove(0);
			card.setId("Card"+(i+1));
			hand.add(card);
		}
	}
	public final void replaceACard(int index) {
		if (deck.size()==0) {
			putDiscardsInDeck();
		}
		Card card = deck.remove(0);
		card.setId("Card"+(index+1));
		hand.set(index,card);
	}
	
	public final List<Card> getHand() {
		return hand;
	}
	public final void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public final void addCardToHand(Card card) {
		hand.add(card);
	}
	public final int handSize() {
		int handSize=0;
		for (Card card:hand) {
			if (!(card instanceof NoCard)) {
				handSize++;
			}
		}
		return handSize;
	}
	
	public final int absoluteHandSize() {
		return hand.size();
	}
	
	public final Card viewCardInHand(int index) {
		return hand.get(index);
	}

	public final List<Card> getDiscard() {
		return discard;
	}
	public final void initDiscard() {
		discard = new ArrayList<>();
	}

	public final void discardCardFromHand(Card card) {
		//System.out.println(getHand().size());
		int index=getHand().indexOf(card);
		//System.out.println("discardCardFromHand, index="+index);
		Card oldCard=getHand().get(index);
		discard.add(oldCard);
		NoCard noCard = new NoCard(myParent,oldCard.getId().replaceAll("Card", ""));
		//System.out.println("discardCardFromHand: "+noCard.getId());
		hand.set(index,noCard);
		//System.out.println("discardCardFromHand NoCard check:"+hand.get(index));
		setCardClicked(null);
		int firstCardIndex=ScreenUtil.getIndexOfId(myParent,"#Card1");
		if (firstCardIndex==-1) {
			firstCardIndex=ScreenUtil.getIndexOfId(myParent,"#NoCard1");
		}
		//System.out.println("FirstCardIndex:"+firstCardIndex+", index*2:"+(index*2));
		myParent.getChildren().set(firstCardIndex+index*2, viewCardInHand(index));
		myParent.getChildren().set(firstCardIndex+index*2+1, noCard.getImageView());
		
		//System.out.println("=============[End of discardCardFromHand]========\n\n");
	}
	
	public final void putDiscardsInDeck() {
		if (!isGameOver()) {
			deck.addAll(discard);
			shuffleDeck();
			discard.clear();
		}
	}
	
	public final Character getCharacter() {
		return character;
	}
	
	public final void setCharacter(Character character) {
		this.character = character;
	}

	/*
	 * Methods that can be extended/overridden
	 */
	// Additional logic can be added for targeting (see AnExtendedCard)
	public void setCardClicked(Card cardClicked) {
		this.cardClicked = cardClicked;
	}
	
	public boolean isGameOver() {
		System.out.println("===========> Game over logic not implemented!");
		return false;
	}
	
	public void shuffleDeck() {
		System.out.println(">>>>>>>> Not shuffling deck!");
	}
	
}
