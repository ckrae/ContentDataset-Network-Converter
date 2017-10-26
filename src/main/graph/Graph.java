package main.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Graph {

	///// Attributes /////

	List<Node> nodes;

	List<Edge> edges;

	int size;

	///// Constructor /////

	public Graph() {

		edges = new ArrayList<>();
		nodes = new ArrayList<>();
		size = -1;
	}
	
	public Graph(int size) {

		edges = new ArrayList<>();
		nodes = new ArrayList<>(size);
		size = -1;
	}

	///// Getter /////

	public List<Edge> getEdges() {
		return edges;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	///// Nodes /////

	public int nodeCount() {
		return getNodes().size();
	}

	public Node getNode(int innerId) {

		if (innerId < 0 || innerId > nodeCount())
			throw new IllegalArgumentException("invalid nodeId");

		return getNodes().get(innerId);
	}

	public int addNode() {
		size++;
		Node structureNode = new Node(size);
		getNodes().add(size, structureNode);
		return size;
	}

	///// Edges /////

	public int edgeCount() {
		return getEdges().size();
	}

	public Edge addEdge(int fromId, int toId) {

		if (fromId < 0 || toId < 0)
			throw new IllegalArgumentException("no negative ids");

		if (nodeCount() < fromId || nodeCount() < toId)
			throw new IllegalArgumentException("node ids to high");
		
		if (fromId == toId)
			throw new IllegalArgumentException("no self loops");

		Edge edge = getEdge(getNode(fromId), getNode(toId));
		if (edge == null) {
			edge = new Edge(getNode(fromId), getNode(toId));
			getEdges().add(edge);
			return edge;
		}
		
		edge.increase();
		return edge;
	}

	public Edge getEdge(Node from, Node to) {

		for (Edge edge : getEdges()) {
			if ((edge.getFrom().equals(from) && edge.getTo().equals(to))
					|| (edge.getFrom().equals(to) && edge.getTo().equals(from))) {
				return edge;
			}
		}
		return null;
	}

	///// /////

	public void sort() {
		Collections.sort(edges);
	}

}
