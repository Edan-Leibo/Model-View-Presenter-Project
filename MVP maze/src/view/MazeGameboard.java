package view;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

public class MazeGameboard extends Gameboard {
	private int[][] mazeData;				//The floor we currently working on 
	private int endRow;
	private int endCol;
	private int endFloor;
	private GameCharacter gameCharacter;
	private Maze3d maze;							//The maze we currently working on 
	private String mazeName;
	private boolean withHint;
	private ArrayList<Position> hintPath;
	private Random random=new Random();
	private Image grassImage = new Image(getDisplay(),"resources"+File.separator+"grass.jpg");
	private Image openingImage = new Image(getDisplay(),"resources"+File.separator+"opening.jpg");
	private	Image upImage = new Image(getDisplay(),"resources"+File.separator+"up.png");
	private Image downImage = new Image(getDisplay(),"resources"+File.separator+"down.png");
	private Image wallImage = new Image(getDisplay(),"resources"+File.separator+"wall.png");
	private Image hintImage = new Image(getDisplay(),"resources"+File.separator+"hint.png");

	
	/**
	 *	constructs a new game board
	 * 
	 * @param parent the parent of the canvas
	 * @param style the style of the canvas
	 */
	public MazeGameboard(Shell parent, int style) {
		super(parent, style);
		hintPath=null;
		mazeData=null;
		withHint=false;
		//Adding the event handler which will paint the maze every time needed
		this.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				
				int width=getSize().x;
				int height=getSize().y;
				
				if (mazeData == null){
					e.gc.drawImage(openingImage, 0, 0, 612, 612, 0, 0, width,height);  
					return;
				}
				
				int w=width/mazeData[0].length;
				int h=height/mazeData.length;
				
				for(int i=0;i<mazeData.length;i++)
					for(int j=0;j<mazeData[i].length;j++){
						int x=j*w;
						int y=i*h;
						//Draw grass in any cell
						e.gc.drawImage(grassImage, 0, 0, 256, 256, x, y, w, h); 
						//Drawing arrows on maze
						if (mazeData[i][j]!=1 && maze.getMaze3d()[gameCharacter.getFloor()+1][i][j]==0){
							e.gc.drawImage(upImage, 0, 0, 256, 256, x, y, w/2, h);
						}
						if (mazeData[i][j]!=1 && maze.getMaze3d()[gameCharacter.getFloor()-1][i][j]==0){
							e.gc.drawImage(downImage, 0, 0, 256, 256, x+w/2, y, w/2, h);
						}
						if(mazeData[i][j]!=0){
							e.gc.drawImage(wallImage, 0, 0, 256, 256, x, y, w, h); 
						}
						
					}
				//If user asked to draw the maze with hint on it
				if (withHint){
					for (Position p: hintPath){
						if (p.z==gameCharacter.getFloor()){
								e.gc.drawImage(hintImage, 0, 0, 256, 256, p.x*w+w/4, p.y*h+h/4, w/4, h/4); 
						}
					}
				}
				//Drawing the ending point of the character is in right floor
				if (gameCharacter.getFloor()==endFloor){
					Image image = new Image(null,"resources"+File.separator+"bugs.gif");
					e.gc.drawImage(image,0,0,444,433,w*endCol,h*endRow, w, h);
				}
				//Drawing the gameCharacter
				gameCharacter.paint(e, w, h);
				
				//if gameCharacter reached the end point show game over
				if (gameCharacter.getFloor()==endFloor && gameCharacter.getRow()==endRow &&gameCharacter.getCol()==endCol){
					hintPath=null;
					mazeData=null;
					withHint=false;

					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Image myImage = new Image(getDisplay(),"resources"+File.separator+"gameOver.jpg");
					e.gc.drawImage(myImage, 0, 0, 1894, 1036, 0, 0, width,height);  
					MessageBox dialog = new MessageBox(getShell(), SWT.ICON_INFORMATION | SWT.OK);
			    	dialog.setText("Congratulation");
			    	dialog.setMessage("Game Over!");
			    	getShell().setEnabled(true);
			    	
			    	
			    	// open dialog and await user selection
			    	dialog.open();
			    	
				}
				
			}
		});

	}
	
	public void setGameBoard(int[][] gameBoard) {
		//When given a new maze 'look' it updates its inner data
		this.mazeData = gameBoard;
		redraw();
	}

	/**
	 * this a setter method for the gameCharacter
	 * @param gameCharacter the gameCharacter to set
	 */
	public void setGameCharacter(GameCharacter gameCharacter) {
		this.gameCharacter = gameCharacter;
	}

	public void setEndPosition(int z, int y, int x) {

		this.endFloor = z;
		this.endRow = y;
		this.endCol = x;
		
	}
	
	/**
	 * this a getter method for the character floor
	 * @return the CharacterFloor
	 */
	public int getCharacterFloor() {
		return gameCharacter.getFloor();
	}

	public void setData(Object data) {
		this.maze = (Maze3d)data;
		hintPath=null;
		withHint=false;
	}

	/**
	 * this a getter method for the game board name
	 * @return the name
	 */
	public String getGameboardName() {
		return mazeName;
	}

	/**
	 * this a setter method for the game board name
	 * @param name the name to set
	 */
	public void setGameboardName(String name) {
		this.mazeName = name;
	}
	
	/**
	 * this method tells the game board to solve the maze
	 * 
	 * @param path the path of the solution thats needs to be displayed
	 */
	public void showSolution(ArrayList<Position> path) {
		Timer myTimer = new Timer();
		MovingAlongPath myTask= new MovingAlongPath(this,path, myTimer);
		myTimer.scheduleAtFixedRate(myTask, 0, 500);
	}
	
	/**
	 * this method tells the game board to give a hintS 
	 * 
	 * @param path the path of the solution, will create a hint using that solution
	 */
	public void showHint(ArrayList<Position> path) {
		hintPath=new ArrayList<Position>();
		for (Position p:path){ 
			if (random.nextInt(10)<2){
				hintPath.add(p);
			}
		}
		
		withHint=true;
		redraw();
	}
	
	private class MovingAlongPath extends TimerTask{

			ArrayList<Position> path;
			MazeGameboard mazeDisplay;
			int i;
			Timer timer;
			
			public MovingAlongPath(MazeGameboard mazeDisplay, ArrayList<Position> path, Timer timer){
				this.mazeDisplay=mazeDisplay;
				this.path=path;
				this.i=0;
				this.timer=timer;
			}
			
			@Override
			public void run() {
				getDisplay().syncExec(new Runnable() {
					
					@Override
					public void run() {	
						gameCharacter.setFloor(path.get(i).z);
						gameCharacter.setRow(path.get(i).y);
						gameCharacter.setCol(path.get(i).x);
						mazeData=maze.getCrossSectionByZ(path.get(i).z);
						mazeDisplay.redraw();
						i=i+1;
						if (i==path.size()) {
							cancel();
							timer.cancel();
						}
						
					}
				});
			}
	}

	@Override
	public void moveUp() {
		if (mazeData[gameCharacter.getRow()-1][gameCharacter.getCol()]==0){
			gameCharacter.setRow(gameCharacter.getRow()-1);
		}
		redraw();
	}
	@Override
	public void moveLeft() {
		if (mazeData[gameCharacter.getRow()][gameCharacter.getCol()-1]==0){
			gameCharacter.setCol(gameCharacter.getCol()-1);
		}
		redraw();
	}
	@Override
	public void moveRight() {
		if (mazeData[gameCharacter.getRow()][gameCharacter.getCol()+1]==0){
			gameCharacter.setCol(gameCharacter.getCol()+1);
		}
		redraw();
	}
	@Override
	public void moveDown() {
		if (mazeData[gameCharacter.getRow()+1][gameCharacter.getCol()]==0){
			gameCharacter.setRow(gameCharacter.getRow()+1);
		}
		redraw();
	}
	@Override
	public void movePageUp() {
		if (maze.getMaze3d()[gameCharacter.getFloor()+1][gameCharacter.getRow()][gameCharacter.getCol()]==0){
			gameCharacter.setFloor(gameCharacter.getFloor()+1);
			mazeData=maze.getCrossSectionByZ(gameCharacter.getFloor());
		}
		redraw();
	}
	@Override
	public void movePageDown() {
		if (maze.getMaze3d()[gameCharacter.getFloor()-1][gameCharacter.getRow()][gameCharacter.getCol()]==0){
			gameCharacter.setFloor(gameCharacter.getFloor()-1);
			mazeData=maze.getCrossSectionByZ(gameCharacter.getFloor());
		}
		redraw();
	}
	
	
}