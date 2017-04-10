import java.io.*;
import java.util.*;
public class BlackJack
{
   public static void main (String [] args){
       
       Random randomGeneratedNr = new Random();
       int randomCardIndex;   
       int nrOfDraws;
       int playerHandValue = 0, dealerHandValue = 0;
       boolean stop = false, playerLost = false;
       
       try {
            Deck deck = Deck.getInstance(); // create Singleton instance
            deck.createNewDeck(); // creating the deck
            //deck.showDeck();
            ArrayList<Card> playerHand = new ArrayList<>();
            ArrayList<Card> dealerHand = new ArrayList<>();
            InputStreamReader inCons = new InputStreamReader(System.in);
            BufferedReader cons = new BufferedReader(inCons);             
            String cmd;     
            
            System.out.print("Initial Draw");
            
            
            //First Draws for dealer
            randomCardIndex = randomGeneratedNr.nextInt(deck.getSize());
            dealerHand.add(deck.pickCard(randomCardIndex));
            deck.removePick(randomCardIndex);
            randomCardIndex = randomGeneratedNr.nextInt(deck.getSize());
            dealerHand.add(deck.pickCard(randomCardIndex));
            deck.removePick(randomCardIndex);
            
            //First Draws for player
            randomCardIndex = randomGeneratedNr.nextInt(deck.getSize());
            playerHand.add(deck.pickCard(randomCardIndex));
            deck.removePick(randomCardIndex);
            randomCardIndex = randomGeneratedNr.nextInt(deck.getSize());
            playerHand.add(deck.pickCard(randomCardIndex));
            deck.removePick(randomCardIndex);
            
            System.out.println("\nDealer Draw: " + dealerHand.get(0) +", Hidden");
            
            nrOfDraws = 1; // initialization for hands number
            playerHandValue = playerHand.get(0).getValue() + playerHand.get(1).getValue();
            dealerHandValue = dealerHand.get(0).getValue() + dealerHand.get(1).getValue();
            System.out.println("\nPlayer Draw("+nrOfDraws+"): " + playerHand.get(0) +", " + playerHand.get(1)+". Do you want to draw another card? (Y/N)");
            while(!stop && !playerLost){
                 cmd = cons.readLine();
                 switch(cmd.toUpperCase()){
                     case "Y":{
                          nrOfDraws++;
                          randomCardIndex = randomGeneratedNr.nextInt(deck.getSize());
                          playerHand.add(deck.pickCard(randomCardIndex));
                          deck.removePick(randomCardIndex);
                          playerHandValue += playerHand.get(nrOfDraws).getValue();
                          if (playerHandValue > 21){
                              playerLost = true; // 
                              System.out.println("\nPlayer Hand is over 21. Player lost with " + playerHandValue + " in his hand!");
                            }else{
                              System.out.println("\nPlayer Draw("+nrOfDraws+"): " + playerHand.get(nrOfDraws) + ". Do you want to draw another card? (Y/N)");
                          }   
                          break;
                          
                        }
                     case "N":{
                          stop = true;
                          break;
                        }
                     default:{
                        System.out.println("Invalid command! please type Y or N.");
                        break;
                        } 
                 }               
                 
            }
            
            if (!playerLost) {
                nrOfDraws = 1;
                System.out.println("\nDealer Hidden Card Was: " + dealerHand.get(nrOfDraws) + " player hand is " + playerHandValue);
            }    
        }catch (TooManyCardsException e){
            System.out.println("Too many cards inserted in the deck!!");          
        }catch(Exception e){
            e.printStackTrace(); 
        }    
    }
}


