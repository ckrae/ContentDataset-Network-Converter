package main.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import main.graph.mailing.Mailing;
import main.graph.mailing.MailingEntry;

public class MailingReader extends AbstractReader {
	
	public Mailing readGraph(String fileName) {

		System.out.println("readGraph " + fileName);

		Mailing graph = null;
		FileReader fileReader = null;
		BufferedReader reader = null;
		try {
			File file = new File(fileName);
			fileReader = new FileReader(file);
			reader = new BufferedReader(fileReader);
			graph = readGraph(reader);

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
		return graph;
	}
	
	public Mailing readGraph(BufferedReader reader) {

		Mailing graph = null;

		try {

			String[] line = StringUtils.split(reader.readLine());
			int contentIndex = -1;
			int senderIndex = -1;
			int receiverIndex = -1;
			int threadIndex = -1;
			int dateIndex = -1;
			int idIndex = -1;
			int parentIndex = -1;
			int projectIndex = -1;
			int mediaIndex = -1;
			int headerIndex = -1;
			int index = 0;

			if (line.length <= 0) {
				System.out.println("No line information");
			}

			for (String token : line) {
				switch (token.toUpperCase()) {
				case "MEDIA_TYPE":
					mediaIndex = index;
					break;
				case "HEADER":
					headerIndex = index;
					break;
				case "CONTENT":
					contentIndex = index;
					break;
				case "SENT_BY":
					senderIndex = index;
					break;
				case "REPLIES_TO":
					receiverIndex = index;
					break;
				case "THREAD_ID":
					threadIndex = index;
					break;
				case "DATE":
					dateIndex = index;
					break;
				case "ID":
					idIndex = index;
					break;
				case "PROJECT":
					projectIndex = index;
					break;
				case "PARENT_MSG":
					parentIndex = index;
					break;
				}
				index++;
			}

			graph = readContentGraph(idIndex, projectIndex, mediaIndex, headerIndex, senderIndex, receiverIndex,
					contentIndex, dateIndex, parentIndex, threadIndex, line.length, reader);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception e) {
			}
		}
		return graph;
	}
	
	public Mailing readContentGraph(int idIndex, int projectIndex, int mediaIndex, int headerIndex,
			int senderIndex, int receiverIndex, int contentIndex, int dateIndex, int parentIndex, int threadIndex,
			int lineLength, Reader reader) throws IOException {

		Mailing graph = new Mailing();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		List<String> line = readLineTabIgnoreLineBreak(reader, lineLength);
		while (line.size() > 0) {

			int id = Integer.parseInt(line.get(idIndex));
			int project = Integer.parseInt(line.get(projectIndex));
			String media = line.get(mediaIndex);
			String header = line.get(headerIndex);
			int sender = Integer.parseInt(line.get(senderIndex));
			int receiver = Integer.parseInt(line.get(receiverIndex));
			String content = line.get(contentIndex);

			Date date = new Date();
			try {
				date = df.parse(line.get(dateIndex));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			int parent = Integer.parseInt(line.get(parentIndex));
			int thread = Integer.parseInt(line.get(threadIndex));

			MailingEntry entry = new MailingEntry();
			entry.setContent(content);
			entry.setDate(date);
			entry.setHeader(header);
			entry.setId(id);
			entry.setMediaType(media);
			entry.setParent(parent);
			entry.setProject(project);
			entry.setReceiver(receiver);
			entry.setSender(sender);
			entry.setThread(thread);
			graph.add(entry);

			line = readLineTabIgnoreLineBreak(reader, lineLength);
		}

		graph.sort();
		return graph;
	}

}
