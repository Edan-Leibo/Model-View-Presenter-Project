package view;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
* <h1>View interface</h1>
* This interface represents a general view
* It has the functionality that a view must have.*
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/

public interface View {
	/**
	 * this method activates the view 
	 */
	void start();
	/**
	 * this method uses to notify the view that the maze 'name' was created 
	 * 
     * @param name - the maze name
	 */
	void notifyMazeIsReady(String name);
	/**
	 * this method uses to display the maze 
	 * 
     * @param maze - the maze name
	 */
	void displayMaze(Maze3d maze);	
	/**
	 * this method uses to display the content of path 
	 * 
     * @param path - the given path
     * @param files - the files and directories in the given path
	 */
	void displayFilesInPath(String path, String[] files);
	/**
	 * this method uses to notify the view that a bad argument was inserted 
	 */
	void displayBadArguments();
	/**
	 * this method uses to display a plain in the maze
	 * 
     * @param index - the line
     * @param axis - the axis in which the maze will be cut by
     * @param name - the name of the maze
     * @param maze2d - the maze to be displayed
	 */
	void displayCrossSectionMaze(int index, String axis, String name, int[][] maze2d);
	/**
	 * this method uses to display an error massage
	 * 
     * @param error - the error to be displayed 
	 */
	void displayErrorMessage(String error);
	/**
	 * this method uses to notify the view that the maze 'name' was saved
	 * 
     * @param name - the maze name
	 */
	void notifyMazeSaved(String name);
	/**
	 * this method uses to notify that the maze 'name' was loaded 
	 * 
     * @param name - the maze name
	 */
	void notifyMazeLoaded(String name);
	/**
	 * this method uses to notify that the solution for the maze 'name' is ready
	 * 
     * @param name - the maze name
	 */
	void notifySolutionIsReady(String name);
	/**
	 * this method uses to display the maze 'name' solution
	 * 
     * @param name - the maze name
     * @param sol - the solution for the maze
	 */
	void displaySolution(String name, Solution<Position> sol);
	/**
	 * this method uses to display a message that came from the presenter
	 * 
     * @param msg - the massage to display
	 */
	void displayMessage(String msg);	

}

