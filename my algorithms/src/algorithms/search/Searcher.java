package algorithms.search;
/**
* <h1>Searcher<T></h1>
* This interface describes the functionality of any searcher algorithm
* 
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/
public interface Searcher<T> {
	/**
	 * when given a searchable problem, this method generates a solution for it
	 * 
	 * @param s - a searchable problem
	 * @return the solution for the problem
	 */	
    public Solution<T> search(Searchable<T> s);
	/**
	 * this method calculates the number of nodes evaluated during any algorithm
	 * 
	 * @return number of nodes
	 */	
    public int getNumberOfNodesEvaluated();
}
