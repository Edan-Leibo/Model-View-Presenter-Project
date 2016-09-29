package algorithms.mazeGenerators;
/**
* <h1>Maze3dGeneratorBase</h1>
* 
* an abstract generator class which calculates the time of creating a new maze
* 
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/
public abstract class Maze3dGeneratorBase implements Maze3dGenerator {

	@Override
	/**
	 * this method is a common implementation for all concrete generators
	 * that measure the time to create the maze
	 * 
	 * @param floors - the amount of floors
	 * @param rows - the amount of rows
	 * @param cols - the amount of coloumns
	 * @return the time to create the maze
	 */	
	public String measureAlgorithmTime(int floors, int rows, int cols){
		long begin=System.currentTimeMillis();
		generate(floors,rows,cols);
		long end=System.currentTimeMillis();
		long diff= end- begin;
		return String.valueOf(diff);
	}
}
