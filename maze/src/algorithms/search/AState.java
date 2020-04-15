package algorithms.search;

/**
 * hold the representation of the searching object
 */
public abstract class AState {

    private String state;
    private int Cost;
    private  AState cameFrom;
    private boolean visited;

    /**
     * constructor given string describe the state
     * @param name
     */
    public AState(String name) {
        this.state=name;
        this.visited=false;
        this.Cost=0;
        this.cameFrom=null;
    }

    /**
     * enpty constructor
     */
    public AState(){
        this.visited=false;
        this.Cost=0;
        this.cameFrom=null;
    }

    /**
     * guarantee equals fanc
     * @param o, AState
     * @return true or false
     */
    public abstract boolean equals(AState o);

    /**
     *
     * @return true if we visit in this stat
     */
    public boolean getVisited(){ return this.visited; }

    /**
     *swith visit field to true
     */
    public void switchVisited() { this.visited = true; }

    /**
     * guarantee print func
     */
    public abstract void print();

    /**
     *
     * @return father of this stat
     */
    public AState getCameFrom() { return cameFrom;}

    /**
     *
     * @return the cost fron the start to stat
     */
    public int getCost() {return Cost;}

    /**
     *set the cost fron the start to stat
     * @param cost
     */
    public void setCost(int cost) { Cost = cost; }

    /**
     *set the father of this stat
     * @param cameFrom
     */
    public void setCameFrom(AState cameFrom) {this.cameFrom = cameFrom;}

    /**
     * compare by two state by cost
     * @param A2 state
     * @return -1- A2 bigger 0-equal ,+ A2 is small
     */
    public int compareTo(AState A2){ return this.getCost()-A2.getCost(); }

    /**
     *set state field
     * @param state
     */
    public void setState(String state) {this.state = state; }

    /**
     *representation of state we use the representation of child;
     * @return
     */
    public String toString(){ return this.state; }

}
