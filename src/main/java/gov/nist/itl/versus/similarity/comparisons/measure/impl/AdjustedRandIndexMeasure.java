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
 * name          AdjustedRandIndex 
 * description   
 * @author       Benjamin Long
 * @version      1.4
 * date          
 */
package gov.nist.itl.versus.similarity.comparisons.measure.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import edu.illinois.ncsa.versus.descriptor.Descriptor;
import edu.illinois.ncsa.versus.descriptor.impl.ThreeDimensionalDoubleArrayFeature;
import edu.illinois.ncsa.versus.measure.Similarity;
import edu.illinois.ncsa.versus.measure.SimilarityNumber;
import edu.illinois.ncsa.versus.measure.SimilarityPercentage;
import edu.illinois.ncsa.versus.utility.HasCategory;
import gov.nist.itl.versus.similarity.comparisons.ImageData;
import gov.nist.itl.versus.similarity.comparisons.MathOpsE;
import gov.nist.itl.versus.similarity.comparisons.descriptor.impl.LabeledThreeDimensionalDoubleArrayFeature;
import gov.nist.itl.versus.similarity.comparisons.exception.*;
import gov.nist.itl.versus.similarity.comparisons.measure.LabeledMeasure;


public class AdjustedRandIndexMeasure implements LabeledMeasure, HasCategory
{
	private MathOpsE mops = new MathOpsE();

	@Override
	public SimilarityPercentage normalize(Similarity similarity) {
		return null;
	}		

        /**
         * Compares two images based on their pixels
         *
         * @param feature1 ThreeDimensionalDoubleArrayFeature
         * @param feature2 ThreeDimensionalDoubleArrayFeature
         * @return SimilarityNumber
         * @throws Exception
         */	
	
	public SimilarityNumber compare(ThreeDimensionalDoubleArrayFeature feature1, ThreeDimensionalDoubleArrayFeature feature2) 
			throws Exception 
	{
		// check for same height
		if (feature1.getHeight() != feature2.getHeight()) {
			throw new ImageCompatibilityException("Features must have the same height");
		}
		// check for same width
		if (feature1.getWidth() != feature2.getWidth()) {
			throw new ImageCompatibilityException("Features must have the same width");
		}
		// check for same number of bands
		if (feature1.getNumBands() != feature2.getNumBands()) {
			throw new ImageCompatibilityException("Features must have the same number of bands");
		}

		ImageData im1 = new ImageData(feature1.getValues());
			
			if ( im1 == null ) 
					throw new SWIndependenceException("failed to create object for ThreeDimensionalDoubleArrayFeature pixel array1");
		
		ImageData im2 = new ImageData(feature2.getValues());
		
			if ( im2 == null ) 
					throw new SWIndependenceException("failed to create object for ThreeDimensionalDoubleArrayFeature pixel array2");
		
		Double measurement = mops.pixel_measure_ari(im1, im2 );	
			if ( measurement == null ) 
				throw new SingularityTreatmentException("Received null measurement value");	
				
		SimilarityNumber result = new SimilarityNumber(measurement.doubleValue());
				
			if ( result == null )
				throw new SingularityTreatmentException("Received null RGBHistogramDescriptor comparison value");							
						
		return result;		
	}

	public SimilarityNumber[] compare(LabeledThreeDimensionalDoubleArrayFeature feature1, LabeledThreeDimensionalDoubleArrayFeature feature2) 
			throws Exception 
	{
		ThreeDimensionalDoubleArrayFeature f1 = null, f2 = null;
		ImageData labels1[] = feature1.getLabels();
			if ( labels1 == null ) 
				throw new SWIndependenceException("failed to create object for LabeledThreeDimensionalDoubleArrayFeature label array1");
		ImageData labels2[] = feature2.getLabels();
			if ( labels2 == null ) 
				throw new SWIndependenceException("failed to create object for LabeledThreeDimensionalDoubleArrayFeature label array2");
					
		SimilarityNumber results[] = new SimilarityNumber[labels1.length];	
			if ( results == null ) 
				throw new SWIndependenceException("failed to create object for LabeledSimilarityNumber results");
		
		for (int i=0; i < labels1.length; i++ ) {
			f1 = new ThreeDimensionalDoubleArrayFeature(labels1[i].getValues());
				if ( f1 == null ) 
					throw new SWIndependenceException("failed extract a given ThreeDimensionalDoubleArrayFeature label array1");
				
			f2 = new ThreeDimensionalDoubleArrayFeature(labels1[i].getValues());
				if ( f2 == null ) 
					throw new SWIndependenceException("failed extract a given ThreeDimensionalDoubleArrayFeature label array2");
			
			results[i] = compare(f1,f2);
				if ( results == null ) 
					throw new SingularityTreatmentException("Received null SimilarityNumber comparison result");
		}		
		return results;
	}	
	
	@Override
	public SimilarityNumber compare(Descriptor feature1, Descriptor feature2)	throws Exception {

		if (feature1 instanceof ThreeDimensionalDoubleArrayFeature && feature2 instanceof ThreeDimensionalDoubleArrayFeature) {
			
			ThreeDimensionalDoubleArrayFeature segmentFeature1 = (ThreeDimensionalDoubleArrayFeature) feature1;
				if ( segmentFeature1 == null ) 
					throw new SWIndependenceException("failed to create object for ThreeDimensionalDoubleArrayFeature array1");
					
			ThreeDimensionalDoubleArrayFeature segmentFeature2 = (ThreeDimensionalDoubleArrayFeature) feature2;
				if ( segmentFeature2 == null ) 
					throw new SWIndependenceException("failed to create object for ThreeDimensionalDoubleArrayFeature array2");
				
				SimilarityNumber result = compare(segmentFeature1, segmentFeature2);
					if ( result == null ) 
						throw new SingularityTreatmentException("Received null SimilarityNumber comparison result");
						
			return result;
		} 
		else {
			throw new SWIndependenceException(
						"Similarity measure expects features of type " + supportedTypesString() );
		}
	}	

	private String supportedTypesString() {
		String str="";
		Set f   = supportedFeaturesTypes();
		Iterator it = f.iterator();
		while (it.hasNext()) {
			str += ((Class)it.next()).getName() + " ";
		}
		return str;
	}

	@Override
	public Set<Class<? extends Descriptor>> supportedFeaturesTypes()
	{
		Set features = new HashSet();
		features.add( ThreeDimensionalDoubleArrayFeature.class );
		features.add( LabeledThreeDimensionalDoubleArrayFeature.class );
		return features;
	}
		
	public String getFeatureType() {
		return ThreeDimensionalDoubleArrayFeature.class.getName();
	}
	
	@Override
	public String getName() {
		return "Adjusted Rand Index";
	}

	@Override
	public Class getType() {
		return AdjustedRandIndexMeasure.class;
	}	
	
	@Override
	public String getCategory() {
		return "Pixel-Based Family";
	}
}