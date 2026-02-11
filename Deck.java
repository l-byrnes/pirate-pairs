import java.util.Arrays;

public class Deck{
    private int[] deck;
    public Deck(){
        deck = new int[55];
        makeDeck();
    }

    public void makeDeck(){
        int maxCard = 10;
        int cardNum = 0;
        for(int i = 1; i <=maxCard; i++){
            for(int j = 0; j<i; j++){
                deck[cardNum] = i;
                cardNum++;
            }
        }
        System.out.println(Arrays.toString(deck));
    }

    public void shuffleDeck(){
        for(int i = 0; i < deck.length; i++){
            int randomNum = (int) (Math.random() * 55);
            int temp = deck[randomNum];
            deck[randomNum] = deck[i];
            deck[i] = temp;
        }
        System.out.println(Arrays.toString(deck));
     }

    public void drawCard(){
        // cards.indexOf(0);

    }
}
