import java.util.Arrays;

public class Player {
    String name;
    public int[] cards;
    int points;
    private static int maxPoints = 10;
    public boolean playerStillIn = true;

    public Player(String name) {
        this.name = name;
        cards = new int[10];
        points = 0;
    }

    public void takeCard(int myCard) {
        System.out.println("They got a " + myCard + " from the dealer");
        if (cards[0] == 0) {
            cards[0] = myCard;
        } else {
            for (int i = 0; i < cards.length; i++) {
                if (cards[i] == 0) {
                    cards[i] = myCard;
                    break;
                }

            }
        }
    }

    public void showCards() {
        System.out.println(this.name + "'s cards: " + Arrays.toString(cards));
    }

    public int checkPair(int toBeChecked) {
        int count = 0;
        for (int card : cards) {
            if (card == toBeChecked) {
                count++;
            }
        }
        if (count > 1) {
            System.out.println("Uh-Oh! This made a pair!");
            return toBeChecked;
        } else {
            return 0;
        }
    }

    public void givePoints(int pointsToGive) {
        points += pointsToGive;
    }

    public void checkTotalPoints() {
        if (points > maxPoints) {
            System.out.println(this.name + " is out of the game");
            playerStillIn = false;
        } else {
            System.out.println(this.name + " has " + this.points + " point(s)");

        }
    }

    public void stealCard(Player playerNum, int stolenCard, int[] discardDeck) {
        System.out.println(" They stole a " + stolenCard + " from " + playerNum.name);
        for (int i = 0; i < cards.length; i++) {
            if (playerNum.cards[i] == stolenCard) {
                playerNum.cards[i] = 0;
                break;
            }
        }
        this.givePoints(stolenCard);
        this.clearHand(discardDeck);
    }

    public void clearHand(int[] discardDeck) {
        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < discardDeck.length; j++) {
                if (discardDeck[j] == 0) {
                    discardDeck[j] = cards[i];
                    break;
                }
            }
            cards[i] = 0;
        }
    }

    public void playerDescisions(int myCard, Player[] players, int[] discardDeck) {
        // count the amount of cards in play for the one just drawn
        int count = 0;
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players[i].cards.length; j++) {
                if (players[i].cards[j] == myCard) {
                    count++;
                }
            }
        }
        for (int i = 0; i < discardDeck.length; i++) {
            if (discardDeck[i] == myCard) {
                count++;
            }
        }

        // find the person with the lowest card
        int min = 11;
        int playerWithMin = 0;
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players[i].cards.length; j++) {
                if (players[i].cards[j] != 0 && players[i].cards[j] < min) {
                    min = players[i].cards[j];
                    playerWithMin = i;
                }
            }
        }
        // if their hand is empty, they are forced to take a card, if noth their
        // individual strateies are implemented
        if (emptyHand()) {
            takeCard(myCard);
        } else {
            if (this == players[0]) {
                if (count > 0 && myCard / count > (1 / 2) * myCard) {
                    stealCard(players[playerWithMin], min, discardDeck);
                } else {
                    takeCard(myCard);
                }
            } else if (this == players[1]) {
                // if player 1's lowest card is less than the min draw, if player1 has the
                // lowest card out there though, draw
                boolean hasLower = false;
                for (int j = 0; j < cards.length; j++) {
                    if ((cards[j] != 0 && cards[j] < min) && playerWithMin != 1) {
                        hasLower = true;
                        break;
                    }
                }
                if (hasLower) {
                    takeCard(myCard);
                } else {
                    stealCard(players[playerWithMin], min, discardDeck);
                }
            } else if (this == players[2]) {
                // nice player, never steals cards
                takeCard(myCard);
            } else {
                // steals if their highest card is greater than the amount of points they have
                // left, otherwise draw
                boolean didStealCard = false;
                for (int j = 0; j < players[3].cards.length; j++) {
                    if (players[3].cards[j] > maxPoints - players[3].points) {
                        stealCard(players[playerWithMin], min, discardDeck);
                        didStealCard = true;
                    }
                }
                if (!didStealCard) {
                    takeCard(myCard);
                }
            }
        }
    }

    // checks if the current player has any cards
    public boolean emptyHand() {
        for (int card : cards) {
            if (card != 0) {
                return false;
            }
        }
        return true;
    }
}
