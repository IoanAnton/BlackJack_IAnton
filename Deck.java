import java.util.*;

public class Deck
{
    // Deck is a singleton pattern implementation
    List<Card> list;      
    private Deck(){
        this.list = new ArrayList<>();
    }    
    private static final class SingletonHolder{
        private static final Deck instance = new Deck();
    }    
    public static Deck getInstance(){
        return SingletonHolder.instance;
    }    
    
    //deck creation with exception to make sure that it doesn't create more than 52 cards
    public void createNewDeck() throws TooManyCardsException{
         for (int i=1; i<=4; i++){
            for (int j=2;j<=14; j++) {
                if ( list.size() <= 52 ) {
                    list.add(new Card( String.valueOf(j) , String.valueOf(i) )); 
                }else{
                    throw new TooManyCardsException();                
                }
            }      
        }
    }
    //utility method to print all cards
    public void showDeck (){
        list.forEach(System.out::println);
    }
    //method utilized for removing the card from the deck with the index specified
    public void removePick(int index){
    
        if ( list != null && list.size() != 0){               
            list.remove(index);         
        }
    }
    //method utilized for picking the card from the deck with the index specified
    public Card pickCard(int index){
    
        if ( list != null && list.size() != 0){               
            return list.get(index);         
        }else{
            return null;
        }
    }
    public int getSize(){
        if ( list != null && list.size() != 0){               
            return list.size();     
        }else{
            return 0;
        }
    }
    

}
