package main.graph;

public class Node {
	
	private int id;
	
	public Node() {

	}
	
	public Node(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
	
	public String string() {
		return String.valueOf(id);
	}
	
}
