package Model;

//import algorithms.mazeGenerators.*;
import algorithms.mazeGenerators.Position;
import javafx.scene.input.KeyCode;

import java.util.Observable;

public class Model extends Observable implements IModel {

    private int characterPositionRow ;
    private int characterPositionColumn;
    private algorithms.mazeGenerators.MyMazeGenerator generator=new algorithms.mazeGenerators.MyMazeGenerator();
    private algorithms.mazeGenerators.Maze G_maze;
    private int[][] MatrixMaze;
    private  int numOfRow;
    private  int numOfCol;

    public Model() {

    }

    @Override
    public void GenerateMaze(int Rows, int Column) {
        this.G_maze=this.generator.generate(Rows,Column);
        this.MatrixMaze=this.G_maze.getTheMaze();
        characterPositionRow=this.G_maze.getStart_position().getRowIndex();
        characterPositionColumn=this.G_maze.getStart_position().getColumnIndex();
        this.numOfRow=this.MatrixMaze.length;
        this.numOfCol=this.MatrixMaze[0].length;
        this.G_maze.print2();
        setChanged(); //Raise a flag that I have changed
        notifyObservers();
    }

    @Override
    public int[][] getMaze() {
        return MatrixMaze;
    }

    @Override
    public void MoveCharacter(KeyCode Movement) {

    }

    public algorithms.mazeGenerators.Maze getMazeObj(){return this.G_maze;}

    @Override
    public void moveCharacter(KeyCode movement) {
        this.G_maze.print2();
        System.out.println("{"+characterPositionRow+","+characterPositionColumn+"}");

        Position next_pos=new algorithms.mazeGenerators.Position(0,0) ;
        switch (movement) {
            case NUMPAD8:
                next_pos.setRowPosition(characterPositionRow-1);
                next_pos.setColumnPosition(characterPositionColumn);
                if (characterPositionRow > 0 && this.G_maze.validPosition(next_pos))
                    characterPositionRow--;
                break;


            case NUMPAD2:
                next_pos.setRowPosition(characterPositionRow+1);
                next_pos.setColumnPosition(characterPositionColumn);
                if (characterPositionRow < this.numOfRow - 1 && this.G_maze.validPosition(next_pos) )
                    characterPositionRow++;
                break;

            case NUMPAD6:
                next_pos.setRowPosition(characterPositionRow);
                next_pos.setColumnPosition(characterPositionColumn+1);
                if (characterPositionColumn < this.numOfCol - 1 && this.G_maze.validPosition(next_pos) )
                    characterPositionColumn++;
                break;

            case NUMPAD4:
                next_pos.setRowPosition(characterPositionRow);
                next_pos.setColumnPosition(characterPositionColumn-1);
                if (characterPositionColumn > 0 && this.G_maze.validPosition(next_pos) )
                    characterPositionColumn--;
                break;
            case NUMPAD7:
                next_pos.setRowPosition(characterPositionRow-1);
                next_pos.setColumnPosition(characterPositionColumn-1);
                if(characterPositionRow>0 && characterPositionColumn>0 && this.G_maze.validPosition(next_pos)  )
                {
                    characterPositionRow--;
                    characterPositionColumn--;
                }
                break;
            case  NUMPAD9:
                next_pos.setRowPosition(characterPositionRow-1);
                next_pos.setColumnPosition(characterPositionColumn+1);
                if(characterPositionRow>0 && characterPositionColumn<this.numOfCol  &&  this.G_maze.validPosition(next_pos) ){
                    characterPositionRow--;
                    characterPositionColumn++;
                }
                break;
            case NUMPAD1:
                next_pos.setRowPosition(characterPositionRow+1);
                next_pos.setColumnPosition(characterPositionColumn-1);
                if(characterPositionRow<this.numOfRow && characterPositionColumn>0 &&  this.G_maze.validPosition(next_pos)){
                    characterPositionRow++;
                    characterPositionColumn--;
                }
                break;
            case NUMPAD3:
                next_pos.setRowPosition(characterPositionRow+1);
                next_pos.setColumnPosition(characterPositionColumn+1);
                if(characterPositionRow<this.numOfRow && characterPositionColumn<this.numOfCol && this.G_maze.validPosition(next_pos)){
                    characterPositionRow++;
                    characterPositionColumn++;
                }
                break;

            case HOME:
                characterPositionRow=this.G_maze.getStart_position().getRowIndex();
                characterPositionColumn=this.G_maze.getStart_position().getColumnIndex();
                break;
        }
        setChanged();
        notifyObservers();
    }




    @Override
    public int getCharacterPositionRow() {
        return this.characterPositionRow;
    }

    @Override
    public int getCharacterPositionCol() {
        return this.characterPositionColumn;
    }

    @Override
    public void Close() {

    }
}
