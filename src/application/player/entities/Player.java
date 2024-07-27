package application.player.entities;

import java.util.ArrayList;
import java.util.List;

import application.card.entities.Card;
import application.card.entities.NoCard;
import application.entities.Character;
import javafx.scene.Group;

public class Player {

	private Card cardClicked;
	private List<Card> deck;
	private List<Card> hand;
	private List<Card> discard;
	private Character character;
	
	private Group group;

	public Player(Group group) {
		deck = new ArrayList<>();
		hand = new ArrayList<>();
		discard = new ArrayList<>();
		this.group=group;
		// 20 health, 0 armor, 0 attack, 3 spell points, x=100,y=100 
		character = new Character("images\\characters\\wizard-point-tp.png",this,group,20,0,0,3,100,100);  
	}
	
	public Group getGroup() {
		return group;
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
	public final int deckSize() {
		return deck.size();
	}
	public final void drawACard() {
		if (deck.size()==0) {
			putDiscardsInDeck();
		}
		hand.add(deck.remove(0));
	}
	public final void replaceACard(int index) {
		if (deck.size()==0) {
			putDiscardsInDeck();
		}
		hand.set(index, deck.remove(0));
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
		int index=getHand().indexOf(card);
		discard.add(getHand().get(index));
		hand.set(index, new NoCard(this));
		setCardClicked(null);
		updateDiscardCardImage(index);
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
	
	public void updateDiscardCardImage(int index) {	
	}
	
	public boolean isGameOver() {
		System.out.println("===========> Game over logic not implemented!");
		return false;
	}
	
	public void shuffleDeck() {
		System.out.println(">>>>>>>> Not shuffling deck!");
	}
	
}
