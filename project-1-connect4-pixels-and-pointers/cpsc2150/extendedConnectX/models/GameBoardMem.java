/*
    Cody Martin
    Cleaned up for statements in whatsAtPos() method.
    Too much was being used in dropToken(), so I cleaned up some space while keeping efficiency.
    Fixed precondition for dropToken().
 */

package cpsc2150.extendedConnectX.models;

import java.util.*;

/**
 * GameBoardMem implements a memory-efficient version of IGameBoard.java
 * A map is used to store tokens placed by the players and their related BoardPositions.
 *
 * invariant [A position on the board must have a token or be empty] AND
 * [The board cannot contain invalid players or tokens] AND
 * [All tokens placed are recorded in the board map]
 *
 * @corresponds:
 *          self = boardMap
 *          rows = the number of rows in the game board
 *          cols = the number of columns in the game board
 *          numToWin = the number of tokens required to win
 */

public class GameBoardMem extends AbsGameBoard {
    private int rows;
    private int cols;
    private int numToWin;
    private Map<Character, List<BoardPosition>> boardMap;

    /**
     * Constructs a GameBoardMem object with the chosen dimensions and win conditions.
     * Initializes an empty board map.
     *
     * @param rows     the number of rows in the board
     * @param cols     the number of columns in the board
     * @param numToWin the number of tokens needed consecutively to win
     * @pre 3 <= rows <= 100 AND 3 <= cols <= 100 AND 3 <= numToWin <= min(rows, cols)
     * @post [A GameBoardMem object is made with the new dimensions and
     * boardMap is initialized]
     */

    public GameBoardMem(int rows, int cols, int numToWin) {
        this.rows = rows;
        this.cols = cols;
        this.numToWin = numToWin;
        this.boardMap = new HashMap<>();
    }

    /**
     * places the token in the lowest available spot in the requested column
     *
     * @param p, The token being placed.
     * @param c, The column that the token in being placed in
     *
     * @pre 0 <= c <= 99 AND p != ' ' AND there is at least one free row in column c
     *
     * @post [token p gets dropped in the lowest available spot in colum c]
     */
    public void dropToken(char p, int c) {
        /*BoardPosition position;
        boardMap.putIfAbsent(p, new ArrayList<>());
        int i = 0;
        int row = 0;
        for (i = rows - 1; i >= 0; i--) {
            position = new BoardPosition(i, c);
            if (whatsAtPos(position) == ' ') {
                row = i;
                break;
            }
        }
        position = new BoardPosition(row, c);
        boardMap.get(p).add(position);*/

        for (int i = rows - 1; i >= 0; i--) {
            BoardPosition pos = new BoardPosition(i, c);
            if (whatsAtPos(pos) == ' ') {
                boardMap.putIfAbsent(p, new ArrayList<>());
                boardMap.get(p).add(pos);
                return;
            }
        }
    }

    /**
     * checks what token is in the requested position
     * @param pos, the positioned getting checked
     *
     * @return the token character in the position or a blank space character if the position is empty.
     *
     * @pre [pos must have a row value between 0 and 99 and a column value between 0 and 99]
     *
     * @post [the function will return the token in the position]
     */
    public char whatsAtPos(BoardPosition pos) {
        List<Character> players = new ArrayList<>(boardMap.keySet());

        for (char player : players) {
            List<BoardPosition> positions = boardMap.get(player);

            for (BoardPosition position : positions) {
                if (position.equals(pos)) {
                    return player;
                }
            }
        }
        return ' ';
    }

    /**
     * Standard getter for rows
     * @return number of rows in game board
     *
     * @pre none
     *
     * @post getRows = rows AND rows = #rows AND columns = #columns AND numToWin = #numToWin
     */
    public int getRows(){ return rows; }

    /**
     * Standard getter for cols
     * @return number of tokens in game board
     *
     * @pre none
     *
     * @post getRows = rows AND rows = #rows AND columns = #columns AND numToWin = #numToWin
     */
    public int getColumns(){ return cols; }

    /**
     * Standard getter for numToWin
     * @return number of tokens required to win
     *
     * @pre none
     *
     * @post getRows = rows AND rows = #rows AND columns = #columns AND numToWin = #numToWin
     */
    public int getNumToWin(){ return numToWin; }
}
