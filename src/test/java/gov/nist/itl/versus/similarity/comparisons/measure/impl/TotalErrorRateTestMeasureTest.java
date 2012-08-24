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
 * name          TotalErrorRateTest 
 * description   
 * @author       Benjamin Long
 * @version      1.4
 * date          
 */
package gov.nist.itl.versus.similarity.comparisons.measure.impl;

import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import gov.nist.itl.versus.similarity.comparisons.measure.impl.RandIndexMeasure;
import gov.nist.itl.versus.similarity.comparisons.measure.impl.AdjustedRandIndexMeasure;
import gov.nist.itl.versus.similarity.comparisons.measure.impl.TotalErrorRateEvaluationMeasure;
import gov.nist.itl.versus.similarity.comparisons.measure.impl.TotalErrorRateTestMeasure;
import gov.nist.itl.versus.similarity.comparisons.adapter.impl.LabeledImageObjectAdapter;
import gov.nist.itl.versus.similarity.comparisons.extract.impl.LabeledArrayFeatureExtractor;
import gov.nist.itl.versus.similarity.comparisons.descriptor.impl.LabeledThreeDimensionalDoubleArrayFeature;
import gov.nist.itl.versus.similarity.comparisons.measure.LabeledMeasure;
import edu.illinois.ncsa.versus.adapter.impl.ImageObjectAdapter;
import edu.illinois.ncsa.versus.descriptor.impl.ThreeDimensionalDoubleArrayFeature;
import edu.illinois.ncsa.versus.extract.impl.ArrayFeatureExtractor;
import edu.illinois.ncsa.versus.measure.SimilarityNumber;
import gov.nist.itl.versus.similarity.comparisons.MathOpsE;
import gov.nist.itl.versus.similarity.comparisons.exception.*;



/**
 * TotalErrorRateTest Test
 */

public class TotalErrorRateTestMeasureTest extends junit.framework.TestCase
{
	// default settings if no external file pairs are specified

	private static String fileName1 = "test/data/01_GSC_100x100.tif";
	private static String fileName2 = "test/data/02_GSC_100x100.tif";	
	
	public TotalErrorRateTestMeasureTest(){
	
	}
	
	public static SimilarityNumber unlabeled( String fileName1, String fileName2 ) throws Exception
	{
	// unlabeled case: whole image (no labels)	
		ImageObjectAdapter a1 = new ImageObjectAdapter();
			if ( a1 == null )
				throw new SWIndependenceException("failed to create ImageObjectAdapter adapter for file1");
				
		ImageObjectAdapter a2 = new ImageObjectAdapter();
			if ( a2 == null )
				throw new SWIndependenceException("failed to create ImageObjectAdapter adapter for file2");
				
		SimilarityNumber result = null;
		
		try {
			
			File f1 = new File(fileName1);			
				if ( f1 == null )
					throw new ImageCompatibilityException("failed to create file object for file1");
			
			File f2 = new File(fileName2);
				if ( f2 == null )
					throw new ImageCompatibilityException("failed to create file object for file2");
			
			a1.load( f1 );
				if ( a1 == null )
					throw new SWIndependenceException("failed to load file1 into adapter1");
			a2.load( f2 );			
				if ( a2 == null )
					throw new SWIndependenceException("failed to load file2 into adapter2");	
			
			ArrayFeatureExtractor x1 = new ArrayFeatureExtractor();
				if ( x1 == null )
					throw new SWIndependenceException("failed to create ArrayFeatureExtractor object for extractor1");	
					
			ArrayFeatureExtractor x2 = new ArrayFeatureExtractor();
				if ( x2 == null )
					throw new SWIndependenceException("failed to create ArrayFeatureExtractor object for extractor2");				
						
			ThreeDimensionalDoubleArrayFeature desc1 = (ThreeDimensionalDoubleArrayFeature) x1.extract(a1);
				if ( desc1 == null )
					throw new SWIndependenceException("failed to extract ThreeDimensionalDoubleArrayFeature feature1 via extractor1");
					
			ThreeDimensionalDoubleArrayFeature desc2 = (ThreeDimensionalDoubleArrayFeature) x2.extract(a2);
				if ( desc2 == null )
					throw new SWIndependenceException("failed to extract ThreeDimensionalDoubleArrayFeature feature2 via extractor2");					

			TotalErrorRateTestMeasure m 		= new TotalErrorRateTestMeasure();
				if ( m == null )
					throw new SWIndependenceException("failed to create TotalErrorRateTestMeasure object for measure");				
			
			result 	= m.compare(desc1, desc2);
				if ( result == null )
					throw new SingularityTreatmentException("received null comparison result");				
					
			String SEP = ",";
			String rString = fileName1 + SEP + fileName2 + SEP + "ImageObjectAdapter" + SEP + "ThreeDimensionalDoubleArrayFeature" + SEP + "TotalErrorRateTestMeasure" + SEP + result.getValue() ;
			System.out.println ( rString );					
			
		} catch (IOException e) {
			throw new SWIndependenceException( "failed to load file via adapter into memory" );
		} 
		
		return result;
	}
	
	public static SimilarityNumber[] labeled( String fileName1, String fileName2 ) throws Exception
	{
		// labeled case
	
		LabeledImageObjectAdapter la1 = new LabeledImageObjectAdapter();
			if ( la1 == null )
				throw new SWIndependenceException("failed to create LabeledImageObjectAdapter adapter for file1");		
		LabeledImageObjectAdapter la2 = new LabeledImageObjectAdapter();	
			if ( la2 == null )
				throw new SWIndependenceException("failed to create LabeledImageObjectAdapter adapter for file2");
				
		SimilarityNumber[] lresult = null;
		
		try {
			File f1 = new File(fileName1);			
				if ( f1 == null )
					throw new ImageCompatibilityException("failed to create file object for file1");
			
			File f2 = new File(fileName2);
				if ( f2 == null )
					throw new ImageCompatibilityException("failed to create file object for file2");

			la1.load( f1 );
				if ( la1 == null )
					throw new SWIndependenceException("failed to load file1 into adapter1");
			la2.load( f2 );			
				if ( la2 == null )
					throw new SWIndependenceException("failed to load file2 into adapter2");			

			LabeledArrayFeatureExtractor lx1 = new LabeledArrayFeatureExtractor();
				if ( lx1 == null )
					throw new SWIndependenceException("failed to create LabeledArrayFeatureExtractor object for extractor1");			
			LabeledArrayFeatureExtractor lx2 = new LabeledArrayFeatureExtractor();
				if ( lx2 == null )
					throw new SWIndependenceException("failed to create LabeledArrayFeatureExtractor object for extractor2");			
			
			LabeledThreeDimensionalDoubleArrayFeature ldesc1 = (LabeledThreeDimensionalDoubleArrayFeature) lx1.extract(la1);
				if ( ldesc1 == null )
					throw new SWIndependenceException("failed to extract LabeledThreeDimensionalDoubleArrayFeature feature1 via extractor1");
					
			LabeledThreeDimensionalDoubleArrayFeature ldesc2 = (LabeledThreeDimensionalDoubleArrayFeature) lx2.extract(la2);						
				if ( ldesc2 == null )
					throw new SWIndependenceException("failed to extract LabeledThreeDimensionalDoubleArrayFeature feature2 via extractor2");		
					
			TotalErrorRateTestMeasure lm 		= new TotalErrorRateTestMeasure();			
				if ( lm == null )
					throw new SWIndependenceException("failed to create TotalErrorRateTestMeasure object for measure");	
					
			lresult 	= lm.compare(ldesc1, ldesc2);
				if ( lresult == null )
					throw new SingularityTreatmentException("received null comparison result");	
			
			String SEP = ",";

			String rString = fileName1 + SEP + fileName2 + SEP + "LabeledImageObjectAdapter" + SEP + "LabeledThreeDimensionalDoubleArrayFeature" + SEP + "AdjustedRandIndexMeasure" + SEP;
			rString += "[";
			for (int i=0; i < lresult.length; i++) {
				if ( i!=0 ) rString += SEP;
				rString += lresult[i].getValue();
			}
			rString +="]";
			
			System.out.println ( rString );					
			
		} catch (IOException e) {
			throw new SWIndependenceException( "failed to load file via adapter into memory" );
		} 

		return lresult;
	}

////////////////	
	
	public void setFileNames( String fileName1, String fileName2 )
	{
		this.fileName1=fileName1;
		this.fileName2=fileName2;
	}

	public String o( String category, String message, String details ) {
		String str = "[Pixel metric]\t[" + category + "]\t[" + message + "]\t[" + details + "]";
		return str; 
	}	

	@Test
	public void test() {	
		try {
				unlabeled(fileName1, fileName2);
				labeled(fileName1, fileName2);	
		}
		catch( Exception e ) {
			System.out.println("Error:" + e.getMessage() );
		}		
	}

	public static void main( String[] args )
	{
		org.junit.runner.JUnitCore.runClasses( TotalErrorRateTestMeasureTest.class );
	}
}