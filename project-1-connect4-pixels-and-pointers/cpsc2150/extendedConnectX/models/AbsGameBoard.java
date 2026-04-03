/*
    Cody Martin
        fixed the append() statements in toString() for proper alignment.
 */

package cpsc2150.extendedConnectX.models;
import java.lang.StringBuilder;


import static cpsc2150.extendedConnectX.models.IGameBoard.*;

public abstract class AbsGameBoard implements IGameBoard {
    /**
     * Creates a string that shows the entire GameBoard.
     *
     * @return Formatted string that represents gameBoard.
     * @pre none
     * @post [Returns formatted string that represents current gameBoard by calling whatsAtPos.]
     */

    private int dimensions;

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        for (int i = 0; i < this.getColumns(); i++) {
            if (i < 10) {
                boardString.append("| ").append(i).append(" ");
            } else {
                boardString.append("|").append(i).append(" ");
            }
        }
        boardString.append("|\n");

        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                char insert = this.whatsAtPos(new BoardPosition(i, j));
                boardString.append("| ").append(insert).append(" ");
            }
            boardString.append("|\n");
        }
        return boardString.toString();
    }
}
