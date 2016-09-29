package algorithms.demo;

import java.util.ArrayList;
import java.util.List;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Searchable;
import algorithms.search.State;
/**
* <h1>MazeAdapter</h1>
* This class is an adapter class that converts
* Maze3d to Searchable<Position>.
* 
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/
public class MazeAdapter implements Searchable<Position> {

	private Maze3d maze;
	/**
	 * this method is a constructor that receives a maze3d as a parameter and adapts
	 * into a searchable<position> 
	 * 
     * @param maze - the name of the maze
	 */
	public MazeAdapter(Maze3d maze) {
		this.maze = maze;
	}	
	/**
	 * this method is a getter for a start position
	 * 
	 * @return start position
	 */	
	@Override
	public State<Position> getStartState() { 
		Position startPos = maze.getStartPosition();
		State<Position> startState = new State<Position>(startPos);
		return startState;	
	}
	/**
	 * this method is a getter for a goal position
	 * 
	 * @return goal position
	 */	
	@Override
	public State<Position> getGoalState() {
		Position goalPos = maze.getGoalPosition();
		State<Position> goalState = new State<Position>(goalPos);
		return goalState;	
	}
	/**
	 * when given a position, this method returns all the different possibilities
	 * of movement
	 * 
	 * @param s - a state
	 * @return a list of possible moves
	 */	
	@Override
	public List<State<Position>> getAllPossibleStates(State<Position> s) {
		Position currPos = s.getValue();
		
		List<Position> moves = maze.getPossiblePositions(currPos);
		List<State<Position>> states = new ArrayList<State<Position>>();
		
		for (Position pos: moves) {
			states.add(new State<Position>(pos));
		}
		return states;		
	}
	/**
	 * when given two positions, this method calculates the cost of making the move
	 * between the positions
	 * 
	 * @param currState - current state
	 * @param neighbor - destination state
	 * @return a cost
	 */	
	@Override
	public double getMoveCost(State<Position> currState, State<Position> neighbor) {		
		return 1; // in the maze all moves have the same cost
	}

}
