import java.io.*;
import java.util.*;
public class BlackJack
{
   static Deck deck = Deck.getInstance(); 
   public static void main (String [] args){
       
       int nrOfDraws;
       int playerHandValue = 0, dealerHandValue = 0;
       boolean stop = false, playerLost = false;
       
       try {
            deck.createNewDeck(); // creating the deck
            //deck.showDeck();
            ArrayList<Card> playerHand = new ArrayList<>();
            ArrayList<Card> dealerHand = new ArrayList<>();
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader cons = new BufferedReader(in);             
            String cmd;     
            
            System.out.print("Initial Draw");           
            
            //First Draws for dealer
            drawCard(dealerHand);
            drawCard(dealerHand);
            
            //First Draws for player
            drawCard(playerHand);
            drawCard(playerHand);            
            
            System.out.println("\nDealer Draw: " + dealerHand.get(0) +", Hidden");
            
            // initialization for hands number
            nrOfDraws = 1;
            //counter for number of Aces in hand
            int nrOfAces = 0;
            playerHandValue = playerHand.get(0).getValue() + playerHand.get(1).getValue();
            //incrementing number of aces if they are drawn
            if (playerHand.get(0).getNumber().equals("A")) nrOfAces++;
            if (playerHand.get(1).getNumber().equals("A")) nrOfAces++;
            System.out.println("\nPlayer Draw("+nrOfDraws+"): " + playerHand.get(0) +", " + playerHand.get(1)+". Do you want to draw another card? (Y/N)");
            // loop which contains game logic
            while(!stop && !playerLost){
                 //input from keyboard
                 cmd = cons.readLine();
                 //checking if the command is Y or N, case insensitive
                 switch(cmd.toUpperCase()){
                     case "Y":{
                          nrOfDraws++;     
                          drawCard(playerHand);
                          playerHandValue += playerHand.get(nrOfDraws).getValue();
                          if (playerHand.get(nrOfDraws).getNumber().equals("A")) nrOfAces++;
                          System.out.println("\nPlayer Draw("+nrOfDraws+"): " + playerHand.get(nrOfDraws) + ". Do you want to draw another card? (Y/N)");                     
                          
                          if (playerHandValue > 21){
                              //Aces have the value 11 by default and if the hand value is over 21 their value becomes 1, this is repeated no more than 4 times/player
                              for (int i=0; i < playerHand.size(); i++){
                                if ( playerHand.get(i).getNumber().equals("A") ){
                                    //checking if we have aces in hand
                                    if (nrOfAces > 0) {
                                        playerHandValue -= 10;
                                        nrOfAces--;
                                    }
                                }
                                if  ( playerHandValue <= 21 ){
                                    break;
                                }
                              }
                              if (playerHandValue > 21){
                                    playerLost = true; // 
                                    System.out.println("\nPlayer Hand is over 21. Player lost with " + playerHandValue + " in his hand!\nDealer WINS!!!");
                              }
                      
                          }    
                     }break;
                     case "N":{
                          stop = true;
                          
                     }break;
                     default:{
                        System.out.println("Invalid command! please type Y or N.");
                        
                     }break; 
                 }               
                 
            }
            //dealer turn
            if (!playerLost) {
                dealerHandValue = dealerHand.get(0).getValue() + dealerHand.get(1).getValue();
                boolean gameOver = false;
                nrOfAces=0;
                if (dealerHand.get(0).getNumber().equals("A")) nrOfAces++;
                if (dealerHand.get(1).getNumber().equals("A")) nrOfAces++;
                //because the variable nrOfDraws isn't used anymore in playerHand, we reinitialize here
                nrOfDraws = 1;
                System.out.print("\nDealer Hidden Card Was: " + dealerHand.get(nrOfDraws) + ".Dealer hand is " +dealerHandValue+ "");
                while(!gameOver){
                                if ( dealerHandValue > playerHandValue ){
                                    System.out.print(", dealer stops.\nPlayer Hand values "+ playerHandValue + ", Dealer Hand values " + dealerHandValue + ".\nDealer WINS!!!");                                    
                                    gameOver = true;
                                    break;
                                }else if ( dealerHandValue == playerHandValue && dealerHandValue >= 17) {
                                    System.out.print(", dealer stops.\nPlayer Hand values "+ playerHandValue + ", Dealer Hand values " + dealerHandValue + ".\nDraw GAME!!!");
                                    gameOver = true;
                                    break;
                                }else{                      
                                    nrOfDraws++;
                                    drawCard(dealerHand);                               
                                    dealerHandValue += dealerHand.get(nrOfDraws).getValue();
                                    if (dealerHand.get(nrOfDraws).getNumber().equals("A")) nrOfAces++;
                                    System.out.print("\nDealer Draw("+nrOfDraws+"):" + dealerHand.get(nrOfDraws) + "");                         
                                    if (dealerHandValue > 21){                                     
                                        for (int i=0; i < dealerHand.size(); i++){
                                            if ( dealerHand.get(i).getNumber().equals("A") ){
                                                if (nrOfAces > 0) {
                                                    dealerHandValue -= 10;
                                                    nrOfAces--;
                                                }
                                      
                                            }
                                            if  ( dealerHandValue <=21 ){
                                                break;
                                            }                       
                                         }
                                        if (dealerHandValue>21){                                    
                                            System.out.println("\nDealer Hand is over 21. Dealer lost with " + dealerHandValue + " in his hand!\nPlayer WINS!!!");
                                            gameOver = true;
                                         }else if (dealerHandValue > playerHandValue){
                                            System.out.println("Dealers hand is " + dealerHandValue + ", dealer stops.");
                                            System.out.print("\nPlayer Hand values "+ playerHandValue + ", Dealer Hand values " + dealerHandValue + ".\nDealer WINS!!!");                                   
                                            gameOver = true;
                                        }else if (dealerHandValue == playerHandValue){
                                            System.out.println("Dealers hand is " + dealerHandValue + ", dealer stops.");
                                            System.out.print("\nPlayer Hand values "+ playerHandValue + ", Dealer Hand values " + dealerHandValue + ".\nDraw GAME!!!");                                 
                                            gameOver = true;
                                        }
                                     }
                                }
                }
            }             
                
                
        }catch (TooManyCardsException e){
            System.out.println("Too many cards inserted in the deck!!");          
        }catch(IOException e){
            e.printStackTrace(); 
        }catch(Exception e){
            e.printStackTrace();
        }
    }

   public static void drawCard(ArrayList<Card> hand){
            int randomCardIndex;
            Random random = new Random();
            //get a card index
            randomCardIndex = random.nextInt(deck.getSize());
            //pick card from index generated
            hand.add(deck.pickCard(randomCardIndex));
            //remove the card from index generated
            deck.removePick(randomCardIndex);
   }
}


