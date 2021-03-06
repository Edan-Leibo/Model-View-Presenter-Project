package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
/**
* <h1>Model interface</h1>
* This interface represents a general model
* It has the functionality that a model must have.*
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/
public interface Model {
	
	/**
	 * this method uses to display the content of path 
	 * 
     * @param path the given path
	 * @return a string array of all the directories and files inside path
	 */
	String[] getDirsAndFilesInPath(String path);
	
	/**
	 * this method uses to generate a maze with the specified
	 *  dimensions by a specific algorithm 
	 * 
     * @param name name of the maze
     * @param floors number of floors in the maze
     * @param rows number of rows in the maze
     * @param cols number of columns in the maze
     * @param type the algorithm by which the maze is generated 
	 */
	void generateMaze(String name,int floors, int rows, int cols, String type);
	
	/**
	 * this method returns the maze 'name'
	 * 
     * @param name the maze name
	 * @return a maze with the specified name
	 */
	Maze3d getMaze(String name);
	
	/**
	 * this method shuts down the model
	 */
	void exit();
	
	/**
	 * this method gets a cross section by specific axis
	 * 
	 * @param index the number of line
	 * @param axis the axis to cut by
	 * @param name the name of the requested maze
	 * @return the requested cross section
	 */
	int[][] getCrossSection(int index, String axis, String name);
	
	/**
	 * this method saves the maze
	 * 
     * @param name the name of the maze
     * @param filename the name of the file in which the maze will be saved
	 */
	void saveMaze(String name, String filename);
	
	/**
	 * this method loads a maze from a file
	 * 
     * @param filename the name of the file
     * @param name the name of the maze
	 * @return True in case saving was successful, False- otherwise
	 */
	boolean loadMaze(String filename, String name);
	
	/**
	 * this method solves the maze and stores the solution inside the model
	 * 
     * @param name the name of the maze
     * @param algorithm String that indicates the algorithm to be used (BFS/DFS)
	 */
	void solveMaze(String name, String algorithm);
	
	/**
	 * this method yields the solution according to maze 'name'
	 * 
     * @param name the name of the maze
	 * @return the maze 'name's solution stored in the model
	 */
	Solution<Position> getSolution(String name);
}
