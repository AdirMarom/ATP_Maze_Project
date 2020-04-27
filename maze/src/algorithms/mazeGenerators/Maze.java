package algorithms.mazeGenerators;



import jdk.nashorn.internal.ir.Splittable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;


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

    public Maze(byte[] maze){
        int start_pos_x=0;
        int start_pos_y=0;
        int end_pos_x=0;
        int end_pos_y=0;
        for(int i=0;i<30;i++){
            if(i<=4)
                this.NumOfRows+=maze[i];
            else if(i>4 && i<=9)
                this.NumOfColumns+=maze[i];
            else if(i>9 && i<=14)
                start_pos_x+=maze[i];
            else if(i>14 && i<=19)
                start_pos_y+=maze[i];
            else if(i>19 && i<=24)
                end_pos_x+=maze[i];
            else
                end_pos_y+=maze[i];
        }
        this.start_position=new Position(start_pos_x,start_pos_y);
        this.end_position=new Position(end_pos_x,end_pos_y);
        this.TheMaze=new int[this.NumOfRows][this.NumOfColumns];
        int maze_length=maze.length;
        int idx=30;



    }

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
     * index 25-29 end index column
     * 30-end compress matrix
     * @return byte array
     */
    public byte[] toByteArray(){
        int[] maze_parameter={this.NumOfRows,this.NumOfColumns,this.start_position.getRowIndex(),this.start_position.getColumnIndex(),this.end_position.getRowIndex(),this.end_position.getColumnIndex()};
        List<Byte> compress_Maze_D=new ArrayList<Byte>();
        set_Maze_compress_param(maze_parameter,compress_Maze_D);
        //set_Maze_compress_value(this.TheMaze,compress_Maze_D);
        int[] b=convert_2d_to_1d(this.getTheMaze());
        String s1= Arrays.toString(b);
        String s2=s1.replace(",","");
        s2=s2.replace(" ","");
        s2=s2.substring(1);
        s2=s2.substring(0,s2.length()-2);
        String[] str=new String[b.length/8+1];
        str=s2.split("(?<=\\G.{8})");
        String p="22";
        /*
        for(int i=8 ;i<b.length;i=i+8){
            if(i+8>=b.length){
                str[str.length-1]=s2.substring(i,str.length-1);
            }
            str[i%8-1]=s2.substring(i-8,i);

        }
*/

        System.out.println(s2);
//        for(int i=0 ;i<b.length;i++){
//            if(i>0 && i%8==0)
//               s1+=" ";
//            s1+=b[i];
//        }
        byte[] compress_Maze_S=new byte[compress_Maze_D.size()];
        for (int i=0 ;i<compress_Maze_D.size();i++){
            compress_Maze_S[i]=compress_Maze_D.get(i);
        }
        String s = fromBinaryStringToBase64(str);
        byte[] res=s.getBytes();
        return res;
    }

    public static String fromBinaryStringToBase64(String[] split) {
       // String[] split = binary.split(" ");
        byte[] arrayBinary = new byte[split.length];
        for(int i=0;i<split.length;i++){
            arrayBinary[i] = (byte)Integer.parseInt(split[i],2);
        }
        return Base64.getEncoder().encodeToString(arrayBinary);
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
    private void set_Maze_compress_param(int[] maze_parameter, List<Byte> compress_Maze_D){
        int res,i;
        i=0;
        for(int j=0 ;j<6;j++) {
            res = maze_parameter[j];
            for(int x=0 ;x<5;x++) {
                if(res<=0)
                    compress_Maze_D.add(i, (byte) 0);
                else if (res < 255)
                    compress_Maze_D.add(i, (byte) res);
                else if (res >= 255)
                    compress_Maze_D.add(i, (byte) 255);
                res -= 255;
                i++;
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
}
