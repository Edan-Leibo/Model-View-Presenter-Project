/**
 * 
 */
package view;

import java.io.File;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;


/**
 * this a representation of the character that will be on a game board
 * 
 * @author Edan
 * @version 1.0
 */
public class GameCharacter {
	private int floor;
	private int row;
	private int col;
	
	/**
	 * this methods construct a new character with the given position
	 * 
	 * @param floor	the floor position
	 * @param row the row position
	 * @param col the column position
	 */
	public GameCharacter(int floor, int row, int col){
		this.floor=floor;
		this.row=row;
		this.col=col;
	}
	
	
	/**
	 * this method order the game character to paint itself
	 * 
	 * @param e the PaintEvent
	 * @param w the width
	 * @param h the height
	 */
	public void paint(PaintEvent e, int w, int h){
		//e.gc.setForeground(new Color(null, 100,100,100));
		//e.gc.drawOval(, h*row, w, h);
		Image image = new Image(null,"resources"+File.separator+"pumba.gif");
		e.gc.drawImage(image,0,0,150,121,w*col,h*row, w, h);
	}

	
	/**
	 * A getter for the floor
	 * 
	 * @return the floor
	 */
	public int getFloor() {
		return floor;
	}

	/**
	 * A getter for the row
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * A getter for the column
	 * 
	 * @return the column
	 */
	public int getCol() {
		return col;
	}

	/**
	 * A setter for the floor
	 * @param floor the floor to set
	 */
	public void setFloor(int floor) {
		this.floor = floor;
	}

	/**
	 * A setter for the row
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * A setter for the column
	 * @param col the column to set
	 */
	public void setCol(int col) {
		this.col = col;
	}

}
