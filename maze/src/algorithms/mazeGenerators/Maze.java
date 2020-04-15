package algorithms.mazeGenerators;

/**
 * maze class bild from matrix of integer
 * every cell of this maze is a Position object contain x,y index
 * 1-wall
 * 0-pass
 */

public class Maze {
    private int NumOfRows;
    private int NumOfColumns;
    private int[][] TheMaze;
    private Position start_position;
    private Position end_position;

    public Maze(){}

    public Maze(int rows,int columns){

        this.NumOfRows=rows;
        this.NumOfColumns=columns;
        this.TheMaze=new int[rows][columns];
        this.start_position=new Position(0,0);
        this.end_position=new Position(rows-1,columns-1);

    }

    /**
     * @return Position hold the start index in matrix
     *  print mark -"S"
     */
    public Position getStart_position() {
        return start_position;
    }

    /**
     * @return Position hold the goal index in matrix
     *  print mark -"E"
     */
    public Position getEnd_position() {
        return end_position;
    }

    public void setStart_position(Position start_position) {
        this.start_position = start_position;
    }

    public void setEnd_position(Position end_position) {
        this.end_position = end_position;
    }

    /**
     * print the maze
     * 1-wall
     * 0-pass
     * 'S'-START
     * 'E'-goal
     */

    public void print() {
        String srow = "";
        for (int j = 0; j < this.NumOfRows; j++) {
            for (int i = 0; i < this.NumOfColumns; i++) {

                if (i == this.start_position.columnPosition && j == this.start_position.rowPosition) {
                    srow+="S";
                }
                else if (i == this.end_position.columnPosition && j == this.end_position.rowPosition) {
                    srow+="E";
                }
                else{
                    srow+=this.TheMaze[j][i];
                }

            }
            System.out.println(srow);
            srow="";
        }
    }

    /**
     * @return representation the maze with matrix of integer
     */
    public int[][] getTheMaze() {
        return TheMaze;
    }

    /**
     * representation the maze with matrix of integer
     */
    public void setTheMaze(int[][] theMaze) {
        TheMaze = theMaze;
        this.NumOfColumns=theMaze[0].length;
        this.NumOfRows=theMaze.length;
    }

    public int getNumOfColumns() {
        return NumOfColumns;
    }

    public int getNumOfRows() {
        return NumOfRows;
    }

    /**
     * @param p ,check if that this index is not out of bound
     * @return true-if is a valid place to pass
     *          false- wall or out of bound
     */
    public boolean validPosition(Position p){
        int x=p.getRowIndex();
        int y=p.getColumnIndex();
        if(x<0 || y<0 || x>=this.getNumOfRows() || y>=this.getNumOfColumns())
            return false;
        if(this.TheMaze[x][y]==1)
            return false;
        return true;
    }

    /**
     * index 0-4 row number
     * index 5-9 column number
     *index 10-14 start index row
     * index 15-19 start index column
     * index 20-24 end index row
     * index 25-30 end index row
     * 31-end compress matrix
     * @return
     */
    public byte[] toByteArray(){
        int[] maze_parameter={this.NumOfRows,this.NumOfColumns,this.start_position.getRowIndex(),this.start_position.getColumnIndex(),this.end_position.getRowIndex(),this.end_position.getColumnIndex()};
        byte[] arr=new byte[this.NumOfRows*this.NumOfColumns+30];
        int res,sum,i;
        byte carry;
        for(int j=0 ;j<6;j++) {
            res=maze_parameter[j]/255;
            sum=res*255;
            carry = (byte)(getNumOfRows()-sum);
/*            for ( i <= res; i++) {
                if (i == res)
                    arr[i] = carry;
                else
                    arr[i] = (byte) 255;
            }
            i+=5;*/
        }
        return arr;
    }

    public void print2() {
        for(int i = 0; i < this.TheMaze.length; ++i) {
            for(int j = 0; j < this.TheMaze[i].length; ++j) {
                if (i == this.start_position.getRowIndex() && j == this.start_position.getColumnIndex()) {
                    System.out.print(" \u001b[46m"+"S");
                } else if (i == this.end_position.getRowIndex() && j == this.end_position.getColumnIndex()) {
                    System.out.print(" \u001b[46m"+"E");
                } else if (this.TheMaze[i][j] == 1) {
                    System.out.print(" \u001b[43m ");
                } else {
                    System.out.print(" \u001b[40m ");
                }
            }

            System.out.println(" \u001b[107m");
        }
        System.out.println("");
        // System.out.println("\u001b[31m\uD83D\uDC99\uD83D\uDC99\uD83D\uDC99 liad is the qween \uD83E\uDDDC\u200D \uD83D\uDC99\uD83D\uDC99\uD83D\uDC99 \u001b[0m");
        System.out.println();
    }
}
