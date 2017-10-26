package main.graph.mailing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import main.graph.Graph;
import main.graph.GraphBuilder;
import main.graph.Structure;

public class Mailing extends Structure {

	List<MailingEntry> mailingEntries = new ArrayList<MailingEntry>();

	public List<MailingEntry> getEntries() {
		return mailingEntries;
	}

	public void setEntries(List<MailingEntry> mailingEntries) {
		this.mailingEntries = mailingEntries;
	}

	public void sort() {
		Collections.sort(mailingEntries);
	}

	public void add(MailingEntry entry) {
		this.getEntries().add(entry);
	}

	public int size() {
		return this.getEntries().size();
	}

	////// Output /////

	public Graph getGraph(Date startDate, Date endDate) {
		
		GraphBuilder builder = new GraphBuilder();
		for (MailingEntry entry : getEntries()) {

			if (entry.getSender() != -1 && entry.getReceiver() != -1)
				if (entry.getDate().before(endDate) && entry.getDate().after(startDate))
					builder.addEdge(entry.getSender(), entry.getReceiver());
		}

		return builder.build();
	}

	public Graph getGraph() {

		GraphBuilder builder = new GraphBuilder();
		for (MailingEntry entry : getEntries()) {

			if (entry.getSender() != -1 && entry.getReceiver() != -1)
				builder.addEdge(entry.getSender(), entry.getReceiver());
		}

		return builder.build();
	}	
}
