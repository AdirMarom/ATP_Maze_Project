package algorithms.mazeGenerators;

/**
 * representation of index in maze
 */
public class Position {
    int rowPosition;
    int columnPosition;

    /**
     *
     * @param x-index
     * @param y-index
     */
    public Position(int x ,int y){
        if(x>-1 && y>-1){
            this.rowPosition=x;
            this.columnPosition=y;
        }
    }

    /**
     *
     * @return Column Index
     */
    public int getColumnIndex() {
        return columnPosition;
    }

    /**
     *
     * @param columnPosition- Column Index
     */
    public void setColumnPosition(int columnPosition) {
        this.columnPosition = columnPosition;
    }

    /**
     *
     * @return  get row index
     */
    public int getRowIndex() {
        return rowPosition;
    }

    /**
     *
     * @param rowPosition set row in position
     */
    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }

    /**
     *
     * @return representation of position
     */

    @Override
    public String toString() {
        return "{"+rowPosition + "," + columnPosition + '}';
    }
}
