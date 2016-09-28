package view;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public class MazeWindow extends BasicWindow implements View {

	private Gameboard mazeDisplay;
	private Button btnSolveMaze;
	private Button btnHintMaze;
	protected boolean hint=false;
	private Button btnGenerateMaze;
	private Menu menuBar, fileMenu, helpMenu;
	private MenuItem fileMenuHeader, helpMenuHeader;
	private MenuItem fileExitItem, fileSaveItem, fileLoadItem ,helpGetHelpItem;
	
	@Override
	protected void initWidgets() {
		shell.setLayout(new GridLayout(2, false));				
		{

		    menuBar = new Menu(shell, SWT.BAR);
		    fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		    fileMenuHeader.setText("&File");

		    fileMenu = new Menu(shell, SWT.DROP_DOWN);
		    fileMenuHeader.setMenu(fileMenu);

		    fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
		    fileSaveItem.setText("&Save");
		    fileSaveItem.setEnabled(false);
		    
		    fileLoadItem = new MenuItem(fileMenu, SWT.PUSH);
		    fileLoadItem.setText("&Load");

		    fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
		    fileExitItem.setText("E&xit");

		    helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		    helpMenuHeader.setText("&Help");

		    helpMenu = new Menu(shell, SWT.DROP_DOWN);
		    helpMenuHeader.setMenu(helpMenu);

		    helpGetHelpItem = new MenuItem(helpMenu, SWT.PUSH);
		    helpGetHelpItem.setText("&Get Help");

		    fileExitItem.addSelectionListener(new fileExitItemListener());
		    fileSaveItem.addSelectionListener(new fileSaveItemListener());
		    fileLoadItem.addSelectionListener(new fileLoadItemListener());
		    helpGetHelpItem.addSelectionListener(new helpGetHelpItemListener());

		    shell.setMenuBar(menuBar);
		}
		Composite btnGroup = new Composite(shell, SWT.BORDER);
		RowLayout myLayout=new RowLayout(SWT.VERTICAL);
		myLayout.fill=true;
		btnGroup.setLayout(myLayout);
	
		btnGenerateMaze = new Button(btnGroup, SWT.PUSH);
		btnGenerateMaze.setText("Generate maze");	
		
		btnGenerateMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.setEnabled(false);
				showGenerateMazeOptions();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnSolveMaze = new Button(btnGroup, SWT.PUSH);
		btnSolveMaze.setText("Solve maze");
		btnSolveMaze.setEnabled(false);
		btnSolveMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnHintMaze.setEnabled(false);
				//btnGenerateMaze.setEnabled(false);
				setChanged();
				notifyObservers("solve "+mazeDisplay.getGameboardName()+ " fromProperties");						
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {				
			}
		});
			
		btnHintMaze = new Button(btnGroup, SWT.PUSH);
		btnHintMaze.setText("Hint");
		btnHintMaze.setEnabled(false);
		
		btnHintMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {

				setChanged();
				notifyObservers("solve "+mazeDisplay.getGameboardName()+ " fromProperties");
				hint=true;
						
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {				
			}
		});
		
		mazeDisplay= new MazeGameboard(shell, SWT.BORDER | SWT.DOUBLE_BUFFERED);//?
		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
	}

	protected void showGenerateMazeOptions() {
		final Shell myShell = new Shell(display);
		shell.setEnabled(false);
		myShell.setText("Generate Maze");
		myShell.setSize(300, 200);
		
		GridLayout layout = new GridLayout(4, true);
		myShell.setLayout(layout);
		
		Label lblName = new Label(myShell, SWT.NONE);
		lblName.setText("Maze name: ");
		final Text txtName = new Text(myShell, SWT.NONE);

		Label lblFloors = new Label(myShell, SWT.NONE);
		lblFloors.setText("Floors: ");
	    final Spinner floorSpinner = new Spinner(myShell, SWT.READ_ONLY);
	    floorSpinner.setBackground(new Color(null,255,255,255));
	    floorSpinner.setMinimum(3);
	    floorSpinner.setMaximum(10);
	    floorSpinner.setIncrement(1);

		Label lblRows = new Label(myShell, SWT.NONE);
		lblRows.setText("Rows: ");
	    final Spinner rowsSpinner = new Spinner(myShell, SWT.READ_ONLY);
	    rowsSpinner.setBackground(new Color(null,255,255,255));
	    rowsSpinner.setMinimum(3);
	    rowsSpinner.setMaximum(10);
	    rowsSpinner.setIncrement(1);
		
		Label lblCols = new Label(myShell, SWT.NONE);
		lblCols.setText("Cols: ");
	    final Spinner colsSpinner = new Spinner(myShell, SWT.READ_ONLY);
	    colsSpinner.setBackground(new Color(null,255,255,255));
	    colsSpinner.setMinimum(3);
	    colsSpinner.setMaximum(10);
	    colsSpinner.setIncrement(1);
		
		Label lblAlgo = new Label(myShell, SWT.NONE);
		lblAlgo.setText("Generation algorithm: ");
		final List listAlgo = new List(myShell, SWT.SINGLE | SWT.BORDER);
		listAlgo.setItems("simple growingRandom growingLast".split(" "));
		listAlgo.select(1);
		
		Button btnGenerate = new Button(myShell, SWT.PUSH);
		btnGenerate.setText("Generate");
		btnGenerate.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {

				setChanged();
				notifyObservers("generate_maze "+txtName.getText()+" "+floorSpinner.getSelection()+ " " + rowsSpinner.getSelection()+ " " + colsSpinner.getSelection()+" "+listAlgo.getSelection()[0]);			
				myShell.close();
				shell.setEnabled(true);
				fileSaveItem.setEnabled(true);
				mazeDisplay.setFocus();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		myShell.pack();
		myShell.open();		
	}

	@Override
	public void notifyMazeIsReady(String name) {
		final String myName= new String(name);
		display.syncExec(new Runnable() {
			
			@Override
			public void run() {
				MessageBox msg = new MessageBox(shell);
				msg.setMessage("Maze " + myName + " is ready");
				msg.open();	
				
				mazeDisplay.setGameboardName(myName);
				
				setChanged();
				notifyObservers("display " + myName);
			}
		});			
	}

	@Override
	public void displayMaze(Maze3d maze) {
		//This entire function will only be called only after the first generating
		mazeDisplay.setData(maze);
		mazeDisplay.setEndPosition(maze.getGoalPosition().z,maze.getGoalPosition().y,maze.getGoalPosition().x);
		mazeDisplay.setGameCharacter(new GameCharacter(maze.getStartPosition().z, maze.getStartPosition().y,maze.getStartPosition().x));
		mazeDisplay.setGameBoard(maze.getCrossSectionByZ(maze.getStartPosition().z));
		//mazeDisplay.redraw();
		
		btnSolveMaze.setEnabled(true);
		btnHintMaze.setEnabled(true);
	}

	@Override
	public void displayMessage(String msg) {
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION);
	    messageBox.setMessage(msg);
	    messageBox.open();		
	}

	@Override
	public void start() {
		run();		
	}

	@Override
	public void displayFilesInPath(String path, String[] files) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayBadArguments() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayCrossSectionMaze(int index, String axis, String name,
			int[][] maze2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayErrorMessage(String error) {
		MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
    	dialog.setText("Error Massage - Lion King 1.0");
    	dialog.setMessage(error);
    	// open dialog and await user selection
    	
    	dialog.open();
		
	}

	@Override
	public void notifyMazeSaved(String name) {
	//	System.out.println("sadasd");
	}

	@Override
	public void notifyMazeLoaded(String name) {
		mazeDisplay.setGameboardName(name);
		
		setChanged();	
		notifyObservers("display "+name);
	}

	@Override
	public void notifySolutionIsReady(String name) {
		
		final String myName= new String(name);
		display.syncExec(new Runnable() {
			
			@Override
			public void run() {
				setChanged();
				notifyObservers("display_solution " + myName);
			}
		});	

	}

	@Override
	public void displaySolution(String name, Solution<Position> sol) {
		final ArrayList<Position> m=getPath(sol.toString());	
		//check if the user only wanted a hint
		if (hint){
			btnHintMaze.setEnabled(false);
			mazeDisplay.showHint(m);
			hint=false;
		}
		//else the user wanted a solution
		else{
			mazeDisplay.showSolution(m);
			btnHintMaze.setEnabled(false);
		}
	}
	
	private ArrayList<Position> getPath(String path) {
		ArrayList<Position> ans= new ArrayList<Position>();
		String[] strSol=(path.toString()).split(" ");
		for(int i=0;i<strSol.length;i++){
			strSol[i]=strSol[i].substring(1, strSol[i].length()-1);
			String[] cell=strSol[i].split(",");
			int z= Integer.parseInt(cell[0]);
			int y= Integer.parseInt(cell[1]);
			int x= Integer.parseInt(cell[2]);
			Position p=new Position(z,y,x);
			ans.add(p);
		}
		return ans;
	}

	class fileExitItemListener implements SelectionListener {
	    public void widgetSelected(SelectionEvent event) {
	      shell.close();
	      display.dispose();
	    }

	    public void widgetDefaultSelected(SelectionEvent event) {
	      shell.close();
	      display.dispose();
	    }
	  }

	class fileSaveItemListener implements SelectionListener {
	    public void widgetSelected(SelectionEvent event){
	    	DirectoryDialog dialog = new DirectoryDialog(shell);
	    	dialog.setText("Save maze");
	    	dialog.setMessage("Select a directory to save your maze in.\n"
	    			+ "File name: "+ mazeDisplay.getGameboardName()+".maz");
	    	String selected=dialog.open();
	        if (selected==null) return;
	    	selected=selected+File.separator+mazeDisplay.getGameboardName()+".maz";

	    	setChanged();
	    	notifyObservers("save_maze "+mazeDisplay.getGameboardName()+" "+selected);	
	    }

	    public void widgetDefaultSelected(SelectionEvent event) {
	    }
	  }
	
	class fileLoadItemListener implements SelectionListener {
	    public void widgetSelected(SelectionEvent event) {
	    	FileDialog fd= new FileDialog(shell, SWT.OPEN);
	    	fd.setText("Load maze");
	    	String[] filterExt= {"*.maz"};
	    	fd.setFilterExtensions(filterExt);
	    	String selected= fd.open();
	    	if (selected==null) return;
	    	String[] arr= selected.split(Pattern.quote(File.separator));
	    	String selectedMazeName=arr[arr.length-1];
	    	
	    	setChanged();
	    	notifyObservers("load_maze "+selected+ " "+ selectedMazeName.substring(0, selectedMazeName.length()-4));	

	    }

	    public void widgetDefaultSelected(SelectionEvent event) {
	    }
	  }

	class helpGetHelpItemListener implements SelectionListener {
	    public void widgetSelected(SelectionEvent event) {
	    	MessageBox dialog = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
	    	dialog.setText("Help - Lion King 1.0");
	    	dialog.setMessage("This Java project was created by Leibovitz Edan"
	    			+ "\nas part of the mandatory requirements of a Java course."
	    			+ "\n\nThe Computer science department of Colman College of Management studies."
	    			+ "\nInstructed by: Mr Nisim Barami. ");
	    	// open dialog and await user selection
	    	
	    	dialog.open();
	    }

	    public void widgetDefaultSelected(SelectionEvent event) {
	    }
	  }

}
