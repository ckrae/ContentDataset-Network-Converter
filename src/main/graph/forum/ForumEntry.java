package main.graph.forum;

import java.util.Date;

public class ForumEntry {

	int id;
	Date date;
	String content;
	int thread;
	int author;
	String authorName;
	
	public int getAuthor() {
		return author;
	}

	public int getPost() {
		return post;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public void setPost(int post) {
		this.post = post;
	}

	int post;

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public Date getDate() {
		return date;
	}

	public int getThread() {
		return thread;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setThread(int thread) {
		this.thread = thread;
	}

	public void setAuthor(String author) {
		authorName = author;		
	}
	
	public String getAuthorName() {
		return this.authorName;
	}

}
