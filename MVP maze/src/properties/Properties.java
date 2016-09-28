package properties;
import java.io.Serializable;

public class Properties implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String perspective;
	private int numOfThreads;
	private String generateMazeAlgorithm;
	private String solveMazeAlgorithm;
	
	
	public int getNumOfThreads() {
		return numOfThreads;
	}
	public void setNumOfThreads(int numOfThreads) {
		this.numOfThreads = numOfThreads;
	}
	public String getGenerateMazeAlgorithm() {
		return generateMazeAlgorithm;
	}
	public void setGenerateMazeAlgorithm(String generateMazeAlgorithm) {
		this.generateMazeAlgorithm = generateMazeAlgorithm;
	}
	public String getSolveMazeAlgorithm() {
		return solveMazeAlgorithm;
	}
	public void setSolveMazeAlgorithm(String solveMazeAlgorithm) {
		this.solveMazeAlgorithm = solveMazeAlgorithm;
	}
	/**
	 * @return the perspective
	 */
	public String getPerspective() {
		return perspective;
	}
	/**
	 * @param perspective the perspective to set
	 */
	public void setPerspective(String perspective) {
		this.perspective = perspective;
	}
	
	
	
}
