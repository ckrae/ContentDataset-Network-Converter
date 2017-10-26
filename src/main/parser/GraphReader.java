package main.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GraphReader extends AbstractReader {

	public void readFile() throws FileNotFoundException {

		String path = "";
		String filename = "";

		File file = new File(path, filename);

		FileReader fileReader = null;
		BufferedReader reader = null;
		try {
			fileReader = new FileReader(file);
			reader = new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
}
