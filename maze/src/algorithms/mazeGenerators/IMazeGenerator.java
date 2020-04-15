package algorithms.mazeGenerators;

public interface IMazeGenerator {

    /**
     * @param rows
     * @param columns
     * @return return maze object in size (row X columns)
     */
     Maze generate(int rows, int columns);

    /**
     *
     * @param rows
     * @param columns
     * @return mesure the time to generate a maze
     */
     long measureAlgorithmTimeMillis(int rows, int columns);

}
