package main.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphBuilder {

	Graph network;	
	
	Map<Integer, Integer> nodeMap;

	public GraphBuilder() {
		
		network = new Graph();
		nodeMap = new HashMap<>();
	}

	public int addNode(int nodeId) {

		if (nodeMap.containsKey(nodeId))
			return nodeMap.get(nodeId);

		int innerId = network.addNode();
		nodeMap.put(nodeId, innerId);
		return innerId;
	}
	
	public boolean addEdge(String fromId, String toId) {
		return addEdge(Integer.valueOf(fromId), Integer.valueOf(toId));		
	}
	
	public boolean addEdge(int fromId, int toId) {

		int innerId1 = addNode(fromId);
		int innerId2 = addNode(toId);
		try {
			network.addEdge(innerId1, innerId2);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;

	}

	public Graph build() {
		return this.network;
	}

	
}
