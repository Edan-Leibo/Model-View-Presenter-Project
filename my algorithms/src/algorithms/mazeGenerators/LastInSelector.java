package algorithms.mazeGenerators;

import java.util.List;
/**
* <h1>LastInSelectorr</h1>
* This class uses for strategy design pattern.
* It is a concrete class that its policy is to select the last cell.
* 
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/
public class LastInSelector implements CellSelector{
	/**
	 * This method uses to determine the policy of the cell selection
	 * The policy is to select the last cell from the list.
	 * 
	 * @param cells - a list of cells
	 * @return a position chosen by a specific strategy
	 */	
	@Override
	public Position select(List<Position> cells) {
		return cells.get(cells.size() - 1);
	}

}
