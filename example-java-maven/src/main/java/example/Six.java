package example;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Six {
	private String fileName;
	private List<String> propertyList;
	private static final Logger LOGGER = Logger.getLogger("Six-Logger");

	public Six() {
		fileName=null;
		propertyList = new ArrayList<String>();
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void readTheFile() throws Exception{
		Path path = Paths.get(this.getFileName());
		try{
			BufferedReader reader = Files.newBufferedReader(path);
			try{
				String line = reader.readLine();
				while (line != null) {
					line = reader.readLine();
				}
			}finally{
				reader.close();
			}
		}
		catch(Exception e){
			LOGGER.info(e.getMessage());
			throw e;
		}		
	}

	public void readTheFile2() throws Exception{
		try{
			OutputStream stream = new FileOutputStream(this.getFileName());
			try {
				for(String property : propertyList){
					stream.write(property.getBytes());
				}
			}finally{
				stream.close();
			}
		}catch(Exception e){
			LOGGER.info(e.getMessage());
			throw e;
		}
	}
}
