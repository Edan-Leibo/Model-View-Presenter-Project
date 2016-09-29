
package algorithms.mazeGenerators;

import java.util.List;
import java.util.Random;
/**
* <h1>RandomSelector</h1>
* This class uses for strategy design pattern.
* It is a concrete class that its policy is to select a random cell.
* 
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/
public class RandomSelector implements CellSelector{
	 
	private Random rand = new Random();	
	/**
	 * This method uses to determine the policy of the cell selection
	 * The policy is to select a random cell from the list.
	 * 
	 * @param cells - a list of cells
	 * @return a position chosen by a specific strategy
	 */	
	@Override
	public Position select(List<Position> cells) {
		int r = rand.nextInt(cells.size());
		return cells.get(r);
	}

}