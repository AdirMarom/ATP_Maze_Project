import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.SearchableMaze;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JUnitTestingBestFirstSearch {

    @Test
    void getName() {

        BestFirstSearch bestTest=new BestFirstSearch();
        assertEquals(bestTest.getName(),"Best First Search");
    }

    @Test
    void solve() {

        BestFirstSearch bestTest = new BestFirstSearch();

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



        assertNotEquals(bestTest.solve(Smaze1).getSolutionSize(),0);
        assertNotEquals(bestTest.getNumberOfNodesEvaluated(),0);
        assertNotEquals(bestTest.solve(Smaze2).getSolutionSize(),0);
        assertNotEquals(bestTest.getNumberOfNodesEvaluated(),0);
        assertNotEquals(bestTest.solve(Smaze3).getSolutionSize(),0);
        assertNotEquals(bestTest.getNumberOfNodesEvaluated(),0);
        assertNotEquals(bestTest.solve(Smaze4).getSolutionSize(),0);
        assertNotEquals(bestTest.getNumberOfNodesEvaluated(),0);
        assertNotEquals(bestTest.solve(Smaze5).getSolutionSize(),0);
        assertNotEquals(bestTest.getNumberOfNodesEvaluated(),0);

        assertNotEquals(bestTest.solve(Smaze1).getSolutionCost(),0);
        assertNotEquals(bestTest.solve(Smaze1).getSolutionCost(),0);
        assertNotEquals(bestTest.solve(Smaze1).getSolutionCost(),0);
        assertNotEquals(bestTest.solve(Smaze1).getSolutionCost(),0);
        assertNotEquals(bestTest.solve(Smaze1).getSolutionCost(),0);

        assertNotNull(bestTest.solve(Smaze1).getSolutionPath());
        assertNotNull(bestTest.solve(Smaze2).getSolutionPath());
        assertNotNull(bestTest.solve(Smaze3).getSolutionPath());
        assertNotNull(bestTest.solve(Smaze4).getSolutionPath());
        assertNotNull(bestTest.solve(Smaze5).getSolutionPath());
        }
    }
