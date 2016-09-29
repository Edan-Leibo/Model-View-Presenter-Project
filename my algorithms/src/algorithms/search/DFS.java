package algorithms.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/**
* <h1>DFS<T></h1>
* This class is a concrete class of a searcher.
* It searches using DFS algorithm.
* 
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/
public class DFS<T> extends CommonSearcher<T> {

	private Stack<State<T>> openList = new Stack<State<T>>();
	/**
	 * this method searches through the searchable using the DFS algorithm
	 * 
	 * @param s - a searchable
	 * @return a solution for the searchable problem
	 */
	@Override
	public Solution<T> search(Searchable<T> s) {
		openList.add(s.getStartState());
		ArrayList<State<T>> closedSet = new ArrayList<State<T>>();
		State<T> goalState =s.getGoalState();
		
		while (openList.size()>0 )
		{
			State<T> n = openList.pop();
			evaluatedNodes++;
			closedSet.add(n);
				
			if(n.equals(goalState))
			{
				return backTrace(n);	
			}
			
			List<State<T>> successors = s.getAllPossibleStates(n);
			for(State<T> state : successors)
			{
				//check if we visited this state before
				if (closedSet.contains(state))
				{
					continue;
				}
				
				//new state - not in open and not in closed
				if (!openList.contains(state))
				{
					state.setCameFrom(n);
					openList.add(state);
				}
					
			}
		}
		return null;
	}
}
