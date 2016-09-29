package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
* <h1>Maze3dGenerator</h1>
* 
* An interface for a general generators
* 
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/
public class Maze3d implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int sizeX;
	private int sizeY;
	private int sizeZ;
	
	private Position startPosition;
	private Position goalPosition;
	private int[][][] maze3d;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static final int FREE = 0;
	public static final int WALL = 1;
	public static int NUM_OF_OPTIONAL_MOVES = 6;
	
	/**
	 * This method is a constructor.
	 * When given the dimensions it creates a new maze 
	 * 
	 * @param z - the amount of floors
	 * @param y - the amount of rows
	 * @param x - the amount of coloumns
	 * @return 
	 */		
	public Maze3d(int z, int y, int x) {
		this.sizeZ = z*2+1;
		this.sizeY = y*2+1;
		this.sizeX = x*2+1;
		
		// Added borders of ones to the requested maze's Size
		this.maze3d = new int[z*2+1][y*2+1][x*2+1];    
		
		//initialize all the array with ones
		for (int i = 0; i < sizeZ; i++) {
			for (int j = 0; j < sizeY; j++) {
				for (int k = 0; k < sizeX; k++){
					maze3d[i][j][k]=1;
				}
			}
		}
		
	}
	/**
	 * This method is a constructor.
	 * When given a byte representation it creates a new maze 
	 * 
	 * @param arr - a byte representation of the array
	 * @return 
	 */		
	public Maze3d(byte[] arr) {
		int k = 0;
		this.sizeZ = arr[k++];
		this.sizeY = arr[k++];
		this.sizeX = arr[k++];
		maze3d = new int[sizeZ][sizeY][sizeX];		
		
		Position startPos = new Position(arr[k++],arr[k++], arr[k++]);
		this.setStartPosition(startPos);
		Position goalPos = new Position(arr[k++],arr[k++], arr[k++]);
		this.setGoalPosition(goalPos);
		
		for (int z = 0; z < sizeZ; z++) {
			for (int y = 0; y < sizeY; y++) {
				for (int x = 0; x < sizeX; x++) {
					maze3d[z][y][x] = arr[k++];
				}			
			}
		}
	}
	/**
	 * This method generates a byte representation 
 	 * of the maze 
	 * 
	 * @return byte representation of the maze
	 */		
	public byte[] toByteArray() {
		//The byte Array structure: sizes, start position, end position, maze
		ArrayList<Byte> arr = new ArrayList<Byte>();
		arr.add((byte)sizeZ);
		arr.add((byte)sizeY);
		arr.add((byte)sizeX);
		arr.add((byte)startPosition.z);
		arr.add((byte)startPosition.y);
		arr.add((byte)startPosition.x);
		arr.add((byte)goalPosition.z);
		arr.add((byte)goalPosition.y);
		arr.add((byte)goalPosition.x);
		
		for (int z = 0; z < sizeZ; z++){
			for (int y = 0; y < sizeY; y++) {
				for (int x = 0; x < sizeX; x++) {
					arr.add((byte)maze3d[z][y][x]);
				}
			}			
		}
		
		byte[] bytes = new byte[arr.size()];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte)arr.get(i);
		}
		return bytes;
	}
	/**
	 * This method is a getter for the x dimension
	 * 
	 * @return the x dimension
	 */	
	public int getSizeX() {
		return sizeX;
	}
	/**
	 * This method is a getter for the y dimension
	 * 
	 * @return the y dimension
	 */		
	public int getSizeY() {
		return sizeY;
	}
	/**
	 * This method is a getter for the z dimension
	 * 
	 * @return the z dimension
	 */	
	public int getSizeZ() {
		return sizeZ;
	}
	
	/**
	 * @param sizeX the sizeX to set
	 */
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}
	/**
	 * @param sizeY the sizeY to set
	 */
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
	/**
	 * @param sizeZ the sizeZ to set
	 */
	public void setSizeZ(int sizeZ) {
		this.sizeZ = sizeZ;
	}
	
	/**
	 * This method is a setter.
	 * Its sets a wall in a specific position
	 * 
	 * @param z - the z dimension
	 * @param y - the y dimension
	 * @param x - the x dimension
	 */	
	public void setWall(int z, int y, int x) {
		maze3d[z][y][x] = WALL;
	}
	/**
	 * This method is a setter.
	 * It sets a free space in a specific position
	 * 
	 * @param z - the z dimension
	 * @param y - the y dimension
	 * @param x - the x dimension
	 */	
	public void setFree(int z, int y, int x) {
		maze3d[z][y][x] = FREE;
	}
	/**
	 * This method is a getter.
	 * It returns the value of a a specific position
	 * 
	 * @param z - the z dimension
	 * @param y - the y dimension
	 * @param x - the x dimension
	 * @return the value
	 */	
	public int getValue(int z, int y, int x) {
		return maze3d[z][y][x];
	}
	/**
	 * This method generates a String that describes the maze

	 * @return the String that describes the maze
	 */	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("start ("+startPosition.z+","+startPosition.y+","+startPosition.x +")\n");
		sb.append("end ("+goalPosition.z+","+goalPosition.y+","+goalPosition.x +")\n");
		for (int i = 0; i < sizeZ; i++) {
			sb.append("floor number "+i +"\n");
			for (int j = 0; j < sizeY; j++) {
				for (int k = 0; k < sizeX; k++){
					sb.append(maze3d[i][j][k] + " ");
				}
			sb.append("\n");
			}

	}
		return sb.toString();
	}
	/**
	 * This method is a getter for the start position
	 * 
	 * @return the start position
	 */	
	public Position getStartPosition() {
		return startPosition;
	}
	/**
	 * This method is a setter.
	 * It sets the value of the start position
	 * 
	 * @param z - the z dimension
	 * @param y - the y dimension
	 * @param x - the x dimension
	 * @return the value
	 */	
	public void setStartPosition(Position startPosition) {
		this.startPosition = startPosition;
		setFree(startPosition.z, startPosition.y, startPosition.x);
	}
	/**
	 * This method is a getter for the goal position
	 * 
	 * @return the goal position
	 */	
	public Position getGoalPosition() {
		return goalPosition;
	}
	/**
	 * This method is a setter.
	 * It sets the value of the goal position
	 * 
	 * @param goalPosition- a goal position
	 * @return nothing
	 */	
	public void setGoalPosition(Position goalPosition) {
		this.goalPosition = goalPosition;
		setFree(goalPosition.z,goalPosition.y,goalPosition.x);
	}
	/**
	 * This method calculates all the possible moves from the given position
	 * 
	 * @param p- a position
	 * @return an array of all the position as Strings
	 */	
	public String[] getPossibleMoves(Position p) {
		String[] ans= new String[NUM_OF_OPTIONAL_MOVES];
		int i=0;
		
		Position p1=new Position(p);
		p1.x-=1;
		if (maze3d[p1.z][p1.y][p1.x]== Maze3d.FREE){
			p1.x-=1;		
			ans[i++]=p1.toString();
		}
		
		Position p2=new Position(p);
		p2.x+=1;
		if (maze3d[p2.z][p2.y][p2.x]== Maze3d.FREE){
			p2.x+=1;		
			ans[i++]=p2.toString();
		}
			
		Position p3=new Position(p);
		p3.y-=1;
		if (maze3d[p3.z][p3.y][p3.x]== Maze3d.FREE){
			p3.y-=1;
			ans[i++]=p3.toString();
		}
			
		Position p4=new Position(p);
		p4.y+=1;
		if (maze3d[p4.z][p4.y][p4.x]== Maze3d.FREE){
			p4.y+=1;
			ans[i++]=p4.toString();
		}
		
		Position p5=new Position(p);
		p5.z-=1;
		if (maze3d[p5.z][p5.y][p5.x]== Maze3d.FREE){
			p5.z-=1;
			ans[i++]=p5.toString();
		}
		
		Position p6=new Position(p);
		p6.z+=1;
		if (maze3d[p6.z][p6.y][p6.x]== Maze3d.FREE){
			p6.z+=1;
			ans[i++]=p6.toString();
		}
		return ans;
	}
	/**
	 * This method calculates all the possible moves from the given position
	 * 
	 * @param p- a position
	 * @return a list of all the position as positions
	 */	
	public List<Position> getPossiblePositions(Position p) {
		List<Position> ans= new ArrayList<Position>();
		
		Position p1=new Position(p);
		p1.x-=1;
		if (maze3d[p1.z][p1.y][p1.x]== Maze3d.FREE){
			p1.x-=1;		
			ans.add(p1);
		}
		
		Position p2=new Position(p);
		p2.x+=1;
		if (maze3d[p2.z][p2.y][p2.x]== Maze3d.FREE){
			p2.x+=1;		
			ans.add(p2);
		}
			
		Position p3=new Position(p);
		p3.y-=1;
		if (maze3d[p3.z][p3.y][p3.x]== Maze3d.FREE){
			p3.y-=1;
			ans.add(p3);
		}
			
		Position p4=new Position(p);
		p4.y+=1;
		if (maze3d[p4.z][p4.y][p4.x]== Maze3d.FREE){
			p4.y+=1;
			ans.add(p4);
		}
		
		Position p5=new Position(p);
		p5.z-=1;
		if (maze3d[p5.z][p5.y][p5.x]== Maze3d.FREE){
			p5.z-=1;
			ans.add(p5);
		}
		
		Position p6=new Position(p);
		p6.z+=1;
		if (maze3d[p6.z][p6.y][p6.x]== Maze3d.FREE){
			p6.z+=1;
			ans.add(p6);
		}
		return ans;
	}
	/**
	 * This method calculates a x cross section of the 3d mazes
	 * 
	 * @param i the number of line
	 * @return the 2d cross section
	 */		
	public int[][] getCrossSectionByX(int i)  throws IndexOutOfBoundsException {
//		i=i*2-1;
		if (i<0 || i>=sizeX) throw new IndexOutOfBoundsException();
		
		int[][] Xarray= new int[sizeZ][sizeY];
		for (int z=0;z<sizeZ;z++){
			for (int y=0;y<sizeY;y++){
				Xarray[z][y]=maze3d[z][y][i];
			}
		}
		return Xarray;	
	}
	/**
	 * This method calculates a y cross section of the 3d mazes
	 * 
	 * @param i the number of line
	 * @return the 2d cross section
	 */		
	public int[][] getCrossSectionByY(int i) throws IndexOutOfBoundsException {
//		i=i*2-1;
		if (i<0 || i>=sizeY) throw new IndexOutOfBoundsException();
		
		int[][] Yarray= new int[sizeZ][sizeX];
		for (int z=0;z<sizeZ;z++){
			for (int x=0;x<sizeX;x++){
				Yarray[z][x]=maze3d[z][i][x];
			}
		}
		return Yarray;	
	}
	/**
	 * This method calculates a z cross section of the 3d mazes
	 * 
	 * @param i the number of line
	 * @return the 2d cross section
	 */		
	public int[][] getCrossSectionByZ(int i) throws IndexOutOfBoundsException {
//		i=i*2-1;
		if (i<0 || i>=sizeZ) throw new IndexOutOfBoundsException();
		
		int[][] Zarray= new int[sizeY][sizeX];
		for (int y=0;y<sizeY;y++){
			for (int x=0;x<sizeX;x++){
				Zarray[y][x]=maze3d[i][y][x];
			}
		}
		return Zarray;	
	}
	/**
	 * This method is a getter.
	 * It returns the 3d maze
	 * 
	 * @return the 3d maze
	 */		
	public int[][][] getMaze3d() {
		return maze3d;
	}
	/**
	 * @param maze3d the maze3d to set
	 */
	public void setMaze3d(int[][][] maze3d) {
		this.maze3d = maze3d;
	}
	/**
	 * This method checks if two mazes are equals
	 * 
	 * @param obj - the other maze
	 * @return true if they are equal, false otherwise
	 */	
	@Override
	public boolean equals(Object obj) {
		Maze3d other = (Maze3d) obj;
		if ((goalPosition.x != other.goalPosition.x) || (goalPosition.y != other.goalPosition.y)|| (goalPosition.z != other.goalPosition.z)) 
		return false;
		if ((startPosition.x != other.startPosition.x) || (startPosition.y != other.startPosition.y)|| (startPosition.z != other.startPosition.z)) 
			return false;
		if (!Arrays.deepEquals(maze3d, other.maze3d))
			return false;
		if (sizeX != other.sizeX)
			return false;
		if (sizeY != other.sizeY)
			return false;
		if (sizeZ != other.sizeZ)
			return false;

		return true;
	}

	/**
	 * @return the nUM_OF_OPTIONAL_MOVES
	 */
	public static int getNUM_OF_OPTIONAL_MOVES() {
		return NUM_OF_OPTIONAL_MOVES;
	}
	/**
	 * @param nUM_OF_OPTIONAL_MOVES the nUM_OF_OPTIONAL_MOVES to set
	 */
	public static void setNUM_OF_OPTIONAL_MOVES(int nUM_OF_OPTIONAL_MOVES) {
		NUM_OF_OPTIONAL_MOVES = nUM_OF_OPTIONAL_MOVES;
	}
	/**
	 * @return the free
	 */
	public static int getFree() {
		return FREE;
	}
	/**
	 * @return the wall
	 */
	public static int getWall() {
		return WALL;
	}

}
