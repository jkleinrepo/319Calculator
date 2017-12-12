import java.util.Iterator;
 
import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.Function;
import com.fathzer.soft.javaluator.Parameters;

/**
 * Class to process equations entered in CalcWindow; extends DoubleEvaluator from Javaluator library
 * @author Josh Klein
 */
public class ExtendedDoubleEvaluator extends DoubleEvaluator{

	/**
	 * SQRT- Square root function
	 * COTANGENT- Contangent function
	 * SECANT- Secant function
	 * COSECANT- Cosecant function
	 */
	private static final Function SQRT = new Function("sqrt", 1), COTANGENT = new Function("cot", 1),
			SECANT = new Function("sec", 1), COSECANT = new Function("csc", 1);
	
	/**
	 * PARAMS- new parameter set for extended evaluator
	 * 		Initialized with default parameters from DoubleEvaluator
	 * 		New functions added to new set
	 */
	private static final Parameters PARAMS;
	static {
		PARAMS = DoubleEvaluator.getDefaultParameters();
		PARAMS.add(SQRT);
		PARAMS.add(COTANGENT);
		PARAMS.add(SECANT);
		PARAMS.add(COSECANT);
	}
	
	/**
	 * ExtendedDoubleEvaluator- Constructor to override existing parameter set
	 */
	public ExtendedDoubleEvaluator() {
		super(PARAMS);
	}
	
	/**
	 * evaluate- Executes equation from inputField in CalcWindow
	 * @param function- Function to be used on provided numbers
	 * @param arguments- Numbers that function will operatet on
	 * @param evaluationContext
	 * @return Square root of arguments- if function is SQRT
	 * 			Cotangent of arguments- if function is COTANGENT
	 * 			Secant of arguments- if function is SECANT
	 * 			Cosecant of arguments- if function is COSECANT
	 * 			Solution through standard evaluator if no other choices apply
	 */
	protected Double evaluate(Function function, Iterator<Double> arguments, Object evaluationContext) {
	    if (function == SQRT) {
	    	return Math.sqrt(arguments.next());
	    }
	    else if (function == COTANGENT){
	    	return 1/Math.tan(arguments.next());
	    }
	    else if (function == SECANT) {
	    	return 1/Math.cos(arguments.next());
	    }
	    else if (function == COSECANT) {
	    	return 1/Math.sin(arguments.next());
	    }
	    else {
	      return super.evaluate(function, arguments, evaluationContext);
	    }
	}	  
}