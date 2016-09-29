package properties;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * PropertiesLoader is implemented using the singleton design pattern
 * which means you can only create one instance from that class
 * It hold the properties of the system inside it
 * 
 * @author Edan
 * @version 1.0
 */
public class PropertiesLoader {
	private static PropertiesLoader instance;
	private Properties properties;
	
	/**
	 * this method uses to get the properties inside PropertiesLoader
	 * @return the Properties of the system
	 */
	public Properties getProperties() {
		return properties;
	}
	
	private PropertiesLoader() 
	{
		XMLDecoder decoder=null;
		try {
			decoder = new XMLDecoder(new FileInputStream("utilities/properties.xml"));
			properties = (Properties)decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			decoder.close();
		}
	}
	
	/**
	 * this method allows the user to get the 
	 * one and only instance of PropertiesLoader
	 * 
	 * @return the PropertiesLoader which hold the properties of the system
	 */
	public static PropertiesLoader getInstance() {
		//Creating a new instance in a lazy evaluation manner
		if (instance == null) 
			instance = new PropertiesLoader();
		return instance;
	}
	
	
}
