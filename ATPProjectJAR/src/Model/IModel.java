package Model;

import javafx.scene.input.KeyCode;

public interface IModel {

    void GenerateMaze(int Rows,int Column);
    int[][] getMaze();
    void MoveCharacter(KeyCode Movement);
    int getCharacterPositionRow();
    int  getCharacterPositionCol();
    void Close();
    public algorithms.mazeGenerators.Maze getMazeObj();

    void moveCharacter(KeyCode movement);
}
