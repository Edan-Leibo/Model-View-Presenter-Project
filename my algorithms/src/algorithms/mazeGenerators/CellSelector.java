package algorithms.mazeGenerators;

import java.util.List;
/**
* <h1>CellSelector</h1>
* This interface uses for strategy design pattern.
* Different concrete classes that will implement this class will have
* to decide the policy of the cell selection.
* 
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/
public interface CellSelector {
	/**
	 * this method uses to determine the policy of the cell selection
	 * 
	 * @param cells - a list of cells
	 * @return a position chosen by a specific strategy
	 */	
	public Position select(List<Position> cells);
}
