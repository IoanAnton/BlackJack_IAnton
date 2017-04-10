import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class Deck
{
    private Random randomGenerator;
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
    public void showDeck (){
        list.forEach(System.out::println);
    }
    public void removePick(int index){
    
        if ( list != null && list.size() != 0){               
            list.remove(index);         
        }
    }
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
