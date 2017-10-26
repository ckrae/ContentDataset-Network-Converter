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

import main.graph.forum.Forum;
import main.graph.forum.ForumEntry;

public class ForumReader extends AbstractReader {
	
	public Forum readForum(String fileName, String start, String end) {

		System.out.println("readGraph " + fileName);

		Forum graph = null;
		FileReader fileReader = null;
		BufferedReader reader = null;
		try {
			File file = new File(fileName);
			fileReader = new FileReader(file);
			reader = new BufferedReader(fileReader);
			graph = readForum(reader,start, end);

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

	

	public Forum readForum(BufferedReader reader, String start, String end) {

		Forum graph = null;

		try {

			String[] line = StringUtils.split(reader.readLine());
			int contentIndex = -1;
			int senderIndex = -1;
			int receiverIndex = -1;
			int threadIndex = -1;
			int authorIndex = -1;
			int dateIndex = -1;
			int postIndex = -1;
			int idIndex = -1;
			int parentIndex = -1;
			int projectIndex = -1;
			int mediaIndex = -1;
			int headerIndex = -1;
			int authorStringIndex = -1;
			int timeStampIndex = -1;
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
				case "AUTHOR_ID":
					authorIndex = index;
					break;
				case "AUTHOR":
					authorStringIndex = index;
					break;
				case "PARENT_MSG":
					parentIndex = index;
					break;
				case "POST_ID":
					postIndex = index;
					break;
				case "CREATION_TIMESTAMP":
					timeStampIndex = index;
					break;
				}
				index++;
			}

			graph = readURCHForum(threadIndex, authorStringIndex, timeStampIndex, start, end, line.length, reader);

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

	public Forum readURCHForum(int threadIndex, int authorIndex, int dateIndex, String start, String end,
			int lineLength, Reader reader) throws IOException {

		Forum forum = new Forum();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

		List<String> line = readLineTabIgnoreLineBreak(reader, lineLength);
		while (line.size() > 0) {

			String author = line.get(authorIndex);
			int thread = Integer.parseInt(line.get(threadIndex));
			try {
			Date date = df.parse(line.get(dateIndex));
			
			
				if(date.before(df.parse(end+" 00:00:00")) && date.after(df.parse(start+" 23:59:59"))) {
					ForumEntry entry = new ForumEntry();
					entry.setAuthor(author);
					entry.setThread(thread);
					forum.add(entry);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			line = readLineTabIgnoreLineBreak(reader, lineLength);
		}
		
		forum.setAuthors();
		return forum;
	}
	
	public Forum readSTDOCTORForum(int threadIndex, int authorIndex, int dateIndex, String start, String end,
			int lineLength, Reader reader) throws IOException {

		Forum forum = new Forum();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

		List<String> line = readLineTabIgnoreLineBreak(reader, lineLength);
		while (line.size() > 0) {


			int author = Integer.parseInt(line.get(authorIndex));
			int thread = Integer.parseInt(line.get(threadIndex));
			try {
			Date date = df.parse(line.get(dateIndex));
			
			
				if(date.before(df.parse(end+" 00:00:00")) && date.after(df.parse(start+" 23:59:59"))) {
					ForumEntry entry = new ForumEntry();
					entry.setAuthor(author);
					entry.setThread(thread);
					forum.add(entry);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			line = readLineTabIgnoreLineBreak(reader, lineLength);
		}

		return forum;
	}


	
}
