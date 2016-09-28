package properties;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PropertiesCreatorMain {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setPerspective("gui");
		prop.setNumOfThreads(6);
		prop.setGenerateMazeAlgorithm("growingTree");
		prop.setSolveMazeAlgorithm("DFS");
		
		XMLEncoder xmlEncoder = null;
		try {
			xmlEncoder = new XMLEncoder(new FileOutputStream("utilities"+File.separator+"properties.xml"));
			xmlEncoder.writeObject(prop);			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			xmlEncoder.close();
		}
	}

}
