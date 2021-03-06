package presenter;

import java.util.HashMap;

import model.Model;
import view.View;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
/**
* <h1>Command Manager</h1>
* This class holds all the commands and their names
* furthermore, it encapsulates the different implementations of the commands inside it.  
*
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/

/**
 * CommandsManager wraps a HashMap of a String - Command
 * this is an implementation of the command design pattern
 * 
 * @author Edan
 * @version 1.0
 */
public class CommandsManager {
	
	private Model model;
	private View view;
	
	/**
	 * a constructor method
	 * 
     * @param model - the model
     * @param view - the view
	 */	
	public CommandsManager(Model model, View view) {
		this.model = model;
		this.view = view;		
	}
	
	/**
	 *this method is a getter for the command map

	 * @return a hash map with all the commands and their names
	 */		
	public HashMap<String, Command> getCommandsMap() {
		HashMap<String, Command> commands = new HashMap<String, Command>();
		commands.put("dir", new DisplayDirCommand());
		commands.put("generate_maze", new GenerateMazeCommand());
		commands.put("display", new DisplayMazeCommand());
		commands.put("display_solution", new DisplaySolutionCommand());
		commands.put("display_cross_section", new DisplayCrossSectionCommand());
		commands.put("save_maze", new SaveMazeCommand());
		commands.put("load_maze", new LoadMazeCommand());
		commands.put("solve", new SolveCommand());
		commands.put("maze_ready", new MazeReadyCommand());
		commands.put("solution_ready", new SolutionReadyCommand());
		commands.put("display_msg", new DisplayMessage());
		commands.put("exit", new ExitCommand());

		return commands;
	}
	
	public class DisplayMessage implements Command {

		@Override
		public void doCommand(String[] args) {
			StringBuilder sb = new StringBuilder();

			for (String s : args) {
				sb.append(s + " ");
			}

			view.displayMessage(sb.toString());

		}
	}
	
	/**
	* <h1>SolveCommand</h1>
	* This class is a command that solves the maze
	*/
	class SolveCommand implements Command{
		@Override
		public void doCommand(String[] args){
			if (args==null || args.length!=2){
				view.displayBadArguments();
				return;
			}
			model.solveMaze(args[0], args[1]);
		}		
	}
	
	/**
	* <h1>SaveMazeCommand</h1>
	* This class is a command that saves a maze into a file
	*/
	class SaveMazeCommand implements Command{
		@Override
		public void doCommand(String[] args){
			if (args==null || args.length!=2){
				view.displayBadArguments();
				return;
			}
			model.saveMaze(args[0], args[1]);
			view.notifyMazeSaved(args[0]);
		}		
	}
	
	/**
	* <h1>LoadMazeCommand</h1>
	* This class is a command that loads a maze from a file
	*/
	class LoadMazeCommand implements Command{
		@Override
		public void doCommand(String[] args){
			if (args==null || args.length!=2){
				view.displayBadArguments();
				return;
			}
			if (model.loadMaze(args[0], args[1])){
				view.notifyMazeLoaded(args[1]);
			}
		}		
	}
	
	/**
	* <h1>DisplayCrossSectionCommand</h1>
	* This class is a command that creates a plain made out of a maze by a specific axis
	*/
	class DisplayCrossSectionCommand implements Command{
		@Override
		public void doCommand(String[] args){
			if (args==null || args.length!=3){
				view.displayBadArguments();
				return;
			}
			try{
				int[][] maze2d= model.getCrossSection(Integer.parseInt(args[0]),args[1], args[2]);
				view.displayCrossSectionMaze(Integer.parseInt(args[0]),args[1], args[2], maze2d);
			}
			catch(NumberFormatException e){
				view.displayBadArguments();
			}
		}		
	}
	
	/**
	* <h1>DisplayDirCommand</h1>
	* This class is a command that produces all the files and directories in a path
	*/
	class DisplayDirCommand implements Command {
		@Override
		public void doCommand(String[] args){
			if (args==null|| args.length!=1){
				view.displayBadArguments();
				return;
			}
			String[] files= model.getDirsAndFilesInPath(args[0]);
			view.displayFilesInPath(args[0], files);
		}		
	}
	
	/**
	* <h1>GenerateMazeCommand</h1>
	* This class is a command that generates a maze by a specific algorithm and dimensions
	*/
	class GenerateMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			if (args==null || args.length!=5){
				view.displayBadArguments();
				return;
			}
			
			String name = args[0];
			try{
				int floors = Integer.parseInt(args[1]);
				int rows = Integer.parseInt(args[2]);
				int cols = Integer.parseInt(args[3]);
				model.generateMaze(name,floors,rows, cols,args[4]);
			}
			catch(NumberFormatException e){
				view.displayBadArguments();
			}
		}		
	}
	
	/**
	* <h1>DisplayMazeCommand</h1>
	* This class is a command that produces the requested maze
	*/
	class DisplayMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			if (args==null || args.length!=1){
				view.displayBadArguments();
				return;
			}
			String name = args[0];
			Maze3d maze = model.getMaze(name);
			view.displayMaze(maze);
		}
		
	}
	
	/**
	* <h1>DisplaySolutionCommand</h1>
	* This class is a command that produces a previously calculated solution 
	*/
	class DisplaySolutionCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			if (args==null || args.length!=1){
				view.displayBadArguments();
				return;
			}
			String name = args[0];
			Solution<Position> sol= model.getSolution(name);
			view.displaySolution(name, sol);
		}
		
	}
	
	/**
	* <h1>MazeReadyCommand</h1>
	* This class is a command that lets the presenter know that the 
	* requested maze was generated
	*/
	class MazeReadyCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			view.notifyMazeIsReady(name);
		}
		
	}
	
	/**
	* <h1>SolutionReadyCommand</h1>
	* This class is a command that lets the presenter know that the 
	* requested solution is ready
	*/
	class SolutionReadyCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			view.notifySolutionIsReady(name);
		}
		
	}
	
	/**
	* <h1>SolveCommand</h1>
	* This class is a command that solves the maze
	*/
	class ExitCommand implements Command{
		@Override
		public void doCommand(String[] args){
			model.exit();
		}		
	}
		
}
