
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.SearchableMaze;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JUnitTestingBreadthFirstSearchTest {

    @Test
    void getName() {

        BreadthFirstSearch bestTest=new BreadthFirstSearch();
        assertEquals(bestTest.getName(),"Breadth First Search");
    }

    @Test
    void solve() {

        BreadthFirstSearch bfsTest = new BreadthFirstSearch();

        AMazeGenerator TestMaze=new MyMazeGenerator();

        Maze maze1=TestMaze.generate(2,2);
        Maze maze2=TestMaze.generate(3,2);
        Maze maze3=TestMaze.generate(2,11);
        Maze maze4=TestMaze.generate(91,50);
        Maze maze5=TestMaze.generate(1000,1000);

        SearchableMaze Smaze1=new SearchableMaze(maze1) ;
        SearchableMaze Smaze2=new SearchableMaze(maze2) ;
        SearchableMaze Smaze3=new SearchableMaze(maze3) ;
        SearchableMaze Smaze4=new SearchableMaze(maze4) ;
        SearchableMaze Smaze5=new SearchableMaze(maze5) ;

        assertNotEquals(bfsTest.solve(Smaze1).getSolutionSize(),0);
        assertNotEquals(bfsTest.getNumberOfNodesEvaluated(),0);
        assertNotEquals(bfsTest.solve(Smaze2).getSolutionSize(),0);
        assertNotEquals(bfsTest.getNumberOfNodesEvaluated(),0);
        assertNotEquals(bfsTest.solve(Smaze3).getSolutionSize(),0);
        assertNotEquals(bfsTest.getNumberOfNodesEvaluated(),0);
        assertNotEquals(bfsTest.solve(Smaze4).getSolutionSize(),0);
        assertNotEquals(bfsTest.getNumberOfNodesEvaluated(),0);
        assertNotEquals(bfsTest.solve(Smaze5).getSolutionSize(),0);
        assertNotEquals(bfsTest.getNumberOfNodesEvaluated(),0);

        assertNotEquals(bfsTest.solve(Smaze1).getSolutionCost(),0);
        assertNotEquals(bfsTest.solve(Smaze2).getSolutionCost(),0);
        assertNotEquals(bfsTest.solve(Smaze3).getSolutionCost(),0);
        assertNotEquals(bfsTest.solve(Smaze4).getSolutionCost(),0);
        assertNotEquals(bfsTest.solve(Smaze5).getSolutionCost(),0);

        assertNotNull(bfsTest.solve(Smaze1).getSolutionPath());
        assertNotNull(bfsTest.solve(Smaze2).getSolutionPath());
        assertNotNull(bfsTest.solve(Smaze3).getSolutionPath());
        assertNotNull(bfsTest.solve(Smaze4).getSolutionPath());
        assertNotNull(bfsTest.solve(Smaze5).getSolutionPath());

    }
}
