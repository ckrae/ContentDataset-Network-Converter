package main.graph.forum;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.graph.Graph;
import main.graph.GraphBuilder;
import main.graph.Structure;

public class Forum extends Structure {

	List<ForumEntry> entries = new ArrayList<ForumEntry>();
	List<ForumThread> threads = new ArrayList<ForumThread>();

	public List<ForumEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<ForumEntry> entries) {
		this.entries = entries;
	}

	public void add(ForumEntry entry) {
		this.getEntries().add(entry);
	}

	public int size() {
		return this.getEntries().size();
	}

	/**
	 * Translate String Author Names into Int Author ids. Because URCH datasets
	 * do not have author ids, but author names
	 */
	public void setAuthors() {
		System.out.println("setAuthors");
		Map<String, Integer> map = new HashMap();
		int id = 0;
		for (ForumEntry entry : entries) {
			String name = entry.getAuthorName();
			if (!map.containsKey(name)) {
				map.put(name, id);
				id++;
			}
			entry.setAuthor(map.get(name));
		}
	}

	public void generateThreads(Date startDate, Date endDate) {

		ArrayList<ForumThread> threads = new ArrayList<ForumThread>();
		HashMap<Integer, ForumThread> map = new HashMap<Integer, ForumThread>();
		System.out.println(entries.size() + " - ");
		for (ForumEntry entry : entries) {
			if (entry.getDate().after(startDate) && entry.getDate().before(endDate)) {

				ForumThread thread = map.get(entry.getThread());
				if (thread == null) {
					thread = new ForumThread();
					thread.setId(entry.getThread());
					map.put(entry.getThread(), thread);
					threads.add(thread);
				}
				thread.addAuthor(entry.getAuthor());
			}
		}

		this.threads = threads;
		System.out.print("finish threads " + threads.size());
	}

	public void generateThreads() {

		ArrayList<ForumThread> threads = new ArrayList<ForumThread>();
		HashMap<Integer, ForumThread> map = new HashMap<Integer, ForumThread>();
		System.out.println(entries.size() + " - ");
		for (ForumEntry entry : entries) {
			ForumThread t = map.get(entry.getThread());
			if (t == null) {
				t = new ForumThread();
				t.setId(entry.getThread());
				map.put(entry.getThread(), t);
				threads.add(t);
			}
			t.addAuthor(entry.getAuthor());
		}

		this.threads = threads;
		System.out.print("finish threads " + threads.size());
	}

	////// Output /////

	public Graph getGraph(Date startDate, Date endDate) {

		generateThreads(startDate, endDate);
		return generateGraph();
	}

	public Graph getGraph() {

		generateThreads();
		return generateGraph();
	}

	private Graph generateGraph() {
		System.out.println("generate Graph");
		GraphBuilder builder = new GraphBuilder();
		for (ForumThread t : threads) {
			for (Integer i : t.getAuthors()) {
				for (Integer j : t.getAuthors()) {
					if (i != j)
						builder.addEdge(i, j);
				}
			}
		}
		return builder.build();
	}
}
