package common.benchmark;

import common.problem.IInstance;
import common.problem.IProblem;

public class RelativeExecutionStats<P extends IProblem, I extends IInstance<? extends P>> extends ExecutionStats<P,I> {
	
	public RelativeExecutionStats(IExecution bench) {
		super(bench);
	}

	public void addCost(double cost){
		stats.addValue(cost);
	}
	
	public String toString() {
		
		StringBuffer sb = new StringBuffer();

		
		
		sb.append("{");
		sb.append("problem: "+ bench.getProblem().getClass().getSimpleName());
		sb.append(", ");
		sb.append("algorithm: "+ bench.getAlgorithm().getClass().getSimpleName());
		sb.append(", ");
		sb.append("mean: "+stats.getMean());
		sb.append(", ");
		sb.append("best: "+stats.getMin());
		sb.append(", ");
		sb.append("worse: "+stats.getMax());
		sb.append("}");
		
		return sb.toString();
	};
}
