package org.processmining.plugins.causalnet.temp.aggregation;

public abstract interface AggregationFunction<A> {
	
	public abstract String getCategory();
	public abstract String getDescription();
	
	public abstract A aggregate(Object value) throws Exception;
}
