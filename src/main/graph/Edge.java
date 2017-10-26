package main.graph;

public class Edge implements Comparable<Edge> {

	Node from;
	Node to;
	
	private int weight;
	
	public Edge() {

	}
	
	public Edge(Node from, Node to) {
		this.from = from;
		this.to= to;
		this.weight = 1;
	}
	
	public Node getFrom() {
		return from;
	}
	
	public Node getTo() {
		return to;
	}
	
	public Node getOther(Node structureNode) {
		
		if(from.equals(structureNode))
		return to;
		
		if(to.equals(structureNode))
			return from;
		
		throw new IllegalArgumentException("invalid node");
	}
	
	public int weight() {
		return this.weight;
	}
	
	protected void increase() {
		this.weight++;
	}
	
	@Override
	public int compareTo(Edge edge) {
		if(this.getFrom().getId() > edge.getFrom().getId())
			return 1;
		if(this.getFrom().getId() < edge.getFrom().getId())
			return -1;	
		
		if(this.getTo().getId() > edge.getTo().getId())
			return 1;
		if(this.getTo().getId() < edge.getTo().getId())
			return -1;
		
		return 0;
	}
}
