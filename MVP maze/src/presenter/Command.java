package presenter;

/**
 * Command class represents a command that can be passed
 * in our MVP system - from the presenter to the model and the view.
 * 
 * @author Edan
 * @version 1.0
 */
public interface Command {
	/**
	 * @param args the String array of all the arguments
	 */
	void doCommand(String[] args);
}
