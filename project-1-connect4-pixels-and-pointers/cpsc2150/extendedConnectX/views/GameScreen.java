/*
    Cody Martin
        Added promptPlayAgain() method to factor out used code.
 */
package cpsc2150.extendedConnectX.views;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
Pixels and Pointers

Pierson Rinchere - piers0n

Angel Feliz - Afelizg

Lena Blankenbaker - lblankenbaker

Ryan Harvey - rjharve
*/

import java.util.HashSet;
import  java.util.Scanner;
import java.util.Set;

import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.GameBoardMem;
import cpsc2150.extendedConnectX.models.IGameBoard;

/**
 * Controls game flow
 *
 * @pre none
 *
 * @post none
 */
public class GameScreen {
    public static void main(String[] args){
        boolean gameOver = false;
        boolean playAgain = false;
        char [] playersTokens;
        String gameMode;
        int playerCount;
        int row;
        int column;
        int inARow;
        Scanner scanner = new Scanner(System.in);

        do {
            //set the amount of players
            do {
                System.out.println("How many players? (2-10)");
                String playerCountString = scanner.nextLine();
                playerCount = Integer.parseInt(playerCountString);

                if (playerCount < 2 || playerCount > 10) {
                    System.out.println("Please enter a number between 2 and 10");
                }

                playersTokens = new char[playerCount];
            }while(playerCount < 2 || playerCount > 10);

            Set<Character> usedTokens = new HashSet<>();

            //Have the users select each player's token
            for(int i = 0; i < playerCount; i++) {
                boolean validToken = false;
                while (!validToken) {
                    System.out.println("Enter the character to represent player " + (i + 1));
                    String playersTokenStr = scanner.nextLine();
                    playersTokenStr = playersTokenStr.toUpperCase();
                    char token = playersTokenStr.charAt(0);

                    if (usedTokens.contains(token)) {
                        System.out.println("Token already in use. Please choose a different character.");
                    } else {
                        playersTokens[i] = token;
                        usedTokens.add(token);
                        validToken = true;
                    }
                }
            }

            //ask how many rows and columns are wanted
            do {
                System.out.println("How many rows would you like to be on the board? (3-100)");
                String rowStr = scanner.nextLine();
                row = Integer.parseInt(rowStr);
            }while (row < 3 || row > 100);
            do {
                System.out.println("How many columns would you like to be on the board? (3-100)");
                String columnStr = scanner.nextLine();
                column = Integer.parseInt(columnStr);
            }while(column < 3 || column > 100);

            //ask how many in a row to win
            do {
                System.out.println("How many in a row to win? (3-100)");
                String inARowStr = scanner.nextLine();
                inARow = Integer.parseInt(inARowStr);
            }while(inARow < 3 || inARow > 25);

            //Ask if they prefer a fast game or a memory efficient game
            do {
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                gameMode = scanner.nextLine();
            }while(!(gameMode.equalsIgnoreCase("f")) && !(gameMode.equalsIgnoreCase("m")));

            IGameBoard gameBoard;
            if(gameMode.equalsIgnoreCase("f")){
                gameBoard = new GameBoard(row, column, inARow);
            }
            else {
                gameBoard = new GameBoardMem(row, column, inARow);
            }

            gameOver = false;
            int placeInCol;
            int currentPlayerIndex = 0;

            while (!gameOver) {
                System.out.println(gameBoard);
                char currentPlayer = playersTokens[currentPlayerIndex];
                System.out.println("Player " + currentPlayer + ", what column do you want to place your marker in?");
                placeInCol = scanner.nextInt();
                scanner.nextLine();

                if (placeInCol < 0 || placeInCol >= gameBoard.getColumns()) {
                    System.out.println("Invalid Column! please try again");
                    continue;
                }

                if (!gameBoard.checkIfFree(placeInCol)) {
                    System.out.println("Column is full, try again.");
                    continue;
                }

                gameBoard.dropToken(currentPlayer, placeInCol);
                if (gameBoard.checkForWin(placeInCol)) {
                    System.out.println(gameBoard);
                    System.out.println(" Player " + currentPlayer + " Won!");
                    gameOver = true;
                    playAgain = promptPlayAgain(scanner);
                    if (playAgain) { System.out.println("Thanks for playing!"); }
                } else if (gameBoard.checkTie()) {
                    System.out.println(gameBoard);
                    System.out.println("Game has ended up in a tie");
                    gameOver = true;
                    playAgain = promptPlayAgain(scanner);
                    if (playAgain) { System.out.println("Thanks for playing!"); }
                }

                if (currentPlayer == 'X') {
                    currentPlayer = 'O';
                } else {
                    currentPlayer = 'X';
                }

                //switch to the next player
                currentPlayerIndex = (currentPlayerIndex + 1) % playerCount;
            }
        } while (playAgain);
    }

    private static boolean promptPlayAgain(Scanner scanner) {
        System.out.println("Would you like to play again? Y/N");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("Y");
    }

}