package common.benchmark;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import common.problem.IInstance;
import common.problem.IProblem;

public class BenchmarkStats<P extends IProblem, I extends IInstance<? extends P>> {
	
	public DescriptiveStatistics stats = new DescriptiveStatistics();
	
	public BenchmarkStats(IBenchmark bench) {
		this.bench = bench;
	}
	
	IBenchmark bench;
	
	public double getEpsilon() {
		return 1.96*stats.getStandardDeviation()/Math.sqrt(stats.getN());
	}
	
	public double get2Epsilon() {
		return 2*1.96*stats.getStandardDeviation()/Math.sqrt(stats.getN());
	}
	
	
	
}
