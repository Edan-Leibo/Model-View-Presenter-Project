package view;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Position;

public abstract class Gameboard extends Canvas {

	public Gameboard(Composite parent, int style) {
		super(parent, style);
	}

	public abstract void setGameBoard(int[][] gameBoard);

	/**
	 * @param gameCharacter the gameCharacter to set
	 */
	public abstract void setGameCharacter(GameCharacter gameCharacter);
	
	/**
	 * @param endCol the endCol to set
	 */
	public abstract void setEndPosition(int z, int y, int x);
	
	/**
	 * @return the CharacterFloor
	 */
	public abstract int getCharacterFloor();

	/**
	 * @param maze the maze to set
	 */
	public abstract void setData(Object data);

	/**
	 * @return the name
	 */
	public abstract String getGameboardName();

	/**
	 * @param name the name to set
	 */
	public abstract void setGameboardName(String name);
	
	/**
	 * @param path the path of the solution thats needs to be displayed
	 */
	public abstract void showSolution(ArrayList<Position> path);
	
	/**
	 * @param path the path of the solution, will create a hint using that solution
	 */
	public abstract void showHint(ArrayList<Position> path);

	public abstract void moveUp();

	public abstract void movePageUp();

	public abstract void movePageDown();

	public abstract void moveDown();

	public abstract void moveLeft();

	public abstract void moveRight();
	
}
