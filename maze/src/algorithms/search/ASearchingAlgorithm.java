package algorithms.search;

/**
 * all search algorithm
 */
public  abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    protected int numOfSteps;

    public ASearchingAlgorithm(){
        this.numOfSteps=0;
    }

    /**
     * abstract function
     * @param domain search object
     * @return solution after the algorithm searching
     */
    public abstract Solution solve(ISearchable domain);

    /**
     *
     * @param state-posion on search object
     * @param sol-Solution
     * @return update Solution
     */
    public Solution updateSol (AState state, Solution sol) {

        AState tmpState = state;
        while (tmpState != null) {
            sol.add(tmpState);
            tmpState = tmpState.getCameFrom();

        }
        return sol;
    }

    /**
     *
     * @return  number that take to algirithn to find solution
     */
    public int getNumberOfNodesEvaluated() {
        return numOfSteps;
    }

    /**
     *
     * @param numOfSteps that take to algirithn to find solution
     */
    public void setNumOfEvaluated(int numOfSteps) {
        this.numOfSteps = numOfSteps;
    }
}
