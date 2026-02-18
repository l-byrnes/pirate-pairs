import java.util.Arrays;

public class Deck{
    private int[] deck;
    public  int[] discardDeck;

    public Deck(){
        deck = new int[55];
        discardDeck = new int[55];
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
    }

    public void shuffleDeck(){
        for(int i = 0; i < deck.length; i++){
            int randomNum = (int) (Math.random() * 55);
            int temp = deck[randomNum];
            deck[randomNum] = deck[i];
            deck[i] = temp;
        }
     }

    public int dealCard(){
        int dealtCard = 0;
        for(int i = deck.length - 1; i >= 0; i--){
            if(deck[i] != 0){
                dealtCard = deck[i];
                deck[i] = 0;
                System.out.println("(dealCard)dealt: " + dealtCard);
                return dealtCard;
            } 
        }
        return dealtCard;
    }

    public int getDiscardnextOpenSlot(){
        for(int i = 0; i < discardDeck.length; i++){
            if(discardDeck[i] == 0){
                return i;
            }
        }
        return -1;
    }

    public void trashHand(int[] cards){
        for(int i = 0; i < cards.length; i++){
            discardDeck[getDiscardnextOpenSlot()] = cards[i];
            cards[i] = 0;
        }
        
        System.out.println("(trashhand)Discard Deck:");
        System.out.println(Arrays.toString(discardDeck));
        System.out.println("(trashhand)Hand:");
        System.out.println(Arrays.toString(cards));
        
    }
    public boolean isDeckEmpty(){
        for(int i = 0; i < deck.length; i++){
            if(deck[i] != 0){
                return false;
            }
        }
        return true;
    }

    public void refillDeck(){
        for(int i = 0; i < deck.length; i++){
            deck[i] = discardDeck[i];
            discardDeck[i] = 0;
        }
    }

}
