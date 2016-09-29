package algorithms.search;

import java.util.List;
/**
* <h1>Searchable<T></h1>
* This class interface discribes the functionality of any searchable problem
* 
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/
public interface Searchable<T> {
	/**
	 * Returns the initial state
	 * @return 
	 */
	State<T> getStartState();
	
	State<T> getGoalState();
	/**
	 * this method calculates all the states we can go to from the current state
	 * 
	 * @param s the current state
	 * @return a list of all the states we can move to from the input state
	 */
	List<State<T>> getAllPossibleStates(State<T> s);
	/**
	 * this method calculates the cost of moving from one state to another
	 * 
	 * @param currState - the current state
	 * @param neighbor - a neighbor state
	 * @return the cost of moving between two adjacent states
	 */
	double getMoveCost(State<T> currState, State<T> neighbor);
}
