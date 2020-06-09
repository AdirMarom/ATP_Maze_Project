package View;

import ViewModel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class MyViewController implements Observer,IView {

    public MazeDisplay mazeDisplay;
    public javafx.scene.control.TextField txtfld_rowsNum;
    public javafx.scene.control.TextField txtfld_columnsNum;
    public javafx.scene.control.Label lbl_rowsNum;
    public javafx.scene.control.Label lbl_columnsNum;
    public javafx.scene.control.Button btn_generateMaze;
    public javafx.scene.control.Button btn_solveMaze;

    @FXML
    private ViewModel viewModel ;
            //=new ViewModel();
    private Scene mainScene;
    private Stage mainStage;

    public void initialize(ViewModel viewModel, Stage mainStage, Scene mainScene) {
        this.viewModel = viewModel;
       // this.mainScene = mainScene;
       // this.mainStage = mainStage;
       // bindProperties();
       // setResizeEvent();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == viewModel) {
            displayMaze(viewModel.getMaze());
            btn_generateMaze.setDisable(false);
        }
    }

    public void displayMaze(int[][] maze) {

        mazeDisplay.setMazeObject(viewModel.getMazeObj());
        mazeDisplay.setMaze(maze);
        //mazeDisplay(maze);
        int characterPositionRow = viewModel.getCharacterPositionRow();
        int characterPositionColumn = viewModel.getCharacterPositionColumn();
        mazeDisplay.setCharacterPosition(characterPositionRow, characterPositionColumn);
       // this.characterPositionRow.set(characterPositionRow + "");
      //  this.characterPositionColumn.set(characterPositionColumn + "");
       // btn_solveMaze.setDisable(false);
    }
    public void KeyPressed(KeyEvent keyEvent) {
        viewModel.moveCharacter(keyEvent.getCode());
        keyEvent.consume();

    }
    public void generateMaze() {
        int heigth = Integer.valueOf(txtfld_rowsNum.getText());
        int width = Integer.valueOf(txtfld_columnsNum.getText());
        viewModel.generateMaze(heigth,width);
      //  mazeDisplay.setMazeObject(viewModel.getMazeObj());
       // displayMaze(viewModel.getMaze());


       //btn_generateMaze.setDisable(true);
       // btn_solveMaze.setDisable(true);
       // viewModel.generateMaze(width, heigth);
    }
}
