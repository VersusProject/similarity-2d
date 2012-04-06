/*
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of
 * their official duties. Pursuant to title 17 Section 105 of the United
 * States Code this software is not subject to copyright protection and is
 * in the public domain. This software is an experimental system. NIST assumes
 * no responsibility whatsoever for its use by other parties, and makes no
 * guarantees, expressed or implied, about its quality, reliability, or
 * any other characteristic. We would appreciate acknowledgement if the
 * software is used.
 *
 *
 *  Author: Benjamin Long, blong@nist.gov
 * Version: 1.0
 *    Date: Fri Dec 23 20:29:34 EST 2011
 */


package gov.nist.itl.versus.similarity.comparisons.measure.impl;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;



import edu.illinois.ncsa.versus.adapter.impl.ImageObjectAdapter;
import edu.illinois.ncsa.versus.descriptor.impl.ThreeDimensionalDoubleArrayFeature;
import edu.illinois.ncsa.versus.extract.impl.ArrayFeatureExtractor;
import edu.illinois.ncsa.versus.measure.SimilarityNumber;
import gov.nist.itl.versus.similarity.comparisons.adapter.impl.LabeledImageObjectAdapter;
import gov.nist.itl.versus.similarity.comparisons.descriptor.impl.LabeledThreeDimensionalDoubleArrayFeature;
import gov.nist.itl.versus.similarity.comparisons.extract.impl.LabeledArrayFeatureExtractor;
import gov.nist.itl.versus.similarity.comparisons.measure.LabeledMeasure;
import gov.nist.itl.versus.similarity.comparisons.measure.impl.AdjustedRandIndexMeasure;
import gov.nist.itl.versus.similarity.comparisons.measure.impl.TotalErrorRateEvaluationMeasure;
import gov.nist.itl.versus.similarity.comparisons.measure.impl.TotalErrorRateTestMeasure;

/**
 * TotalErrorRateTest Test
 * 
 * @author Benjamin Long
 * 
 */
public class TotalErrorRateTestMeasureTest 
{
	@Test
	public void test()
	{
	
	// unlabeled case: whole image (no labels)
	
		ImageObjectAdapter a1 = new ImageObjectAdapter();
		ImageObjectAdapter a2 = new ImageObjectAdapter();

		String fileName1 = "test/data/math_ops/a.tif";
		String fileName2 = "test/data/math_ops/b.tif";

		try {
			a1.load(new File(fileName1));
			a2.load(new File(fileName2));
			
			ArrayFeatureExtractor x1 = new ArrayFeatureExtractor();
			ArrayFeatureExtractor x2 = new ArrayFeatureExtractor();
			ThreeDimensionalDoubleArrayFeature desc1 = (ThreeDimensionalDoubleArrayFeature) x1.extract(a1);
			ThreeDimensionalDoubleArrayFeature desc2 = (ThreeDimensionalDoubleArrayFeature) x2.extract(a2);			

			TotalErrorRateTestMeasure m 		= new TotalErrorRateTestMeasure();
			SimilarityNumber result 	= m.compare(desc1, desc2);
			System.out.println( "TotalErrorRateTestMeasure Similarity value (unlabeled)\t" + result.getValue() + "\t" + fileName1 + "\t" + fileName2  );

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	// labeled case
	
		LabeledImageObjectAdapter la1 = new LabeledImageObjectAdapter();
		LabeledImageObjectAdapter la2 = new LabeledImageObjectAdapter();
		
		try {
			la1.load(new File(fileName1));
			la2.load(new File(fileName2));
			
			LabeledArrayFeatureExtractor lx1 = new LabeledArrayFeatureExtractor();
			LabeledArrayFeatureExtractor lx2 = new LabeledArrayFeatureExtractor();
			LabeledThreeDimensionalDoubleArrayFeature ldesc1 = (LabeledThreeDimensionalDoubleArrayFeature) lx1.extract(la1);
			LabeledThreeDimensionalDoubleArrayFeature ldesc2 = (LabeledThreeDimensionalDoubleArrayFeature) lx2.extract(la2);			

			TotalErrorRateTestMeasure lm 		= new TotalErrorRateTestMeasure();
			SimilarityNumber[] lresult 	= lm.compare(ldesc1, ldesc2);
			System.out.print( "TotalErrorRateTestMeasure Similarity value (labeled)\t{");
			for (int i=0; i < lresult.length; i++) { 
				if ( i!=0 ) { 
					System.out.print(","); 
				} 
				System.out.print(""+lresult[i].getValue()); 
			}
			System.out.println( "}\t" + fileName1 + "\t" + fileName2  );

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}

	public static void main( String[] args )
	{
		new TotalErrorRateTestMeasureTest().test();
	}
}
