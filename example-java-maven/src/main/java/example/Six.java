package example;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Six {
	private String fileName;
	private List<String> propertyList;

	public Six() {
		fileName=null;
		propertyList = new ArrayList<>();
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void readTheFile() throws IOException{
		Path path = Paths.get(this.getFileName());
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

	public void readTheFile2() throws IOException{
			OutputStream stream = new FileOutputStream(this.getFileName());
			try {
				for(String property : propertyList){
					stream.write(property.getBytes());
				}
			}finally{
				stream.close();
			}
	}
}
