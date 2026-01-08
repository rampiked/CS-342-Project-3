/**------------------------------------------
 Project 2: Blackjack

 Course: CS 342, Spring 2024.
 System: MacOS using IntelliJ
 Student Author: Matthew Sagat

 Brief Description: These files define the behavior of the JavaFX program that allows the user to play blackjack against a dealer.
 This file in particular defines the structure and members/functions that make up the card class.
 -------------------------------------------*/
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Card {
    String suit;
    int value;

    // Constructor
    public Card(String suitInput, int valueInput) {
        suit = suitInput;
        value = valueInput;
    }
    //This function creates a card in JavaFX by using a stackpane.
    //Show parameter is used to hide the card of the banker's first draw.
    public StackPane createCardGui(boolean show){
        StackPane cardPane = new StackPane();

        Rectangle bg = new Rectangle(150, 225);
        bg.setArcWidth(20);
        bg.setArcHeight(20);
        bg.setFill(Color.WHITE);

        Text text1 = new Text(suit.charAt(0) + String.valueOf(value));
        text1.setFont(Font.font(18));
        text1.setX(150 - text1.getLayoutBounds().getWidth() - 10);
        text1.setY(text1.getLayoutBounds().getHeight());


        if(suit.charAt(0) == 'D' || suit.charAt(0) == 'H') {
            text1.setFill(Color.RED);
        }
        else{
            text1.setFill(Color.BLACK);
        }

        if(!show){
            text1.setOpacity(0);
        }

        cardPane.getChildren().addAll(bg, text1);
        return cardPane;
    }
}