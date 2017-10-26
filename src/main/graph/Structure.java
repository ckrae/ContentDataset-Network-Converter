package main.graph;

import java.util.Date;

public abstract class Structure {

	public abstract Graph getGraph();
	
	public abstract Graph getGraph(Date startDate, Date endDate);
	
}
