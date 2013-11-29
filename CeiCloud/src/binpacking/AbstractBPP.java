package binpacking;
import common.AbstractProblem;
import common.Problem;


/**
 * This class is a definition of a general BPP problem. Things like binsize are NOT set yet.
 * 
 * @author thomas
 *
 */
public abstract class AbstractBPP extends AbstractProblem {

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Problem)) return false;
		return isSameProblemAs((Problem) obj);
	}

	public abstract boolean isSameProblemAs(Problem other);
	
}
