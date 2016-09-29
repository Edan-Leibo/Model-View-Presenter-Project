package algorithms.search;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
/**
* <h1>BFS<T></h1>
* This class is a concrete class of a searcher.
* It searches using BFS algorithm.
* 
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/
public class BFS<T> extends CommonSearcher<T> {

	private PriorityQueue<State<T>> openList = new PriorityQueue<State<T>>();
	private Set<State<T>> closedList = new HashSet<State<T>>();
	/**
	 * this method searches through the searchable using the BFS algorithm
	 * 
	 * @param s - a searchable
	 * @return a solution for the searchable problem
	 */		
	@Override
	public Solution<T> search(Searchable<T> s) {
		State<T> startState = s.getStartState();
		State<T> goalState = s.getGoalState();

		openList.add(startState);	
		
		while (!openList.isEmpty()) {
			State<T> currState = openList.poll();
			evaluatedNodes++;
			closedList.add(currState);
						
			if (currState.equals(goalState)) {
				return backTrace(currState);
			}
			
			List<State<T>> neighbors = s.getAllPossibleStates(currState);
			
			for (State<T> neighbor : neighbors) {
				if (!openList.contains(neighbor) && !closedList.contains(neighbor)) {
					neighbor.setCameFrom(currState);
					neighbor.setCost(currState.getCost() + s.getMoveCost(currState, neighbor));
					openList.add(neighbor);
				}
				else {
					double newPathCost = currState.getCost() + s.getMoveCost(currState, neighbor);
					if (neighbor.getCost() > newPathCost) {
						neighbor.setCost(newPathCost);
						neighbor.setCameFrom(currState);
						
						if (!openList.contains(neighbor)) {
							openList.add(neighbor);
						} 
						else { // must notify the priority queue about the change of cost
							openList.remove(neighbor);
							openList.add(neighbor);
						}
					}
				}			
			}
		}
		return null;
	}

}
