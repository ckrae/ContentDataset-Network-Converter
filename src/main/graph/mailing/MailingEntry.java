package main.graph.mailing;

import java.util.Date;

public class MailingEntry implements Comparable<MailingEntry> {

	int id;
	int project;
	String mediaType;
	String header;
	int sender;
	int receiver;
	String content;
	Date date;
	int parent;
	int thread;

	public int getId() {
		return id;
	}

	public int getProject() {
		return project;
	}

	public String getMediaType() {
		return mediaType;
	}

	public String getHeader() {
		return header;
	}

	public int getSender() {
		return sender;
	}

	public int getReceiver() {
		return receiver;
	}

	public String getContent() {
		return content;
	}

	public Date getDate() {
		return date;
	}

	public int getParent() {
		return parent;
	}

	public int getThread() {
		return thread;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProject(int project) {
		this.project = project;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public void setThread(int thread) {
		this.thread = thread;
	}

	////////// //////////

	public int compareTo(MailingEntry mailingEntry) {
		if (this.getSender() > mailingEntry.getSender())
			return 1;
		if (this.getSender() < mailingEntry.getSender())
			return -1;
		return 0;
	}

}
