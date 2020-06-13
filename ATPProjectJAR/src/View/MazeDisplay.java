package View;


import algorithms.search.MazeState;
import algorithms.search.Solution;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MazeDisplay extends Canvas {
    private int[][] Maze;
    private int characterPositionRows;
    private int characterPositionCol;
    private StringProperty ImageFileNameWall = new SimpleStringProperty();
    private StringProperty ImageFileNameCharacter = new SimpleStringProperty();
    private StringProperty ImageFileStart = new SimpleStringProperty();
    private StringProperty ImageFileEnd = new SimpleStringProperty();
    private StringProperty ImageFileNameTrail=new SimpleStringProperty();
    private algorithms.mazeGenerators.Maze Maze_obj;
    private Boolean solution_flag=false;
    private Solution solution;


    public void setSolution(Solution sol){this.solution=sol;}

    public boolean getSolutionFlag(){return this.solution_flag;};

    public void setMaze(int[][] maze){
        if(this.Maze_obj==null)
            return;
        this.Maze=maze;
        //this.characterPositionRows=this.Maze_obj.getStart_position().getRowIndex();
       // this.characterPositionCol=this.Maze_obj.getStart_position().getColumnIndex();
        draw();
    }

    public void setMazeObject(algorithms.mazeGenerators.Maze m){this.Maze_obj=m;}

    public void characterPosition(int Row,int Col){
        if(Row>0 && Row< this.Maze.length)
            this.characterPositionRows=Row;
        if(Col>0 && Col< this.Maze[0].length)
            this.characterPositionCol=Col;
    }

    public void setCharacterPosition(int row, int column) {
        characterPositionRows = row;
        characterPositionCol = column;
        draw();
    }

    private void draw(){
        if (Maze != null) {
            double canvasHeight = getHeight();
            double canvasWidth = getWidth();
            double cellHeight = canvasHeight / Maze.length;
            double cellWidth = canvasWidth / Maze[0].length;

            try {
                Image wallImage = new Image(new FileInputStream(ImageFileNameWall.get()));
                Image characterImage = new Image(new FileInputStream(ImageFileNameCharacter.get()));
                Image StartImage = new Image(new FileInputStream(ImageFileStart.get()));
                Image EndtImage = new Image(new FileInputStream(ImageFileEnd.get()));
                Image TrailImage = new Image(new FileInputStream(this.ImageFileNameTrail.get()));
                GraphicsContext gc = getGraphicsContext2D();
                gc.clearRect(0, 0, getWidth(), getHeight());

                //Draw Maze
                for (int i = 0; i < Maze[0].length; i++) {
                    for (int j = 0; j < Maze.length; j++) {
                        if (Maze[j][i] == 1) {
                            //gc.fillRect(i * cellHeight, j * cellWidth, cellHeight, cellWidth);
                            gc.drawImage(wallImage, i * cellWidth, j * cellHeight, cellWidth, cellHeight);
                        }
                        if(Maze[j][i] == 0)
                            gc.drawImage(TrailImage, i * cellWidth, j * cellHeight, cellWidth, cellHeight);
                        if (this.solution_flag) {

                            for (int x = 0; x < this.solution.getSolutionPath().size(); x++) {
                                MazeState MazeStep = (MazeState) (this.solution.getSolutionPath().get(x));
                                if (MazeStep.getRowIndex() == j && MazeStep.getColIndex() == i) {
                                    gc.setFill(Color.YELLOW);
                                    gc.fillOval(i * cellWidth, j * cellHeight,cellWidth,cellHeight);
                                    break;
                                }
                            }
                        }
                    }
                }

                        //Draw Character
                        //gc.setFill(Color.RED);
                        //gc.fillOval(characterPositionColumn * cellHeight, characterPositionRow * cellWidth, cellHeight, cellWidth);
                        gc.drawImage(StartImage, this.Maze_obj.getStart_position().getColumnIndex() * cellWidth, this.Maze_obj.getStart_position().getRowIndex() * cellHeight, cellWidth, cellHeight);
                        gc.drawImage(characterImage, characterPositionCol * cellWidth, characterPositionRows * cellHeight, cellWidth, cellHeight);
                        gc.drawImage(EndtImage, this.Maze_obj.getEnd_position().getColumnIndex() * cellWidth, this.Maze_obj.getEnd_position().getRowIndex() * cellHeight, cellWidth, cellHeight);

                    } catch(FileNotFoundException e){
                        //e.printStackTrace();
                    }
                }
            }




/*
            if(this.Maze!=null){
                double canvasHeight=getHeight();
                double canvasWidth=getWidth();
                double cellHeight=canvasHeight/this.Maze.length;
                double cellWidth=canvasWidth/this.Maze[0].length;

                try {
                    Image wallImage = new Image(new FileInputStream(ImageFileNameWall.get()));
                    Image characterImage = new Image(new FileInputStream(ImageFileNameCharacter.get()));
                    Image StartImage = new Image(new FileInputStream(ImageFileStart.get()));
                    Image EndtImage = new Image(new FileInputStream(ImageFileEnd.get()));
                    GraphicsContext gc = getGraphicsContext2D();
                    gc.clearRect(0, 0, getWidth(), getHeight());


                    //Draw Maze
                    System.out.println("XXXXXXXXXX");
                    this.Maze_obj.print2();
                    for (int i = 0; i < Maze.length; i++) {
                        for (int j = 0; j < Maze[i].length; j++) {
                            if (this.Maze[i][j] == 1) {
                               // gc.fillRect(i * cellHeight, j * cellWidth, cellHeight, cellWidth);
                                gc.drawImage(wallImage, i * cellHeight, j * cellWidth,cellHeight , cellHeight);
                            }
                        }
                    }
                    gc.drawImage(characterImage, characterPositionCol * cellHeight, this.characterPositionRows * cellWidth, cellHeight, cellWidth);
                    //gc.drawImage(StartImage, this.Maze_obj.getStart_position().getColumnIndex() * cellHeight, this.Maze_obj.getStart_position().getRowIndex() * cellWidth, cellHeight, cellWidth);
                    gc.drawImage(EndtImage, this.Maze_obj.getEnd_position().getColumnIndex()* cellHeight, this.Maze_obj.getEnd_position().getRowIndex() * cellWidth, cellHeight, cellWidth);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                */



    public String getImageFileNameWall() {
        return ImageFileNameWall.get();
    }

    public String getImageFileStart() {
        return ImageFileStart.get();
    }

    public String getImageFileEnd() {
        return ImageFileEnd.get();
    }
    public String getImageFileTrail() {
        return this.ImageFileNameTrail.get();
    }

    public void setImageFileEnd(String imageFileEnd) {
        this.ImageFileEnd.set(imageFileEnd);
    }

    public void setImageFileStart(String imageFileStart) {
        this.ImageFileStart.set(imageFileStart);
    }

    public void setImageFileNameWall(String imageFileNameWall) {
        this.ImageFileNameWall.set(imageFileNameWall);
    }

    public void setImageFileTrail(String imageFileTrail) {
        this.ImageFileNameTrail.set(imageFileTrail);
    }

    public String getImageFileNameCharacter() {
        return ImageFileNameCharacter.get();
    }

    public void setImageFileNameCharacter(String imageFileNameCharacter) {
        this.ImageFileNameCharacter.set(imageFileNameCharacter);
    }


    public void switch_Solution_status(){
        if(this.solution_flag==true)
            this.solution_flag=false;
        else
            this.solution_flag=true;
    }
}
