package algorithms.search;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *Best-first search is a search algorithm which explores a graph by expanding the most promising node chosen according to a specified rule.
 */
//adir change
public class BestFirstSearch extends BreadthFirstSearch {

    public String getName() { return "Best First Search"; }

    /**
     * @param domain (Searchable Object)
     * @return the cheepest Solution with bfs searching from start state to end state
     * solve it with Queue with prioritize condition
     */
    public Solution solve(ISearchable domain) {
        if(domain==null )
            return null;
        this.setNumOfEvaluated(0);
        PriorityQueue<AState> HeapOpen = new PriorityQueue<AState>((AState o1, AState o2) -> (o1.compareTo(o2)));
        HashMap<String,AState> hashMap= new HashMap<String,AState>();

        return bfsSolve(domain, domain.getStartState(), domain.getGoalState(),HeapOpen,hashMap);
    }


}

