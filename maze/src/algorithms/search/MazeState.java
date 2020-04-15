package algorithms.search;
import algorithms.mazeGenerators.Position;

/**
 * maze position ,contain row index and column index
 */
public class MazeState extends AState {

    private int x;
    private int y;

    /**
     *
     * @param point Position on maze
     */
    public MazeState(Position point){
        super();
        if(point!=null ) {
            this.x = point.getRowIndex();
            this.y = point.getColumnIndex();
            setState(point.toString());
        }
    }

    public int getRowIndex() { return x; }

    public int getColIndex() { return y; }

    /**
     *
     * @param o, AState
     * @return true if coordinate egual
     */
    @Override
    public boolean equals(AState o){
        if(o==null)
            return false;
       int _x=((MazeState)o).getRowIndex();
       int _y=((MazeState)o).getColIndex();
       if(this.x==_x && this.y==_y)
           return true;
       return false;
    }

    /**
     * point representation
     */
    public void print(){ System.out.println("{"+this.x+" , "+this.y+"}"); }




}


