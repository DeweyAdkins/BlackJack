package org.example;
import java.util.Scanner;

public class GameRunner {
    private DeckActions deckActions;
    private Player player;
    private Hand dealerHand;

    public GameRunner(int initialBalance) {
        deckActions = new DeckActions();
        player = new Player(initialBalance);
        dealerHand = new Hand();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            deckActions.shuffleDeck();

            // Deal initial hands
            player.getHand().addCard(deckActions.dealCard());
            player.getHand().addCard(deckActions.dealCard());
            dealerHand.addCard(deckActions.dealCard());
            dealerHand.addCard(deckActions.dealCard());

            System.out.println("Your hand: " + player.getHand());
            System.out.println("Dealer's face-up card: " + dealerHand.getCards().get(0));

            boolean playerTurn = true;
            while (playerTurn) {
                System.out.println("Your hand value: " + player.getHand().getValue());
                System.out.print("Hit or Stand? (h/s): ");
                String action = scanner.nextLine();
                if (action.equalsIgnoreCase("h")) {
                    player.getHand().addCard(deckActions.dealCard());
                    System.out.println("Your hand: " + player.getHand());
                    if (player.getHand().getValue() > 21) {
                        System.out.println("You bust!");
                        playerTurn = false;
                    }
                } else {
                    playerTurn = false;
                }
            }

            // Dealer's turn
            System.out.println("Dealer's hand: " + dealerHand);
            while (dealerHand.getValue() < 17) {
                dealerHand.addCard(deckActions.dealCard());
                System.out.println("Dealer hits: " + dealerHand);
            }

            if (dealerHand.getValue() > 21) {
                System.out.println("Dealer busts!");
            }


            int playerValue = player.getHand().getValue();
            int dealerValue = dealerHand.getValue();
            System.out.println("Your hand value: " + playerValue);
            System.out.println("Dealer's hand value: " + dealerValue);

            if (playerValue > 21) {
                System.out.println("You lose!");
            } else if (dealerValue > 21 || playerValue > dealerValue) {
                System.out.println("You win!");
                player.winBet();
            } else if (playerValue == dealerValue) {
                System.out.println("It's a draw!");
                player.drawBet();
            } else {
                System.out.println("You lose!");
            }

            System.out.println("Your balance: $" + player.getBalance());

            System.out.print("Play again? (y/n): ");
            playAgain = scanner.nextLine().equalsIgnoreCase("y");

            player.getHand().getCards().clear();
            dealerHand.getCards().clear();

            if (deckActions.getRemainingCards() < 10) {
                deckActions = new DeckActions();
                deckActions.shuffleDeck();
                System.out.println("Shuffling the deck...");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        String filepath = "/CasinoJazz.wav";
        PlayMusic music = new PlayMusic();
        music.playMusic(filepath);
        music.setVolume(-19.0f);
        GameRunner game = new GameRunner(100);
        game.play();
    }
}
