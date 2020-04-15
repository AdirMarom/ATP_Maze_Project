package algorithms.mazeGenerators;

/**
 *  create empty maze
 */
public class EmptyMazeGenerator extends AMazeGenerator {


    /**
     * @param rows
     * @param columns
     * @return Maze object, every cell hold the value 0;
     */
    public Maze generate(int rows,int columns){
        if(rows<=1 ||columns <=1)
            return null;
        int [][] matrix=new int[rows][columns];
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                matrix[i][j]=0;
            }
        }
        Maze NewMaze=new Maze(rows,columns);
        NewMaze.setTheMaze(matrix);
        return NewMaze;
    }

}
