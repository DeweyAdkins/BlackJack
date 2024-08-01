package org.example;
public class DeckActions {
    private Deck deck;

    public DeckActions() {
        this.deck = new Deck();
    }

    public void shuffleDeck() {
        deck.shuffle();
    }

    public Card dealCard() {
        return deck.deal();
    }

    public int getRemainingCards() {
        return deck.cardsRemaining();
    }
}
