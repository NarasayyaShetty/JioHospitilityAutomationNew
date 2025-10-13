package Properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesClass {
	
	Properties p=new Properties();
	
	public PropertiesClass() throws IOException {
		String path=System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+
				"Properties"+File.separator+"UserData.properties";
		FileInputStream fis=new FileInputStream(path);
		p.load(fis);	
	}
	
	public String getProperty(String key) {
		return p.getProperty(key);
	}

}
