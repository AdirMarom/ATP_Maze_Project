package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures.
 */
public class DepthFirstSearch extends ASearchingAlgorithm {

    public  DepthFirstSearch(){}

    /**
     *
     * @param domain
     * @return the solution path from Start State to End State
     */
    public Solution solve(ISearchable domain){
        if(domain==null )
            return null;
        this.setNumOfEvaluated(0);
        HashMap<String,AState> hashMap= new HashMap<String,AState>();
        return dfsSolve(domain,domain.getStartState(),domain.getGoalState(),hashMap);
    }
    @Override
    /**
     * name of class
     */
    public String getName() { return "Depth First Search"; }


    /**
     *
     * @param domain (Searchable Object)
     * @param stateStart
     * @param statEnd
     * @param hashMap (contains all the states in dfs path)
     * @return the Solution of dfs searching from start state to end state
     */
    private Solution dfsSolve(ISearchable domain, AState stateStart, AState statEnd,HashMap<String,AState> hashMap){
        Solution sol =new Solution();
        hashMap.put(stateStart.toString(),stateStart);

        Stack<AState> stack=new Stack<AState>();
        stack.add(stateStart);

        while(!stack.isEmpty()){
            this.numOfSteps+=1;
            AState node=stack.peek();
            if(node.equals(statEnd)){
                updateSol(node,sol);
                return sol;
            }
            if (!node.getVisited()) {
                node.switchVisited();
            }

            ArrayList<AState> neighbours= domain.getAllPossibleStates(node);
            AState pointer=null;
            if(neighbours!=null ) {
                for (int i = 0; i < neighbours.size(); i++) {
                    if (neighbours.get(i) != null) {
                        AState neighboor=neighbours.get(i);
                        if(!hashMap.containsKey(neighboor.toString())){
                            initializationNeighbor(neighboor,hashMap,stack.peek());
                            pointer=neighboor;
                            stack.add(neighboor);
                        }
                        else
                            continue;
                    }
                    break;
                }
            }
            if(pointer==null)
                stack.pop();

        }
        return sol;
    }

    /**
     * initialize the new neighbor that found
     * @param neighbor
     * @param hashMap
     * @param source
     */
    private void initializationNeighbor(AState neighbor,HashMap<String,AState> hashMap,AState source){
        neighbor.setCameFrom(source);
        neighbor.switchVisited();
        hashMap.put(neighbor.toString(),neighbor);
    }
}

