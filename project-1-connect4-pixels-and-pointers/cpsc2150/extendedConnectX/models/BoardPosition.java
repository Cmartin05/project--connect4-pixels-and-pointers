/*
    Cody Martin
        variables Row and Column don't need to be capitalized.
        Cleaned up equals() method for better space.
        Some postconditions needed to be changed and be shorter.
        Fixed toString() to use this.row and this.column instead of getRow() and getColumn().
 */

package cpsc2150.extendedConnectX.models;

/* Pixels and Pointers
   Lena Blankenbaker- lblankenbaker
   Pierson Rinchere - piers0n
   Angel Feliz - Afelizg
   Ryan Harvey - rjharve
 */


public class BoardPosition
{
    private int row;
    private int column;

    /**
     * A parameterized constructor for BoardPosition, accepts 2 params
     * @param aRow the value to be set for Row
     * @param aColumn the value to be set for Column
     *
     * @pre  0 <= aRow <= 8 AND 0 <= aColumn <= 6
     *
     * @post row = aRow  AND column = aColumn
     */
    public BoardPosition(int aRow, int aColumn)
    {
        this.row = aRow;
        this.column = aColumn;
    }

    /**
     * Standard getter for Row
     * @return Row, an int
     *
     * @pre none
     *
     * @post getRow = row AND row = #row AND column = #column
     */
    public int getRow() {
        return row;
    }

    /**
     * Standard getter for Column
     * @return Column, an int
     *
     * @pre none
     *
     * @post getColumn = column AND row = #row AND column = #column
     */
    public int getColumn()
    {
        return column;
    }

    /**
     * Checks to see if two objects are equal.
     *
     * @return whether or not Row and Columns are equal, a boolean
     *
     * @param obj The object being compared
     *
     * @pre obj != null
     *
     * @post equals = (this.row = obj.row AND this.column = obj.column)
     */

    @Override
    public boolean equals(Object obj) {
        BoardPosition pos2 = (BoardPosition) obj;
        return this.getRow() == pos2.getRow() && this.getColumn() == pos2.getColumn();
    }

    /**
     * Creates a string containing Row and Column.
     *
     * @return Returns formatted string: "<Row>,<col>"
     *
     * @pre none
     *
     * @post [Returns formatted string: "<Row>,<col>" by calling the getters for Boardposition's instance variables.] Row = #Row AND Column = #Column
     */

    @Override
    public String toString()
    {
        return row + "," + column;
    }
}