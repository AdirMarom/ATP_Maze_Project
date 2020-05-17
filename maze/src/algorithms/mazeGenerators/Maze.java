package algorithms.mazeGenerators;



import jdk.nashorn.internal.ir.Splittable;

import java.io.Serializable;
import java.util.*;


/**
 * maze class bild from matrix of integer
 * every cell of this maze is a Position object contain x,y index
 * 1-wall
 * 0-pass
 */

public class Maze implements Serializable {

    private int NumOfRows;
    private int NumOfColumns;
    private int[][] TheMaze;
    private Position start_position;
    private Position end_position;

    public Maze(){}

    public Maze(byte[] maze_byte){

        if(maze_byte==null)
            return;
        int start_pos_x=0;
        int start_pos_y=0;
        int end_pos_x=0;
        int end_pos_y=0;
        for(int i=0;i<30;i++){
            if(i<=4)
                this.NumOfRows+=Byte.toUnsignedInt(maze_byte[i]);
            else if(i>4 && i<=9)
                this.NumOfColumns+=Byte.toUnsignedInt(maze_byte[i]);
            else if(i>9 && i<=14)
                start_pos_x+=Byte.toUnsignedInt(maze_byte[i]);
            else if(i>14 && i<=19)
                start_pos_y+=Byte.toUnsignedInt(maze_byte[i]);
            else if(i>19 && i<=24)
                end_pos_x+=Byte.toUnsignedInt(maze_byte[i]);
            else
                end_pos_y+=Byte.toUnsignedInt(maze_byte[i]);
        }
        this.start_position=new Position(start_pos_x,start_pos_y);
        this.end_position=new Position(end_pos_x,end_pos_y);
        this.TheMaze=new int[this.NumOfRows][this.NumOfColumns];
        this.TheMaze=convert_1d_to_2d(this.NumOfRows,this.NumOfColumns,maze_byte);
    }

    public Maze(int rows,int columns){

        this.NumOfRows=rows;
        this.NumOfColumns=columns;
        this.TheMaze=new int[rows][columns];
        this.start_position=new Position(0,0);
        this.end_position=new Position(rows-1,columns-1);

    }
    public boolean equals(Maze Sec_maze){
        if(Sec_maze==null)
            return false;
        return Arrays.equals(this.toByteArray(),Sec_maze.toByteArray());
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
     * index 25-29 end index column
     * 30-end compress matrix
     * @return byte array
     */
    public byte[] toByteArray(){
        int[] maze_parameter={this.NumOfRows,this.NumOfColumns,this.start_position.getRowIndex(),this.start_position.getColumnIndex(),this.end_position.getRowIndex(),this.end_position.getColumnIndex()};
        byte[] compress_param_D=new byte[30];
        set_Maze_compress_param(maze_parameter,compress_param_D);
        byte [] all_data_bytes=new byte[compress_param_D.length+(this.NumOfColumns*this.NumOfRows)];
        for(int i=0;i<30;i++){
            all_data_bytes[i]=compress_param_D[i];
        }
        int[] matrix=convert_2d_to_1d(this.TheMaze);

        int j=0;
        for(int i=30;i<all_data_bytes.length;i++){
            all_data_bytes[i]=(byte)matrix[j++];
        }
        return all_data_bytes;


    }



    /**
     * start the compresss from value zero
     * intdex 30-end compress maze
     * @param Maze_matrix 1-wall 0-pass
     * @param compress_Maze_D 011100 -->[1,3,2] start
     */
    private  void set_Maze_compress_value(int[][] Maze_matrix ,List<Byte> compress_Maze_D){
        int[] Maze_in_array=convert_2d_to_1d(Maze_matrix);
        boolean zero_flag=true;
        int Maze_size=Maze_in_array.length;
        int counter=0;
        int sum;
        int index=30;
        while(counter<Maze_size){
            if(zero_flag){
                sum=0;
                while(counter<Maze_size && Maze_in_array[counter]==0 ){
                    if(sum<256)
                        sum++;
                    else{
                        compress_Maze_D.add(index,(byte)255);
                        sum=0;
                        index++;
                    }
                    counter++;
                }
                compress_Maze_D.add(index,(byte)sum);
                index++;
                zero_flag=false;
            }
            else{
                sum=0;
                while(counter<Maze_size && Maze_in_array[counter]==1){
                    if(sum<256)
                        sum++;
                    else{
                        compress_Maze_D.add(index,(byte)255);
                        sum=0;
                        index++;
                    }
                    counter++;
                }
                compress_Maze_D.add(index,(byte)sum);
                index++;
                zero_flag=true;
            }
        }
    }

    /**
     *
     * @param matrix 2D int array
     * @return 1D array all the matrix in one int[]
     */
    private  int[] convert_2d_to_1d(int[][] matrix){
        int k=0;
        int[] arr=new int[matrix.length*matrix[0].length];
        for(int i=0 ;i<matrix.length;i++){
            for(int j=0 ;j<matrix[0].length;j++){
                arr[k++]=matrix[i][j];
            }
        }
        return arr;
    }

    /**
     *
     * @param maze_parameter row number,column number,start index row,start index column,end index row,end index column
     * @param compress_Maze_D every cell value of number between 0-255
     */
    private void set_Maze_compress_param(int[] maze_parameter, byte[] compress_Maze_D){
        int next_idx_to_list=0;
        for(int i=0;i<maze_parameter.length;i++){
            int tmp=maze_parameter[i];
            if(tmp<=255) {
                compress_Maze_D[next_idx_to_list]=(byte)tmp;
                next_idx_to_list += 5;
            }
            else{
                int iter=0;
                while (tmp>255){
                    iter++;
                    compress_Maze_D[next_idx_to_list]=(byte)255;
                    tmp-=255;
                    next_idx_to_list++;
                }
                if(tmp>0){
                    iter++;
                    compress_Maze_D[next_idx_to_list]=(byte)tmp;
                    next_idx_to_list++;
                }

                next_idx_to_list+=(5-iter);
            }


        }
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


    int[][] convert_1d_to_2d(int numrows,int numcolumns,byte[] bytes){
        int[][] matrix=new int[numrows][numcolumns];
        int idx=30;
        for(int i=0;i<numrows;i++){
            for(int j=0;j<numcolumns;j++){
                matrix[i][j]=Byte.toUnsignedInt(bytes[idx]);
                idx++;
            }
        }
        return matrix;
    }
}