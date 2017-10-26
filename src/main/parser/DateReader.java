package main.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import main.graph.Edge;
import main.graph.Graph;

public class DateReader extends AbstractReader {

	public List<Date> readData(int projectId) {

		List<Date> dates = new ArrayList<Date>();
		FileReader fileReader = null;
		BufferedReader reader = null;

		int intDate = -1;
		int intProject = -1;
		int intNummer = -1;
		int intDescription = -1;

		try {
			File file = new File("Release.csv");
			fileReader = new FileReader(file);
			reader = new BufferedReader(fileReader);

			String line = reader.readLine();
			String[] tokens = StringUtils.split(line);
			for (int i = 0; i < tokens.length; i++) {

				switch (tokens[i].toUpperCase()) {
				case ("PROJECT"):
					intProject = i;
					break;
				case ("RELEASE_DATE"):
					intDate = i;
					break;
				case ("NUMMER"):
					intNummer = i;
					break;
				}
			}

			if (intProject < 0)
				throw new IllegalArgumentException();
			if (intDate < 0)
				throw new IllegalArgumentException();
			if (intNummer < 0)
				throw new IllegalArgumentException();

			line = reader.readLine();
			while (line != null) {

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

				tokens = StringUtils.split(line);
				if (projectId == Integer.parseInt(tokens[intProject])) {
					try {

						dates.add(formatter.parse(tokens[intDate]));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				line = reader.readLine();

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return dates;

	}

	public List<Integer> readNummer(int projectId) {

		List<Integer> nummers = new ArrayList<Integer>();
		FileReader fileReader = null;
		BufferedReader reader = null;

		int intDate = -1;
		int intProject = -1;
		int intNummer = -1;
		int intDescription = -1;

		try {
			File file = new File("Release.csv");
			fileReader = new FileReader(file);
			reader = new BufferedReader(fileReader);

			String line = reader.readLine();
			String[] tokens = StringUtils.split(line);
			for (int i = 0; i < tokens.length; i++) {

				switch (tokens[i].toUpperCase()) {
				case ("PROJECT"):
					intProject = i;
					break;
				case ("RELEASE_DATE"):
					intDate = i;
					break;
				case ("NUMMER"):
					intNummer = i;
					break;
				}
			}

			if (intProject < 0)
				throw new IllegalArgumentException();
			if (intDate < 0)
				throw new IllegalArgumentException();
			if (intNummer < 0)
				throw new IllegalArgumentException();

			line = reader.readLine();
			while (line != null) {

				tokens = StringUtils.split(line);
				if (projectId == Integer.parseInt(tokens[intProject])) {

					nummers.add(Integer.parseInt(tokens[intNummer]));

				}
				line = reader.readLine();

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return nummers;

	}

}
