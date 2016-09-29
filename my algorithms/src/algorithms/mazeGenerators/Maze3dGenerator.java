package algorithms.mazeGenerators;
/**
* <h1>Maze3dGenerator</h1>
* 
* An interface for a general generators
* 
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/
public interface Maze3dGenerator {
	Maze3d generate(int floors, int rows, int cols);
	/**
	 * this method uses to measure the time to create a maze
	 * 
	 * @param floors - the amount of floors
	 * @param rows - the amount of rows
	 * @param cols - the amount of coloumns
	 * @return the time to create the maze
	 */	
	String measureAlgorithmTime(int floors, int rows, int cols);

}
