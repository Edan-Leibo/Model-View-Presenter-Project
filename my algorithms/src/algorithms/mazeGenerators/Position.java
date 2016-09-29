package algorithms.mazeGenerators;

import java.io.Serializable;

/**
* <h1>Position</h1>
* 
* a bean class encapsulates a position
* 
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/
public class Position implements Serializable {

	private static final long serialVersionUID = 1L;
	public int x;
	public int y;
	public int z;
	/**
	 * this method is a constructor for a position
	 * 
	 * @param z - the z dimension
	 * @param y - the y dimention
	 * @param x - the x dimension
	 * @return nothing
	 */	
	public Position(int z, int y, int x) {		
		this.z = z;
		this.y = y;
		this.x = x;
	}
	/**
	 * this method is a copy constructor for a position
	 * 
	 * @param other - other position
	 * @return nothing
	 */		
	public Position(Position other) {
		super();
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
	}
	/**
	 * this method generates a string describing the position
	 * 
	 * @return a string that contains the position
	 */	
	@Override
	public String toString() {
		return "{" + z + "," + y + "," + x+"}";
	}
	/**
	 * this method compares between two positions
	 * 
	 * @return true if the two positions are equal, false otherwise
	 */	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Position))
			throw new IllegalArgumentException("Object must be position");
		
		Position p = (Position)obj;
		return x == p.x && y == p.y & z==p.z;			
	}
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return the z
	 */
	public int getZ() {
		return z;
	}
	/**
	 * @param z the z to set
	 */
	public void setZ(int z) {
		this.z = z;
	}
}
