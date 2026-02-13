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

        // have the deck deal a card, retun the int of that card, player has option to
        // take or leave that card.

    }
}
