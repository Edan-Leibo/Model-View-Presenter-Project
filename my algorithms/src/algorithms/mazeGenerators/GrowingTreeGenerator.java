package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
* <h1>GrowingTreeGenerator</h1>
* 
* a concrete generator class which creates a new maze
* by the growing tree algorithm
* 
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/
public class GrowingTreeGenerator extends Maze3dGeneratorBase {

	private Random rand = new Random();	
	private CellSelector selector; 			//dependency injection
	/**
	 * this method is a constructor
	 * 
	 * @return nothing
	 */	
	public GrowingTreeGenerator() {	
		selector=new RandomSelector();
	}
	/**
	 * this method is a constructor
	 * that uses dependency injection of the selector
	 * 
	 * @return nothing
	 */	
	public GrowingTreeGenerator(CellSelector s) {	
		selector=s;
	}
	/**
	 * this method enables dependency injection of the cell selector
	 * 
	 * @param selector - a cell selector
	 * @return nothing
	 */	
	public void setSelector(CellSelector selector) {
		//dependency injection- The policy to choose next cell
		this.selector = selector;
	}
	/**
	 * this method is an inherited method which creates a maze by the
	 * given sizes
	 * 
	 * @param floors - the amount of floors
	 * @param rows - the amount of rows
	 * @param cols - the amount of coloumns
	 * @return the generated maze
	 */	
	@Override
	public Maze3d generate(int floors, int rows, int cols){		
		Maze3d maze = new Maze3d(floors,rows,cols);
		List<Position> cells = new ArrayList<Position>();		

		// Choose a random starting cell (must be in an odd row and column)		
		Position startPos = chooseRandomPosition(maze);
		maze.setStartPosition(startPos);
		maze.setFree(startPos.z, startPos.y, startPos.x);

		cells.add(startPos);
		
		while (!cells.isEmpty() ) {
			// The user chooses the selection method
			Position pos = selector.select(cells);
			
			// Find the unvisited neighbors of this cell
			List<Position> neighbors = findUnvisitedNeighbors(maze, pos);	
			
			if (!neighbors.isEmpty()) {
				// Choose a random neighbor
				int idx = rand.nextInt(neighbors.size());
				Position neighbor = neighbors.get(idx);
				
				// Carve a passage between current cell and the neighbor
				carvePassageBetweenCells(maze, pos, neighbor);				
				cells.add(neighbor);
			} 
			else {
				cells.remove(pos);
			}	
		}		
		
		Position goalPosition = chooseRandomPosition(maze);
		maze.setGoalPosition(goalPosition);
		
		return maze;
	}
	/**
	 * this method chooses a random position on the maze
	 * 
	 * @param maze3d - a 3d maze
	 * @return a position on the maze
	 */	
	private Position chooseRandomPosition(Maze3d maze3d) {	
		int x = rand.nextInt(maze3d.getSizeX()-2)+1;
		//The selected position MUST be at an odd place
		while (x % 2 != 1) {
			x = rand.nextInt(maze3d.getSizeX()-2)+1;
		}

		int y = rand.nextInt(maze3d.getSizeY()-2)+1;
		while (y % 2 != 1) {
			y = rand.nextInt(maze3d.getSizeY()-2)+1;
		}
		
		int z = rand.nextInt(maze3d.getSizeZ()-2)+1;
		while (z % 2 != 1) {
			z = rand.nextInt(maze3d.getSizeZ()-2)+1;
		}
		return new Position(z, y, x);
	}
	/**
	 * when given a maze and a position, this method finds unvisited neighbors
	 * of the input position
	 * 
	 * @param maze - a maze
	 * @param pos - a position on the maze
	 * @return a list of unvisited neighbors
	 */		
	private List<Position> findUnvisitedNeighbors(Maze3d maze, Position pos) {
		int[][][] mat = maze.getMaze3d();
		List<Position> neighbors = new ArrayList<Position>();
		
		if (pos.x - 2 >= 0 && mat[pos.z][pos.y][pos.x - 2] == Maze3d.WALL) {
			neighbors.add(new Position(pos.z, pos.y, pos.x - 2));
		}
		
		if (pos.x + 2 < maze.getSizeX() && mat[pos.z][pos.y][pos.x + 2] == Maze3d.WALL) {
			neighbors.add(new Position(pos.z, pos.y, pos.x + 2));
		}
		
		if (pos.y - 2 >= 0 && mat[pos.z][pos.y - 2][pos.x] == Maze3d.WALL) {
			neighbors.add(new Position(pos.z, pos.y - 2, pos.x));
		}	
		
		if (pos.y + 2 < maze.getSizeY() && mat[pos.z][pos.y + 2][pos.x] == Maze3d.WALL) {
			neighbors.add(new Position(pos.z, pos.y + 2, pos.x));
		}
		
		if (pos.z - 2 >= 0 && mat[pos.z - 2][pos.y][pos.x] == Maze3d.WALL) {
			neighbors.add(new Position( pos.z - 2, pos.y, pos.x));
		}
		
		if (pos.z + 2 < maze.getSizeZ() && mat[pos.z + 2][pos.y][pos.x] == Maze3d.WALL) {
			neighbors.add(new Position(pos.z + 2, pos.y, pos.x));
		}
		
		return neighbors;
	}	
	/**
	 * this method curves a passage between two neighbor positions
	 * 
	 * @param maze - a maze
	 * @param pos - a position
	 * @param neighbor - a neighbor position
	 * @return nothing
	 */		
	private void carvePassageBetweenCells(Maze3d maze, Position pos, Position neighbor) {
		if (neighbor.x == pos.x + 2) {
			maze.setFree(pos.z, pos.y, pos.x + 1);
			maze.setFree(pos.z, pos.y, pos.x + 2);
		}
		else if (neighbor.x == pos.x - 2) {
			maze.setFree(pos.z, pos.y, pos.x - 1);
			maze.setFree(pos.z, pos.y, pos.x - 2);
		}
		else if (neighbor.y == pos.y + 2) {
			maze.setFree(pos.z, pos.y + 1, pos.x);
			maze.setFree(pos.z, pos.y + 2, pos.x);
		}
		else if (neighbor.y == pos.y - 2) {
			maze.setFree(pos.z, pos.y - 1, pos.x);
			maze.setFree(pos.z, pos.y - 2, pos.x);
		}
		else if (neighbor.z == pos.z + 2) {
			maze.setFree(pos.z + 1, pos.y, pos.x);
			maze.setFree(pos.z + 2, pos.y, pos.x);
		}
		else if (neighbor.z == pos.z - 2) {
			maze.setFree(pos.z - 1, pos.y, pos.x);
			maze.setFree(pos.z - 2, pos.y, pos.x);
		}
	}
	

	

}
