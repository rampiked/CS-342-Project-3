import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class gameTest {
    BlackjackGame newGame = new BlackjackGame();
    //Not only does this function test size but it also checks that drawOne() and dealHand() work properly.
    @Test
    public void testSize() {
        newGame.theDealer.generateDeck();
        assertEquals(newGame.theDealer.generatedDeck.size(), 52);
        newGame.theDealer.shuffleDeck();
        assertEquals(newGame.theDealer.gameDeck.size(), 52);
        newGame.theDealer.drawOne();
        assertEquals(newGame.theDealer.gameDeck.size(), 51);
        newGame.theDealer.dealHand();
        assertEquals(newGame.theDealer.gameDeck.size(), 49);
    }
    //This function tests the shuffle and prints out all the drawn cards to the terminal, which verifies that the function works.
    @Test
    public void testShuffle() {
        newGame.theDealer.generateDeck();
        newGame.theDealer.shuffleDeck();
        assertEquals(newGame.theDealer.gameDeck.size(), 52);
        for(int i = 0; i < 52; i++) {
            System.out.println("Index: " + i);
            System.out.println(newGame.theDealer.gameDeck.peek().suit);
            System.out.println(newGame.theDealer.gameDeck.peek().value);
            newGame.theDealer.gameDeck.pop();
        }
    }
    //Checks that when adding three aces to a deck, the first one is added as 11 but the second two are not played high.
    @Test
    public void testHandTotal1() {
        newGame.theDealer.generateDeck();
        Card aceTestCard = new Card("Hearts", 1);
        newGame.playerHand.add(aceTestCard);
        newGame.playerHand.add(aceTestCard);
        newGame.playerHand.add(aceTestCard);
        System.out.println(newGame.gameLogic.handTotal(newGame.playerHand));
        assertEquals(newGame.gameLogic.handTotal(newGame.playerHand), 13);
    }

    //Tests that when adding a value that puts the hand over 21 (if aces are played high) to the end,
    //the evaluation of the deck isn't messed up.
    @Test
    public void testHandTotal2() {
        newGame.theDealer.generateDeck();
        Card aceTestCard = new Card("Hearts", 1);
        Card otherTestCard = new Card("Hearts", 10);
        newGame.playerHand.add(aceTestCard);
        newGame.playerHand.add(otherTestCard);
        System.out.println(newGame.gameLogic.handTotal(newGame.playerHand));
        assertEquals(newGame.gameLogic.handTotal(newGame.playerHand), 21);
    }
    //Case where player should win.
    @Test
    public void testWhoWon1() {
        newGame.theDealer.generateDeck();
        Card testCard1 = new Card("Hearts", 4);
        Card testCard2 = new Card("Hearts", 10);
        Card testCard3 = new Card("Hearts", 7);
        newGame.playerHand.add(testCard1);
        newGame.playerHand.add(testCard2);
        newGame.playerHand.add(testCard3);
        Card testCard4 = new Card("Hearts", 4);
        Card testCard5 = new Card("Hearts", 10);
        Card testCard6 = new Card("Hearts", 6);
        newGame.bankerHand.add(testCard4);
        newGame.bankerHand.add(testCard5);
        newGame.bankerHand.add(testCard6);
        assertEquals("player", newGame.gameLogic.whoWon(newGame.playerHand, newGame.bankerHand));
    }
    //Case where dealer should win.
    @Test
    public void testWhoWon2() {
        newGame.theDealer.generateDeck();
        Card testCard1 = new Card("Hearts", 4);
        Card testCard2 = new Card("Hearts", 10);
        Card testCard3 = new Card("Hearts", 6);
        newGame.playerHand.add(testCard1);
        newGame.playerHand.add(testCard2);
        newGame.playerHand.add(testCard3);
        Card testCard4 = new Card("Hearts", 4);
        Card testCard5 = new Card("Hearts", 10);
        Card testCard6 = new Card("Hearts", 7);
        newGame.bankerHand.add(testCard4);
        newGame.bankerHand.add(testCard5);
        newGame.bankerHand.add(testCard6);
        assertEquals("dealer", newGame.gameLogic.whoWon(newGame.playerHand, newGame.bankerHand));
    }
    //Push situation.
    @Test
    public void testWhoWon3() {
        newGame.theDealer.generateDeck();
        Card testCard1 = new Card("Hearts", 4);
        Card testCard2 = new Card("Hearts", 10);
        Card testCard3 = new Card("Hearts", 7);
        newGame.playerHand.add(testCard1);
        newGame.playerHand.add(testCard2);
        newGame.playerHand.add(testCard3);
        Card testCard4 = new Card("Hearts", 4);
        Card testCard5 = new Card("Hearts", 10);
        Card testCard6 = new Card("Hearts", 7);
        newGame.bankerHand.add(testCard4);
        newGame.bankerHand.add(testCard5);
        newGame.bankerHand.add(testCard6);
        assertEquals("push", newGame.gameLogic.whoWon(newGame.playerHand, newGame.bankerHand));
    }
    //Tests that it returns true when under or at 16, then true when over.
    @Test
    public void testEvaluateBankerDraw(){
        newGame.theDealer.generateDeck();
        Card testCard1 = new Card("Hearts", 4);
        Card testCard2 = new Card("Hearts", 10);
        Card testCard3 = new Card("Hearts", 7);
        newGame.playerHand.add(testCard1);
        newGame.playerHand.add(testCard2);
        newGame.playerHand.add(testCard3);
        Card testCard4 = new Card("Hearts", 4);
        Card testCard5 = new Card("Hearts", 10);
        newGame.bankerHand.add(testCard4);
        newGame.bankerHand.add(testCard5);

        assertTrue(newGame.gameLogic.evaluateBankerDraw(newGame.bankerHand));
        Card testCard6 = new Card("Hearts", 2);
        newGame.bankerHand.add(testCard6);
        assertTrue(newGame.gameLogic.evaluateBankerDraw(newGame.bankerHand));
        Card testCard7 = new Card("Hearts", 1);
        newGame.bankerHand.add(testCard7);
        assertFalse(newGame.gameLogic.evaluateBankerDraw(newGame.bankerHand));
    }
}