package lib.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertyUtils extends Properties {

	PropertyUtils instance = null;

	private PropertyUtils() {

	}

	ResourceBundle rb = ResourceBundle.getBundle("application");

	public PropertyUtils getInstance() {
		if (instance == null) {
			instance = new PropertyUtils();
			try (FileInputStream fis = new FileInputStream(rb.getString("path"))) {
				instance.load(fis);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				instance = null;
			} catch (IOException e1) {
				e1.printStackTrace();
				instance = null;
			}
		}
		return instance;
	}
}
