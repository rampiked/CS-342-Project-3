/**------------------------------------------
 Project 2: Blackjack

 Course: CS 342, Spring 2024.
 System: MacOS using IntelliJ
 Student Author: Matthew Sagat

 Brief Description: These files define the behavior of the JavaFX program that allows the user to play blackjack against a dealer.
 This file in particular defines the structure and members/functions that make up the dealer class.
 -------------------------------------------*/
import java.util.ArrayList;
import java.util.*;
public class BlackjackDealer {
    ArrayList<Card> generatedDeck = new ArrayList<Card>();
    Stack<Card> gameDeck = new Stack<Card>();
    //Goes through all the cards in a deck, generates them. Four 10 value cards are added per suit for 10 and face cards.
    public void generateDeck(){
        generatedDeck.clear();
        //TODO:
        for(int i = 1; i < 10; i++){
            Card cardToAdd = new Card("Clubs", i);
            gameDeck.add(cardToAdd);
            generatedDeck.add(cardToAdd);
        }
        for(int i = 0; i < 4; i++){
            Card cardToAdd = new Card("Clubs", 10);
            gameDeck.add(cardToAdd);
            generatedDeck.add(cardToAdd);
        }
        for(int i = 1; i < 10; i++){
            Card cardToAdd = new Card("Diamonds", i);
            gameDeck.add(cardToAdd);
            generatedDeck.add(cardToAdd);
        }
        for(int i = 0; i < 4; i++){
            Card cardToAdd = new Card("Diamonds", 10);
            gameDeck.add(cardToAdd);
            generatedDeck.add(cardToAdd);
        }
        for(int i = 1; i < 10; i++){
            Card cardToAdd = new Card("Hearts", i);
            gameDeck.add(cardToAdd);
            generatedDeck.add(cardToAdd);
        }
        for(int i = 0; i < 4; i++){
            Card cardToAdd = new Card("Hearts", 10);
            gameDeck.add(cardToAdd);
            generatedDeck.add(cardToAdd);
        }
        for(int i = 1; i < 10; i++){
            Card cardToAdd = new Card("Spades", i);
            gameDeck.add(cardToAdd);
            generatedDeck.add(cardToAdd);
        }
        for(int i = 0; i < 4; i++){
            Card cardToAdd = new Card("Spades", 10);
            gameDeck.add(cardToAdd);
            generatedDeck.add(cardToAdd);
        }
    }
    //Deals two cards and returns the arrayList of the cards.
    public ArrayList<Card> dealHand(){
        if(gameDeck.size() < 2){
            generateDeck();
            shuffleDeck();
        }
        ArrayList<Card> hand1 = new ArrayList<Card>();
        hand1.add(gameDeck.pop());
        hand1.add(gameDeck.pop());
        return hand1;
    }
    //Returns the next card in the pile and removes it.
    public Card drawOne(){
        if(gameDeck.isEmpty()){
            generateDeck();
            shuffleDeck();
        }
        return gameDeck.pop();
    }
    //Takes the generated deck and makes a stack that is randomized.
    public void shuffleDeck(){
        generateDeck();
        gameDeck.clear();
        int index = 0;
        Random rand = new Random();
        //Goes through for each possible card.
        for(int i = 0; i < 52; i++) {
            //index is assigned a value based on the size left in generatedDeck.
            //This will be the index removed from the current deck, that will be added to the gameDeck.
            index = rand.nextInt(generatedDeck.size());
            gameDeck.add(generatedDeck.remove(index));
        }
    }
    //Returns deck size.
    public int deckSize(){
        //TODO:
        return gameDeck.size();
    }
}
