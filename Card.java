public class Card{
    
    private String number,suit;
    private int value;
    public Card (){}
    public Card (String number, String suit){
        setNumber(number);
        setSuit(suit);
        setValue(getNumber());
    }    
    
    public String getNumber(){
        return number;
    }
    public String getSuit(){
        return suit;
    }
    public int getValue(){
        return value;
    }
    
    //the special cards are treated individually to make sure that they have the coresponding value
    private void setValue(String number){
        if ( number.equals("J") || number.equals("Q") || number.equals("K") ) {
            value = 10;
        }else if ( number.equals("A") ) {
            value = 11;
        }else{
            value = Integer.parseInt(number);  
        }
    }
    //initialisation of the suit variables as it is called from deck constructor
    private void setSuit(String suit){
        switch ( suit ){
            case "1" : {
                this.suit = "Hearts";
                break;
            }
            case "2" : {
                this.suit = "Spades";
                break;
            }
            case "3" : {
                this.suit = "Clubs";
                break;
            }
            case "4" : {
                this.suit = "Diamonds";
                break;
            }
        }
    }
    private void setNumber(String number){
        switch ( number ){
            case "11" : {
                this.number = "A";
                break;
            }
            case "12" : {
                this.number = "J";
                break;
            }
            case "13" : {
                this.number = "Q";
                break;
            }
            case "14" : {
                this.number = "K";
                break;
            }
            default :{
                this.number = number;
                break;
            }
        }
    }      
    
    @Override
    public String toString(){
        return getNumber() + " " + getSuit();
    }
}
