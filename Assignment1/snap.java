import java.io.*;
import java.util.*;

public class snap {

	private static final int DECK_SIZE = 52;				//初始化牌堆，52張
	
	private static int maxRounds;

	private static int roundsCompleted = 0;

	private static boolean thisGame = false;
	
	private static MultiDS<Card> deck = new MultiDS<Card>(DECK_SIZE);				
	private static MultiDS<Card> faceDown1 = new MultiDS<Card>(DECK_SIZE);			
	private static MultiDS<Card> faceDown2 = new MultiDS<Card>(DECK_SIZE);		
	private static MultiDS<Card> faceUp1 = new MultiDS<Card>(DECK_SIZE);		
	private static MultiDS<Card> faceUp2 = new MultiDS<Card>(DECK_SIZE);	
	private static MultiDS<Card> snapPool = new MultiDS<Card>(DECK_SIZE);				
	
	

	public static void main(String[] args) {
		
		
		//扫描最大的轮数, scan for the max round user wants
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Please enter the max rounds:");
        maxRounds = keyboard.nextInt();

		
		
		System.out.println("Welcome to the Game of Snap!\n");

		createDeck(deck);													
		
		deck.shuffle();														
		
		System.out.println("Now dealing the cards to the players...\n");			
		
		dealCards(deck, faceDown1, faceDown2);						
		
		//展示双方手牌,show what each has in their hand
		System.out.println("\nHere is Player 1's face-down pile:\n");						
		System.out.println(faceDown1.toString());				
		
		System.out.println("\nHere is Player 2's face-down pile:\n");					
		System.out.println(faceDown2.toString());				
		
		System.out.println("\nStarting the game!\n");				// Message to begin the game
		
		do {
			roundsCompleted++;								// starts from the 1st round, and print the current round，内嵌判断输赢method
			if(roundsCompleted!=0&&(roundsCompleted<=maxRounds)){
			System.out.println("Round: " + roundsCompleted);
			} 
			drawCards();//draw&compare
										
		} while (!thisGame);		// 判断游戏结束, see if the game is over
		
	}
	
	
	
   

	private static void createDeck(MultiDS<Card> deck) {
		
		for (int s = 0; s < Card.Suits.values().length; s++)				
			for (int r = 0; r < Card.Ranks.values().length; r++)			
				deck.addItem(new Card(Card.Suits.values()[s], Card.Ranks.values()[r]));	
	}
	
	
	
   private static void dealCards(MultiDS<Card> deck, MultiDS<Card> p1, MultiDS<Card> p2) {
		
		for (int i = 0; i < DECK_SIZE; i += 2) {			
			p1.addItem(deck.removeItem());					
			p2.addItem(deck.removeItem());					//remove cards from top and deal them
		}
	}
	
	
	
   
   private static void drawCards() {
		
		checkCardCount();//check if round/hand completed/empty							
		
		

		Card p1DrawnCard = faceDown1.removeItem();		// Removes a card from player 1's hand and stores it
		Card p2DrawnCard = faceDown2.removeItem();		// Removes a card from player 2's hand and stores it
		Card snapDrawnCard = snapPool.top();     // cards on top of  snappool
		
		
		
		faceUp1.addItem(p1DrawnCard);					// Adds player 1's removed card to faceUp1
		faceUp2.addItem(p2DrawnCard);					// Adds player 2's removed card to faceUp2
		
		if(roundsCompleted!=0){
		System.out.println("P1 Has drawn:" + faceUp1.top());
		System.out.println("P2 Has drawn:" + faceUp2.top());
		}
		if(!snapPool.empty()){
			System.out.println("Card on snapPool top is: " + snapDrawnCard);
		}

		compareCards(p1DrawnCard, p2DrawnCard, snapDrawnCard);			//compare, then show what they left
      
	  	System.out.println("P1 Has " + faceDown1.size() + " cards remain in FaceDown.");
		System.out.println("P2 Has " + faceDown2.size() + " cards remain in FaceDown.");
		
      
	}
	
	
	
   
	private static void compareCards(Card p1, Card p2, Card p3) {
		
		int result1 = p1.compareTo(p2);				
		int result2;
		int result3;
		int snapPoolSize = snapPool.size();				
		
		//写可能性, random a number for shout
		Random randomNum = new Random();
		int correctShout = randomNum.nextInt(10);
		Random numRandom = new Random();
		int wrongShout = numRandom.nextInt(100);
		
		//如果pool顶是空的直接算错, false if pool has nothing in it
		if ( p3 == null) {
            result2 = result3 = -1;
        } else {
            result2 = p1.compareTo(p3);
            result3 = p2.compareTo(p3);
        }


		//if matches
		if (result1==0||result2==0||result3==0) {												
			
			//int roundsCompletedplus1 = roundsCompleted+1;
			System.out.println("Rounds: " + roundsCompleted + " match!");
			
			if(result1==0){
				System.out.println("Card from pile 1:" + p1 +" matches card from pile 2:" + p2);
			}else if(result2==0){
				System.out.println("Card from pile 1:" + p1 +" matches card from pile 3:" + p3);
			}else if(result3==0){
				System.out.println("Card from pile 2:" + p2 +" matches card from pile 3:" + p3);
			}

			if(correctShout <= 3){
				//玩家1赢了，把2的up堆和pool洗到自己的down里, player 1 won
				System.out.println("Player1 wins!");

			for (int i = 0; i <= snapPoolSize; i++){						
				faceDown1.addItem(snapPool.removeItem());			
				faceDown1.shiftRight();
				faceDown1.shuffle();
			}
			for (int i = 0; i <= faceUp2.size(); i++){
				faceDown1.addItem(faceUp2.removeItem());
				faceDown1.shiftRight();
				faceDown1.shuffle();
			}
			}else if(3 < correctShout && correctShout <= 7){							
				//同上，反之 same as above but for p2
				System.out.println("Player 2 wins!");	

			for (int i = 0; i <= snapPoolSize; i++){						
				faceDown2.addItem(snapPool.removeItem());			
				faceDown2.shiftRight();
				faceDown2.shuffle();
			}
			for (int i = 0; i <= faceUp1.size(); i++){
				faceDown2.addItem(faceUp1.removeItem());
				faceDown2.shiftRight();
				faceDown2.shuffle();
			}
			}else{
				System.out.println("NO ONE SHOUTS");
			}
			
		}
		else {
		if(wrongShout == 0){//p1 is wrong，同下
				
				snapPool.shuffle();
				
				for(int i = 0; i<faceUp1.size();i++){
					snapPool.addItem(faceUp1.removeItem());
					//snapPool.shiftRight();
				}
		}else if(wrongShout==100){//p2 is wrong，牌洗到pool里
				
				snapPool.shuffle();
				
				for(int i = 0; i<faceUp2.size();i++){
					snapPool.addItem(faceUp2.removeItem());
					//snapPool.shiftRight();
				}
		}else {
			if(roundsCompleted!=0){
			System.out.println("NO ONE SHOUTS, GAME CONT");
			}
		}

		
	}
	}
	
	
	
	
	private static void checkCardCount() {
		
		int p1FaceupSize = faceUp1.size();			
		int p2FaceupSize = faceUp2.size();
		int p1FacedownSize = faceDown1.size();
		int p2FacedownSize = faceDown2.size();
		int trueMaxRounds = maxRounds+1;//run one more rounds

		if (roundsCompleted == trueMaxRounds) {//判断是否达到最大轮数，谁牌多谁赢

			thisGame = true;
            if ((p1FaceupSize + p1FacedownSize) > (p2FaceupSize + p2FacedownSize)) {
                System.out.println("Player 1 has total cards of " +(p1FaceupSize + p1FacedownSize) + ". Player 2 has total cards of " + (p2FaceupSize + p2FacedownSize));
				System.out.println("\n player 1 win \n");
            } else if ((p2FaceupSize + p2FacedownSize) > (p1FaceupSize + p1FacedownSize)) {
				System.out.println("Player 1 has total cards of " +(p1FaceupSize + p1FacedownSize) + ". Player 2 has total cards of " + (p2FaceupSize + p2FacedownSize));
                System.out.println("\n player 2 win \n");
            } else {
				System.out.println("Player 1 has total cards of " +(p1FaceupSize + p1FacedownSize) + ". Player 2 has total cards of " + (p2FaceupSize + p2FacedownSize));
                System.out.println("\n Ties \n");
            }
			System.out.println("\nGAME OVER\n");
            System.exit(0);	

        }

		
					
		


		if (faceDown1.size() == 0) {						//if player 1's hand is empty
			System.out.println("\tReloading and shuffling Player 1's Face-up pile.");	
			if (p1FaceupSize == 0) {//如果没牌就算输

				thisGame = true;										
				System.out.println("\nPlayer 1 has no cards remaining!");						
				System.out.println("GAME OVER\n");
				System.exit(0);			
			}
			else {											// if player 1 has cards in their up pile
				for (int i = 0; i < p1FaceupSize; i++)		//将牌洗回去				
					faceDown1.addItem(faceUp1.removeItem());	
				faceDown1.shuffle();					
			}
		}
		if (faceDown2.size() == 0) {						//if player 2's hand is empty
			System.out.println("\tReloading and shuffling Player 2's Face-up pile.");	// Message for fetching the pile
			if (p2FaceupSize == 0) {

				thisGame = true;								
				System.out.println("\nPlayer 2 has no cards remaining!");						
				System.out.println("GAME OVER\n");	
				System.exit(0);												
			}
			else {											// if player 2 has cards in their up pile
				for (int i = 0; i < p2FaceupSize; i++)					
					faceDown2.addItem(faceUp2.removeItem());	
				faceDown2.shuffle();					
			}
		}
	}
	
}