package algorithms.search;

import java.util.*;
import java.util.Queue;

/**
 * Breadth-first search (BFS) is an algorithm for traversing or searching tree or graph data structures.
 */
//ben change
public class BreadthFirstSearch extends ASearchingAlgorithm {
    /**
     * enpty constructor
     */
    public  BreadthFirstSearch(){}

    /**
     *
     * @param domain
     * @return the solution path from Start State to the End State
     */
    public Solution solve(ISearchable domain){
        if(domain==null )
            return null;
        this.setNumOfEvaluated(0);
        Queue<AState> queue=new LinkedList<AState>();
        HashMap<String,AState> hashMap= new HashMap<String,AState>();
        Solution sol= bfsSolve(domain,domain.getStartState(),domain.getGoalState(),queue,hashMap);
        return sol;
    }

    @Override
    public String getName() { return "Breadth First Search"; }

    /**
     *
     * @param domain (Searchable Object)
     * @param stateStart
     * @param statEnd
     * @param queue (Normal Queue without prioritize condition)
     * @param hashMap (storage all states that found in the way)
     * @return the shortest Solution of bfs searching from start state to end state
     */
    protected Solution bfsSolve(ISearchable domain, AState stateStart, AState statEnd, Queue<AState> queue, HashMap<String,AState> hashMap){
        Solution sol =new Solution();
        AState currNeighbours;
        queue.add(stateStart);
        hashMap.put(stateStart.toString(),stateStart);
        //stop if visit all possible neighbours
        while(!queue.isEmpty()){
            this.numOfSteps+=1;
            AState node=queue.remove();
            if (!node.getVisited()) {
                node.switchVisited();
            }
            if(node.equals(statEnd)){
                updateSol(node,sol);
                return sol;
            }
            ArrayList<AState> neighbours= domain.getAllPossibleStates(node);
            if(neighbours!=null ) {
                for (int i = 0; i < neighbours.size(); i++) {
                    if (neighbours.get(i) != null) {
                        currNeighbours=neighbours.get(i);
                        if(!hashMap.containsKey(currNeighbours.toString())) {
                            initializationNeighbor(currNeighbours,hashMap,node);
                            queue.add(currNeighbours);
                        }
                        else
                            currNeighbours = hashMap.get(currNeighbours.toString());

                        if(currNeighbours.equals(statEnd)){
                            this.numOfSteps++;
                            updateSol(currNeighbours,sol);
                            return sol;
                        }
                    }
                }
            }
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
