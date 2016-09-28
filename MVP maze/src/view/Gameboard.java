package view;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import algorithms.mazeGenerators.Maze3d;
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
