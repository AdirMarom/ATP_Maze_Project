package algorithms.mazeGenerators;

import java.util.Random;
import java.util.Stack;

/**
 * create random maze in given size , guarantee a solution
 * 1-wall
 * 0-pass
 *
 */
public class MyMazeGenerator extends AMazeGenerator {
    /**
     *
     * @param rows
     * @param columns
     * @return return maze object in size (row X columns)
     */
    public  Maze generate(int rows,int columns) {
        if(rows<2 ||columns <2)
            return null;

        int[] rand = random_edge(rows, columns);
        int[][] matrix=setMatrix(rows,columns,1);
        DFS_generate(matrix,rand);

        Maze myMaze= new Maze(rows, columns);
        Position start_pos=new Position(0,0);

        int directionin=randomdirection();
        break_the_wall(matrix,rand,directionin,start_pos);
        myMaze.setStart_position(start_pos);
        myMaze.setTheMaze(matrix);

        int[] startPoint={start_pos.getRowIndex(),start_pos.getColumnIndex()};
        Boolean setendPos=false;
        int[] finalPoint;
        while (!setendPos){
            finalPoint=random_frame(rows,columns,startPoint);
            setendPos=SetEndPosition(finalPoint,myMaze,startPoint);
        }
        return myMaze;


    }

    /**
     *
     * @param matrix all value is 1
     * @param rand start posion random
     * @return matrix imt[][] in size (row X columns) bild from DFS algorithm
     */
    private int[][] DFS_generate(int[][] matrix ,int[] rand) {
        Stack<int[]> stack = new Stack<int[]>();
        stack.push(rand);
        matrix[rand[0]][rand[1]] = 0;
        int[] curr_place=rand;
        while (stack.size()>0) {
            curr_place[0] = stack.peek()[0];
            curr_place[1] = stack.peek()[1];
            //out of bound
            if (cheackIfJam(matrix, curr_place) == false)
                stack.pop();

            else {
                int num = randomdirection();
                switch (num) {
                    case 1:
                        //--up case--
                        if (stack.peek()[0] - 2 >= 0)
                            set_Case_On_Matrix(matrix,curr_place,stack,1);
                        break;

                    case 2:
                        //--down case--
                        if (stack.peek()[0] + 2 <= matrix.length - 1 )
                            set_Case_On_Matrix(matrix,curr_place,stack,2);
                        break;
                    case 3:
                        //--left case--
                        if (stack.peek()[1] - 2 >= 0 )
                            set_Case_On_Matrix(matrix,curr_place,stack,3);
                        break;

                    case 4:
                        //--right case--
                        if (stack.peek()[1] + 2 <= matrix[0].length - 1 )
                            set_Case_On_Matrix(matrix,curr_place,stack,4);
                        break;
                }
            }
        }
        return matrix;
    }

    /**
     *
     * @param matrix
     * @param curr_place current index
     * @param stack contain all the step to this index
     * @param Case number -up,dowm,left,right
     */
    private void set_Case_On_Matrix( int[][] matrix,int[] curr_place,Stack<int[]> stack,int Case){

        //--up case--
        if(Case==1){

                if (matrix[curr_place[0] - 2][curr_place[1]] != 0) {
                    matrix[curr_place[0] - 1][curr_place[1]] = 0;
                    matrix[curr_place[0] - 2][curr_place[1]] = 0;
                    curr_place[0] -= 2;
                    stack.push(new int[2]);
                    stack.peek()[0]=curr_place[0];
                    stack.peek()[1]=curr_place[1];
                } }
        //--down case--
        else if(Case==2){

                if (matrix[curr_place[0] + 2][curr_place[1]] != 0) {
                    matrix[curr_place[0] + 1][curr_place[1]] = 0;
                    matrix[curr_place[0] + 2][curr_place[1]] = 0;
                    curr_place[0] += 2;
                    stack.push(new int[2]);
                    stack.peek()[0]=curr_place[0];
                    stack.peek()[1]=curr_place[1];
                } }
        //--left case--
        else if(Case==3){

                    if (matrix[curr_place[0]][curr_place[1] - 2] != 0) {
                        matrix[curr_place[0]][curr_place[1] - 1] = 0;
                        matrix[curr_place[0]][curr_place[1] - 2] = 0;
                        curr_place[1] -= 2;
                        stack.push(new int[2]);
                        stack.peek()[0]=curr_place[0];
                        stack.peek()[1]=curr_place[1];
                    } }
        //--right case--
        else if(Case==4){

                if (matrix[curr_place[0]][curr_place[1] + 2] != 0) {
                    matrix[curr_place[0]][curr_place[1] + 1] = 0;
                    matrix[curr_place[0]][curr_place[1] + 2] = 0;
                    curr_place[1] += 2;
                    stack.push(new int[2]);
                    stack.peek()[0]=curr_place[0];
                    stack.peek()[1]=curr_place[1];
                } }
    }

    /**
     *
     * @param rows
     * @param columns
     * @param defult -value
     * @return matrix all the value equal to defult
     */
    private int[][] setMatrix(int rows ,int columns,int defult){
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = defult;
            }
        }
        return matrix;

    }

    /**
     *
     * @param v -index
     * @param u-index
     * @return true-this two index share column or row
     * false if not
     */
    private  boolean EqualEdge(int[] v,int[] u){
        if(v[0]==u[0] || v[1]==u[1] )
            return true;
        return false;
    }

    /**
     *
     * @param Rows
     * @param Columns
     * @param x
     * @param y
     * @return true-if index is inside the matrix
     */
    private boolean inBound(int Rows,int Columns, int x, int y ){
        if((Rows>x && x>=0 && Columns>y && y>=0)) {
                return true;
        }
        return false;
    }

    /**
     *
     * @param EndPos random index in maze
     * @param maze
     * @param startPoint-we need to check that the start and the and not share index
     * @return
     */
    private boolean SetEndPosition(int[] EndPos,Maze maze,int[] startPoint){
         int[][] matrix=maze.getTheMaze();
         int rows=maze.getNumOfRows();
         int colums=maze.getNumOfColumns();
         int[] currEnd=EndPos;
         if((rows<4 && colums==2) || (colums<4 &&rows==2)){
             if(inBound(rows,colums,EndPos[0]-1,EndPos[1]))
                 matrix[currEnd[0]-1][currEnd[1]] = 0;
             else if(inBound(rows,colums,EndPos[0]+1,EndPos[1]))
                 matrix[currEnd[0]+1][currEnd[1]] = 0;
             else if(inBound(rows,colums,EndPos[0],EndPos[1]-1))
                 matrix[currEnd[0]+1][currEnd[1]-1] = 0;
             else if(inBound(rows,colums,EndPos[0],EndPos[1]+1))
                 matrix[currEnd[0]][currEnd[1]+1] = 0;
         }
        if(!EqualEdge(startPoint,EndPos)) {

            if (matrix[currEnd[0]][currEnd[1]] != 0) {

                //up
                if (inBound(rows, colums, currEnd[0] - 1, currEnd[1]) && matrix[currEnd[0] - 1][currEnd[1]] == 0)
                    matrix[currEnd[0]][currEnd[1]] = 0;

                //down
                else if (inBound(rows, colums, currEnd[0] + 1, currEnd[1]) && matrix[currEnd[0] + 1][currEnd[1]] == 0)
                    matrix[currEnd[0]][currEnd[1]] = 0;

                //left
                else if (inBound(rows, colums, currEnd[0], currEnd[1] + 1) && matrix[currEnd[0]][currEnd[1] + 1] == 0)
                    matrix[currEnd[0]][currEnd[1]] = 0;

                //right
                else if (inBound(rows, colums, currEnd[0], currEnd[1] - 1) && matrix[currEnd[0]][currEnd[1] - 1] == 0)
                    matrix[currEnd[0]][currEnd[1]] = 0;

                else
                    return false;
            }
        }
         Position pos=new Position(currEnd[0],currEnd[1]);
         maze.setEnd_position(pos);
         maze.setTheMaze(matrix);
         return true;
    }

    /**
     *
     * @param matrix
     * @param curr_place-index in matrix
     * @return false- if we dont have place to forward ,true we have one or more valid direction
     */
    private boolean cheackIfJam(int[][] matrix, int[] curr_place){

        int rows=matrix.length;
        int columns=matrix[0].length;
        boolean bool=false;

        if(curr_place[0] - 2 >= 0  ){
            if( matrix[curr_place[0] - 2][curr_place[1]] != 0){
                bool=true;
            }
        }

        if(curr_place[0]+ 2 <= rows - 1 ){
            if (matrix[curr_place[0] + 2][curr_place[1]] != 0) {
                bool=true;
            }

        }

        if(curr_place[1] - 2 >= 0  ) {
            if (matrix[curr_place[0]][curr_place[1] - 2] != 0) {
                bool = true;
            }
        }

        if(curr_place[1] + 2 <= columns - 1  ) {
            if (matrix[curr_place[0]][curr_place[1] + 2] != 0) {
                bool = true;
            }
        }
        return bool;

    }

    /**
     *
     * @param rows
     * @param columns
     * @return random index in maze
     */
    private int[] random_edge(int rows,int columns){


        Random r = new Random();
        int low = 0;
        int high = columns;
        int RandColumn = r.nextInt(high-low) + low;

        int low1 = 0;
        int high1 = rows;
        int Randline=r.nextInt(high1-low1) + low1;

        int[] res=new int[2];
        res[0]=Randline;
        res[1]=RandColumn;
        return res;
    }

    /**
     *
     * @param rows
     * @param columns
     * @param startpoint
     * @return random index in maze on the bound
     */
    private int[] random_frame(int rows,int columns,int[] startpoint) {

        Random r = new Random();
        int low = 0;
        int high = columns;
        int RandColumn = r.nextInt(high-low) + low;
        while(RandColumn==startpoint[1]){
            RandColumn= r.nextInt(high-low) + low;
        }
        int[] p1={0,RandColumn};
        int[] p2={rows-1,RandColumn};

        int low1 = 0;
        int high1 = rows;
        int Randline=r.nextInt(high1-low1) + low1;
        while(Randline==startpoint[0]){
            Randline= r.nextInt(high1-low1) + low1;
        }
        int[] p3={Randline,0};
        int[] p4={Randline,columns-1};

        int[][] array_tmp={p1,p2,p3,p4};
        int low2 = 0;
        int high2 = 4;
        int Rand= r.nextInt(high2-low2) + low2;
        while( array_tmp[Rand][0]== startpoint[0] || array_tmp[Rand][1]== startpoint[1] ){
            Rand= r.nextInt(high2-low2) + low2;
        }
        return array_tmp[Rand] ;

    }

    /**
     *
     * @return 1-4 directin (1-up ,2-down ,3-right ,4-left)
     */
    private int randomdirection(){

        /*
        1--up
        2--down
        3--left
        4--right
         */
        Random r = new Random();
        int low1 = 1;
        int high1 = 5;
        return r.nextInt(high1-low1) + low1;
    }

    /**
     *
     * @param matrix
     * @param startpoint
     * @param direction
     * @param pos ,valid position on the frem.take random index on the frem and conect the position to the trail
     */
    private void break_the_wall(int[][] matrix,int [] startpoint,int direction, Position pos) {

        if (direction == 1) {
            for (int i = matrix.length - 1; i >= 0; i--) {
                if (matrix[i][startpoint[1]] == 0) {
                    pos.setRowPosition(matrix.length - 1);
                    pos.setColumnPosition(startpoint[1]);
                    break;
                } else {
                    matrix[i][startpoint[1]] = 0;
                }
            }
        }
        else if (direction == 2) {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][startpoint[1]] == 0) {
                    pos.setColumnPosition(startpoint[1]);
                    break;
                } else
                    matrix[i][startpoint[1]] = 0;
            }
        }
        else if (direction == 4) {
            for (int i = 0; i < matrix[0].length; i++) {
                if (matrix[startpoint[0]][i] == 0) {
                    pos.setRowPosition(startpoint[0]);
                    break;
                } else
                    matrix[startpoint[0]][i] = 0;
            }
        }
        else if (direction == 3) {
            for (int i = matrix[0].length - 1; i >= 0; i--) {
                if (matrix[startpoint[0]][i] == 0) {
                    pos.setRowPosition(startpoint[0]);
                    pos.setColumnPosition(matrix[0].length - 1);
                    break;
                } else
                    matrix[startpoint[0]][i] = 0;
            }
        }
    }

}



