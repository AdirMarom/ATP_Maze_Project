package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

/**
 * ISearchable implements ,
 * maze represented in collection of position object
 */

public class SearchableMaze implements ISearchable {
    private  Maze maze;
    private  int cross_cost;
    private  int straight_cost;


    /**
     *
     * @param _maze Maze a searchable object
     */
    public SearchableMaze(Maze _maze){
        if (_maze!=null)
            maze = _maze;
        this.cross_cost=15;
        this.straight_cost=10;
    }

    /**
     *
     * @return cross cost
     */
    public int getCross_cost() { return cross_cost; }

    /**
     *
     * @return Straight cost
     */
    public int getStraight_cost() { return straight_cost; }

    /**
     *
     * @param cross_cost
     */
    public void setCross_cost(int cross_cost) { this.cross_cost = cross_cost; }

    /**
     *
     * @param straight_cost
     */
    public void setStraight_cost(int straight_cost) {this.straight_cost = straight_cost;}

    /**
     *
     * @return AState object ,hold the start
     */
    public AState getStartState(){ return new MazeState(maze.getStart_position()); }

    /**
     *
     * @returnAState object ,hold the END
     */
    public AState getGoalState(){ return new MazeState(maze.getEnd_position()); }

    /**
     *
     * @param x row index
     * @param y col index
     * @return true if in frame
     */
    private boolean inBound(int x, int y ){
        if(this.maze.getNumOfRows()<=x || x<0)
            return false;
        if(this.maze.getNumOfColumns()<=y || y<0)
            return false;
        return true;
    }

    /**
     *
     * @param myPos Maze index
     * @return array of all the possibility move from this index
     */
    private  ArrayList<Position> generate_all_position( MazeState myPos){
        ArrayList<Position> myNeighbor_pos= new ArrayList<Position>(8);
        //12 o'clock
        myNeighbor_pos.add(0,new Position(myPos.getRowIndex() - 1,myPos.getColIndex()));
        //14 o'clock
        myNeighbor_pos.add(1,new Position(myPos.getRowIndex() - 1,myPos.getColIndex() + 1));
        //15 o'clock
        myNeighbor_pos.add(2,new Position(myPos.getRowIndex() ,myPos.getColIndex() + 1));
        //17 o'clock
        myNeighbor_pos.add(3,new Position(myPos.getRowIndex() + 1,myPos.getColIndex() + 1));
        //18 o'clock
        myNeighbor_pos.add(4,new Position(myPos.getRowIndex() + 1,myPos.getColIndex()));
        //20 o'clock
        myNeighbor_pos.add(5,new Position(myPos.getRowIndex() + 1,myPos.getColIndex() - 1));
        //21 o'clock
        myNeighbor_pos.add(6,new Position(myPos.getRowIndex() ,myPos.getColIndex() - 1));
        //23 o'clock
        myNeighbor_pos.add(7,new Position(myPos.getRowIndex() -1,myPos.getColIndex() -1));

        return myNeighbor_pos;
    }

    /**
     *
     * @param p current position
     * @param father_state cureent state
     * @param myNeighbor_state  array of all the possibility move from this index
     */
    private void Straight_move(Position p,AState father_state,ArrayList<AState> myNeighbor_state){
        if(!maze.validPosition(p))
            return;
        AState NewState = new MazeState(p);
        NewState.setCost(father_state.getCost() + this.getStraight_cost());
        myNeighbor_state.add(NewState);

    }

    /**
     *
     * @param p current position
     * @param pass_through_p1 index to pass to cross
     * @param pass_through_p2 index to pass to cross
     * @param father_state cureent state
     * @param myNeighbor_state  array of all the possibility move from this index
     */
    private void cross_move(Position p,Position pass_through_p1,Position pass_through_p2,AState father_state,ArrayList<AState> myNeighbor_state){
        if(!maze.validPosition(p) || !(maze.validPosition(pass_through_p1)||maze.validPosition(pass_through_p2)))
            return;
        AState NewState = new MazeState(p);
        NewState.setCost(father_state.getCost()+this.getCross_cost());
        myNeighbor_state.add(NewState);
    }

    /**
     *
     * @param state current position
     * @return  array of all the-- *valid* -- possibility move from this index
     */
    public ArrayList<AState> getAllPossibleStates(AState state) {
        if (state==null)
            return null;

        ArrayList<AState> myNeighbor= new ArrayList<AState>(8);
        ArrayList<Position> myNeighbor_position= new ArrayList<Position>(8);
        MazeState myPos = (MazeState) state;
        myNeighbor_position=generate_all_position(myPos);

        //12 o'clock
        if( inBound(myPos.getRowIndex() - 1,myPos.getColIndex())) { this.Straight_move(myNeighbor_position.get(0),state,myNeighbor);}

        //14 o'clock
        if(inBound(myPos.getRowIndex() - 1,myPos.getColIndex() + 1)  )
            this.cross_move(myNeighbor_position.get(1),myNeighbor_position.get(0),myNeighbor_position.get(2),state,myNeighbor);

        //15 o'clock
        if(inBound(myPos.getRowIndex() ,myPos.getColIndex() + 1)) {this.Straight_move(myNeighbor_position.get(2),state,myNeighbor);}

        //17 o'clock
        if (inBound(myPos.getRowIndex() + 1,myPos.getColIndex() + 1))
            this.cross_move(myNeighbor_position.get(3),myNeighbor_position.get(2),myNeighbor_position.get(4),state,myNeighbor);

        //18 o'clock
        if( inBound(myPos.getRowIndex() + 1,myPos.getColIndex() ) ) { this.Straight_move(myNeighbor_position.get(4),state,myNeighbor);}

        //20 o'clock
        if(inBound(myPos.getRowIndex() + 1,myPos.getColIndex() - 1)  )
            this.cross_move(myNeighbor_position.get(5),myNeighbor_position.get(4),myNeighbor_position.get(6),state,myNeighbor);

        //21 o'clock
        if(inBound(myPos.getRowIndex() ,myPos.getColIndex() - 1) ) { this.Straight_move(myNeighbor_position.get(6),state,myNeighbor);}

        //23 o'clock
        if( inBound(myPos.getRowIndex() -1,myPos.getColIndex() -1))
            this.cross_move(myNeighbor_position.get(7),myNeighbor_position.get(6),myNeighbor_position.get(0),state,myNeighbor);

        return myNeighbor;
    }
}
