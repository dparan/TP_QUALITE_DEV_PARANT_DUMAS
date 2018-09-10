package example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Six {
	private String fileName;
	private List<String> propertyList;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void readTheFile() throws IOException{
		Path path = Paths.get(this.getFileName());
		BufferedReader reader = null;
		try
		{
			reader = Files.newBufferedReader(path);
			String line = reader.readLine();
			while (line != null) {
				line = reader.readLine();
			}
		}
		finally
		{
			reader.close();
		}
	}

	public void readTheFile2() throws IOException {
		OutputStream stream = null;
		try {
			for (String property : propertyList) {
				stream = new FileOutputStream(this.getFileName());
				stream.write(property.getBytes());
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} finally {
			stream.close();
		}
	}
}
