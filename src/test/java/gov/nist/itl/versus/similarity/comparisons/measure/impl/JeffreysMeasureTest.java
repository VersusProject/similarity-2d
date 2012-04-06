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
 *    Date: Fri Dec 23 20:03:03 EST 2011
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


/**
 * Jeffreys Test
 * 
 * @author Benjamin Long
 * 
 */
public class JeffreysMeasureTest 
{
	@Test
	public void test()
	{
	
	// grayscale
	
		ImageObjectAdapter a1 = new ImageObjectAdapter();
		ImageObjectAdapter a2 = new ImageObjectAdapter();

		String fileName1 = "test/data/01_GSC_100x100.tif";
		String fileName2 = "test/data/02_GSC_100x100.tif";

		try {
			a1.load(new File(fileName1));
			a2.load(new File(fileName2));
			
			GrayscaleHistogramExtractor x1 = new GrayscaleHistogramExtractor();
			GrayscaleHistogramExtractor x2 = new GrayscaleHistogramExtractor();
			GrayscaleHistogramDescriptor desc1 = (GrayscaleHistogramDescriptor) x1.extract(a1);
			GrayscaleHistogramDescriptor desc2 = (GrayscaleHistogramDescriptor) x2.extract(a2);			

			JeffreysMeasure m 		= new JeffreysMeasure();
			SimilarityNumber result 	= m.compare(desc1, desc2);
			System.out.println( "JeffreysMeasure Similarity value (Grayscale)\t" + result.getValue() + "\t" + fileName1 + "\t" + fileName2  );

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	// rgb
		a1 = new ImageObjectAdapter();
		a2 = new ImageObjectAdapter();

		fileName1 = "test/data/01_RGB_218x172.tif"; 
		fileName2 = "test/data/02_RGB_218x172.tif";

		try {
			a1.load(new File(fileName1));
			a2.load(new File(fileName2));
			
			RGBHistogramExtractor rx1 = new RGBHistogramExtractor();
			RGBHistogramExtractor rx2 = new RGBHistogramExtractor();
			RGBHistogramDescriptor rdesc1 = (RGBHistogramDescriptor) rx1.extract(a1);
			RGBHistogramDescriptor rdesc2 = (RGBHistogramDescriptor) rx2.extract(a2);			

			JeffreysMeasure rm 		= new JeffreysMeasure();
			SimilarityNumber rresult 	= rm.compare(rdesc1, rdesc2);
			System.out.println( "JeffreysMeasure Similarity value (RGB)\t" + rresult.getValue() + "\t" + fileName1 + "\t" + fileName2  );

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

	public static void main( String[] args )
	{
		new JeffreysMeasureTest().test();
	}
}
