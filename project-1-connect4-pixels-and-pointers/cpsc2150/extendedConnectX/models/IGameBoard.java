/*
   Cody Martin
    Most Fixes are to method contracts to be more specific in the
    preconditions and postconditions.
    I also reduced some if else statements into one line for a better cleanup of space.
 */
package cpsc2150.extendedConnectX.models;

/**
 * IGameBoard is an interface that defines behavior for all GameBoard objects.
 * GameBoard have constants MAX_ROW, MAX_COL, and NUM_TO_WIN. This interface
 * specifies the ability access, mutate, and return.
 *
 * @initialization_ensures: The gameboard is initialized into its respective number of rows and columns,
 * the number of tokens needed to win, MAX_ROW and MAX_COL are non-null.
 *
 * @defines: self: A gameboard
 *         MAX_ROW: the maximum number of rows for the gameboard.
 *         MAX_COL: the maximum number of columns for the gameboard.
 *         TOKENS_TO_WIN: The number of tokens (consecutively) needed to win.
 *
 * @constraints: MAX_ROX > 0 AND MAX_ROW <= 9
 * MAX_COL > 0 AND MAX_COL <= 7
 * TOKENS_TO_WIN = 5
 */

public interface IGameBoard {
    // Constants
    int MAX_ROW = 9;
    int MAX_COL = 7;
    int TOKENS_TO_WIN = 5;

    // Primary and Secondary Methods

    /**
     * Checks to see if the column can accept the token
     * @param c, the column being checked
     * @return true if the column can accept the token, false otherwise
     *
     * @pre 0 <= c <= MAX_COL
     * @post checkIfFree = true iff whatAtPos(BoardPosition(0, c)) == ' '
     */

    // Secondary Method
    public default boolean checkIfFree(int c)
    {
        return whatsAtPos(new BoardPosition(0, c)) == ' ';
    }

    /**
     * Drops a token for the player in the given column.
     * @param p the players token to be placed
     * @param c the column the token will be dropped in.
     *
     * @pre p == 'X' OR p == 'O' AND 0 <= c <= MAX_COL
     * AND checkIfFree = true
     *
     * @post [The token is dropped in the lowest empty row of the
     * column and the board is updated to include the token]
     */
    // Primary Method
    public void dropToken(char p, int c);

    /**
     *
     * Checks if the players last placed token results in a win.
     * @param c, the column the token is being placed in.
     * @return true if the token does result in a win (5 in a row), false otherwise.
     *
     * @pre 0 <= c <= MAX_COL
     *
     * @post [If the token placed results in 5 in a row (vertically, horizontally, or diagonally)]
     * [if the result is true the player wins the game]
     */

    //secondary
    public default boolean checkForWin(int c){
        int currRow = -1;

        // Check if empty first //
        /*for(int i = 0;  i < MAX_ROW; i++) {
            if (whatsAtPos(new BoardPosition(i, c)) == ' ') {
                continue;
            }
            else {
                token = whatsAtPos(new BoardPosition(i, c));
                currRow = i;
                break;
            }
        }*/
        for (int i = 0; i < getRows(); i++) {
            if (whatsAtPos(new BoardPosition(i, c)) != ' ') {
                currRow = i;
            }
        }

        if (currRow == -1) return false;

        BoardPosition pos = new BoardPosition(currRow, c);
        char token = whatsAtPos(pos);
        return checkHorizWin(pos, token) || checkVertWin(pos, token) || checkDiagWin(pos, token);
    }

    /**
     * check if the game results in a tie
     * @return true if there are not one kind of token 5 times in a row either vertically, Horizontally, or diagonally,
     *         otherwise return false.
     *
     * @pre none
     *
     * @post [If there are not one kind of token 5 times in a row then the game results in a tie]
     */

    //secondary
    public default boolean checkTie(){
        for(int i = 0; i < MAX_COL; i++){
            if (checkIfFree(i)){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the last placed token results in 5 in a row horizontally.
     * @param pos, position of the last placed token
     * @param p, the player that placed the last token
     * @return true if the last placed token results in 5 in a row horizontally, false otherwise.
     *
     * @pre pos != null AND pos is a valid position
     *
     * @post [returns true if 5 tokens are in a row horizontally and consecutively, otherwise returns false]
     */

    //secondary
    public default boolean checkHorizWin(BoardPosition pos, char p){
        int row = pos.getRow();
        int col = pos.getColumn();
        int numTokens = 1;

        //check to the left of pos
        for(int i = col - 1; i >= 0; i--){
            if (whatsAtPos(new BoardPosition(row, i)) == p){
                numTokens++;
            }
            else {
                break;
            }
        }
        //check to the right of pos
        for(int i = col + 1; i < MAX_COL; i++){
            if (whatsAtPos(new BoardPosition(row, i)) == p){
                numTokens++;
            }
            else {
                break;
            }
        }

        return numTokens >= TOKENS_TO_WIN;
    }

    /**
     * Checks if the last placed token results in 5 in a row vertically.
     * @param pos, position of the last placed token
     * @param p, the player that placed the last token
     * @return true if the last placed token results in 5 in a row vertically, false otherwise.
     *
     * @pre pos != null AND pos is a valid position
     *
     * @post [returns true if 5 in a row vertically, otherwise returns false]
     */

    //secondary
    public default boolean checkVertWin(BoardPosition pos, char p) {
        int row = pos.getRow();
        int col = pos.getColumn();
        int numTokens = 1;

        for (int i = row - 1; i >= 0; i--) {
            if (whatsAtPos(new BoardPosition(i, col)) == p) {
                numTokens++;
            } else {
                break;
            }
        }
        for (int i = row + 1; i < MAX_ROW; i++) {
            if (whatsAtPos(new BoardPosition(i, col)) == p) {
                numTokens++;
            } else {
                break;
            }
        }
        return numTokens >= TOKENS_TO_WIN;
    }

    /**
     * Checks if last placed token result in 5 in a row diagonally
     * @param pos, the position of the last placed token
     * @param p, player that placed the token
     * @return true if there are 5 tokens in a row diagonally, return false otherwise
     *
     * @pre pos != null AND pos is a valid position
     *
     * @post [returns true if there are 5 in a row diagonally, otherwise it will return false]
     */

    //secondary

    public default boolean checkDiagWin(BoardPosition pos, char p) {
        int row = pos.getRow();
        int col = pos.getColumn();
        int RDnumTokens = 1;
        int LDnumTokens = 1;

        // Checking left leaning diagonal: To bottom right //
        int i = 1;
        while (row + i < MAX_ROW && col + i < MAX_COL) {
            if (whatsAtPos(new BoardPosition(row + i, col + i)) == p){
                LDnumTokens++;
            }
            else {
                break;
            }
            i++;
        }

        // Checking left leaning diagonal: To top left //
        i = 1;
        while (row - i >= 0 && col - i >= 0) {
            if (whatsAtPos(new BoardPosition(row - i, col - i))== p){
                LDnumTokens++;
            }
            else {
                break;
            }
            i++;
        }

        // Checking right leaning diagonal: To top right //
        i = 1;
        while (row - i >= 0 && col + i < MAX_COL) {
            if (whatsAtPos(new BoardPosition(row - i, col + i))== p){
                RDnumTokens++;
            }
            else {
                break;
            }
            i++;
        }

        // Checking right leaning diagonal: To bottom left //
        i = 1;
        while (row + i < MAX_ROW && col - i >= 0) {
            if (whatsAtPos(new BoardPosition(row + i, col - i))== p){
                RDnumTokens++;
            }
            else {
                break;
            }
            i++;
        }

        return LDnumTokens >= TOKENS_TO_WIN || RDnumTokens >= TOKENS_TO_WIN;
    }

    /**
     * Checks what's at a specific position on the gameboard
     * @param pos, the position being checked on the gameboard
     * @return the token at the position
     *
     * @pre pos != null AND pos is a valid position
     *
     * @post [Returns the token at the position('X' or 'O'. If empty returns ' '.]
     */

    //primary
    public char whatsAtPos(BoardPosition pos);

    /**
     * checks what token is in the requested position
     * @param pos, the position getting checked
     * @param player, the token being looked for the in the requested position
     *
     * @return true if the token in the position is the same as the player's token, false otherwise
     *
     * @pre pos != null AND pos is a valid position AND player is a valid token
     *
     * @post [returns true if the player is in that position, false otherwise]
     */

    //secondary

    public default boolean isPlayerAtPos(BoardPosition pos, char player){
        int row = pos.getRow();
        int col = pos.getColumn();

        if (row >= 0 && row < MAX_ROW && col >= 0 && col < MAX_COL) {
            return whatsAtPos(pos) == player;
        }
        return false;
    }

    /**
     * Standard getter for rows
     * @return rows, an int
     *
     * @pre none
     *
     * @post getRows = rows AND rows = #rows AND columns = #columns AND numToWin = #numToWin
     */
    public int getRows();

    /**
     * Standard getter for cols
     * @return cols, an int
     *
     * @pre none
     *
     * @post getRows = rows AND rows = #rows AND columns = #columns AND numToWin = #numToWin
     */
    public int getColumns();


    /**
     * Standard getter for numToWin
     * @return numToWin, an int
     *
     * @pre none
     *
     * @post getRows = rows AND rows = #rows AND columns = #columns AND numToWin = #numToWin
     */
    public int getNumToWin();

}