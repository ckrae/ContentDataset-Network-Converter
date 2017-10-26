package main.graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GraphWriter {
	
	public void write(String fileName, Graph graph) {

		System.out.println("write Graph");

		FileWriter fileWriter = null;
		BufferedWriter writer = null;
		try {
			File file = new File(fileName);
			fileWriter = new FileWriter(file);
			writer = new BufferedWriter(fileWriter);

			graph.sort();
			for (Edge edge : graph.getEdges()) {
				System.out.println(edge.getFrom() + " " + edge.getTo());
				writer.append(String.valueOf(edge.getFrom().string())).append("\t")
						.append(String.valueOf(edge.getTo().string())).append("\t")
						.append(String.valueOf(edge.weight())).append("\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
