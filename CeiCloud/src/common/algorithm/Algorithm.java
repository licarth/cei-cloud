package common.algorithm;

import common.problem.IInstance;
import common.problem.IProblem;
import common.problem.InputDataException;
import common.solution.Solution;

public abstract class Algorithm<P extends IProblem, I extends IInstance<P>> implements IAlgorithm<P,I>  {
	private String name;
	private String abbrev;
	private I instance;
	
	@Override
	public abstract Solution<P, I> solve(I instance) throws InputDataException;
	
	public Algorithm() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getAbbrev() {
		return this.getClass().getSimpleName();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

	public I getInstance() {
		return instance;
	}

	public void setInstance(I instance) {
		this.instance = instance;
	};
}
