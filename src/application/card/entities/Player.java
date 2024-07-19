package application.card.entities;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private Card cardClicked;
	private List<Card> deck;
	private List<Card> hand;
	private List<Card> discard;

	public Player() {
		deck = new ArrayList<>();
		hand = new ArrayList<>();
		discard = new ArrayList<>();
	}
	
	public Card getCardClicked() {
		return cardClicked;
	}
	public void setCardClicked(Card cardClicked) {
		this.cardClicked = cardClicked;
	}
	
	public List<Card> getDeck() {
		return deck;
	}
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	public void addCardToDeck(Card card) {
		deck.add(card);
	}
	public int deckSize() {
		return deck.size();
	}
	public void drawACard() {
		hand.add(deck.remove(0));
	}
	
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public void addCardToHand(Card card) {
		hand.add(card);
	}
	public int handSize() {
		return hand.size();
	}
	public Card viewCardInHand(int index) {
		return hand.get(index);
	}

	public List<Card> getDiscard() {
		return discard;
	}
	public void initDiscard() {
		discard = new ArrayList<>();
	}
	public final void discardCardFromHand(Card card) {
		int index=getHand().indexOf(card);
		getDiscard().add(getHand().get(index));
		getHand().set(index, new NoCard(this));
		setCardClicked(null);
		updateDiscardCardImage(index);
	}
	
	public void updateDiscardCardImage(int index) {	
	}
}
