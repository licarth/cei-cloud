package knapsack;

import java.util.ArrayList;
import java.util.List;

import common.problem.InputDataException;
import common.problem.Instance;

public class KPInstance extends Instance<KP> {

	private List<KPItem> items = new ArrayList<>();
	
	public KPInstance(KP problem) {
		super(problem);
	}

	@Override
	public void checkProblemInput() throws InputDataException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toStringDetailed() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
