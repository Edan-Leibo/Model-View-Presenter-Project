package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
* <h1>Solution<T></h1>
* This class describes a solution for a searchable.
* 
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/
public class Solution<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<State<T>> states = new ArrayList<State<T>>();
	/**
	 * this method is a getter for all the states in the solution
	 * 
	 * @return a list of all the states
	 */	
	public List<State<T>> getStates() {
		return states;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * this method is a setter that sets the states in the solution
	 * 
	 * @param states - a list of all the states
	 * @return nothing
	 */	
	public void setStates(List<State<T>> states) {
		this.states = states;
	}
	/**
	 * this method generates a string that describes the solution
	 * 
	 * @return a string that describes the solution
	 */		
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (State<T> s : states) {
			sb.append(s.toString()).append(" ");
		}
		return sb.toString();
	}
}
