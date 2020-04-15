package algorithms.mazeGenerators;

/**
 * abstract class inheritance interface of IMazeGenerator
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    /**
     * @param rows
     * @param columns
     * @return time to generate maze in size (rows,columns)
     */
    public long measureAlgorithmTimeMillis(int rows,int columns){
        if(rows<=2 ||columns <=0)
            return 0;
        long beforetime=System.currentTimeMillis();
        generate(rows,columns);
        long aftertime=System.currentTimeMillis();
        return (aftertime-beforetime);
    }
}
