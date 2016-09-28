package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.MyModel;
import presenter.Presenter;
import properties.Properties;
import properties.PropertiesLoader;
import view.MazeWindow;
import view.MyView;

public class Run {

	public static void main(String[] args) {
		Properties properties = PropertiesLoader.getInstance().getProperties();
		String perspective = properties.getPerspective();
		if (perspective.equals("cli"))
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out = new PrintWriter(System.out);
			
			MyView view = new MyView(in, out);
			MyModel model = new MyModel();
			Presenter presenter = new Presenter(model, view);
			model.addObserver(presenter);
			view.addObserver(presenter);
			view.start();
		}
		else{
			MazeWindow mazeWindow = new MazeWindow();
			MyModel model = new MyModel();	
			Presenter presenter = new Presenter(model, mazeWindow);
			model.addObserver(presenter);
			mazeWindow.addObserver(presenter);
			mazeWindow.start();	
		}
	}
}
