/**------------------------------------------
 Project 2: Blackjack

 Course: CS 342, Spring 2024.
 System: MacOS using IntelliJ
 Student Author: Matthew Sagat

 Brief Description: These files define the behavior of the JavaFX program that allows the user to play blackjack against a dealer.
 This file in particular defines the structure and members/functions that make up the game logic class.
 -------------------------------------------*/
import java.util.ArrayList;

public class BlackjackGameLogic {
    //Takes the player and banker hand and determines the winner.
    public String whoWon(ArrayList<Card> playerHand1, ArrayList<Card> dealerHand){
        int playerTotal = handTotal(playerHand1);
        int dealerTotal = handTotal(dealerHand);

        if(playerTotal == dealerTotal){
            return "push";
        }
        else if(playerTotal > dealerTotal){
            return "player";
        }
        else{
            return "dealer";
        }
    }
    //Evaluates the total of a hand, with some extra logic to apply aces as they most benefit the player or dealer.
    public int handTotal(ArrayList<Card> hand){
        int handTotal = 0;
        int totalAces = 0;
        for(int i = 0; i < hand.size(); i++){
            if(hand.get(i).value == 1){
                totalAces++;
            }
            handTotal+= hand.get(i).value;
        }
        //Ensures that an ace is not played high if it will cause a bust.
        if(totalAces > 0){
            for(int i = 0; i < totalAces; i++){
                if((handTotal + 10) <= 21){
                    handTotal+=10;
                }
            }
        }
        return handTotal;
    }
    //Just checks if the banker should draw another card.
    public boolean evaluateBankerDraw(ArrayList<Card> hand){
        return handTotal(hand) <= 16;
    }
}
