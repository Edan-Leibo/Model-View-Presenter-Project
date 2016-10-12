package properties;
import java.io.Serializable;

/**
 * This Properties holds the system properties
 * 
 * @author Edan
 * @version 1.0
 */
public class Properties implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String perspective;
	private int numOfThreads;
	private String generateMazeAlgorithm;
	private String solveMazeAlgorithm;
	
	
	/**
	 * this is getter for the number of threads
	 * @return the number of threads
	 */
	public int getNumOfThreads() {
		return numOfThreads;
	}
	
	/**
	 * this is a setter method for the number of threads
	 * 
	 * @param numOfThreads the number of threads to set
	 */
	public void setNumOfThreads(int numOfThreads) {
		this.numOfThreads = numOfThreads;
	}

	/**
	 * this is a getter method for the generating Algorithm
	 * 
	 * @return the generating algorithm for creating mazes as 
	 * it is defines in the system
	 */
	public String getGenerateMazeAlgorithm() {
		return generateMazeAlgorithm;
	}
	
	/**
	 * the algorithm in which the application will generate the mazes 
	 * it is defines in the system
	 * 
	 * @param generateMazeAlgorithm the generating algorithm
	 */
	public void setGenerateMazeAlgorithm(String generateMazeAlgorithm) {
		this.generateMazeAlgorithm = generateMazeAlgorithm;
	}
	
	/**
	 * this is a getter method for the solving algorithm
	 * @return the algorithm in which the application will solve the mazes 
	 * it is defines in the system
	 */
	public String getSolveMazeAlgorithm() {
		return solveMazeAlgorithm;
	}
	
	/**
	 * this is a setter method of the algorithm in which the application will solve the mazes 
	 * it is defines in the system
	 * @param solveMazeAlgorithm the solving algorithm to set
	 */
	public void setSolveMazeAlgorithm(String solveMazeAlgorithm) {
		this.solveMazeAlgorithm = solveMazeAlgorithm;
	}
	
	/**
	 * this is a getter for the perspective of the application
	 * 
	 * @return the perspective GUI or CLI
	 */
	public String getPerspective() {
		return perspective;
	}
	
	/**
	 * this is a getter for the perspective of the application
	 * 
	 * @param perspective the perspective to set
	 */
	public void setPerspective(String perspective) {
		this.perspective = perspective;
	}
	
	
	
}
