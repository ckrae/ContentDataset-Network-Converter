package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.graph.GraphWriter;
import main.graph.forum.Forum;
import main.graph.mailing.Mailing;
import main.parser.DateReader;
import main.parser.ForumReader;
import main.parser.MailingReader;

public class Parse {

	public static void main(String[] args) {

	}
	
	
	public static void writeMonthlyForum(String fileName, String graphName , int year) {
		
		Forum graph;
		ForumReader reader = new ForumReader();
		GraphWriter writer = new GraphWriter();
		String yyyy = String.valueOf(year);
		String yyyyAfter = String.valueOf(year + 1);
		String yyyyBefore = String.valueOf(year - 1);
		
		graph = reader.readForum(fileName, yyyyBefore + "-12-31", yyyy + "-02-01");
		writer.write(graphName + "-01", graph.getGraph());
		
		graph = reader.readForum(fileName, yyyy + "-1-31", yyyy + "-03-01");
		writer.write(graphName + "-02", graph.getGraph());
		
		graph = reader.readForum(fileName, yyyy + "-2-31", yyyy + "-04-01");
		writer.write(graphName + "-03", graph.getGraph());
		
		graph = reader.readForum(fileName, yyyy + "-3-31", yyyy + "-05-01");
		writer.write(graphName + "-04", graph.getGraph());
		
		graph = reader.readForum(fileName, yyyy + "-4-31", yyyy + "-06-01");
		writer.write(graphName + "-05", graph.getGraph());
		
		graph = reader.readForum(fileName, yyyy + "-5-31", yyyy + "-07-01");
		writer.write(graphName + "-06", graph.getGraph());
		
		graph = reader.readForum(fileName, yyyy + "-6-31", yyyy + "-08-01");
		writer.write(graphName + "-07", graph.getGraph());
		
		graph = reader.readForum(fileName, yyyy + "-7-31", yyyy + "-09-01");
		writer.write(graphName + "-08", graph.getGraph());
		
		graph = reader.readForum(fileName, yyyy + "-8-31", yyyy + "-10-01");
		writer.write(graphName + "-09", graph.getGraph());
		
		graph = reader.readForum(fileName, yyyy + "-9-31", yyyy + "-11-01");
		writer.write(graphName + "-10", graph.getGraph());
		
		graph = reader.readForum(fileName, yyyy + "-10-31", yyyy + "-12-01");
		writer.write(graphName + "-11", graph.getGraph());
		
		graph = reader.readForum(fileName, yyyy + "-11-31", yyyyAfter + "-01-01");
		writer.write(graphName + "-12", graph.getGraph());
		
	}
	

	public static void writeReleases(String fileName, String graphName, int projectId) {

		DateReader dtr = new DateReader();
		List<Date> dates = new ArrayList<>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dates.add(df.parse("1600-01-01"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		dates.addAll(dtr.readData(projectId));
		List<Integer> nummers = dtr.readNummer(projectId);

		System.out.println(dates.size() - 1);
		
		MailingReader reader = new MailingReader();
		Mailing graph = reader.readGraph(fileName);
		GraphWriter writer = new GraphWriter();
		
		for (int i = 0; i < dates.size() - 1; i++) {
			Date startDate = dates.get(i);
			Date endDate = dates.get(i + 1);
			String name = graphName + "_" + nummers.get(i);
			writer.write(name, graph.getGraph(startDate, endDate));
		}
	}

}
