package common.benchmark;

import java.util.ArrayList;
import java.util.List;

import common.problem.IInstance;
import common.problem.IProblem;

public class BenchmarkStats<P extends IProblem, I extends IInstance<? extends P>> {
	
//	private final List<Solution<P, I>> solutions = new ArrayList();
	
	private List<Float> ratios = new ArrayList<>();

	public List<Float> getRatios() {
		return ratios;
	}

	public void setRatios(List<Float> ratios) {
		this.ratios = ratios;
	}

	
}
