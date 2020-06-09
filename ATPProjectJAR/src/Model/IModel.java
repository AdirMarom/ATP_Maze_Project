package Model;

import algorithms.search.Solution;
import javafx.scene.input.KeyCode;

public interface IModel {

    void GenerateMaze(int Rows,int Column);
    int[][] getMaze();
    void MoveCharacter(KeyCode Movement);
    int getCharacterPositionRow();
    int  getCharacterPositionCol();
    void Close();
    algorithms.mazeGenerators.Maze getMazeObj();
    Solution GenerateSolution();
    Solution getSolution();
    void moveCharacter(KeyCode movement);
}
