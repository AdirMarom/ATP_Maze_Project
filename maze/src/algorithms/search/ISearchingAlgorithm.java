package algorithms.search;

/**
 * every searching  algorithm
 */
public interface ISearchingAlgorithm {
     /**
      *
      * @param domain
      * @return object contain Solution
      */
     Solution solve(ISearchable domain);

     /**
      *
      * @return name of searching algorithm
      */
     String getName();

     /**
      *
      * @return number of step on searching
      */
     int getNumberOfNodesEvaluated();

}
