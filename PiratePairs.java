import java.util.Arrays;

public class PiratePairs {
    public static void main(String[] args) {
        Deck myDeck = new Deck();
        myDeck.shuffleDeck();
        Player[] players = new Player[4];
        Player player1 = new Player("Lilly");
        Player player2 = new Player("Ally");
        Player player3 = new Player("Maddy");
        Player player4 = new Player("Elsie");

        players[0] = player1;
        players[1] = player2;
        players[2] = player3;
        players[3] = player4;

        // int dealtCard = myDeck.dealCard();
        // players[0].takeCard(dealtCard);
        for (int i = 0; i < players.length; i++) {
            players[i].showCards();
        }
        System.out.println("while");
        while (countPlayersStillIn(players) > 1) {
            System.out.println("players: " + countPlayersStillIn(players));
            for (Player player : players) {
                if (player.playerStillIn == true) {
                    int dealtCard = myDeck.dealCard();
                    if (myDeck.isDeckEmpty()) {
                        myDeck.refillDeck();
                    }
                    int pointsToGive = player.checkPair(dealtCard);
                    if (pointsToGive != 0) {
                        myDeck.trashHand(player.cards);
                    }

                    // insert descisions here, delete next line
                    player.playerDescisions(dealtCard, players, myDeck.discardDeck);
                    // player.takeCard(dealtCard);
                    player.givePoints(pointsToGive);
                    player.showCards();
                    player.checkTotalPoints();
                }
            }
            break;
        }
        for (int i = 0; i < players.length; i++) {
            players[i].showCards();
        }


    }

    public static int countPlayersStillIn(Player[] players) {
        int count = 0;
        for (Player player : players) {
            if (player.playerStillIn) {
                count++;
            }
        }
        return count;
    }
}
