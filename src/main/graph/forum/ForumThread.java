package main.graph.forum;

import java.util.HashSet;
import java.util.Set;

public class ForumThread {

	int id;
	Set<Integer> authors = new HashSet<Integer>();

	public int getId() {
		return id;
	}

	public Set<Integer> getAuthors() {
		return authors;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAuthors(Set<Integer> authors) {
		this.authors = authors;
	}

	public void addAuthor(int id) {
		this.authors.add(id);
	}

}
