package algorithms.search;
///just for print
import algorithms.mazeGenerators.Maze;
////
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 */
public class Solution implements Serializable {

    private ArrayList<AState> path_solution;
   private ArrayList<MazeState> sol_for_debbage;

    /**
     * init array for Astate
     */
    public Solution(){

        this.path_solution =new ArrayList<AState>();
        this.sol_for_debbage =new ArrayList<MazeState>();
    };

    /**
     *
     * @return array contain all the path
     */
    public ArrayList<AState> getSolutionPath(){ return this.path_solution; }

    /**
     * add state object to sol
     * @param state
     */
    public void add(AState state){ this.path_solution.add(0,state);}

    /**
     * return the cost for the path
     * @return
     */
    public int getSolutionCost(){
        if (this.path_solution.size()==0)
            return 0;
        return this.path_solution.get(this.path_solution.size()-1).getCost();
    }

    public void print_path() {
        for (int i = this.path_solution.size() - 1; i >= 0; i--) {
            this.path_solution.get(i).print();
        }

    }

    public int getSolutionSize(){ return this.path_solution.size();}

    private void convert () {

        for(int i = this.path_solution.size()-1; i>0 ; i--){
            this.sol_for_debbage.add((MazeState) this.path_solution.get(i));
        }



    }

    public void print_toDebug(Maze M){

        convert();
        for(int i = 0; i < M.getTheMaze().length; ++i) {
            for(int j = 0; j < M.getTheMaze()[i].length; ++j) {

                if (i == M.getStart_position().getRowIndex() && j == M.getStart_position().getColumnIndex()) {
                    System.out.print(" \u001b[46m"+"S");
                } else if (i == M.getEnd_position().getRowIndex() && j == M.getEnd_position().getColumnIndex()) {
                    System.out.print(" \u001b[46m"+"E");
                } else if ( M.getTheMaze()[i][j] == 1) {
                    System.out.print(" \u001b[40m ");
                }
                else {
                    for(int x = 0; x<this.sol_for_debbage.size() ; x++){
                        if(this.sol_for_debbage.get(x).getRowIndex()==i && this.sol_for_debbage.get(x).getColIndex()==j) {
                            System.out.print(" \u001b[43m ");
                            break;
                        }
                        else if(x==this.sol_for_debbage.size()-1)
                            System.out.print(" \u001b[107m ");
                    }

                }
            }

            System.out.println(" \u001b[107m");
        }
        System.out.println("");
        System.out.println();
    }


}
