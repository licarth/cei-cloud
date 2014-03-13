package binpacking;
import common.problem.AbstractProblem;
import common.problem.IProblem;


/**
 * This class is a definition of a general BPP problem. Things like binsize are NOT set yet.
 * 
 * @author thomas
 *
 */
public abstract class AbstractBPP extends AbstractProblem {

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof IProblem)) return false;
		return isSameProblemAs((IProblem) obj);
	}
	
}
