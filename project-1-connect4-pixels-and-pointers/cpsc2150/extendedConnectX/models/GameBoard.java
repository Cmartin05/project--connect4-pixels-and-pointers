/*
    Cody Martin
        Added parameters to constructor contract.
        Added parameters to dropToken() method.
 */

package cpsc2150.extendedConnectX.models;

/* Github user names and names
Lena Blankenbaker- lblankenbaker
Angel Feliz - Afelizg
Ryan Harvey - rjharve
Pierson Rinchere - piers0n
 */


import static cpsc2150.extendedConnectX.models.IGameBoard.MAX_COL;
import static cpsc2150.extendedConnectX.models.IGameBoard.MAX_ROW;

/**
 * GameBoard implements fundamental interactions dealing with the board
 * This class is an implementation od IGameBoard
 *
 * invariant [pieces must abide by gravity, so there cannot be a space between two vertically placed tokens]
 * AND [player 1 must have 0-1 more tokens than player 2]
 *
 * @corresponds:
 *              self = board
 */
public class GameBoard extends AbsGameBoard
{
    public int rows;
    public int cols;
    public int numToWin;


    private char[][] board;
    /**
     * Constructor for GameBoard class
     * @param rows # of rows in board
     * @param cols # of columns in board
     * @param numToWin # of tokens needed to win
     * @pre none
     * @post [Initializes the board with empty spaces and board is 6x7 filled
     * with empty slots]
     */

    public GameBoard(int rows, int cols, int numToWin){
        this.rows = rows;
        this.cols = cols;
        this.numToWin = numToWin;
        board = new char[rows][cols];

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                board[i][j] = ' ';
            }
        }
    }
    /**
     * places the token in the lowest available spot in the requested column
     *
     * @param p The token being placed.
     * @param c The colum that the token in being placed in
     *
     * @pre [p must be a char]
     * @pre 0 <= c <= 99
     *
     * @post [token p gets dropped in the lowest available spot in colum c]
     */
    public void dropToken(char p, int c)
    {
        //places the character p in column c. The token will be placed in the lowest available row in column c.
        for (int i = rows - 1; i >= 0; i--) {
            if (board[i][c] == ' ') {
                board[i][c] = p;
                break;
            }
        }
    }

    /**
     * checks what token is in the requested position
     * @param pos, the positioned getting checked
     *
     * @return the token character in the position or a blank space character if the position is empty.
     *
     * @pre [pos must have a row value between 0 and 8 and a column value between 0 and 6]
     *
     * @post [the function will return the token in the position]
     */
    public char whatsAtPos(BoardPosition pos)
    {
        return board[pos.getRow()][pos.getColumn()];
        //returns what is in the GameBoard at position pos If no marker is there, it returns a blank space char.
    }

    /**
     * Standard getter for rows
     * @return rows, an int
     *
     * @pre none
     *
     * @post getRows = rows AND rows = #rows AND columns = #columns AND numToWin = #numToWin
     */
    public int getRows(){
        return rows;
    }

    /**
     * Standard getter for cols
     * @return cols, an int
     *
     * @pre none
     *
     * @post getRows = rows AND rows = #rows AND columns = #columns AND numToWin = #numToWin
     */
    public int getColumns(){
        return cols;
    }

    /**
     * Standard getter for numToWin
     * @return numToWin, an int
     *
     * @pre none
     *
     * @post getRows = rows AND rows = #rows AND columns = #columns AND numToWin = #numToWin
     */
    public int getNumToWin(){
        return numToWin;
    }
}
