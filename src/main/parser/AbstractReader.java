package main.parser;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class AbstractReader {
	
	public List<String> readLine(Reader reader) throws IOException {
		List<String> line = new ArrayList<String>();
		int nextChar = reader.read();

		while (isLineBreak(nextChar)) {
			nextChar = reader.read();
		}
		/*
		 * Extracts all strings from the line, separated by whitespace
		 */
		while (!isLineBreak(nextChar) && nextChar != -1 && reader.ready()) {
			String str = "";
			/*
			 * Skips white space other than line separators
			 */
			while (Character.isWhitespace(nextChar) && !isLineBreak(nextChar)) {
				nextChar = reader.read();
			}
			/*
			 * Reads string until next whitespace or EOF
			 */
			while (nextChar != -1 && !Character.isWhitespace(nextChar) && !isLineBreak(nextChar)) {
				str += (char) nextChar;
				nextChar = reader.read();
			}
			if (!str.equals("")) {
				line.add(str);
			}
		}
		return line;
	}
	
	public List<String> readLineTabIgnoreLineBreak(Reader reader, int lineLength) throws IOException {
		List<String> line = new ArrayList<String>();
		int nextChar = reader.read();

		while (isLineBreak(nextChar)) {
			nextChar = reader.read();
		}

		// Extract all strings of a line, separated by tab but ignore line
		// breaks until specified length
		while (line.size() < lineLength && nextChar != -1) {
			String str = "";

			// skip line breaks in between the lines
			if (isLineBreak(nextChar) && line.size() < lineLength) {
				nextChar = reader.read();
			}

			if (nextChar == '\t') {
				nextChar = reader.read();
				if (nextChar == '\t') {
					str = " "; // prevent empty strings after two tabs
					line.add(str);
					str = "";
				}
			}
			while (nextChar != -1 && !(nextChar == '\t')) {
				if (isLineBreak(nextChar)) {
					if (line.size() == lineLength - 1) {
						break;
					}
					nextChar = ' ';
				}
				str += (char) nextChar;
				nextChar = reader.read();
			}

			if (!str.equals("")) {
				line.add(str);
			}
		}
		return line;
	}

	public boolean isLineBreak(int character) {
		switch (character) {
		case '\r':
			return true;
		case '\n':
			return true;
		default:
			return false;
		}
	}
	
}
