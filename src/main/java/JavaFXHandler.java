/**------------------------------------------
 Project 2: Blackjack

 Course: CS 342, Spring 2024.
 System: MacOS using IntelliJ
 Student Author: Matthew Sagat

 Brief Description: These files define the behavior of the JavaFX program that allows the user to play blackjack against a dealer.
 This file in particular defines the actual JavaFX scenes that are used throughout the program.
 -------------------------------------------*/
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import java.util.*;


public class JavaFXHandler extends Application {
	double userBalance = 0;
	double currentBet = 0;
	BorderPane mainGamePane = new BorderPane();
	Text userMoney = new Text();
	Text winHandText = new Text("HAND WON");
	Text loseHandText = new Text("HAND LOST");
	Text blackjackHandText = new Text("BLACKJACK!");
	Text dealerBlackjackText = new Text("DEALER BLACKJACK, HAND LOST");
	Text pushText = new Text("PUSH");
	TextField moneyField = new TextField();
	Text betPrompt = new Text("Enter bet amount:");
	//Opening screen.
	Text enterMoneyText = new Text("Enter starting money value:");
	Button newGameButton = new Button("Start new game");
	VBox newGameButtonResultText = new VBox(15);
	StackPane firstBankerCard = new StackPane();
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//This portion of the code handles the title screen.
		mainGamePane.setStyle("-fx-background-color: rgb(16,82,64);");
		BorderPane openingGamePane = new BorderPane();
		openingGamePane.setStyle("-fx-background-color: rgb(16,82,64);");
		Text nameText = new Text("Made by Matt Sagat");
		Text titleText = new Text("Blackjack");
		VBox titleTextBox = new VBox(180);
		titleText.setFont(Font.font(100));
		titleTextBox.getChildren().addAll(nameText, titleText);
		titleTextBox.setAlignment(Pos.TOP_CENTER);
		openingGamePane.setTop(titleTextBox);
		VBox textMoneyAndButton = new VBox(8);
		HBox moneyAndButton = new HBox(8);
		enterMoneyText.setFont(Font.font(30));
		moneyField.setPrefWidth(200);
		moneyField.setPrefHeight(40);
		Button enterMoneyButton = new Button("Submit");
		enterMoneyButton.setPrefHeight(40);
		moneyAndButton.getChildren().addAll(moneyField, enterMoneyButton);
		moneyAndButton.setAlignment(Pos.CENTER);
		textMoneyAndButton.getChildren().addAll(enterMoneyText, moneyAndButton);
		textMoneyAndButton.setAlignment(Pos.CENTER);
		openingGamePane.setCenter(textMoneyAndButton);
		Scene scene = new Scene(openingGamePane, 1500,700);

		primaryStage.setScene(scene);
		primaryStage.show();

		//This section of the code defines the main game screen.
		BlackjackGame currentGame = new BlackjackGame();
		currentGame.theDealer.generateDeck();
		currentGame.theDealer.shuffleDeck();
		currentGame.playerHand = currentGame.theDealer.dealHand();
		currentGame.bankerHand = currentGame.theDealer.dealHand();
		StackPane resultText = new StackPane();
		resultText.getChildren().addAll(winHandText,loseHandText,blackjackHandText,pushText,dealerBlackjackText);
		newGameButtonResultText.getChildren().addAll(newGameButton,resultText);
		newGameButton.setPrefHeight(50);
		newGameButton.setPrefWidth(200);
		newGameButton.setStyle("-fx-font-size:20");
		newGameButtonResultText.setAlignment(Pos.CENTER);
		newGameButton.setAlignment(Pos.CENTER);
		mainGamePane.setCenter(newGameButtonResultText);
		newGameButton.setOpacity(0);
		newGameButton.setDisable(true);
		winHandText.setOpacity(0);
		winHandText.setFont(Font.font(20));
		loseHandText.setOpacity(0);
		loseHandText.setFont(Font.font(20));
		blackjackHandText.setOpacity(0);
		blackjackHandText.setFont(Font.font(20));
		dealerBlackjackText.setOpacity(0);
		dealerBlackjackText.setFont(Font.font(20));
		pushText.setOpacity(0);
		pushText.setFont(Font.font(20));

		VBox playerSide = new VBox(25);
		VBox bankerSide = new VBox(25);
		Button callButton = new Button("Call");
		Button standButton = new Button("Stand");
		callButton.setPrefHeight(40);
		callButton.setPrefWidth(150);
		callButton.setStyle("-fx-font-size:20");
		standButton.setPrefHeight(40);
		standButton.setPrefWidth(150);
		standButton.setStyle("-fx-font-size:20");
		callButton.setDisable(true);
		standButton.setDisable(true);
		HBox playerCards = new HBox(20);
		HBox bankerCards = new HBox(20);
		playerSide.getChildren().add(callButton);
		bankerSide.getChildren().add(standButton);
		playerSide.setAlignment(Pos.CENTER_LEFT);
		bankerSide.setAlignment(Pos.CENTER_RIGHT);
		mainGamePane.setLeft(playerSide);
		mainGamePane.setRight(bankerSide);
		VBox moneyAndBetAmount = new VBox(5);
		HBox betAmountAndSubmit = new HBox();
		TextField betAmount = new TextField();
		betAmount.setPrefWidth(300);
		betAmount.setPrefHeight(40);
		Button submitBet = new Button("Submit");
		submitBet.setPrefHeight(40);
		betAmountAndSubmit.getChildren().addAll(betAmount, submitBet);
		betAmountAndSubmit.setAlignment(Pos.TOP_CENTER);
		betPrompt.setFont(Font.font(30));
		userMoney.setFont(Font.font(30));
		moneyAndBetAmount.getChildren().addAll(userMoney, betPrompt, betAmountAndSubmit);
		moneyAndBetAmount.setAlignment(Pos.TOP_CENTER);
		mainGamePane.setTop(moneyAndBetAmount);
		Scene mainScene = new Scene(mainGamePane, 1500,700);
		Text playerHandTotalText = new Text("Hand total: ");
		playerHandTotalText.setFont(Font.font(30));
		Text bankerHandTotalText = new Text("Hand total: ");
		bankerHandTotalText.setFont(Font.font(30));
		enterMoneyButton.setOnAction(e -> enterMoneyButtonPressed(moneyField, enterMoneyText, mainScene, primaryStage, submitBet, betAmount));
		submitBet.setOnAction(e -> handleBetSubmission(primaryStage, scene, playerCards, bankerCards, betAmount, submitBet, currentGame, playerSide, bankerSide, callButton, standButton, playerHandTotalText, bankerHandTotalText));
		playerSide.getChildren().addAll(playerHandTotalText, playerCards);
		bankerSide.getChildren().addAll(bankerHandTotalText, bankerCards);
		newGameButton.setOnAction(e -> startNewGame(scene, primaryStage));
		//Call and stand button lambda function calls.
		callButton.setOnAction(e -> callButtonHandler(primaryStage, scene, playerCards, currentGame, playerHandTotalText, bankerCards, callButton, standButton, submitBet, betAmount));
		standButton.setOnAction(e -> standButtonHandler(primaryStage, scene, playerCards, currentGame, bankerHandTotalText, bankerCards, callButton, standButton, submitBet, betAmount));
	}
	//Controls the behavior when the new game button is pressed. It just hides the button once again and changes the scene.
	public void startNewGame(Scene scene, Stage stage){
		newGameButton.setOpacity(1);
		newGameButton.setDisable(true);
		stage.setScene(scene);
	}
	//Handles the result of the cards. Triggers a reset of the game.
	public void resultHandler(Stage primaryStage, Scene scene, String result, BlackjackGame currentGame, HBox playerCards, HBox bankerCards, Button callButton, Button standButton, Button submitBet, TextField betAmount){
		//Function takes a string that indicates how to alter the user balance.
		if(Objects.equals(result, "BJ")){
			userBalance = userBalance + (currentBet * 1.5);
			blackjackHandText.setOpacity(1);
		}
		else if(Objects.equals(result, "DBJ")){
			userBalance = userBalance - currentBet;
			dealerBlackjackText.setOpacity(1);
		}
		else if(Objects.equals(result, "W")){
			userBalance = userBalance + currentBet;
			winHandText.setOpacity(1);
		}
		//Push.
		else if(Objects.equals(result, "P")){
			pushText.setOpacity(1);
		}
		//Loss
		else{
			userBalance = userBalance - currentBet;
			loseHandText.setOpacity(1);
		}
		//Resets the scene to allow placing the bet again.
		userMoney.setText("$" + String.valueOf(userBalance));
		callButton.setDisable(true);
		standButton.setDisable(true);
		submitBet.setDisable(false);
		betAmount.setText("");
		betAmount.setDisable(false);
		//If the user reaches 0 balance, the title screen is re-entered.
		if(userBalance == 0){
			newGameButton.setOpacity(1);
			newGameButton.setDisable(false);
			betAmount.setDisable(true);
			submitBet.setDisable(true);
			moneyField.clear();
			enterMoneyText.setText("Enter starting money value:");
		}
	}
	//Handles the functionality of the call button.
	public void callButtonHandler(Stage primaryStage, Scene scene, HBox playerHand, BlackjackGame currentGame, Text playerHandTotal, HBox bankerHand, Button callButton, Button standButton, Button submitBet, TextField betAmount){
		//Draws a card and creates a JavaFX StackPane to go on the screen.
		Card newCard = currentGame.theDealer.drawOne();
		currentGame.playerHand.add(newCard);
		playerHand.getChildren().add(newCard.createCardGui(true));
		//Checks for the player busting. If they do it signals a loss and returns the function.
		if(currentGame.gameLogic.handTotal(currentGame.playerHand) > 21){
			playerHandTotal.setText("Hand total: BUST!");
			resultHandler(primaryStage, scene, "",currentGame, playerHand, bankerHand, callButton, standButton, submitBet, betAmount);
			return;
		}
		//Updates player hand total.
		playerHandTotal.setText("Hand total: " + String.valueOf(currentGame.gameLogic.handTotal(currentGame.playerHand)));
	}
	//Handles the stand functionality.
	public void standButtonHandler(Stage primaryStage, Scene scene, HBox playerHand, BlackjackGame currentGame, Text bankerHandTotal, HBox bankerHand, Button callButton, Button standButton, Button submitBet, TextField betAmount){
		//Make first card visible.
		firstBankerCard.getChildren().get(firstBankerCard.getChildren().size() - 1).setOpacity(1);
		//If we stand, banker starts to draw cards until evaluateBankerDraw returns false (when the hand is worth 16 or more).
		//This part of the code is like an automated version of the call code.
		while(currentGame.gameLogic.evaluateBankerDraw(currentGame.bankerHand)){
			Card newCard = currentGame.theDealer.drawOne();
			currentGame.bankerHand.add(newCard);
			bankerHand.getChildren().add(newCard.createCardGui(true));
			//Signals a bust. Banker loses.
			if(currentGame.gameLogic.handTotal(currentGame.bankerHand) > 21){
				bankerHandTotal.setText("Hand total: BUST!");
				resultHandler(primaryStage, scene, "W",currentGame, playerHand, bankerHand, callButton, standButton, submitBet, betAmount);
				return;
			}
		}
		//Updates the banker hand total. Evaluates result of the round and passes appropriate resultHandler string.
		bankerHandTotal.setText("Hand total: " + String.valueOf(currentGame.gameLogic.handTotal(currentGame.bankerHand)));
		if(Objects.equals(currentGame.gameLogic.whoWon(currentGame.playerHand, currentGame.bankerHand), "player")){
			resultHandler(primaryStage, scene, "W", currentGame, playerHand, bankerHand, callButton, standButton, submitBet, betAmount);
		}
		else if(Objects.equals(currentGame.gameLogic.whoWon(currentGame.playerHand, currentGame.bankerHand), "push")){
			resultHandler(primaryStage, scene, "P", currentGame, playerHand, bankerHand, callButton, standButton, submitBet, betAmount);
		}
		else{
			resultHandler(primaryStage, scene, "", currentGame, playerHand, bankerHand, callButton, standButton, submitBet, betAmount);
		}
	}
	//Controls the behavior when the user submits a bet.
	public void handleBetSubmission(Stage primaryStage, Scene scene, HBox playerHand, HBox bankerHand, TextField betAmount, Button submitButton, BlackjackGame currentGame, VBox playerSide, VBox bankerSide, Button callButton, Button standButton, Text playerHandTotalText, Text bankerHandTotalText){
		//Checks for a valid input.
		if(!containsOnlyNumbers(betAmount.getText()) || Double.parseDouble(betAmount.getText()) > userBalance){
			betPrompt.setText("Enter valid bet:");
			return;
		}
		//Sets all the possible hand end messages to be invisible.
		dealerBlackjackText.setOpacity(0);
		winHandText.setOpacity(0);
		loseHandText.setOpacity(0);
		blackjackHandText.setOpacity(0);
		pushText.setOpacity(0);
		//Resets betPrompt text in the case that the user previously entered an invalid bet.
		betPrompt.setText("Enter bet amount:");
		//Clears the hands from the last round.
		clearHands(playerHand, bankerHand, currentGame);
		//Takes the user input and converts it to a double rather than a string.
		currentBet = Double.parseDouble(betAmount.getText());
		//Adds the first two cards to the player's hand.
		for(int i = 0; i < currentGame.playerHand.size(); i++){
			playerHand.getChildren().add(currentGame.playerHand.get(i).createCardGui(true));
		}
		//Adds the first two cards to the banker's hand.
		for(int i = 0; i < currentGame.bankerHand.size(); i++){
			//This enables the first drawn hand to be hidden.
			//The boolean parameter just controls card visibility.
			if(i == 0){
				firstBankerCard = currentGame.bankerHand.get(i).createCardGui(false);
				bankerHand.getChildren().add(firstBankerCard);
			}
			else{
				bankerHand.getChildren().add(currentGame.bankerHand.get(i).createCardGui(true));
			}
		}
		//Disables bet text field and buttons.
		betAmount.setDisable(true);
		submitButton.setDisable(true);
		//Enables call and stand buttons.
		callButton.setDisable(false);
		standButton.setDisable(false);
		//Updates player and banker hand totals.
		playerHandTotalText.setText("Hand total: " + String.valueOf(currentGame.gameLogic.handTotal(currentGame.playerHand)));
		bankerHandTotalText.setText("Hand total: " + "???");
		//Checks if the user or player were dealt blackjack.
		if(currentGame.gameLogic.handTotal(currentGame.playerHand) == 21 && currentGame.gameLogic.handTotal(currentGame.bankerHand) != 21){
			firstBankerCard.getChildren().get(firstBankerCard.getChildren().size() - 1).setOpacity(1);
			bankerHandTotalText.setText("Hand total: " + String.valueOf(currentGame.gameLogic.handTotal(currentGame.bankerHand)));
			resultHandler(primaryStage, scene, "BJ", currentGame, playerHand, bankerHand, callButton, standButton, submitButton, betAmount);
		}
		if(currentGame.gameLogic.handTotal(currentGame.bankerHand) == 21 && currentGame.gameLogic.handTotal(currentGame.playerHand) != 21){
			firstBankerCard.getChildren().get(firstBankerCard.getChildren().size() - 1).setOpacity(1);
			bankerHandTotalText.setText("Hand total: " + String.valueOf(currentGame.gameLogic.handTotal(currentGame.bankerHand)));
			resultHandler(primaryStage, scene, "DBJ", currentGame, playerHand, bankerHand, callButton, standButton, submitButton, betAmount);
		}
		//Handles very rare situation that both banker and player draw blackjack. This results in a push.
		if(currentGame.gameLogic.handTotal(currentGame.playerHand) == 21 && currentGame.gameLogic.handTotal(currentGame.bankerHand) == 21){
			firstBankerCard.getChildren().get(firstBankerCard.getChildren().size() - 1).setOpacity(1);
			bankerHandTotalText.setText("Hand total: " + String.valueOf(currentGame.gameLogic.handTotal(currentGame.bankerHand)));
			resultHandler(primaryStage, scene, "P", currentGame, playerHand, bankerHand, callButton, standButton, submitButton, betAmount);
		}
	}
	//Empties both card HBoxes and the actual ArrayLists.
	public void clearHands(HBox playerCards, HBox bankerCards, BlackjackGame currentGame){
		playerCards.getChildren().clear();
		bankerCards.getChildren().clear();
		currentGame.playerHand.clear();
		currentGame.bankerHand.clear();
		currentGame.playerHand = currentGame.theDealer.dealHand();
		currentGame.bankerHand = currentGame.theDealer.dealHand();
	}
	//Outlines behavior when the opening screen's button is pressed.
	public void enterMoneyButtonPressed(TextField textField1, Text textAbove, Scene sceneToSet, Stage stage, Button betAmount, TextField submitBet){
		winHandText.setOpacity(0);
		loseHandText.setOpacity(0);
		blackjackHandText.setOpacity(0);
		dealerBlackjackText.setOpacity(0);
		pushText.setOpacity(0);
		newGameButton.setOpacity(0);
		newGameButton.setDisable(true);
		betAmount.setDisable(false);
		submitBet.setDisable(false);
		//Dot already just checks that only one '.' is present in the user inputted text.
		boolean dotAlready = false;
		//Checks that input is valid (solely numerical, not lead with a 0, field contains something).
		if(Objects.equals(textField1.getText(), "")){
			textAbove.setText("Please enter a valid number.");
			return;
		}
		//Loops through each digit to ensure proper input.
		for(int i = 0; i < textField1.getText().length(); i++){
			//Ensures that there is only a single '.' in the user number. Also checks that the '.' isn't at the end of the line.
			if(textField1.getText().charAt(i) == '.'){
				if(!dotAlready) {
					dotAlready = true;
					//If the '.' is at the end of the line, we have another issue.
					if(textField1.getText().charAt(i) == textField1.getText().charAt(textField1.getText().length()-1)){
						textAbove.setText("'.' may not be at the end of your number.");
						return;
					}
				}
				else{
					textAbove.setText("Only one '.' may be present in your number.");
					return;
				}
			}
			//Checks that there is only digits and '.' in the number as well as only being one '.'.
			if((!Character.isDigit(textField1.getText().charAt(i)) && textField1.getText().charAt(i) != '.')|| ((textField1.getText().charAt(0) == '0') && (textField1.getText().length() >= 2) && (textField1.getText().charAt(1) != '.'))){
				textAbove.setText("Must enter an entirely numerical value that is not lead with 0.");
				return;
			}
			//This was just something that made for easier messing with the strings on my end.
			if(dotAlready && textField1.getText().charAt(textField1.getText().length()-1) == '0'){
				textAbove.setText("Decimal number may not end with a 0.");
				return;
			}
		}
		//If the function gets to this point, the user has entered a properly formatted number to use for the game.
		textAbove.setText("Successful input.");
		userBalance = Double.parseDouble(textField1.getText());
		userMoney.setText("$" + String.valueOf(userBalance) + "0");
		//Switches scene to the main scene.
		stage.setScene(sceneToSet);
	}
	//Helper function found on stack overflow post and modified by me to allow for '.' to be an acceptable character.
	public boolean containsOnlyNumbers(String str) {
		//It can't contain only numbers if it's null or empty...
		if (str == null || str.length() == 0)
			return false;

		for (int i = 0; i < str.length(); i++) {

			//If we find a non-digit character we return false.
			if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != '.')
				return false;
		}

		return true;
	}
}