package ViewModel;

import Model.IModel;
import Model.Model;
import javafx.scene.input.KeyCode;

import java.util.Observable;
import java.util.Observer;

public class ViewModel extends Observable implements Observer {

    private IModel model;

    public ViewModel(IModel model){
        this.model = model;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o==model){
            setChanged();
            notifyObservers();
        }
    }

    public void generateMaze(int row ,int col){this.model.GenerateMaze(row,col);}

    public int[][] getMaze(){return model.getMaze();}

    public algorithms.mazeGenerators.Maze getMazeObj(){return this.model.getMazeObj();}

    public void moveCharacter(KeyCode movement) {model.moveCharacter(movement);}

    public int getCharacterPositionRow() {return this.model.getCharacterPositionRow();}

    public int getCharacterPositionColumn() { return this.model.getCharacterPositionCol();}
}
