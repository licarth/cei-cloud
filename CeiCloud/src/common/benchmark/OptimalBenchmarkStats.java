package common.benchmark;

import common.problem.IInstance;
import common.problem.IProblem;

public class OptimalBenchmarkStats<P extends IProblem, I extends IInstance<? extends P>> extends BenchmarkStats<P, I> {
	
	public OptimalBenchmarkStats(IBenchmark bench) {
		super(bench);
		// TODO Auto-generated constructor stub
	}

	public void addRatio(double ratio){
		stats.addValue(ratio);
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
		sb.append("conf-width: "+1.96*stats.getStandardDeviation()/Math.sqrt(stats.getN()));
//		sb.append(", ");
//		sb.append("stdDev: "+stats.getStandardDeviation());
//		sb.append(", ");
//		sb.append("best: "+stats.getMin());
//		sb.append(", ");
//		sb.append("worse: "+stats.getMax());
		sb.append("}");
		
		return sb.toString();
	};
}
