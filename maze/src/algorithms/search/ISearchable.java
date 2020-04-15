package algorithms.search;
import java.util.ArrayList;

/**
 * interface of object that can turn on them search algorithms
 */
    public interface ISearchable {
    /**
     *
     * @return start position
     */
    AState getStartState();

    /**
     *
     * @return goal position
     */
    AState getGoalState();

    /**
     *
     * @param state
     * @return array of all the valid neighbours
     */
    ArrayList<AState> getAllPossibleStates(AState state);


    }
