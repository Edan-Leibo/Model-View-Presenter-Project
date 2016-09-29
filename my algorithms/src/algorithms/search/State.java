package algorithms.search;

import java.io.Serializable;

/**
* <h1>State<T></h1>
* This class represents a state in a search problem
* 
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/
public class State<T> implements Comparable<State<T>>, Serializable{

	private static final long serialVersionUID = 1L;
	private State<T> cameFrom;
	private double cost;
	private T value;
	/**
	 * this method is a getter for the state in which we came to current state
	 * 
	 * @return the state the led us to the current state
	 */		
	public State<T> getCameFrom() {
		return cameFrom;
	}
	/**
	 * this method is a setter for the state in which we came to current state
	 * 
	 * @param cameFrom - the state the led us to the current state
	 */	
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	/**
	 * this method is a getter for the cost
	 * 
	 * @return the cost
	 */	
	public double getCost() {
		return cost;
	}
	/**
	 * this method is a setter for the cost
	 * 
	 * @param cost - the cost
	 */	
	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
	 * this method is a getter for the value
	 * 
	 * @return the value
	 */	
	public T getValue() {
		return value;
	}
	/**
	 * this method is a setter for the value
	 * 
	 * @param value - the value
	 */	
	public void setValue(T value) {
		this.value = value;
	}
	/**
	 * this method is a constructor which let us create a new state using a value 
	 * 
	 * @param value - a value
	 */		
	public State(T value) {		
		this.value = value;
	}
	/**
	 * this method generates a string that describes the state
	 * 
	 * @return a string the describes the state
	 */		
	@Override
	public String toString() {
		return value.toString();
	}
	/**
	 * this method compares between two states
	 * 
	 * @param obj - a state 
	 * @return true if they are equal, false otherwise
	 */		
	@Override
	public boolean equals(Object obj) {
		@SuppressWarnings("unchecked")
		State<T> s = (State<T>)obj;
		return s.value.equals(this.value);
	}
	/**
	 * this method compares between two states
	 * 
	 * @param s - a state 
	 * @return a positive number if this is bigger, zero if they are equal, negative
	 * number otherwise
	 */	
	@Override
	public int compareTo(State<T> s) {
		return (int)(this.getCost() - s.getCost());	

	}
	/**
	 * @return the Serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
