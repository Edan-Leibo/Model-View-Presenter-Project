package view;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Position;

/**
 * a generic 3D game board that can be used on different games
 * @author Edan
 * @version 1.0
 */
public abstract class Gameboard extends Canvas {

	/**
	 * @param parent the parent to set in the canvas
	 * @param style the style to set in the canvas
	 */
	public Gameboard(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * this is a setter of the gameBoard in a 2D representation
	 * 
	 * @param gameBoard the game board of the game in a 2D representation
	 */
	public abstract void setGameBoard(int[][] gameBoard);

	/**
	 * this is a setter for the gameCharacter
	 * 
	 * @param gameCharacter the gameCharacter to set
	 */
	public abstract void setGameCharacter(GameCharacter gameCharacter);
	
	/**
	 *  this is a setter for the end position
	 * @param z the z value
	 * @param y the y value
	 * @param x the x value
	 */
	public abstract void setEndPosition(int z, int y, int x);
	
	/**
	 * this is a getter for the Character floor
	 * 
	 * @return the CharacterFloor
	 */
	public abstract int getCharacterFloor();

	/**
	 * This method use in order to give the game board satellite data
	 * 
	 * @param data the maze to set
	 */
	public abstract void setData(Object data);

	/**
	 * this is a getter method for GameboardName
	 * 
	 * @return the name
	 */
	public abstract String getGameboardName();

	/**
	 * this is a setter method for GameboardName
	 * 
	 * @param name the name to set
	 */
	public abstract void setGameboardName(String name);
	
	/**
	 * use this method in order to tell the board to show the solution
	 * 
	 * @param path the path of the solution thats needs to be displayed
	 */
	public abstract void showSolution(ArrayList<Position> path);
	
	/**
	 * use this method in order to tell the board to show a hint
	 * 
	 * @param path the path of the solution, will create a hint using that solution
	 */
	public abstract void showHint(ArrayList<Position> path);

	/**
	 * this method tells the board to move the character
	 * up on the board
	 */
	public abstract void moveUp();

	/**
	 * this method tells the board to move the character
	 * to the upper floor on the board
	 */
	public abstract void movePageUp();

	/**
	 * this method tells the board to move the character
	 * to the lower floor on the board
	 */
	public abstract void movePageDown();

	/**
	 * this method tells the board to move the character
	 * down on the board
	 */
	public abstract void moveDown();

	/**
	 * this method tells the board to move the character
	 * left on the board
	 */
	public abstract void moveLeft();

	/**
	 * this method tells the board to move the character
	 * right on the board
	 */
	public abstract void moveRight();
	
}
