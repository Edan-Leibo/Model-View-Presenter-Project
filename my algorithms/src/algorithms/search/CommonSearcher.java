package algorithms.search;

import java.util.List;
/**
* <h1>CommonSearcher<T></h1>
* This class is an abstract class of searcher that enables different functionalities
* for all the concrete classes to be created.
* 
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/
public abstract class CommonSearcher<T> implements Searcher<T> {

	protected int evaluatedNodes;
	/**
	 * this method calculates the number of nodes evaluated during any algorithm
	 * 
	 * @return number of nodes
	 */	
	@Override
	public int getNumberOfNodesEvaluated() {		
		return evaluatedNodes;
	}
	/**
	 * this method backtraces the path and creates a solution to the search problem
	 * 
	 * @param goalState - a goal state
	 * @return the solution for the search problem
	 */	
	protected Solution<T> backTrace(State<T> goalState) {
		Solution<T> sol = new Solution<T>();
		
		State<T> currState = goalState;
		List<State<T>> states = sol.getStates();
		while (currState != null) {		
			states.add(0, currState);
			currState = currState.getCameFrom();
		}
		return sol;
	}

}
