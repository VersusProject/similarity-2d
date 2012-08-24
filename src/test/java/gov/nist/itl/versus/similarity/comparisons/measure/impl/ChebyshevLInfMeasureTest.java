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
 * name          ChebyshevLInf 
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
import edu.illinois.ncsa.versus.UnsupportedTypeException;
import edu.illinois.ncsa.versus.measure.Similarity;
import edu.illinois.ncsa.versus.measure.SimilarityNumber;
import edu.illinois.ncsa.versus.adapter.impl.ImageObjectAdapter;
import edu.illinois.ncsa.versus.extract.impl.GrayscaleHistogramExtractor;
import edu.illinois.ncsa.versus.descriptor.impl.GrayscaleHistogramDescriptor;
import edu.illinois.ncsa.versus.extract.impl.RGBHistogramExtractor;
import edu.illinois.ncsa.versus.descriptor.impl.RGBHistogramDescriptor;
import edu.illinois.ncsa.versus.measure.SimilarityNumber;
import gov.nist.itl.versus.similarity.comparisons.measure.impl.*;
import gov.nist.itl.versus.similarity.comparisons.MathOpsE;
import gov.nist.itl.versus.similarity.comparisons.exception.*;


/**
 * ChebyshevLInf Test
 */
public class ChebyshevLInfMeasureTest extends junit.framework.TestCase
{
	// default settings if no external file pairs are specified
	private static String gsFileName1 = "test/data/01_GSC_100x100.tif";
	private static String gsFileName2 = "test/data/02_GSC_100x100.tif";
	private static String rgbFileName1 = "test/data/01_RGB_218x172.tif"; 
	private static String rgbFileName2 = "test/data/02_RGB_218x172.tif";
	private static MathOpsE mopsE = new MathOpsE();

	public ChebyshevLInfMeasureTest(){
	
	}
	
	public static SimilarityNumber grayscale( String fileName1, String fileName2 ) throws Exception
	{
		
		gsFileName1 = fileName1;
		gsFileName2 = fileName2;
		
	// grayscale	
		ImageObjectAdapter a1 = new ImageObjectAdapter();
			if ( a1 == null )
				throw new SWIndependenceException("failed to create ImageObjectAdapter adapter for file1");
				
		ImageObjectAdapter a2 = new ImageObjectAdapter();
			if ( a2 == null )
				throw new SWIndependenceException("failed to create ImageObjectAdapter adapter for file2");
				
		SimilarityNumber result = null;
		
		try {
			File f1 = new File(gsFileName1);			
				if ( f1 == null )
					throw new ImageCompatibilityException("failed to create file object for file1");
			
			File f2 = new File(gsFileName2);
				if ( f2 == null )
					throw new ImageCompatibilityException("failed to create file object for file2");

			a1.load( f1 );
				if ( a1 == null )
					throw new SWIndependenceException("failed to load file1 into adapter1");
			a2.load( f2 );			
				if ( a2 == null )
					throw new SWIndependenceException("failed to load file2 into adapter2");			
					
			GrayscaleHistogramExtractor x1 = new GrayscaleHistogramExtractor();
				if ( x1 == null )
					throw new SWIndependenceException("failed to create GrayscaleHistogramExtractor object for extractor1");			
					
			GrayscaleHistogramExtractor x2 = new GrayscaleHistogramExtractor();
				if ( x2 == null )
					throw new SWIndependenceException("failed to create GrayscaleHistogramExtractor object for extractor2");						
			
			GrayscaleHistogramDescriptor desc1 = (GrayscaleHistogramDescriptor) x1.extract(a1);
				if ( desc1 == null )
					throw new SWIndependenceException("failed to extract GrayscaleHistogramDescriptor feature1 via extractor1");			
					
			GrayscaleHistogramDescriptor desc2 = (GrayscaleHistogramDescriptor) x2.extract(a2);
				if ( desc2 == null )
					throw new SWIndependenceException("failed to extract GrayscaleHistogramDescriptor feature2 via extractor2");
			
			ChebyshevLInfMeasure m 		= new ChebyshevLInfMeasure();
			
				if ( m == null )
					throw new SWIndependenceException("failed to create ChebyshevLInfMeasure object for measure");
					
			result 	= m.compare(desc1, desc2);
			
				if ( result == null )
					throw new SingularityTreatmentException("received null comparison result");			
			
			String SEP = ",";
			String rString = fileName1 + SEP + fileName2 + SEP + "ImageObjectAdapter" + SEP + "GrayscaleHistogramDescriptor" + SEP + "ChebyshevLInfMeasure" + SEP + result.getValue() ;
			System.out.println ( rString );
	
		} catch (IOException e) {
			throw new ImageCompatibilityException( "failed to load file via adapter into memory" );
			
		} 
		
		return result;
	}
	
	public static SimilarityNumber rgb( String fileName1, String fileName2 ) throws Exception
	{

			rgbFileName1 = fileName1;
			rgbFileName2 = fileName2;
			
	// rgb
		ImageObjectAdapter a1 = new ImageObjectAdapter();
			if ( a1 == null )
				throw new SWIndependenceException("failed to create ImageObjectAdapter adapter for file1");
				
		ImageObjectAdapter a2 = new ImageObjectAdapter();
			if ( a2 == null )
				throw new SWIndependenceException("failed to create ImageObjectAdapter adapter for file2");
		
		SimilarityNumber rresult = null;
		
		try {
		
			File f1 = new File(rgbFileName1);			
				if ( f1 == null )
					throw new ImageCompatibilityException("failed to create file object for file1");
			
			File f2 = new File(rgbFileName2);
				if ( f2 == null )
					throw new ImageCompatibilityException("failed to create file object for file2");

			a1.load( f1 );
				if ( a1 == null )
					throw new SWIndependenceException("failed to load file1 into adapter1");
			a2.load( f2 );			
				if ( a2 == null )
					throw new SWIndependenceException("failed to load file2 into adapter2");			
					
			RGBHistogramExtractor rx1 = new RGBHistogramExtractor();
				if ( rx1 == null )
					throw new SWIndependenceException("failed to create RGBHistogramExtractor object for extractor1");	
					
			RGBHistogramExtractor rx2 = new RGBHistogramExtractor();
				if ( rx2 == null )
					throw new SWIndependenceException("failed to create RGBHistogramExtractor object for extractor2");			
			
			RGBHistogramDescriptor rdesc1 = (RGBHistogramDescriptor) rx1.extract(a1);
				if ( rdesc1 == null )
					throw new SWIndependenceException("failed to extract RGBHistogramDescriptor feature1 via extractor1");	
					
			RGBHistogramDescriptor rdesc2 = (RGBHistogramDescriptor) rx2.extract(a2);
				if ( rdesc2 == null )
					throw new SWIndependenceException("failed to extract RGBHistogramDescriptor feature1 via extractor2");
					
			ChebyshevLInfMeasure rm 		= new ChebyshevLInfMeasure();
			
				if ( rm == null )
					throw new SWIndependenceException("failed to create ChebyshevLInfMeasure object for measure");			
			
			rresult 	= rm.compare(rdesc1, rdesc2);
			
				if ( rresult == null )
					throw new SingularityTreatmentException("received null comparison result");				
			
			String SEP = ",";
			String rString = fileName1 + SEP + fileName2 + SEP + "ImageObjectAdapter" + SEP + "RGBHistogramDescriptor" + SEP + "ChebyshevLInfMeasure" + SEP + rresult.getValue() ;
			System.out.println ( rString );

		} catch (IOException e) {
			throw new ImageCompatibilityException( "failed to load file via adapter into memory" );
		} 

		return rresult;
	}	

////////////////

	public void setGSFiles( String gsFileName1, String gsFileName2 ) {
		this.gsFileName1 = gsFileName1;
		this.gsFileName2 = gsFileName2;
	}	

	public  void setRGBFiles( String rgbFileName1, String rgbFileName2 ) {
		this.rgbFileName1 = rgbFileName1;
		this.rgbFileName2 = rgbFileName2;
	}
	
	public static String o( String category, String message, String details ) {
		String str = "[Histogram metric]\t[" + category + "]\t[" + message + "]\t[" + details + "]";
		//System.out.println( str );
		return str; 
	}	
	
	@Test
	public void test() {
		try {
				grayscale(gsFileName1, gsFileName2);
				rgb(rgbFileName1, rgbFileName2);	
		}
		catch( Exception e ) {
			System.out.println("Error:" + e.getMessage() );
		}		
	}
	

	public static void main( String[] args )
	{
		org.junit.runner.JUnitCore.runClasses( ChebyshevLInfMeasureTest.class );
	}
}