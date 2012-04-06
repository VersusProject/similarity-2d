
/*
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of
 * their official duties. Pursuant to title 17 Section 105 of the United
 * States Code this software is not subject to copyright protection and is
 * in the public domain. This software is an experimental system. NIST assumes
 * no responsibility whatsoever for its use by other parties, and makes no
 * guarantees, expressed or implied, about its quality, reliability, or
 * any other characteristic. We would appreciate acknowledgment if the
 * software is used.
 *
 *
 *  @author  Benjamin Long, blong@nist.gov
 *  @version 1.1
 *    Date: Tue Jan  3 12:31:41 EST 2012
 */

package gov.nist.itl.versus.similarity.comparisons;

import java.util.ArrayList;

import edu.illinois.ncsa.versus.descriptor.impl.RGBHistogramDescriptor;
import edu.illinois.ncsa.versus.descriptor.impl.GrayscaleHistogramDescriptor;
import edu.illinois.ncsa.versus.measure.SimilarityNumber;

/*
 *  Metric calculation implementations
 *  
 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
 *
 *	description:	Each assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
 *  
 *  @author 		Benjamin Long, blong@nist.gov
 *  version:		1.0
 */	

public class MathOps 
{
	
	

	// ========================================================================
	// Histrogram-based metrics
	// ========================================================================	
	
	/*
	 *  metric family: 	Minkowski Family Metric
	 *  name:			Euclidean_L2
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *  pseudo_code1(in J language): 		0.5 mult [: sum [: abs sub
	 *  pseudo_code2(in J language,terse): 	0.5 * [: +/ [: | -
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */	
	public double d_euclidean( double P[], double Q[] ) {
		double d1[] = absSubSquared(P,Q);
		double d2 = sum(d1);
		double d3 = sqrt(d2);
		return d3;
	}

	/*
	 *  metric family: 	Minkowski Family Metric
	 *  name:			City block L1
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *  pseudo_code1(in J language): 		0.5 mult [: sum [: abs sub
	 *  pseudo_code2(in J language,terse): 	0.5 * [: +/ [: | -
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */		
	public double d_city_block( double P[], double Q[] ) {
		double d1[] = absSub(P,Q);
		double d2 = sum(d1);
		return d2;
	}

	/*
	 *  metric family: 	Minkowski Family Metric
	 *  name:			Minkowski_LP
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *  pseudo_code1(in J language): 		0.5 mult [: sum [: abs sub
	 *  pseudo_code2(in J language,terse): 	0.5 * [: +/ [: | -
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double d_minkowski( double P[], double Q[], int power ) {
		double d1[] = absSubPower(P,Q,power);
		double d2 = sum(d1);
		double d3 = root(d2,power);
		return d3;
	}
	
	/*
	 *  metric family: 	Minkowski Family Metric
	 *  name:			Chebyshev L_INF
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *  pseudo_code1(in J language): 		0.5 mult [: sum [: abs sub
	 *  pseudo_code2(in J language,terse): 	0.5 * [: +/ [: | -
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */				 

	/*// need to think about meaning of: max_i |P_i - Q_i|	
	public double d_chebyshev( double P[], double Q[] ) {
		double d1[] = absSub
	}
	*/

	/*
	 *  metric family: 	L1 Family Metric
	 *  name:			Sorensen
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *  pseudo_code1(in J language): 		0.5 mult [: sum [: abs sub
	 *  pseudo_code2(in J language,terse): 	0.5 * [: +/ [: | -
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */				 	
	public double d_sorensen( double P[], double Q[] )
	{
		// top
		double d1[] = absSub(P,Q);
		double d2 = sum(d1);		
		// bottom
		double d3[] = add(P,Q);
		double d4 = sum(d3);
		double d5 = div(d2,d4);
		return d5;
	}

	/*
	 *  metric family: 	L1 Family Metric
	 *  name:			Gower
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *  pseudo_code1(in J language): 		0.5 mult [: sum [: abs sub
	 *  pseudo_code2(in J language,terse): 	0.5 * [: +/ [: | -
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */				 		
	public double d_gower( double P[], double Q[], double R[] )
	{
		double d1[] = absSubDiv(P,Q,R);
		double d2 = sum(d1);
		double d3 = multByReciprocal(d2, P.length);
		return d3;
	}

	/*
	 *  metric family: 	L1 Family Metric
	 *  name:			Gower, complement
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *					Is supposed to be equivalent to: 1 - d_gower()
	 *
	 *  pseudo_code1(in J language): 		0.5 mult [: sum [: abs sub
	 *  pseudo_code2(in J language,terse): 	0.5 * [: +/ [: | -
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */				 				
	public double d_gower2( double P[], double Q[], double R[] )
	{
		// TBD
		return 0.0;
	}

	/*
	 *  metric family: 	L1 Family Metric
	 *  name:			Soergel
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *  pseudo_code1(in J language): 		0.5 mult [: sum [: abs sub
	 *  pseudo_code2(in J language,terse): 	0.5 * [: +/ [: | -
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */				 					
	public double d_soergel( double P[], double Q[] )
	{
		// top
		double d1[] = absSub(P,Q);
		double d2 = sum(d1);
		// bottom
		double d3[] = max(P,Q);
		double d4 = sum(d3);
		double d5 = div(d2,d4);
		return d5;		
	}

	/*
	 *  metric family: 	L1 Family Metric
	 *  name:			Kulczynski d
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *  pseudo_code1(in J language): 		0.5 mult [: sum [: abs sub
	 *  pseudo_code2(in J language,terse): 	0.5 * [: +/ [: | -
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */				 						
	public double d_kulczynski( double P[], double Q[] ) 
	{
		// top
		double d1[] = absSub(P,Q);
		double d2 = sum(d1);
		// bottom
		double d3[] = min(P,Q);
		double d4 = sum(d3);
		double d5 = div(d2,d4);
		// return (double) d2/d4;
		return d5;
	}	

	/*
	 *  metric family: 	L1 Family Metric
	 *  name:			Canberra
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *  pseudo_code1(in J language): 		0.5 mult [: sum [: abs sub
	 *  pseudo_code2(in J language,terse): 	0.5 * [: +/ [: | -
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */				 						
	public double d_canberra( double P[], double Q[] )
	{
		double d1[] = absSub(P,Q);
		double d2[] = add(P,Q);
		double d3[] = div(d1,d2);
		double d4 = sum(d3);
		return d4;
	}

	/*
	 *  metric family: 	L1 Family Metric
	 *  name:			Lorentzian
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *  pseudo_code1(in J language): 		0.5 mult [: sum [: abs sub
	 *  pseudo_code2(in J language,terse): 	0.5 * [: +/ [: | -
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */				 							
	public double d_lorentzian( double P[], double Q[] )
	{
		double d1[] = absSub(P,Q);
		double d2[] = add(d1, 1);
		double d3[] = ln(d2);
		double d4 = sum(d3);
		return d4;
	}

	/*
	 *  metric family: 	Intersection Family Metric
	 *  name:			Intersection
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		[: sum min
	 *  pseudo_code2(in J language,terse): 	[: +/ <.
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */
	public double d_intersection_IS( double P[], double Q[] )
	{
		double d1[] = min(P,Q);
		double d2 = sum(d1);
		return d2;
	}

	/*
	 *  metric family: 	Intersection Family Metric
	 *  name:			Intersection, complement
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *					Is supposed to be equivalent to 1 - d_intersection();
	 *
	 *  pseudo_code1(in J language): 		0.5 mult [: sum [: abs sub
	 *  pseudo_code2(in J language,terse): 	0.5 * [: +/ [: | -
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */
	
	public double d_intersection_nonIS( double P[], double Q[] )
	{
		double d1[] = absSub(P,Q);
		double d2 = sum(d1);
		double d3 = multByReciprocal(d2,2);
		return d3;
	}
	
	/*
	 *  metric family: 	Intersection Family Metric
	 *  name:			Wave Hedges
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		[: sum 1 sub min div max
	 *  pseudo_code2(in J language,terse): 	[: +/ 1 - <. % >.
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */	
	public double d_wave_hedges1( double P[], double Q[] )
	{
		double d1[] = min(P,Q);
		double d2[] = max(P,Q);
		double d3[] = div(d1,d2);
		double d4[] = sub(1,d3);
		double d5 = sum(d4);
		return d5;
	}
	
	
	/*
	 *  metric family: 	Intersection Family Metric
	 *  name:			Wave Hedges, alternative form
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		[: sum ([: abs sub) div max
	 *  pseudo_code2(in J language,terse): 	[: +/ ([: | -) % >.
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */		
	public double d_wave_hedges2( double P[], double Q[] )
	{
		double d1[] = absSub(P,Q);
		double d2[] = max(P,Q);
		double d3 = sum(d2);
		return d3;
	}

	/*
	 *  metric family: 	Intersection Family Metric
	 *  name:			Czekanowski
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		(2 mult [: sum min) div [: sum add
	 *  pseudo_code2(in J language,terse): 	(2 * [: +/ <.) % [: +/ +
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double d_czekanowski1( double P[], double Q[] )
	{
		double d1[] = min(P,Q);
		double d2 = sum(d1);
		double d3 = mult(2,d2);
		
		double d4[] = add(P,Q);
		double d5 = sum(d4);
		//double d6 = d3/d5;
		double d6 = div(d3,d5);
		return d6;
	}

	/*
	 *  metric family: 	Intersection Family Metric
	 *  name:			Czekanowski, complement
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *					Is supposed to be equivalent to 1 - d_czekanowski1();
	 *
	 *  pseudo_code1(in J language): 		[: sum ([: abs sub) div [: sum add
	 *  pseudo_code2(in J language,terse): 	[: +/ ([: | -) % [: +/ +
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */				
	public double d_czekanowski2( double P[], double Q[] )
	{
		double d1[] = absSub(P,Q);
		double d2 = sum(d1);		
		double d3[] = add(P,Q);
		double d4 = sum(d3);
		//double d5 = d2/d4;
		double d5 = div(d2,d4);
		return d5;
	}

	/*
	 *  metric family: 	Intersection Family Metric
	 *  name:			Motyka
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *  pseudo_code1(in J language): 		([: sum min) div [: sum add
	 *  pseudo_code2(in J language,terse): 	([: +/ <.) % [: +/ +
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */					
	public double d_motyka1( double P[], double Q[] )
	{
		double d1[] = min(P,Q);
		double d2 = sum(d1);
		double d3[] = add(P,Q);
		double d4 = sum(d3);
		//double d5 = d2/d4;
		double d5 = div(d2,d4);
		return d5;
	}

	/*
	 *  metric family: 	Intersection Family Metric
	 *  name:			Motyka, complement
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *			
	 *					Is supposed to be equivalent to 1 - d_motyka1();
	 *							
	 *  pseudo_code1(in J language): 		[: sum ([: sum max) div [: sum add
	 *  pseudo_code2(in J language,terse): 	[: +/ ([: +/ >.) % [: +/ +
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	
	public double d_motyka2( double P[], double Q[] )
	{
		double d1[] = max(P,Q);
		double d2 = sum(d1);
		double d3[] = add(P,Q);
		double d4 = sum(d3);
		//double d5 = d2/d4;
		double d5 = div(d2,d4);
		return d5;
	}

	/*
	 *  metric family: 	Intersection Family Metric
	 *  name:			Kulczynski s
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *  pseudo_code1(in J language): 		([: sum min) div [: sum [: abs sub
	 *  pseudo_code2(in J language,terse): 	([: +/ <.) % [: +/ [: | -
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */		
	public double d_kulczynski_s( double P[], double Q[] )
	{
		double d1[] = min(P,Q);
		double d2 = sum(d1);
		double d3[] = absSub(P,Q);
		double d4 = sum(d3);
		//double d5 = d2/d4;
		double d5 = div(d2,d4);
		return d5;
	}
	
	/*
	 *  metric family: 	Intersection Family Metric
	 *  name:			Ruzicka
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *  pseudo_code1(in J language): 		([: sum min) div [: sum max
	 *  pseudo_code2(in J language,terse): 	([: +/ <.) % [: +/ >.
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */	
	public double d_ruzicka( double P[], double Q[] )
	{
		double d1[] = min(P,Q);
		double d2 = sum(d1);
		double d3[] = max(P,Q);
		double d4 = sum(d3);
		//double d5 = d2/d4;
		double d5 = div(d2,d4);
		return d5;		
	}

	/*
	 *  metric family: 	Intersection Family Metric
	 *  name:			Tanimoto
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *  pseudo_code1(in J language): 		(([: sum [) add ([: sum ]) sub 2 mult [: sum min) div ([: sum [) add ([: sum ]) sub [: sum min
	 *  pseudo_code2(in J language,terse): 	(([: +/ [) + ([: +/ ]) - 2 * [: +/ <.) % ([: +/ [) + ([: +/ ]) - [: +/ <.
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */		
	public double d_tanimoto1( double P[], double Q[] )
	{
		double d1[] = min(P,Q);
		double d2 = sum(d1);
		double d3 = mult(2,d2);
		double d4 = sum(Q);
		
		double d5 = sub(d4,d3);
		double d6 = sum(P);
		double d7 = add(d6,d5);
		
		double d8[] = min(P,Q);
		double d9   = sum(d8);
		double d10  = sum(Q);
		double d11  = sub(d10,d9);
		double d12  = sum(P);
		double d13  = add(d12,d11);
		
		double d14  = div(d7,d13);
		
		/*
		double d5 = sum(d4);
		double d6[] = add(P,d5);
		double d7 = sum(d6);
		
		double d8[] = min(P,Q);
		double d9 = sum(d8);
		double d10[] = sub(Q,d9);
		double d11 = sum(d10);
		double d12[] = add(P,d11);
		double d13 = sum(d12);		
		double d14 = div(d7,d13);
		*/
		
		return d14;
	}

	/*
	 *  metric family: 	Intersection Family Metric
	 *  name:			Tanimoto, alternative form
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *  pseudo_code1(in J language): 		([: sum max sub min) div [: sum max
	 *  pseudo_code2(in J language,terse): 	([: +/ >. - <.) % [: +/ >.
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */		
	public double d_tanimoto2( double P[], double Q[] )
	{
		double d1[] = min(P,Q);
		double d2[] = max(P,Q);
		double d3[] = sub(d2,d1);
		double d4 = sum(d3);
		
		double d5[] = max(P,Q);
		double d6 = sum(d5);
		
		//double d7 = d4/d6;
		double d7 = div(d4,d6);
		return d7;
	}

	
	/*
	 *  metric family: 	Inner Product Family Metric
	 *  name:			Inner Product
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */		
	public double d_inner_product( double P[], double Q[] )
	{
		double d1[] = mult(P,Q);
		double d2 = sum(d1);
		return d2;
	}

	/*
	 *  metric family: 	Inner Product Family Metric
	 *  name:			Harmonic Mean
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */		
	
	public double d_harmonic_mean( double P[], double Q[] )
	{
		double d1[] = mult(P,Q);
		double d2[] = add(P,Q);
		double d3[] = div(d1,d2);
		double d4 = sum(d3);
		double d5 = mult(2,d4);
		return d5;
	}

	/*
	 *  metric family: 	Inner Product Family Metric
	 *  name:			Cosine
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */		
	
	public double d_cosine( double P[], double Q[] )
	{
		double d1[] = mult(P,Q);
		double d2 = sum(d1);
		
		double d3[] = square(Q);
		double d4 = sum(d3);
		double d5 = sqrt(d4);
		
		double d6[] = square(P);
		double d7 = sum(d6);
		double d8 = sqrt(d7);
		double d9 = mult(d8,d5);
		
		double d10 = div(d2,d9);
		return d10;
	}

	/*
	 *  metric family: 	Inner Product Family Metric
	 *  name:			Kumar-Hassebrook (PCE)
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */		
	
	public double d_kumar_hassebrook_pce( double P[], double Q[] )
	{
		double d1[] = mult(P,Q);
		double d2 = sum(d1);

		double d3[] = mult(P,Q);	// note: redundant!
		double d4 = sum(d3);
		double d5[] = square(Q);
		double d6 = sum(d5);
		double d7[] = square(P);
		double d8 = sum(d7);
		double d9 = add(d8,d6);
		double d10 = sub(d9,d4);
		double d11 = div(d2,d10);		
		return d11;
	}

	/*
	 *  metric family: 	Inner Product Family Metric
	 *  name:			Jaccard
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */		
	
	public 	double d_jaccard1( double P[], double Q[] )
	{
		// NOTE: Seems to be identical to the previous!
		return d_kumar_hassebrook_pce(P,Q);
	}

	/*
	 *  metric family: 	Inner Product Family Metric
	 *  name:			Jaccard, complement
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *					
	 *					Is supposed to be equivalent to: 1 - d_jaccard1();
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */		
	
	public double d_jaccard2( double P[], double Q[] ) // = (1 - d_jaccard1)
	{
		double d1[] = sub(P,Q);
		double d2[] = square(d1);
		double d3 = sum(d2);

		double d4[] = mult(P,Q);	
		double d5 = sum(d4);
		double d6[] = square(Q);
		double d7 = sum(d6);
		double d8[] = square(P);
		double d9 = sum(d8);
		double d10 = add(d9,d7);
		double d11 = sub(d10,d5);
		double d12 = div(d3,d11);		
		return d12;
	}

	/*
	 *  metric family: 	Inner Product Family Metric
	 *  name:			Dice
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */		
	
	public double d_dice1( double P[], double Q[] )
	{
		double d1[] = mult(P,Q);
		double d2 = sum(d1);
		double d3 = mult(2,d2);
		
		double d4[] = square(Q);
		double d5 = sum(d4);
		double d6[] = square(P);
		double d7 = sum(d6);
		double d8 = add(d7,d5);
		
		double d9 = div(d3,d8);
		return d9;		
	}

	/*
	 *  metric family: 	Inner Product Family Metric
	 *  name:			Dice, complement
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *					Is supposed equivalent to: 1 - dice1();
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */		
	
	public double d_dice2( double P[], double Q[] )	// = (1 - d_dice1)
	{
		double d1[] = sub(P,Q);
		double d2[] = square(d1);
		double d3 = sum(d2);
		
		double d4[] = square(Q);
		double d5 = sum(d4);
		double d6[] = square(P);
		double d7 = sum(d6);
		double d8 = add(d7,d5);
		
		double d9 = div(d3,d8);
		return d9;		
	}

	/*
	 *  metric family: 	Fidelity/Chi-Squared Family Metric
	 *  name:			Fidelity
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */		
	
	public double d_fidelity( double P[], double Q[] )
	{
		double d1[] = mult(P,Q);
		double d2[] = sqrt(d1);
		double d3 = sum(d2);
		return d3;		
	}

	/*
	 *  metric family: 	Fidelity/Chi-Squared Family Metric
	 *  name:			Bhattacharyya
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */		
	public double d_bhattacharyya( double P[], double Q[] )
	{
		double d1[] = mult(P,Q);
		double d2[] = sqrt(d1);
		double d3 = sum(d2);		// NOTE: I think the first part of this one is basically d_fidelity
		double d4 = negLn(d3);
		return d4;
	}	

	/*
	 *  metric family: 	Fidelity/Chi-Squared Family Metric
	 *  name:			Hellinger
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double d_hellinger1(  double P[], double Q[] )
	{
		double d1[] = sqrt(Q);
		double d2[] = sqrt(P);
		double d3[] = sub(d2,d1);
		double d4[] = square(d3);
		double d5 = sum(d4);
		double d6 = mult(2,d5);
		double d7 = sqrt(d6);
		return d7;
	}

	/*
	 *  metric family: 	Fidelity/Chi-Squared Family Metric
	 *  name:			Hellinger, alternative form
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	
	public double d_hellinger2(  double P[], double Q[] )
	{
		double d1[] = mult(P,Q);
		double d2[] = square(d1);
		double d3 = sum(d2);
		double d4 = sub(1,d3);
		double d5 = sqrt(d4);
		double d6 = mult(2,d5);
		return d6;
	}
	
	/*
	 *  metric family: 	Fidelity/Chi-Squared Family Metric
	 *  name:			Matusita
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	
	public double d_matusita1(  double P[], double Q[] )
	{
		double d1[] = sqrt(P);
		double d2[] = sqrt(Q);
		double d3[] = sub(d1,d2);
		double d4[] = square(d3);
		double d5 = sum(d4);		// NOTE: up through d5 is d_squared_chord()!
		double d6 = sqrt(d5);
		return d6;
	}

	/*
	 *  metric family: 	Fidelity/Chi-Squared Family Metric
	 *  name:			Matusita, alternative form
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	
	public double d_matusita2(  double P[], double Q[] )
	{
		double d1[] = mult(P,Q);
		double d2[] = sqrt(d1);
		double d3 = sum(d2);
		double d4 = mult(2,d3);
		double d5 = sub(2,d4);
		double d6 = sqrt(d5);
		return d6;
	}
	
	/*
	 *  metric family: 	Fidelity/Chi-Squared Family Metric
	 *  name:			Squared-chord
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */				
	public double d_squared_chord1(  double P[], double Q[] ) 
	{
			double d1[] = sqrt(P);
			double d2[] = sqrt(Q);
			double d3[] = sub(d1,d2);
			double d4[] = square(d3);
			double d5 = sum(d4);
			return d5;		
	}

	/*
	 *  metric family: 	Fidelity/Chi-Squared Family Metric
	 *  name:			Squared-chord, complement
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *					Is supposed equivalent to: 1 - d_squared_chord1()
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */				
	public double d_squared_chord2(  double P[], double Q[] ) 
	{
			double d1[] = mult(P,Q);
			double d2[] = sqrt(d1);
			double d3[] = sub(d2,1);
			double d4 = sum(d3);
			double d5 = mult(2,d4);		// NOTE: Is very similar to matusita2()
			return d5;
	}

	/*
	 *  metric family: 	Squared_L2/Chi-Squared Family Metric
	 *  name:			Squared Euclidean
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */					
	public double d_squared_euclidean(  double P[], double Q[] ) 
	{
		double d1[] = sub(P,Q);
		double d2[] = square(d1);
		double d3 = sum(d2);
		return d3;
	}

	/*
	 *  metric family: 	Squared_L2/Chi-Squared Family Metric
	 *  name:			Pearson Chi-Squared
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */					
	public double d_pearson_chiSquared(  double P[], double Q[] ) 
	{
		double d1[] = sub(P,Q);
		double d2[] = square(d1);
		double d3[] = div(d2,Q);
		double d4 = sum(d3);
		return d4;
	}

	/*
	 *  metric family: 	Squared_L2/Chi-Squared Family Metric
	 *  name:			Neyman Chi-Squared
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */						
	public double d_neyman_chiSquared(  double P[], double Q[] ) 
	{
		double d1[] = sub(P,Q);
		double d2[] = square(d1);
		double d3[] = div(d2,P);
		double d4 = sum(d3);
		return d4;
	}

	/*
	 *  metric family: 	Squared_L2/Chi-Squared Family Metric
	 *  name:			Squared Chi-Squared
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */							
	public double d_squared_chiSquared(  double P[], double Q[] ) 
	{
		double d1[] = sub(P,Q);
		double d2[] = square(d1);
		double d3[] = add(P,Q);
		double d4[] = div(d2,d3);
		double d5 = sum(d4);
		return d5;
	}

	/*
	 *  metric family: 	Squared_L2/Chi-Squared Family Metric
	 *  name:			Probabilistic Symmetric Chi-Squared
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */								
	public double d_probabilistic_symmetric_chiSquared(  double P[], double Q[] ) 
	{
		double d1[] = sub(P,Q);
		double d2[] = square(d1);
		double d3[] = add(P,Q);
		double d4[] = div(d2,d3);
		double d5 = sum(d4);
		double d6 = mult(2,d5);		// NOTE: Is d_squared_chiSquared * 2;
		return d6;
	}

	/*
	 *  metric family: 	Squared_L2/Chi-Squared Family Metric
	 *  name:			Divergence
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */									
	public double d_divergence(  double P[], double Q[] ) 
	{
		double d1[] = sub(P,Q);
		double d2[] = square(d1);
		double d3[] = add(P,Q);
		double d4[] = square(d3);
		double d5[] = div(d2,d4);
		double d6 = sum(d5);
		double d7 = mult(2,d6);		// NOTE: Is d_probabilistic_symm* w/denominator squared;
		return d7;
	}

	/*
	 *  metric family: 	Squared_L2/Chi-Squared Family Metric
	 *  name:			Clark
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */										
	public double d_clark(  double P[], double Q[] ) 
	{
		double d1[] = absSub(P,Q);
		double d2[] = add(P,Q);
		double d3[] = div(d1,d2);
		double d4[] = square(d3);
		double d5 = sum(d4);
		double d6 = sqrt(d5); 
		return d6;
	}

	/*
	 *  metric family: 	Squared_L2/Chi-Squared Family Metric
	 *  name:			Additive Symmetric Chi-Squared
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */											
	public double d_additive_symmetric_chiSquared(  double P[], double Q[] ) 
	{
		double d1[] = sub(P,Q);
		double d2[] = square(d1);
		double d3[] = add(P,Q);
		double d4[] = mult(P,Q);
		double d5[] = mult(d2,d3);
		double d6[] = div(d5,d4);
		double d7 = sum(d6);
		return d7;
	}

	/*
	 *  metric family: 	Shannon's Entropy Family Metric
	 *  name:			Kullback-Liebler
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */												
	public double d_kullback_leibler(  double P[], double Q[] ) 
	{
		double d1[] = div(P,Q);
		double d2[] = ln(d1);
		double d3[] = mult(P,d2);
		double d4 = sum(d3);
		return d4;
	}

	/*
	 *  metric family: 	Shannon's Entropy Family Metric
	 *  name:			Jeffreys
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */													
	public double d_jeffreys(  double P[], double Q[] ) 
	{
		double d1[] = div(P,Q);
		double d2[] = ln(d1);
		double d3[] = sub(P,Q);		// NOTE: except for this step, conforms to pattern of d_kullback_leibler.
		double d4[] = mult(P,d3);
		double d5 = sum(d4);		
		return d5;
	}

	/*
	 *  metric family: 	Shannon's Entropy Family Metric
	 *  name:			K divergence
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */														
	public double d_k_divergence(  double P[], double Q[] ) 
	{
		double d1[] = mult(2,P);
		double d2[] = add(P,Q);
		double d3[] = div(d1,d2);
		double d4[] = ln(d3);
		double d5[] = mult(P,d4);	
		double d6   = sum(d5);
		return d6;
	}

	/*
	 *  metric family: 	Shannon's Entropy Family Metric
	 *  name:			Topsoe
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */															
	public double d_topsoe(  double P[], double Q[] ) 
	{
		double d1[] = mult(2,Q);
		double d2[] = add(P,Q);
		double d3[] = div(d1,d2);
		double d4[] = ln(d3);
		double d5[] = mult(Q,d4);
		double d6[] = mult(2,P);
		double d7[] = add(P,Q);
		double d8[] = div(d6,d7);
		double d9[] = ln(d8);
		double d10[]= mult(P,d9);
		double d11[]= add(d10,d5);
		double d12= sum(d11);
		return d12;
	}

	/*
	 *  metric family: 	Shannon's Entropy Family Metric
	 *  name:			Jensen-Shannon
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */																
	public double d_jensen_shannon(  double P[], double Q[] ) 
	{
		double d1[] = mult(2,Q);
		double d2[] = add(P,Q);
		double d3[] = div(d1,d2);
		double d4[] = ln(d3);
		double d5[] = mult(Q,d4);
		double d6 = sum(d5);
		
		double d7[] = mult(2,P);
		double d8[] = add(P,Q);
		double d9[] = div(d7,d8);
		double d10[] = ln(d9);
		double d11[]= mult(P,d10);
		double d12 = sum(d11);
	
		double d13 = add(d12,d6);		
		double d14 = mult(0.5, d13);
		return d14;
	}

	/*
	 *  metric family: 	Shannon's Entropy Family Metric
	 *  name:			Jensen difference
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */																	
	public double d_jensen_difference(  double P[], double Q[] ) 
	{
		double d1[] = add(P,Q);
		double d2[] = div(P,Q);
		double d3[] = ln(d2);
		double d4[] = mult(d2,d3);
		
		double d5[] = ln(Q);
		double d6[] = mult(Q,d5);
		double d7[] = ln(P);
		double d8[] = mult(P,d7);
		double d9[] = add(d8,d6);
		double d10[]= div(d9,2);
		
		double d11[]= sub(d10,d4);
		double d12  = sum(d11);
		return d12;
	}

	/*
	 *  metric family: 	Combinations Family Metric
	 *  name:			Taneja difference
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */																		
	public double d_taneja_difference(  double P[], double Q[] ) 
	{
		double d1[] = add(P,Q);
		double d2[] = mult(P,Q);
		double d3[] = sqrt(d2);
		double d4[] = mult(2,d3);
		double d5[] = div(d1,d4);
		
		double d6[] = ln(d5);
		
		double d7[] = div(d1,2);
		
		double d8[] = mult(d7,d5);  // BJL: Check - is this a) sumA + lnB or b) sum_over_all?
		double d9   = sum(d8);
	
		return d9;					
	}
	
	
	/*
	 *  metric family: 	Combinations Family Metric
	 *  name:			Kumar-Johnson
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */																			
	public double d_kumar_johnson_difference(  double P[], double Q[] ) 
	{
		double d1[] = square(P);
		double d2[] = square(Q);
		double d3[] = sub(d1,d2);
		double d4[] = square(d3);
		
		double d5[] = mult(P,Q);
		double d6[] = root(d5,3);
		double d7[] = sqrt(d6);
		double d8[] = mult(2,d7);
		
		double d9[] = div(d4,d8);
		double d10  = sum(d9);
		
		return d10;
	}

	/*
	 *  metric family: 	Combinations Family Metric
	 *  name:			Avg(L_1, L_INF)
	 *  @see 			<a href="http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf">http://www.wseas.us/e-library/conferences/2008/harvard/math/49-577-887.pdf</a>
	 *
	 *	description:	Assumes 2 probability distribution functions (1D vector PDFs) as input and generates a single numerical output.
	 *					Is called by appropriate related class in gov.nist.itl.versus.similarity.comparisons.measure.impl.* .
	 *
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */																				
	public double d_avg_difference(  double P[], double Q[] ) 
	{
		double d1[] = absSub(P,Q);
		double d2   = max(d1);		// BJL: For some reason I think this should generate a list of max values. But what exactly?
		double d3[] = add(d1,d2);
		double d4   = sum(d3);
		double d5   = div(d4,2);
		return d5;
	}
	
	// ========================================================================
	// Labelled metrics
	// ========================================================================	
	/*
	 * Adjusted Rand Index : (ARI)
	 */

	public double d_ari( ImageData img1, ImageData img2 )
	{
		ImageData op;
		ImageData Reference_Image = img1;
		ImageData Compare_Image   = img2;
		double ri 		= 0.0;	// RandIndex		
		double ari 		= 0.0;	// AdjustedRandIndex
		op = new ImageData();	
		
		ImageData valid_indices = op.logical( op.add(Reference_Image, Compare_Image) );
		int N = (op.vectorize(valid_indices)).size();
		ArrayList ref_nonZ = op.mask(Reference_Image, valid_indices);
		ArrayList cmp_nonZ = op.mask(Compare_Image, valid_indices);
		ArrayList ref_uniq = op.uniqueSort(ref_nonZ);
		ArrayList cmp_uniq = op.uniqueSort(cmp_nonZ);
		int ref_sz = ref_uniq.size();
		int cmp_sz = cmp_uniq.size();
		double helper_matrix[][] = op.makeZeroMatrix2D(cmp_sz, ref_sz, 0.0);
		ArrayList temp = null;
		ArrayList temp2 = null;
		ArrayList temp3 = null;
		double num_pairs_A = 0;
		double num_pairs_B = 0;
		double num_pairs_C = 0;
		double row_sum = 0.0;
		double col_sum = 0.0;
		double num_pairs_with_same_label = 0.0;
		double num_pairs_with_diff_labels = 0.0;
		double Expected_Index = 0.0;
		double Max_Index = 0.0;
		double Adj_Rand_Index = 0.0;
		double Rand_Index = 0.0;
		
		for (int j=0; j < ref_sz; j++) {
			for (int i=0; i < cmp_sz; i++ ) {
				temp = op.getMatches(cmp_nonZ, (Double)cmp_uniq.get(i));
				temp2= op.getMatches(ref_nonZ, (Double)ref_uniq.get(j));
				temp3= op.and(temp, temp2);
				helper_matrix[i][j] = op.sum(temp3);
			}
		}

		for (int j=0; j < ref_sz; j++) {
			for (int i=0; i < cmp_sz; i++ ) {
				num_pairs_A += op.nChoose2(helper_matrix[i][j]);
			}
		}		
			
		for (int j=0; j < ref_sz; j++) {
				col_sum = 0.0;
				col_sum = op.sum( op.getColumn(helper_matrix, ref_sz, j) );
				num_pairs_C += op.nChoose2( col_sum );
		}
		
		for (int i=0; i < cmp_sz; i++) {
				row_sum = 0.0;
				row_sum = op.sum( op.getRow(helper_matrix, ref_sz, i) );
				num_pairs_B += op.nChoose2( row_sum );
		}
		
		num_pairs_with_same_label = num_pairs_A;
		num_pairs_with_diff_labels = op.nChoose2(N) + num_pairs_A - num_pairs_B - num_pairs_C;
		Expected_Index = (num_pairs_C*num_pairs_B/op.nChoose2(N));
		Max_Index = 0.5*(num_pairs_C+num_pairs_B);
		Adj_Rand_Index  = (num_pairs_with_same_label - Expected_Index)/(Max_Index-Expected_Index);
		Rand_Index = (num_pairs_with_same_label + num_pairs_with_diff_labels)/op.nChoose2(N);
		
		ri=Rand_Index;
		ari=Adj_Rand_Index;		

		return ari;
	}	
		
	/*
	 * Total Error Rate Evaluation Measure: (TEE)
	 */

	public double d_tee( ImageData img1, ImageData img2 )
	{
		 ImageData op = new ImageData();
		 double si 		= 0.0;
		 double fp 		= 0.0;
		 double fn 		= 0.0;
		 double in_TE 	= 0.0;
		 double tet 		= 0.0;
		 double tee 		= 0.0;
		 
			ImageData im1 = img1;
			ImageData im2 = img2;
			
			ImageData not1			= null;
			ImageData not2			= null;
			ImageData im1And2		= null;
			ImageData imNot1And2	= null;
			ImageData im1AndNot2	= null;
			ImageData im1Or2		= null;

			if ( !op.sameSize(im1,im2)) {
				op.output("error: image sizes are not equal");
				return -1;
			}
			
			try {
				not1		= op.not(im1);
				not2		= op.not(im2);
				im1And2		= op.and(im1,im2);
				imNot1And2	= op.and(not1,im2);
				im1AndNot2	= op.and(im1,not2);
				im1Or2		= op.or(im1,im2);
			}
			catch( Exception e ) {
				op.output( e.getMessage() );
				return -1;
			}
			
			double sum1			= op.sum(im1);
			double sum2			= op.sum(im2);
			double sum1And2		= op.sum(im1And2);
			double sumNot1and2	= op.sum(imNot1And2);
			double sum1AndNot2	= op.sum(im1AndNot2);
			double sum1Or2		= op.sum(im1Or2);

			si 		= (double)sum1And2 / sum1Or2;
			fp 		= (double)sumNot1and2 / sum1Or2;
			fn 		= (double)sum1AndNot2 / sum1Or2;
			in_TE 	= (double)sum1And2;
			tet 	= (double)sum1And2 / sum1;
			if ( sum2 == 0 ) 
				tee = 1.0;
			else
				tee = (double)sum1And2 / sum2;

			tee = op.d2b(tee);
			return tee;		
	}
	
	/*
	 * Total Error Rate Test Measure: (TET)
	 */

	public double d_tet( ImageData img1, ImageData img2 )
	{
		 ImageData op = new ImageData();
		 double si 		= 0.0;
		 double fp 		= 0.0;
		 double fn 		= 0.0;
		 double in_TE 	= 0.0;
		 double tet 		= 0.0;
		 double tee 		= 0.0;
		 
			ImageData im1 = img1;
			ImageData im2 = img2;
			
			ImageData not1			= null;
			ImageData not2			= null;
			ImageData im1And2		= null;
			ImageData imNot1And2	= null;
			ImageData im1AndNot2	= null;
			ImageData im1Or2		= null;

			if ( !op.sameSize(im1,im2)) {
				op.output("error: image sizes are not equal");
				return -1;
			}
			
			try {
				not1		= op.not(im1);
				not2		= op.not(im2);
				im1And2		= op.and(im1,im2);
				imNot1And2	= op.and(not1,im2);
				im1AndNot2	= op.and(im1,not2);
				im1Or2		= op.or(im1,im2);
			}
			catch( Exception e ) {
				op.output( e.getMessage() );
				return -1;
			}
			
			double sum1			= op.sum(im1);
			double sum2			= op.sum(im2);
			double sum1And2		= op.sum(im1And2);
			double sumNot1and2	= op.sum(imNot1And2);
			double sum1AndNot2	= op.sum(im1AndNot2);
			double sum1Or2		= op.sum(im1Or2);

			si 		= (double)sum1And2 / sum1Or2;
			fp 		= (double)sumNot1and2 / sum1Or2;
			fn 		= (double)sum1AndNot2 / sum1Or2;
			in_TE 	= (double)sum1And2;
			tet 	= (double)sum1And2 / sum1;
			if ( sum2 == 0 ) 
				tee = 1.0;
			else
				tee = (double)sum1And2 / sum2;

			tee = op.d2b(tee);
			return tet;			
	}

	// ========================================================================
	// Geometry-based metrics
	// ========================================================================
	
	/*
	 *  Mean-Squared Error measure.
	 */
	public double MSE(ImageData im1, ImageData im2)
	{
		// assumes imgs are identical size
		int M = im1.getHeight();
		int N = im1.getWidth();
		int bands = im1.getNumBands();
		double size = mult((double)M, (double)N);
		double sum = 0;
		
		/* BJL: Question - Should we operate on pixel-locations or multi-spectral pixel-values?
		/  solution 1: Operate on pixel-parts: could skew the algorithm??
		/  solution 2: Sum up the full pixel value for all bands at a given location and
		/			   remain true to the algorithm description.
		/
		/  I pick solution #2 for now. Need to verify this assumption.
		*/
		double[][][] pixels1 = im1.getValues();
		double[][][] pixels2 = im2.getValues();
		double x = 0;
		double y = 0;
		double mse = 0;
		
		for (int i=0; i < M; i++) {	// rows
			for (int j=0; j < N; j++) { // cols
					x = getCombinedPixelValue( pixels1[i][j] );
					y = getCombinedPixelValue( pixels2[i][j] );
					sum += square( sub(x,y) );
			}
		}
		
		mse = (1/(size)) * sum;
		
		return mse;
	}	
	
	public double getCombinedPixelValue( double[] bandsForPixel ) {
		return sum( bandsForPixel );		
	}
	
	/*
	 * Partial Hausdorf Distance measure.
	 */
	public double PHDM(ImageData im1, ImageData im2)
	{
		Point2D[] X = getPointSetFor(im1);
		Point2D[] Y = getPointSetFor(im2);
		int M = im1.getHeight();
		int N = im1.getWidth();
		double maxValue = 0;
		double sum = 0;
		
		for (int i=0; i < M; i++) {
			for (int j=0; j < N; j++) {
				maxValue = max(hN(X,Y,i,j), hN(Y,X,i,j));
				sum += maxValue;
			}
		}
		return sum;
	}
	
	public double hN(Point2D[] X, Point2D[] Y, int n, int i) { 
		return (pointFrom(X,n).getValue()) * D(pointFrom(X,i), pointFrom(Y,i)) ;
	}
	
	public double D( Point2D x, Point2D y) {
		return 0;
	}
	
	public Point2D pointFrom( Point2D[] pointSet, int n ) {
		int len = pointSet.length;
		if ( n >= 0 && n < len )
			return pointSet[n];
		else 
			return null;
	}
	
	public Point2D[] getPointSetFor( ImageData x ) {
		int M = x.getHeight();
		int N = x.getWidth();
		int bands = x.getNumBands();
		double pixelValue = 0;
		double[][][] pixels1 = x.getValues();
		Point2D[] pointSet = new Point2D[ M*N ];
		Point2D pt = null;
		
			for (int i=0; i < M; i++) {
				for (int j=0; j < N; j++) {
					pixelValue = this.getCombinedPixelValue(pixels1[i][j]);
					pointSet[((i*j)+j)] = new Point2D(i,j,pixelValue);
				}
			}
			return pointSet;
	}
	
	
	class Point2D
	{ 
		int x=0, y=0; double value=0;
		public Point2D(int x, int y, double value) {this.x=x; this.y=y; this.value=value; }
		public int getX(){return x; }
		public int getY(){return y; }
		public void setX(int x){this.x=x;}
		public void setY(int y){this.y=y;}
		public double getValue(){return value;}
		public void setValue(double value){this.value=value;}
	}
	
	/*
	 *  Closest-point Mean-Squared Error measure.
	 */
	public double MSEcp(ImageData im1, ImageData im2)
	{
		// assumes imgs are identical size
		int M = im1.getHeight();
		int N = im1.getWidth();
		int bands = im1.getNumBands();
		double size = mult((double)M, (double)N);
		double sum = 0;

		double[][][] pixels1 = im1.getValues();
		double[][][] pixels2 = im2.getValues();
		double x = 0;
		double y = 0;
		double mse = 0;

			//
		
		return mse;
	}	
	
	public double[] getXPointCloud( double[][][] pixels, int w, int h ) 
	{
		double[] points = new double[w*h];		 
		for (int i=0; i < h; i++) { // rows
			for (int j=0; j < w; j++) { // cols
				points[((i*j)+j)] = getCombinedPixelValue( pixels[i][j] );
			}
		}
		return points;
	}
	
	public double[] getYPointCloud( double[][][] pixels, int w, int h ) 
	{
		double[] points = new double[w*h];		 
		for (int i=0; i < h; i++) { // rows
			for (int j=0; j < w; j++) { // cols
				points[((i*j)+j)] = getCombinedPixelValue( pixels[i][j] );
			}
		}
		return points;
	}
	
	/*
	 * Pixel-correspondence metric
	 */
	public double PCM( ImageData x, ImageData y) {
		return  mult(100, sub(1, ( div( PCMcost(Mopt(x,y)), (double)calcXorY(x,y))) ) );
	}
	
	public double PCMcost( double m_opt )	// cost of optimal matching bet/w images; r is the localization-error
	{
		return m_opt;		// need more info here
	}
	
	public double Mopt(ImageData x, ImageData y) 
	{
		int M = x.getHeight();
		int N = x.getWidth();
		double[][][] pixels1 = x.getValues();		
		double m = 0;
		double xPixel = 0;
		int r = 2;	// currently set to 2 as in algorithm description
		
		for (int i=0; i < M; i++) {
			for (int j=0; j < N; j++) {
				xPixel = getCombinedPixelValue(pixels1[i][j]);
				m += MoptPixel(i,j,y,r,xPixel);
			}
		}
		return m;
	}
	
	double MoptPixel(int i, int j, ImageData y, int r, double xPixelValue )	// optimal match
	{
		int i1=i-r;
		int i2=i+r;
		int j1=j-r;
		int j2=j+r;
		int M = y.getHeight();
		int N = y.getWidth();
		double[][][] pixels2 = y.getValues();
		double yPixelValue = 0;
		double match = 0;
		double bestMatch = -1;
		if ( i1 < 0 ) i1=0;
		if ( i2 >= M) i2=(M-1);
		if ( j1 < 0 ) j1=0;
		if ( j2 >= N) j2=(N-1);
		
		for (int k=i1; k <= i2; k++) {
			for (int l=j1; l <= j2; l++) {
				// look for match in y's radius r of x's pixel x(i,j);				
				yPixelValue = getCombinedPixelValue(pixels2[i][j]);
				match = absSub( xPixelValue, yPixelValue );
				if ( bestMatch == -1 ) {
					bestMatch = match;
				} else {
					if ( match < bestMatch)
						bestMatch = match;
				}
			}						
		}
		return bestMatch;
	}
	
	public int calcXorY( ImageData x, ImageData y ) 
	{
		int a = calcA(x,y);
		int b = calcB(x);
		int c = calcC(y);
		return (a+b+c);
	}
	
	
	// Support for intensity-based metrics
	int calcA( ImageData im1, ImageData im2 ) {
		return sumCorrespondingPixelsWithValue(im1,im2,1) ; // identical locations in each having pixel_value=1
	}	

	int calcB( ImageData im1 ){
		return sumCorrespondingPixelsWithValue(im1,1);
	}

	int calcC( ImageData im2 ) {
		return sumCorrespondingPixelsWithValue(im2,1);
	}

	int calcD( ImageData im1, ImageData im2 ) {
		return sumCorrespondingPixelsWithValue(im1,im2,0); // identical locations in each having pixel_value=0
	}
	
	public int sumCorrespondingPixelsWithValue( ImageData x, ImageData y, double pixelValue ) {
		double[][] pixelValues1 = getCombinedPixelValues(x);
		double[][] pixelValues2 = getCombinedPixelValues(y);
		int matches = 0;
		int M = x.getHeight();
		int N = x.getWidth();
		double xPixel=0;
		double yPixel=0;
		
		for (int i=0; i < M; i++) {
			for (int j=0; j < N; j++) {
				xPixel = pixelValues1[i][j];
				yPixel = pixelValues2[i][j];
				if ( matchPixelValue(xPixel, yPixel, pixelValue) == true )
					matches++;
			}
		}
		return matches;
	}
	
	public int sumCorrespondingPixelsWithValue( ImageData x, double pixelValue ) {
		double[][] pixelValues1 = getCombinedPixelValues(x);
		int matches = 0;
		int M = x.getHeight();
		int N = x.getWidth();
		double xPixel=0;
		
		for (int i=0; i < M; i++) {
			for (int j=0; j < N; j++) {
				xPixel = pixelValues1[i][j];
				if ( xPixel == pixelValue )
					matches++;
			}
		}
		return matches;
	}
	
	
	public double sum( double[][] d ) {
		double total = 0;
		for (int i=0; i < d[0].length; i++ ) {
			total += sum( d[i] );
		}
		return total;
	}
	
	public double[][] getCombinedPixelValues( ImageData x ) {
		int M = x.getHeight();
		int N = x.getWidth();
		double[][][] pixels1 = x.getValues();
		double[][] pixelValues = new double[pixels1.length][pixels1[0].length];
		double xPixel=0;
		for (int i=0; i < M; i++) {
			for (int j=0; j < N; j++) {
				pixelValues[i][j] = this.getCombinedPixelValue(pixels1[i][j]);
			}
		}
		return pixelValues;
	}
	
	// checking pixel values from identical locations
	public boolean matchPixelValue( double xPixel, double yPixel, double pixelValue )
	{
		if ( xPixel==pixelValue && yPixel==pixelValue ) return true;		
		return false;		
	}
	
	public double CDMr(ImageData x, ImageData y) {
		return  mult(100, sub(1, ( div( MCDcost(MCD(x,y)), (double)calcXorY(x,y))) ) );
	}
	
	public double MCDcost( double m_opt )	// cost of optimal matching bet/w images; r is the localization-error
	{
		return m_opt;		// need more info here
	}
	
	public double MCD(ImageData x, ImageData y) 
	{
		int M = x.getHeight();
		int N = x.getWidth();
		double[][][] pixels1 = x.getValues();		
		double m = 0;
		double xPixel = 0;
		int r = 2;	// currently set to 2 as in algorithm description
		
		for (int i=0; i < M; i++) {
			for (int j=0; j < N; j++) {
				xPixel = getCombinedPixelValue(pixels1[i][j]);
				m += McdPixel(i,j,y,r,xPixel);
			}
		}
		return m;
	}

	double McdPixel(int i, int j, ImageData y, int r, double xPixelValue )	// optimal match
	{
		int i1=i-r;
		int i2=i+r;
		int j1=j-r;
		int j2=j+r;
		int M = y.getHeight();
		int N = y.getWidth();
		double[][][] pixels2 = y.getValues();
		double yPixelValue = 0;
		double match = 0;
		double bestMatch = -1;
		if ( i1 < 0 ) i1=0;
		if ( i2 >= M) i2=(M-1);
		if ( j1 < 0 ) j1=0;
		if ( j2 >= N) j2=(N-1);
		
		for (int k=i1; k <= i2; k++) {
			for (int l=j1; l <= j2; l++) {
				// look for match in y's radius r of x's pixel x(i,j);				
				yPixelValue = getCombinedPixelValue(pixels2[i][j]);
				match = absSub( xPixelValue, yPixelValue );
				if ( bestMatch == -1 ) {
					bestMatch = match;
				} else {
					if ( match < bestMatch)
						bestMatch = match;
				}
			}						
		}
		return bestMatch;
	}
	// ========================================================================
		
	/*
	 * NOTES:
	 *   Need to implement common error conditions for these metrics, once the essential logic
	 *   has been vetted.
	 *   
	 *   These include:
	 *   	1. vector operations
	 *   	2. 0 / 0
	 *   	3. division by zero
	 *   	4. 0 log0
	 *   	5. log of 0
	 * 
	 */
	
	public boolean checkForZeroDivZero( double numerator, double denominator )
	{
		if ( numerator==0 && denominator==0)
			return true;
		return false;
	}

	/*
	 *  See notes accompanying the ln() method implementation.
	 *  This condition is automatically handled at the lowest possible level - a nice
	 *  example of well-encapsulated code.
	 */	
	/*
	public boolean checkForZeroMultLogZero( double multiplier, double logOperand )
	{
		if ( multiplier==0 && logOperand==0 )
			return true;
		return false;
	}
	*/
	
	public boolean checkForLogZero( double logOperand )
	{
		if ( logOperand == 0 )
			return true;
		return false;
	}

	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */		
	public double[] root( double P[], int power ) 
	{
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = root(P[i], power);
		}
		return r;
	}	

	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  Note: nthRoot code used from RosettaCode (http://rosettacode.org/wiki/Nth_root).
	 *
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			

	public double root(double A, int n) {	
		return nthRoot(A, n, .001);
	}

	public double nthRoot(double A, int n, double p) {	
		if(A == 0 || A == 1) {				
			return A;	
		} 
		
		double x_prev = A;	
		double x = div(A,n);  // starting "guessed" value...	
		while(Math.abs(x - x_prev) > p) 
		{		
			x_prev = x;		
			double d1 = n - 1.0;
			double d2 = Math.pow(x,d1);
			double d3 = mult(d1,x);
			double d4 = d3 + A;
			double d5 = div(d4,d2);
			double d6 = div(d5,n);
				// x = ((n - 1.0) * x + A / Math.pow(x, n - 1.0)) / n;
			x = d6;
		}	
		return x;
	}	
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double[] ln( double P[] ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = ln(P[i]);
		}
		return r;
	}		

	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double[] sqrt( double P[] ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = sqrt(P[i]);
		}
		return r;
	}		
		
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double[] absSubPower( double P[], double Q[], int power ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = absSubPower(P[i],Q[i],power);
		}
		return r;
	}		

	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double[] absSubDiv( double P[], double Q[], double R[] ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = absSubDiv(P[i],Q[i],R[i]);
		}
		return r;
	}		

	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double[] square( double P[] ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = square(P[i]);
		}
		return r;
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */		
	public double[] add( double P[], double Q[] ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = add(P[i],Q[i]);
		}
		return r;
	}

	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double[] add( double P[], double val ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = add(P[i],val);
		}
		return r;
	}

	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double[] sub( double val, double P[] ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = sub(val,P[i]);
		}
		return r;
	}

	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double[] sub( double P[], double val ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = sub(P[i],val);
		}
		return r;
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */		
	public double[] sub( double Q[], double P[] ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = sub(Q[i],P[i]);
		}
		return r;
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */		
	public double[] mult( double P[], double Q[] ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = mult(P[i],Q[i]);
		}
		return r;
	}

	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double[] mult( double v, double Q[] ) {
		double r[] = new double[Q.length];
		for (int i=0; i < Q.length; i++) {
			r[i] = mult(v,Q[i]);
		}
		return r;
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double[] mult( double P[], double v ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = mult(P[i],v);
		}
		return r;
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double[] div( double P[], double Q[] ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = div(P[i],Q[i]);
		}
		return r;
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */		
	public double[] div( double P[], double v ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = div(P[i],v);
		}
		return r;
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double[] div( double v, double Q[] ) {
		double r[] = new double[Q.length];
		for (int i=0; i < Q.length; i++) {
			r[i] = div(v,Q[i]);
		}
		return r;
	}

	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double[] max( double P[], double Q[] ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = max(P[i],Q[i]);
		}
		return r;
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double[] absMax( double P[], double Q[] ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = Math.abs( max(P[i],Q[i]) );
		}
		return r;
	}		
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double[] min( double P[], double Q[] ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = min(P[i],Q[i]);
		}
		return r;
	}		

	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double[] absMin( double P[], double Q[] ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = Math.abs( min(P[i],Q[i]) );
		}
		return r;
	}			
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double[] absSub( double P[], double Q[] ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = absSub(P[i],Q[i]);
		}
		return r;
	}		

	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double[] absAdd( double P[], double Q[] ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = absAdd(P[i],Q[i]);
		}
		return r;
	}	
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double[] absSubSquared( double P[], double Q[] ) {
		double r[] = new double[P.length];
		for (int i=0; i < P.length; i++) {
			r[i] = absSubSquared(P[i],Q[i]);
		}
		return r;
	}

	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double max( double P[] ) {
		double _max = 0.0;
		_max = P[0];
		for (int i=1; i < P.length; i++ ) {
			_max = max(_max,P[i]);
		}
		return _max;		
	}	
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double max( double p, double q ) {
		return Math.max(p, q);
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double min( double p, double q ) {
		return Math.min(p, q);
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double multByReciprocal( double v, double d) {
		double d1 = div(1,d);
		double d2 = mult(d1,v);
		return d2;
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double sum( double[] values )
	{
		double sum = 0.0;
		for (int i=0; i < values.length; i++ ) {
			sum += values[i];
		}
		return sum;
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double absSubPower(double p, double q, int power) {
		return Math.pow(absSub(p,q), power);
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double absSubSquared(double p, double q) {
		return mult( absSub(p,q), absSub(p,q) );
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double absSubDiv( double p, double q, double r) {
		return div( absSub(p,q), r );
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double absSub( double p, double q ) {
		return Math.abs(p - q);	
	}

	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double absAdd( double p, double q ) {
		return Math.abs(p + q);	
	}	
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double sqrt( double p ) {
		return Math.sqrt(p);
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double add( double p, double q ) {
		return (p + q);
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double sub( double p, double q ) {
		return (p - q);
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double mult( double p, double q) {
		return (p * q);
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double div( double p, double q ) {
		if ( checkForZeroDivZero(p,q))
			return 0;		
		return (q != 0) ? (double)(p/q) : Double.MIN_VALUE;	// in the case of div(nonZero,0), assigns a minimal value per implementation recommendations.
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double negLn( double p ) {
		return -1 * (ln(p));
	}	
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  Notes:
	 *    This handles the condition where one tries to perform 'log 0' by returning a very small value.
	 *    Also, the metric papers caution that implementors should also handle a '0 log 0' operation by making it == 0.
	 *    The metric papers list the particular metrics that are prone to such an attempted operation.
	 *    However, if the log operation (here, below) of 'log 0' has already been corrected to a minimal value, then
	 *    any attempted '0 log 0' will also yield '0 log 0 = 0 * (min value) = 0', thus achieving the desired result
	 *    in a nicely encapsulated way.
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double ln( double p ) {	
		
		if ( checkForLogZero(p) )
			return Double.MIN_VALUE;
		
		return Math.log(p);
	}
	
	/*
	 *  Support method for metric implementations
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */			
	public double square( double p ) {
		return p * p;
	}

	/*
	 *  Support method for metric use
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */				
	public double [] getVar(int n)
	{
		double var[] = new double[n];
		for (int i=0; i < n; i++) {
			var[i] = Math.random();
		}
		return var;
	}

	/*
	 *  Support method for metric use
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  NOTE: I had to add updated RGBHistogramDescriptor and GrayscaleHistogramDescriptor to the versus-impl*.jar file.
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */				
	public double[] rgbHistogram2Double( RGBHistogramDescriptor d )
	{
		int len 	= d.getNumBins();
		int bands	= d.getNumBands();
		double r[] = new double[len];
		int hist[][] = d.getHistogram();
		
		// init
		for (int i=0; i < len; i++) {
			r[i] = 0.0;
		}
		
		// convert
		for ( int i=0; i < len; i++ ) {
			for (int j=0; j < bands; j++)
			{
				r[i] += hist[i][j];
			}			
		}
		
		return r;
	}
	
	/*
	 *  Support method for metric use
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */					
	public double[] rgbNormHistogram2Double( RGBHistogramDescriptor d )
	{
		int len 	= d.getNumBins();
		int bands	= d.getNumBands();
		double r[] = new double[len];
		int hist[][] = d.computeNormalizedHistogram();
		
		// init
		for (int i=0; i < len; i++) {
			r[i] = 0.0;
		}
		
		// convert
		for ( int i=0; i < len; i++ ) {
			for (int j=0; j < bands; j++)
			{
				r[i] += hist[i][j];
			}			
		}
		
		return r;
	}	

	/*
	 *  Support method for metric use
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */						
	public double[] grayScaleHistogram2Double( GrayscaleHistogramDescriptor d )
	{
		int len    = d.getNumBins();
		double r[] = new double[len];
		int hist[] = d.getHistogram();
		
		// init
		for (int i=0; i < len; i++) {
			r[i] = 0.0;
		}
		
		// convert
		for ( int i=0; i < len; i++ ) {
				r[i] += hist[i];	
		}
		
		return r;	
	}
	
	/*
	 *  Support method for metric use
	 *  name:			X
	 *  pseudo_code1(in J language): 		TBD
	 *  pseudo_code2(in J language,terse): 	TBD
	 *  
	 *  @author 		Benjamin Long, blong@nist.gov
	 *  version:		1.0
	 */						
	public double[] grayScaleNormHistogram2Double( GrayscaleHistogramDescriptor d )
	{
		int len    = d.getNumBins();
		double r[] = new double[len];
		int hist[] = d.computeNormalizedHistogram();
		
		// init
		for (int i=0; i < len; i++) {
			r[i] = 0.0;
		}
		
		// convert
		for ( int i=0; i < len; i++ ) {
				r[i] += hist[i];	
		}
		
		return r;	
	}	
	
}
