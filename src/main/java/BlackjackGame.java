/**------------------------------------------
 Project 2: Blackjack

 Course: CS 342, Spring 2024.
 System: MacOS using IntelliJ
 Student Author: Matthew Sagat

 Brief Description: These files define the behavior of the JavaFX program that allows the user to play blackjack against a dealer.
 This file in particular defines the structure and members/functions that make up the Blackjack class.
 -------------------------------------------*/
import java.util.ArrayList;

public class BlackjackGame {
    ArrayList<Card> playerHand = new ArrayList<Card>();
    ArrayList<Card> bankerHand = new ArrayList<Card>();
    BlackjackDealer theDealer = new BlackjackDealer();
    BlackjackGameLogic gameLogic = new BlackjackGameLogic();
    double currentBet = 0;
    double totalWinnings = 0;
    //Function returns the winner of the hand.
    public double evaluateWinnings(){
        double tempBet = currentBet;
        if(gameLogic.whoWon(playerHand, bankerHand) == "push"){
            currentBet = 0;
            return 0.0;
        }
        else if(gameLogic.whoWon(playerHand, bankerHand) == "player"){
            totalWinnings = totalWinnings + currentBet;
            currentBet = 0;
            return tempBet;
        }
        else{
            totalWinnings = totalWinnings - currentBet;
            currentBet = 0;
            return (tempBet * -1.0);
        }
    }
}
