package org.example;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getValue() {
        int totalValue = 0;
        int aces = 0;

        for (Card card : cards) {
            totalValue += card.getValue();
            if (card.getRank().equals("Ace")) {
                aces++;
            }
        }

        while (totalValue > 21 && aces > 0) {
            totalValue -= 10;
            aces--;
        }

        return totalValue;
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
