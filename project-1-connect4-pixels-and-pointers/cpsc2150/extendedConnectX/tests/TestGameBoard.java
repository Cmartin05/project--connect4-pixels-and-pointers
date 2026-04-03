package cpsc2150.extendedConnectX.tests;
import  cpsc2150.extendedConnectX.models.*;
import static cpsc2150.extendedConnectX.models.IGameBoard.MAX_COL;
import static cpsc2150.extendedConnectX.models.IGameBoard.MAX_ROW;
import static org.junit.Assert.*;

import org.junit.Test;


public class TestGameBoard {

    private IGameBoard makeBoard()
    {
        return new GameBoard(9, 7, 5);
    }

    private static String makeExpectedBoardString(char[][] gb) {
        StringBuilder board = new StringBuilder();
        for(int i = 0; i < MAX_COL; i++) {
            board.append("| ").append(i).append(" ");
        }
        board.append("|\n");
        for (int i = 0; i < (MAX_ROW); i++){
            for (int j = 0; j < MAX_COL; j++){
                board.append("| ").append(gb[i][j]).append(" ");
            }
            board.append("|\n");
        }
        return board.toString();
    }

    // Constructor Test
    @Test
    public void test_Constructor_GameBoard()
    {
        IGameBoard gb = makeBoard();
        char[][] expectedBoard = new char[MAX_ROW][MAX_COL];
        for(int i = 0; i < MAX_ROW; i++)
        {
            for(int j = 0; j < MAX_COL; j++)
            {
                expectedBoard[i][j] = ' ';
            }
        }
        String expected = makeExpectedBoardString(expectedBoard);

        String observed = gb.toString();

        assertEquals(expected, observed);
    }

    // Tests for checkIfFree Function
    @Test
    public void testCheckIfFree_1_Full_Col()
    {
        // Generate GameBoard //
        IGameBoard gb = makeBoard();
        gb.dropToken('X', 0);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('X', 2);
        gb.dropToken('O', 2);
        gb.dropToken('X', 2);
        gb.dropToken('X', 2);
        gb.dropToken('O', 2);
        gb.dropToken('O', 3);
        gb.dropToken('O', 3);
        gb.dropToken('O', 4);
        gb.dropToken('X', 4);
        gb.dropToken('O', 5);
        gb.dropToken('X', 5);

        // Generate position we're verifying and run the function we're testing //
        BoardPosition pos = new BoardPosition(0,1);
        boolean observed = gb.checkIfFree(1);

        // Assert //
        assertFalse("Expected a none free column at column 1", observed);
    }

    @Test
    public void testCheckIfFree_2_Partial_Col()
    {
        // Generate GameBoard //
        IGameBoard gb = makeBoard();
        gb.dropToken('X', 0);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('X', 2);
        gb.dropToken('O', 2);
        gb.dropToken('X', 2);
        gb.dropToken('X', 2);
        gb.dropToken('O', 2);
        gb.dropToken('O', 3);
        gb.dropToken('O', 3);
        gb.dropToken('O', 4);
        gb.dropToken('X', 4);
        gb.dropToken('O', 5);
        gb.dropToken('X', 5);

        // Generate position we're verifying and run the function we're testing //
        BoardPosition pos = new BoardPosition(4,2);
        boolean observed = gb.checkIfFree(2);

        // Assert //
        assertTrue("Expected a free column at column 2", observed);
    }

    @Test
    public void testCheckIfFree_6_Empty_Col()
    {
        // Generate GameBoard //
        IGameBoard gb = makeBoard();
        gb.dropToken('X', 0);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('X', 2);
        gb.dropToken('O', 2);
        gb.dropToken('X', 2);
        gb.dropToken('X', 2);
        gb.dropToken('O', 2);
        gb.dropToken('O', 3);
        gb.dropToken('O', 3);
        gb.dropToken('O', 4);
        gb.dropToken('X', 4);
        gb.dropToken('O', 5);
        gb.dropToken('X', 5);

        // Generate position we're verifying and run the function we're testing //
        BoardPosition pos = new BoardPosition(8,6);
        boolean observed = gb.checkIfFree(6);

        // Assert //
        assertTrue("Expected a free column at column 6", observed);
    }

    // Tests for checkHorizWin Function
    @Test
    public void testCheckHorizontalWin_8_4_X_5xInaRow()
    {
        // Generate GameBoard //
        IGameBoard gb = makeBoard();
        gb.dropToken('X', 0);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('X', 2);
        gb.dropToken('O', 2);
        gb.dropToken('X', 2);
        gb.dropToken('X', 2);
        gb.dropToken('O', 2);
        gb.dropToken('X', 3);
        gb.dropToken('O', 3);
        gb.dropToken('X', 4);
        gb.dropToken('O', 5);
        gb.dropToken('X', 5);

        // Generate position we're verifying and run the function we're testing //
        BoardPosition pos = new BoardPosition(8,4);
        boolean observed = gb.checkHorizWin(pos,'X');

        // Assert //
        assertTrue("Expected horizontal win for 'X' at position (8, 4)", observed);
    }

    @Test
    public void testCheckHorizontalWin_8_4_X_5xNotInaRow()
    {
        // Generate GameBoard //
        IGameBoard gb = makeBoard();
        gb.dropToken('X', 0);
        gb.dropToken('O', 1);
        gb.dropToken('O', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('X', 2);
        gb.dropToken('O', 2);
        gb.dropToken('X', 2);
        gb.dropToken('X', 2);
        gb.dropToken('O', 2);
        gb.dropToken('X', 3);
        gb.dropToken('O', 3);
        gb.dropToken('X', 4);
        gb.dropToken('O', 5);

        // Generate position we're verifying and run the function we're testing //
        BoardPosition pos = new BoardPosition(8,4);
        boolean observed = gb.checkHorizWin(pos,'X');

        // Assert //
        assertFalse("Expected no horizontal win for 'X' at position (8, 4)", observed);
    }

    @Test
    public void testCheckHorizontalWin_8_2_O_5oInaRow()
    {
        // Generate GameBoard //
        IGameBoard gb = makeBoard();
        gb.dropToken('O', 0);
        gb.dropToken('O', 1);
        gb.dropToken('O', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 2);
        gb.dropToken('O', 2);
        gb.dropToken('X', 2);
        gb.dropToken('X', 2);
        gb.dropToken('O', 2);
        gb.dropToken('O', 3);
        gb.dropToken('O', 3);
        gb.dropToken('O', 4);
        gb.dropToken('X', 5);
        gb.dropToken('X', 5);
        gb.dropToken('X', 5);
        gb.dropToken('X', 6);
        gb.dropToken('X', 6);
        gb.dropToken('X', 6);

        // Generate position we're verifying and run the function we're testing //
        BoardPosition pos = new BoardPosition(8,2);
        boolean observed = gb.checkHorizWin(pos,'O');

        // Assert //
        assertTrue("Expected horizontal win for 'O' at position (8, 2)", observed);
    }

    @Test
    public void testCheckHorizontalWin_7_4_O_5oNotInaRow()
    {
        // Generate GameBoard //
        IGameBoard gb = makeBoard();
        gb.dropToken('O', 0);
        gb.dropToken('O', 1);
        gb.dropToken('O', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 2);
        gb.dropToken('O', 2);
        gb.dropToken('X', 2);
        gb.dropToken('X', 2);
        gb.dropToken('O', 2);
        gb.dropToken('O', 3);
        gb.dropToken('O', 3);
        gb.dropToken('X', 4);
        gb.dropToken('O', 4);
        gb.dropToken('X', 5);
        gb.dropToken('X', 5);
        gb.dropToken('X', 5);
        gb.dropToken('X', 6);
        gb.dropToken('X', 6);
        gb.dropToken('X', 6);

        // Generate position we're verifying and run the function we're testing //
        BoardPosition pos = new BoardPosition(7,4);
        boolean observed = gb.checkHorizWin(pos,'O');

        // Assert //
        assertFalse("Expected no horizontal win for 'O' at position (7, 4)", observed);
    }

    // Tests for checkVertWin Function
    @Test
    public void testCheckVerticalWin_4_6_X_5xInaStack()
    {
        // Generate GameBoard //
        IGameBoard gb = makeBoard();
        gb.dropToken('O', 0);
        gb.dropToken('O', 1);
        gb.dropToken('O', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 2);
        gb.dropToken('O', 2);
        gb.dropToken('X', 2);
        gb.dropToken('X', 2);
        gb.dropToken('O', 2);
        gb.dropToken('O', 3);
        gb.dropToken('O', 3);
        gb.dropToken('X', 4);
        gb.dropToken('O', 4);
        gb.dropToken('X', 5);
        gb.dropToken('O', 5);
        gb.dropToken('X', 5);
        gb.dropToken('X', 6);
        gb.dropToken('X', 6);
        gb.dropToken('X', 6);
        gb.dropToken('X', 6);

        // Generate position we're verifying and run the function we're testing //
        BoardPosition pos = new BoardPosition(4,6);
        boolean observed = gb.checkVertWin(pos,'X');

        // Assert //
        assertTrue("Expected vertical win for 'X' at position (4, 6)", observed);
    }

    @Test
    public void testCheckVerticalWin_4_6_X_5xNotInaStack()
    {
        // Generate GameBoard //
        IGameBoard gb = makeBoard();
        gb.dropToken('O', 0);
        gb.dropToken('X', 0);
        gb.dropToken('O', 1);
        gb.dropToken('O', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 2);
        gb.dropToken('O', 2);
        gb.dropToken('X', 2);
        gb.dropToken('X', 2);
        gb.dropToken('O', 2);
        gb.dropToken('O', 3);
        gb.dropToken('O', 3);
        gb.dropToken('X', 3);
        gb.dropToken('X', 4);
        gb.dropToken('O', 4);
        gb.dropToken('X', 5);
        gb.dropToken('O', 5);
        gb.dropToken('X', 6);
        gb.dropToken('X', 6);
        gb.dropToken('O', 6);
        gb.dropToken('X', 6);
        gb.dropToken('X', 6);

        // Generate position we're verifying and run the function we're testing //
        BoardPosition pos = new BoardPosition(4,6);
        boolean observed = gb.checkVertWin(pos,'X');

        // Assert //
        assertFalse("Expected no vertical win for 'X' at position (4, 6)", observed);
    }

    //checking for vertical win
    @Test
    public void testCheckVerticalWin_4_1_X_5xInaStack() {
        IGameBoard gb = makeBoard();
        gb.dropToken('X',0);
        gb.dropToken('O',0);
        gb.dropToken('O',0);
        gb.dropToken('O',0);
        gb.dropToken('O',0);
        gb.dropToken('X',1);
        gb.dropToken('X',1);
        gb.dropToken('X',1);
        gb.dropToken('X',1);
        gb.dropToken('X',1);
        gb.dropToken('X',2);
        gb.dropToken('O',3);
        gb.dropToken('O',4);
        gb.dropToken('O',5);
        gb.dropToken('X',6);

        BoardPosition pos = new BoardPosition(4,1);
        boolean observed = gb.checkVertWin(pos,'X');

        assertTrue("Expected vertical win for 'X' at position (4, 1)",observed);
    }
    //checking for vertical win on top of different token
    @Test
    public void testCheckVerticalWin_1_2_O_5oInaStack() {
        IGameBoard gb = makeBoard();
        gb.dropToken('X',0);
        gb.dropToken('X',0);
        gb.dropToken('O',1);
        gb.dropToken('O',1);
        gb.dropToken('O',1);
        gb.dropToken('O',1);
        gb.dropToken('X',1);
        gb.dropToken('O',1);
        gb.dropToken('X',2);
        gb.dropToken('X',2);
        gb.dropToken('X',2);
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('O',3);
        gb.dropToken('X',3);
        gb.dropToken('X',4);
        gb.dropToken('X',4);
        gb.dropToken('X',4);
        gb.dropToken('O',5);
        gb.dropToken('X',5);
        gb.dropToken('X',6);

        BoardPosition pos = new BoardPosition(1,2);
        boolean observed = gb.checkVertWin(pos,'O');

        assertTrue("Expected vertical win for '0' at position (1, 2)",observed);
    }



    //tests for checkDiagWin function

    //test for 5 in a row when there is none
    @Test
    public void testCheckDiagWin_5_4_O_5oNotInaDiag() {
        IGameBoard gb = makeBoard();
        gb.dropToken('X',0);
        gb.dropToken('O',1);
        gb.dropToken('X',1);
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('X',2);
        gb.dropToken('O',2);
        gb.dropToken('X',3);
        gb.dropToken('X',3);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('X',4);
        gb.dropToken('O',4);
        gb.dropToken('X',4);
        gb.dropToken('O',4);

        BoardPosition pos = new BoardPosition(5,4);
        boolean observed = gb.checkDiagWin(pos,'O');

        assertFalse("Expected no diagonal win for 'O' at position (5, 4)",observed);
    }

    //test for 5 in a diagonal with the piece at the top of the diagonal
    @Test
    public void testCheckDiagWin_4_5_O_5oInaDiag() {
        IGameBoard gb = makeBoard();
        gb.dropToken('X',0);
        gb.dropToken('O',1);
        gb.dropToken('X',1);
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('X',2);
        gb.dropToken('O',2);
        gb.dropToken('X',3);
        gb.dropToken('X',3);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('X',4);
        gb.dropToken('O',4);
        gb.dropToken('X',4);
        gb.dropToken('O',4);
        gb.dropToken('X',5);
        gb.dropToken('X',5);
        gb.dropToken('O',5);
        gb.dropToken('X',5);
        gb.dropToken('O',5);
        gb.dropToken('X',6);
        gb.dropToken('O',6);

        BoardPosition pos = new BoardPosition(4,5);
        boolean observed = gb.checkDiagWin(pos,'O');

        assertTrue("Expected diagonal win for 'O' at position (4, 5), the top of the diagonal",observed);
    }

    //test for 5 in a diagonal with the piece at the top of the diagonal but reversed directions
    @Test
    public void testCheckDiagWin_4_2_X_5xtInaDiag() {
        IGameBoard gb = makeBoard();
        gb.dropToken('X',0);
        gb.dropToken('O',1);
        gb.dropToken('X',1);
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('X',2);
        gb.dropToken('O',2);
        gb.dropToken('X',2);
        gb.dropToken('X',3);
        gb.dropToken('X',3);
        gb.dropToken('O',3);
        gb.dropToken('X',3);
        gb.dropToken('X',4);
        gb.dropToken('O',4);
        gb.dropToken('X',4);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('X',5);
        gb.dropToken('X',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('X',6);
        gb.dropToken('O',6);

        BoardPosition pos = new BoardPosition(4,2);
        boolean observed = gb.checkDiagWin(pos,'X');

        assertTrue("Expected diagonal win for 'X' at position (4, 2), the top left of the diagonal",observed);
    }

    //test for 5 in a diagonal with the piece at the bottom of the diagonal
    @Test
    public void testCheckDiagWin_7_2_X_5xNotInaDiag() {
        IGameBoard gb = makeBoard();
        gb.dropToken('X',0);
        gb.dropToken('O',1);
        gb.dropToken('X',1);
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('X',2);
        gb.dropToken('O',2);
        gb.dropToken('X',3);
        gb.dropToken('X',3);
        gb.dropToken('O',3);
        gb.dropToken('X',3);
        gb.dropToken('X',4);
        gb.dropToken('O',4);
        gb.dropToken('X',4);
        gb.dropToken('O',4);
        gb.dropToken('X',5);
        gb.dropToken('X',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('X',6);
        gb.dropToken('O',6);
        gb.dropToken('X',6);

        BoardPosition pos = new BoardPosition(7,2);
        boolean observed = gb.checkDiagWin(pos,'O');

        assertTrue("Expected diagonal win for 'O' at position (7, 2), the bottom of diagonal",observed);
    }

    //test for 5 in a diagonal with the piece at the middle of the diagonal
    @Test
    public void testCheckDiagWin_2_4_X_5xInaDiag() {
        IGameBoard gb = makeBoard();
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('X',2);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('X',3);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('X',4);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('X',5);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('X',6);

        BoardPosition pos = new BoardPosition(2,4);
        boolean observed = gb.checkDiagWin(pos,'X');

        assertTrue("Expected diagonal win for 'X' at position (2, 4), the middle of the diagonal",observed);
    }

    //test for 5 in a diagonal with the piece at the second to bottom of the diagonal
    @Test
    public void testCheckDiagWin_3_3_X_5xInaDiag() {
        IGameBoard gb = makeBoard();
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('X',2);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('X',3);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('X',4);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('X',5);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('X',6);

        BoardPosition pos = new BoardPosition(3,3);
        boolean observed = gb.checkDiagWin(pos,'X');

        assertTrue("Expected diagonal win for 'X' at position (3, 3), the second to bottom of the diagonal",observed);
    }
    //test for 5 in a diagonal with the piece at the second to top of the diagonal
    @Test
    public void testCheckDiagWin_1_5_x_5xInaDiag() {
        IGameBoard gb = makeBoard();
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('X',2);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('X',3);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('X',4);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('X',5);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('X',6);

        BoardPosition pos = new BoardPosition(1,5);
        boolean observed = gb.checkDiagWin(pos,'X');

        assertTrue("Expected diagonal win for 'X' at position (1, 5), the second to top of the diagonal",observed);
    }

    //tests for checkTie

    //test to check tie on a filled board
    @Test
    public void testCheckTie_filledBoard() {
        IGameBoard gb = makeBoard();
        gb.dropToken('X',0);
        gb.dropToken('O',0);
        gb.dropToken('X',0);
        gb.dropToken('O',0);
        gb.dropToken('O',0);
        gb.dropToken('X',0);
        gb.dropToken('X',0);
        gb.dropToken('O',0);
        gb.dropToken('X',0);
        gb.dropToken('O',1);
        gb.dropToken('X',1);
        gb.dropToken('O',1);
        gb.dropToken('X',1);
        gb.dropToken('X',1);
        gb.dropToken('O',1);
        gb.dropToken('O',1);
        gb.dropToken('X',1);
        gb.dropToken('O',1);
        gb.dropToken('X',2);
        gb.dropToken('O',2);
        gb.dropToken('X',2);
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('X',2);
        gb.dropToken('X',2);
        gb.dropToken('O',2);
        gb.dropToken('X',2);
        gb.dropToken('O',3);
        gb.dropToken('X',3);
        gb.dropToken('O',3);
        gb.dropToken('X',3);
        gb.dropToken('X',3);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('X',3);
        gb.dropToken('O',3);
        gb.dropToken('X',4);
        gb.dropToken('O',4);
        gb.dropToken('X',4);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('X',4);
        gb.dropToken('X',4);
        gb.dropToken('O',4);
        gb.dropToken('X',4);
        gb.dropToken('O',5);
        gb.dropToken('X',5);
        gb.dropToken('O',5);
        gb.dropToken('X',5);
        gb.dropToken('X',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('X',5);
        gb.dropToken('O',5);
        gb.dropToken('X',6);
        gb.dropToken('O',6);
        gb.dropToken('X',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('X',6);
        gb.dropToken('X',6);
        gb.dropToken('O',6);
        gb.dropToken('X',6);

        boolean observed = gb.checkTie();
        assertTrue("Expected tie for filled board",observed);

    }
    // Tests to check tie on an empty board
    @Test
    public void testCheckTie_UnfilledBoard() {
        IGameBoard gb = makeBoard();
        boolean observed = gb.checkTie();
        assertFalse("Expected board to not be a tie when it is unfilled", observed);
    }
    // Tests to check tie on a board with a winner
    @Test
    public void testCheckTie_BoardWithWinner() {
        IGameBoard gb = makeBoard();
        gb.dropToken('X',0);
        gb.dropToken('X',0);
        gb.dropToken('X', 0);
        gb.dropToken('X',0);
        gb.dropToken('O',0);
        gb.dropToken('O',1);
        gb.dropToken('X',1);
        gb.dropToken('O',1);
        gb.dropToken('O',1);
        gb.dropToken('X',2);
        gb.dropToken('X',2);
        gb.dropToken('O',2);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('X',3);
        gb.dropToken('X',3);
        gb.dropToken('O',3);
        gb.dropToken('X',3);
        gb.dropToken('O',4);
        gb.dropToken('X',5);
        gb.dropToken('X',5);
        gb.dropToken('X',5);
        gb.dropToken('O',6);
        gb.dropToken('O',6);

        boolean observed = gb.checkTie();
        assertFalse("expected board not be a tie and there is a winner", observed);
    }

    //checks tie when last piece placed on a full board results in a win
    @Test
    public void testCheckTie_FullExceptOne_Board_With_Winner(){
        IGameBoard gb = makeBoard();
        gb.dropToken('X',0);
        gb.dropToken('O',0);
        gb.dropToken('X',0);
        gb.dropToken('X',0);
        gb.dropToken('X',0);
        gb.dropToken('X',0);
        gb.dropToken('O',0);
        gb.dropToken('X',0);
        gb.dropToken('X',0);
        gb.dropToken('O',1);
        gb.dropToken('O',1);
        gb.dropToken('X',1);
        gb.dropToken('X',1);
        gb.dropToken('O',1);
        gb.dropToken('X',1);
        gb.dropToken('O',1);
        gb.dropToken('O',1);
        gb.dropToken('O',1);
        gb.dropToken('X',2);
        gb.dropToken('X',2);
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('X',2);
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('X',2);
        gb.dropToken('X',2);
        gb.dropToken('X',3);
        gb.dropToken('X',3);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('X',3);
        gb.dropToken('O',3);
        gb.dropToken('X',3);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('X',4);
        gb.dropToken('X',4);
        gb.dropToken('O',4);
        gb.dropToken('X',4);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('X',4);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('X',5);
        gb.dropToken('X',5);
        gb.dropToken('O',5);
        gb.dropToken('X',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('X',5);
        gb.dropToken('O',6);
        gb.dropToken('X',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('X',6);
        gb.dropToken('X',6);
        gb.dropToken('X',6);

        boolean observed = gb.checkTie();
        assertFalse("expected board to not be a tie", observed);
    }

    //Tests what's at position when at Maximum Row and Maximum column.
    @Test
    public void testWhatsAtPos_MaxRow_MaxCol(){
        IGameBoard gb = makeBoard();
        gb.dropToken('X',0);
        gb.dropToken('X',0);
        gb.dropToken('O',0);
        gb.dropToken('O',0);
        gb.dropToken('X',0);
        gb.dropToken('O',0);
        gb.dropToken('O',0);
        gb.dropToken('O',0);
        gb.dropToken('X',0);
        gb.dropToken('O',1);
        gb.dropToken('X',1);
        gb.dropToken('X',1);
        gb.dropToken('O',1);
        gb.dropToken('O',1);
        gb.dropToken('O',1);
        gb.dropToken('X',1);
        gb.dropToken('X',1);
        gb.dropToken('O',1);
        gb.dropToken('X',2);
        gb.dropToken('O',2);
        gb.dropToken('X',2);
        gb.dropToken('O',2);
        gb.dropToken('X',2);
        gb.dropToken('X',2);
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('X',2);
        gb.dropToken('O',2);
        gb.dropToken('X',3);
        gb.dropToken('O',3);
        gb.dropToken('X',3);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('X',3);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('X',3);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('X',4);
        gb.dropToken('X',4);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('X',4);
        gb.dropToken('O',4);
        gb.dropToken('X',4);
        gb.dropToken('O',5);
        gb.dropToken('X',5);
        gb.dropToken('O',5);
        gb.dropToken('X',5);
        gb.dropToken('X',5);
        gb.dropToken('X',5);
        gb.dropToken('O',5);
        gb.dropToken('O',5);
        gb.dropToken('X',5);
        gb.dropToken('X',6);
        gb.dropToken('X',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('X',6);
        gb.dropToken('O',6);
        gb.dropToken('X',6);
        gb.dropToken('X',6);
        gb.dropToken('O',6);

        BoardPosition pos = new BoardPosition(8,6);
        assertEquals('X', gb.whatsAtPos(pos));
    }
//Tests what's at position when at Minimum Row and Minimum Column
    @Test
    public void testWhatsAtPos_MinRow_MinCol(){
        IGameBoard gb = makeBoard();
        BoardPosition pos = new BoardPosition(0,0);
        assertEquals(' ', gb.whatsAtPos(pos));
    }
//Tests what's at position when at Minimum Row and Maximum Column
    @Test
    public void testWhatsAtPos_MinRow_MaxCol(){
        IGameBoard gb = makeBoard();
        gb.dropToken('X',0);
        gb.dropToken('X',0);
        gb.dropToken('X',0);
        gb.dropToken('O',0);
        gb.dropToken('O',1);
        gb.dropToken('X',1);
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('X',2);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('X',3);
        gb.dropToken('X',3);
        gb.dropToken('O',4);
        gb.dropToken('O',4);
        gb.dropToken('X',5);
        gb.dropToken('O',5);
        gb.dropToken('X',6);
        gb.dropToken('X',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('X',6);
        gb.dropToken('x',6);
        gb.dropToken('O',6);
        gb.dropToken('O',6);
        gb.dropToken('X',6);

        BoardPosition pos = new BoardPosition(0,6);
        assertEquals('X', gb.whatsAtPos(pos));
    }

    //Tests what's at position when at Maximum Row and Minimum Column.
    @Test
    public void testWhatsAtPos_MaxRow_MinCol(){
        IGameBoard gb = makeBoard();
        gb.dropToken('X',0);
        gb.dropToken('X',0);
        gb.dropToken('X',0);
        gb.dropToken('X',0);
        gb.dropToken('O',0);
        gb.dropToken('O',1);
        gb.dropToken('O',1);
        gb.dropToken('O',1);
        gb.dropToken('X',1);
        gb.dropToken('O',2);
        gb.dropToken('O',2);
        gb.dropToken('X',3);
        gb.dropToken('X',3);
        gb.dropToken('O',3);
        gb.dropToken('X',3);
        gb.dropToken('X',3);
        gb.dropToken('O',4);
        gb.dropToken('O',5);
        gb.dropToken('X',5);

        BoardPosition pos = new BoardPosition(8,0);
        assertEquals('X', gb.whatsAtPos(pos));
    }
//Tests position in the middle of the board
    @Test
    public void testWhatsAtPos_2_4_FilledPos(){
        IGameBoard gb = makeBoard();
        gb.dropToken('X',0);
        gb.dropToken('O',0);
        gb.dropToken('X',1);
        gb.dropToken('O',1);
        gb.dropToken('O',1);
        gb.dropToken('X',1);
        gb.dropToken('O',1);
        gb.dropToken('O',2);
        gb.dropToken('X',2);
        gb.dropToken('X',2);
        gb.dropToken('X',2);
        gb.dropToken('O',2);
        gb.dropToken('X',2);
        gb.dropToken('X',3);

        BoardPosition pos = new BoardPosition(4,2);
        assertEquals('O', gb.whatsAtPos(pos));
    }

    //Tests player position in middle of board.
    @Test
    public void testIsPlayerAtPos_4_6_CorrectXPosition() {
        IGameBoard gb = makeBoard();
        gb.dropToken('X',0);
        gb.dropToken('O',0);
        gb.dropToken('O',0);
        gb.dropToken('O',0);
        gb.dropToken('X',0);
        gb.dropToken('X',1);
        gb.dropToken('X',1);
        gb.dropToken('O',1);
        gb.dropToken('X',2);
        gb.dropToken('X',2);
        gb.dropToken('O',2);
        gb.dropToken('O',3);
        gb.dropToken('X',3);
        gb.dropToken('X',3);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('O',3);
        gb.dropToken('O',4);
        gb.dropToken('X',5);
        gb.dropToken('O',6);
        gb.dropToken('X',6);
        gb.dropToken('O',6);
        gb.dropToken('X',6);
        gb.dropToken('X',6);

        BoardPosition pos = new BoardPosition(4,6);
        boolean isPlayerAtPos = gb.isPlayerAtPos(pos, 'X');
        assertTrue("Expected 'X' to be at (4,6)", isPlayerAtPos);
    }

    @Test
    public void testIsPlayerAtPos_8_0_CorrectOPosition() {
        IGameBoard gb = makeBoard();
        gb.dropToken('O',0);

        BoardPosition pos = new BoardPosition(8,0);
        boolean isPlayerAtPos = gb.isPlayerAtPos(pos, 'O');
        assertTrue("Expected 'O' to be at (8,0)", isPlayerAtPos);
    }

    @Test
    public void testIsPlayerAtPos_0_0_CorrectOPosition() {
        IGameBoard gb = makeBoard();
        gb.dropToken('O',0);
        gb.dropToken('X',0);
        gb.dropToken('O',0);
        gb.dropToken('X',0);
        gb.dropToken('O',0);
        gb.dropToken('X',0);
        gb.dropToken('O',0);
        gb.dropToken('X',0);
        gb.dropToken('O',0);

        BoardPosition pos = new BoardPosition(0,0);
        boolean isPlayerAtPos = gb.isPlayerAtPos(pos, 'O');
        assertTrue("Expected 'O' to be at (0,0)", isPlayerAtPos);
    }

    @Test
    public void testIsPlayerAtPos_6_0_FalseOPosition() {
        IGameBoard gb = makeBoard();
        gb.dropToken('O',6);
        gb.dropToken('X',6);
        gb.dropToken('O',6);
        gb.dropToken('X',6);
        gb.dropToken('O',6);
        gb.dropToken('X',6);
        gb.dropToken('O',6);
        gb.dropToken('X',6);
        gb.dropToken('O',6);

        BoardPosition pos = new BoardPosition(0,6);
        boolean isPlayerAtPos = gb.isPlayerAtPos(pos, '0');
        assertFalse("Expected 'O' to be at (0,6)", isPlayerAtPos);
    }

    @Test
    public void testIsPlayerAtPos_0_6_CorrectXPosition() {
        IGameBoard gb = makeBoard();
        gb.dropToken('X',6);

        BoardPosition pos = new BoardPosition(8,6);
        boolean isPlayerAtPos = gb.isPlayerAtPos(pos, 'X');
        assertTrue("Expected 'O' to be at (0,6)", isPlayerAtPos);
    }

    @Test
    public void testDropToken_Col1() {
        IGameBoard gb = makeBoard();
        gb.dropToken('X',1);
        String observed = gb.toString();
        char[][] expectedBoard = {

                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {' ','X',' ',' ',' ',' ',' '},
        };

        String expected = makeExpectedBoardString(expectedBoard);
        assertEquals(expected, observed);
    }

    @Test
    public void testDropToken_Col2_BottomLeftCorner() {
        IGameBoard gb = makeBoard();
        gb.dropToken('O',0);
        String observed = gb.toString();
        char[][] expectedBoard = {

                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {'O',' ',' ',' ',' ',' ',' '},
        };

        String expected = makeExpectedBoardString(expectedBoard);
        assertEquals(expected, observed);
    }

    @Test
    public void testDropToken_Col0_TopLeftCorner() {
        IGameBoard gb = makeBoard();
        gb.dropToken('X',0);
        gb.dropToken('O',0);
        gb.dropToken('X',0);
        gb.dropToken('O',0);
        gb.dropToken('X',0);
        gb.dropToken('O',0);
        gb.dropToken('X',0);
        gb.dropToken('O',0);
        gb.dropToken('X',0);
        String observed = gb.toString();
        char[][] expectedBoard = {

                {'X',' ',' ',' ',' ',' ',' '},
                {'O',' ',' ',' ',' ',' ',' '},
                {'X',' ',' ',' ',' ',' ',' '},
                {'O',' ',' ',' ',' ',' ',' '},
                {'X',' ',' ',' ',' ',' ',' '},
                {'O',' ',' ',' ',' ',' ',' '},
                {'X',' ',' ',' ',' ',' ',' '},
                {'O',' ',' ',' ',' ',' ',' '},
                {'X',' ',' ',' ',' ',' ',' '},
        };

        String expected = makeExpectedBoardString(expectedBoard);
        assertEquals(expected, observed);
    }

    @Test
    public void testDropToken_Col6_BottomRightCorner() {
        IGameBoard gb = makeBoard();
        gb.dropToken('O',6);
        String observed = gb.toString();
        char[][] expectedBoard = {

                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ','O'},
        };

        String expected = makeExpectedBoardString(expectedBoard);
        assertEquals(expected, observed);
    }

    @Test
    public void testDropToken_Col6_TopRightCorner() {
        IGameBoard gb = makeBoard();
        gb.dropToken('X',6);
        gb.dropToken('O',6);
        gb.dropToken('X',6);
        gb.dropToken('O',6);
        gb.dropToken('X',6);
        gb.dropToken('O',6);
        gb.dropToken('X',6);
        gb.dropToken('O',6);
        gb.dropToken('X',6);
        String observed = gb.toString();
        char[][] expectedBoard = {

                {' ',' ',' ',' ',' ',' ','X'},
                {' ',' ',' ',' ',' ',' ','O'},
                {' ',' ',' ',' ',' ',' ','X'},
                {' ',' ',' ',' ',' ',' ','O'},
                {' ',' ',' ',' ',' ',' ','X'},
                {' ',' ',' ',' ',' ',' ','O'},
                {' ',' ',' ',' ',' ',' ','X'},
                {' ',' ',' ',' ',' ',' ','O'},
                {' ',' ',' ',' ',' ',' ','X'},
        };

        String expected = makeExpectedBoardString(expectedBoard);
        assertEquals(expected, observed);
    }

}


