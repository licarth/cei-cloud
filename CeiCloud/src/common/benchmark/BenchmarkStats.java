package common.benchmark;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import common.problem.IInstance;
import common.problem.IProblem;

public class BenchmarkStats<P extends IProblem, I extends IInstance<? extends P>> {
	
	public BenchmarkStats(IBenchmark bench) {
		this.bench = bench;
	}
	
	IBenchmark bench;
	
	DescriptiveStatistics ratios = new DescriptiveStatistics();

	public void addRatio(float ratio){
		ratios.addValue(ratio);
	}
	
	public String toString() {
		
		StringBuffer sb = new StringBuffer();

		
		
		sb.append("{");
		sb.append("problem: "+ bench.getProblem().getClass().getSimpleName());
		sb.append(", ");
		sb.append("algorithm: "+ bench.getAlgorithm().getClass().getSimpleName());
		sb.append(", ");
		sb.append("mean: "+ratios.getMean());
		sb.append(", ");
		sb.append("best: "+ratios.getMin());
		sb.append(", ");
		sb.append("worse: "+ratios.getMax());
		sb.append("}");
		
		return sb.toString();
	};
}
