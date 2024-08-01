package org.example;
public class Player {
    private Hand hand;
    private int balance;
    private int bet;

    public Player(int balance) {
        this.hand = new Hand();
        this.balance = balance;
    }

    public void placeBet(int bet) {
        this.bet = bet;
        balance -= bet;
    }

    public void winBet() {
        balance += 2 * bet;
    }

    public void drawBet() {
        balance += bet;
    }

    public Hand getHand() {
        return hand;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Hand: " + hand + ", Balance: $" + balance;
    }
}
