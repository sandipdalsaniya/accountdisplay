package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CustomProperties {
	private static CustomProperties instance;
	private Properties labels;
	
	private CustomProperties() {
		loadProperties();
	}

	public static CustomProperties getInstance() {
		if (instance == null) {
			instance = new CustomProperties();
		}
		return instance;
	}
	
	private void loadProperties(){
		InputStream inputStream  = CustomProperties.class.getClassLoader().getResourceAsStream("labels.properties");
		this.labels = new Properties();
		try {
			this.labels.load(inputStream);
			System.out.println("this.labels="+this.labels);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getValueByKeyName(String name){
		return labels.getProperty(name);
	}
}
