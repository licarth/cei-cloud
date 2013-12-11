package common.algorithm;

import common.problem.IInstance;
import common.problem.Problem;

public abstract class Algorithm<P extends Problem, I extends IInstance<P>> implements IAlgorithm<P,I>  {
	private String name;
	private String abbrev;
	
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
	};
}
