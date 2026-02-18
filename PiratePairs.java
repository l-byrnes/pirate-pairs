public class PiratePairs {
    public static void main(String[] args) {
        Deck myDeck = new Deck();
        myDeck.shuffleDeck();
        Player[] players = new Player[4];
        //change the names here if you would like, I did not get around to putting a scanner in so you can change the names without looking at the code
        Player player1 = new Player("Lilly");
        Player player2 = new Player("Ally");
        Player player3 = new Player("Maddy");
        Player player4 = new Player("Elsie");

        players[0] = player1;
        players[1] = player2;
        players[2] = player3;
        players[3] = player4;

        while (countPlayersStillIn(players) > 1) {
            for (Player player : players) {
                if (player.playerStillIn == true) {
                    System.out.println(player.name + "'s turn");
                    //deal a card, if that was the last one in the deck, refill the deck, then move on to player descisions
                    int dealtCard = myDeck.dealCard();
                    if (myDeck.isDeckEmpty()) {
                        myDeck.refillDeck();
                    }
                    player.playerDescisions(dealtCard, players, myDeck.discardDeck);
                    int pointsToGive = player.checkPair(dealtCard);
                    if (pointsToGive != 0) {
                        myDeck.trashHand(player.cards);
                    }

                    // insert descisions here, delete next line
                    // player.takeCard(dealtCard);
                    player.givePoints(pointsToGive);
                    player.showCards();
                    player.checkTotalPoints();
                }

                System.out.println(player.name + "'s turn is over");
            }
            System.out.println("");
            System.out.println("This round is over, here are the hands at the end of the round:");
            for (int i = 0; i < players.length; i++) {
                players[i].showCards();
            }
            System.out.println("");
        }
        for (Player player : players) {
            if (player.playerStillIn) {
                System.out.println(player.name + " has won!");
            }
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
