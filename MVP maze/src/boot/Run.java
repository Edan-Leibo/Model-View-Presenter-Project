package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.MyModel;
import presenter.Presenter;
import view.MazeWindow;
import view.MyView;

public class Run {

	public static void main(String[] args) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		//The options of the view layerS		
		MyView cli = new MyView(in, out);
		MazeWindow gui = new MazeWindow();
		
		MyModel model = new MyModel();
		
		Presenter presenter = new Presenter(model, gui);
		model.addObserver(presenter);
		gui.addObserver(presenter);
				
		gui.start();

	}

}
