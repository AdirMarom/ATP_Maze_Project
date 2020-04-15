package algorithms.mazeGenerators;
import java.util.*;

/**
 *  create maze build random wall
 */

public class SimpleMazeGenerator extends AMazeGenerator {

    /**
     *
     * @param rows
     * @param columns
     * @return solvable Maze with randoms walls
     */
    public Maze generate(int rows,int columns){
        if(rows<2 ||columns <2)
            return null;

        int[][] matrix=new int[rows][columns];
        int RandColumn1=randomNum(0,columns);
        Position StartPoint=new Position(0,RandColumn1);

        int RandColumn2 = randomNum(0,columns);
        while (RandColumn2==RandColumn1){
            RandColumn2 = randomNum(0,columns);
        }

        Position EndPoint=new Position(rows-1,RandColumn2);
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(i==StartPoint.getRowIndex()&& j==StartPoint.getColumnIndex())
                    matrix[i][j] = 0;
                if(i==EndPoint.getRowIndex() && j==StartPoint.getColumnIndex())
                    matrix[i][j]=0;
                if(j==StartPoint.getColumnIndex())
                    matrix[i][j]=0;
                else{
                    int Rand1 = randomNum(0,2);
                    matrix[i][j]=Rand1;
                }
            }
        }
        int x =rows-1;
        int y=StartPoint.getColumnIndex();
        int[][] fixed_matrix=digToSide(matrix,x,y,EndPoint);

        Maze NewMaze=new Maze(rows,columns);
        initialize_maze(NewMaze,fixed_matrix,StartPoint,EndPoint);

        return NewMaze ;
    }

    /**
     *
     * @param matrix
     * @param x
     * @param y
     * @param endPos
     * @return matrix with path from start position to end position
     */
    private int[][] digToSide(int[][] matrix,int x,int y,Position endPos){
        while (y<endPos.getColumnIndex()){
            matrix[x][y]=0;
            y++;
        }
        while (y>endPos.getColumnIndex()){
            matrix[x][y]=0;
            y--;
        }
        return matrix;
    }

    /**
     * initialize the new maze with the proper fields
     * @param maze
     * @param matrix
     * @param startPosition
     * @param endPosition
     */
    private void initialize_maze(Maze maze,int [][] matrix,Position startPosition,Position endPosition){
        maze.setTheMaze(matrix);
        maze.setStart_position(startPosition);
        maze.setEnd_position(endPosition);
    }

    /**
     *
     * @param low
     * @param high
     * @return random number between low to high-1
     */
    private  int randomNum(int low,int high) {
        Random r = new Random();
        return r.nextInt(high - low) + low;
    }



}

