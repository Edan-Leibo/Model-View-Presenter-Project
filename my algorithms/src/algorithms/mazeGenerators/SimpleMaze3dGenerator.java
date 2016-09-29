package algorithms.mazeGenerators;

import java.util.Random;
/**
* <h1>SimpleMaze3dGenerator</h1>
* 
* a concrete generator class which creates a new maze that has a direct path
* between the start position and the goal position.
* 
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/
public class SimpleMaze3dGenerator extends Maze3dGeneratorBase {
	
	private Random rand = new Random();
	private static final float FREE_RATIO = 0.5F;
	/**
	 * this method is an inherited method which creates a maze by the
	 * given sizes 
	 * 
	 * @param floors - the amount of floors
	 * @param rows - the amount of rows
	 * @param cols - the amount of coloumns
	 * @return the generated maze
	 */	
	@Override
	public Maze3d generate(int floors, int rows, int cols) {

	Maze3d maze3d = new Maze3d(floors,rows,cols);
	
	int freesNum = (int)(FREE_RATIO * rows * cols * floors);
	for (int i = 0; i < freesNum; i++) {			
		int x = rand.nextInt(maze3d.getSizeX()-2)+1;
		int y = rand.nextInt(maze3d.getSizeY()-2)+1;					
		int z = rand.nextInt(maze3d.getSizeZ()-2)+1;	
		maze3d.setFree(z,y,x);
	}
			
	// Choose a random entrance on the bottom level, first floor
	Position startPos = chooseRandomPosition(maze3d,1);
	maze3d.setStartPosition(startPos);	
			
	// Choose a random exit on the top level, last floor
	Position goalPos = chooseRandomPosition(maze3d,maze3d.getSizeZ()-2);
	maze3d.setGoalPosition(goalPos);	
	
	//create a path
	//X Axis
	if (startPos.x<=goalPos.x){
		for (int i=startPos.x ; i<=goalPos.x; i++){
			maze3d.setFree(startPos.z, startPos.y, i);
		}
	}
	else{
		for (int i=startPos.x ; i>=goalPos.x; i--){
			maze3d.setFree(startPos.z, startPos.y, i);
		}
	}

	//Y Axis
		if (startPos.y<=goalPos.y){
			for (int i=startPos.y ; i<=goalPos.y; i++){
				maze3d.setFree(startPos.z, i, goalPos.x);
			}
		}
		else{
			for (int i=startPos.y ; i>=goalPos.y; i--){
				maze3d.setFree(startPos.z, i, goalPos.x);
			}
		}
	
	//Z Axis
		if (startPos.z<=goalPos.z){
			for (int i=startPos.z ; i<=goalPos.z; i++){
				maze3d.setFree(i, goalPos.y, goalPos.x);
			}
		}
		else{
			for (int i=startPos.z ; i>=goalPos.z; i--){
				maze3d.setFree(i, goalPos.y, goalPos.x);
			}
		}
	
	return maze3d;	
	}
	
	/**
	 * this method chooses a random inner position on the maze
	 * 
	 * @param maze3d - a 3d maze
	 * @param floor - a floor
	 * @return a position on the maze
	 */	
	private Position chooseRandomPosition(Maze3d maze3d, int floor) {		
		
		int x = rand.nextInt(maze3d.getSizeX()-2)+1;
		int y = rand.nextInt(maze3d.getSizeY()-2)+1;
		while (x%2!=1){
			x = rand.nextInt(maze3d.getSizeX()-2)+1;
		}
		while (y%2!=1){
			y = rand.nextInt(maze3d.getSizeY()-2)+1;
		}
		int z = floor;
		return new Position(z, y, x);
	}
}
	