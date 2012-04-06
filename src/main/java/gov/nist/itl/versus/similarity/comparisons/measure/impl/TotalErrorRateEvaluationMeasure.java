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
 *  @version 1.2
 *    Date: Mon Jan 23 07:24:45 EST 2012
 */

package gov.nist.itl.versus.similarity.comparisons.measure.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;


import edu.illinois.ncsa.versus.UnsupportedTypeException;
import edu.illinois.ncsa.versus.descriptor.Descriptor;
import edu.illinois.ncsa.versus.descriptor.impl.ThreeDimensionalDoubleArrayFeature;
import edu.illinois.ncsa.versus.measure.SimilarityNumber;
import edu.illinois.ncsa.versus.measure.SimilarityPercentage;
import edu.illinois.ncsa.versus.measure.Similarity;
import edu.illinois.ncsa.versus.measure.Measure;
import gov.nist.itl.versus.similarity.comparisons.ImageData;
import gov.nist.itl.versus.similarity.comparisons.MathOps;
import gov.nist.itl.versus.similarity.comparisons.descriptor.impl.LabeledThreeDimensionalDoubleArrayFeature;
import gov.nist.itl.versus.similarity.comparisons.measure.LabeledMeasure;

public class TotalErrorRateEvaluationMeasure implements LabeledMeasure
{
	@Override
	public SimilarityPercentage normalize(Similarity similarity) {
		return null;
	}		
	
	public SimilarityNumber compare(ThreeDimensionalDoubleArrayFeature feature1, ThreeDimensionalDoubleArrayFeature feature2) 
			throws Exception 
	{
		// check for same height
		if (feature1.getHeight() != feature2.getHeight()) {
			throw new Exception("Features must have the same height");
		}
		// check for same width
		if (feature1.getWidth() != feature2.getWidth()) {
			throw new Exception("Features must have the same width");
		}
		// check for same number of bands
		if (feature1.getNumBands() != feature2.getNumBands()) {
			throw new Exception("Features must have the same number of bands");
		}

		MathOps mops = new MathOps();
		ImageData im1 = new ImageData(feature1.getValues());
		ImageData im2 = new ImageData(feature2.getValues());
		double tee = mops.d_tee(im1, im2 );		
		return new SimilarityNumber(tee);
	}

	public SimilarityNumber[] compare(LabeledThreeDimensionalDoubleArrayFeature feature1, LabeledThreeDimensionalDoubleArrayFeature feature2) 
			throws Exception 
	{
		ThreeDimensionalDoubleArrayFeature f1 = null, f2 = null;
		ImageData labels1[] = feature1.getLabels();
		ImageData labels2[] = feature2.getLabels();
		SimilarityNumber results[] = new SimilarityNumber[labels1.length];
		
		for (int i=0; i < labels1.length; i++ ) {
			f1 = new ThreeDimensionalDoubleArrayFeature(labels1[i].getValues());
			f2 = new ThreeDimensionalDoubleArrayFeature(labels1[i].getValues());
			results[i] = compare(f1,f2);
		}		
		return results;
	}	
	
	@Override
	public SimilarityNumber compare(Descriptor feature1, Descriptor feature2)	throws Exception {

		if (feature1 instanceof ThreeDimensionalDoubleArrayFeature && feature2 instanceof ThreeDimensionalDoubleArrayFeature) {
			
			ThreeDimensionalDoubleArrayFeature segmentFeature1 = (ThreeDimensionalDoubleArrayFeature) feature1;
			ThreeDimensionalDoubleArrayFeature segmentFeature2 = (ThreeDimensionalDoubleArrayFeature) feature2;
			return compare(segmentFeature1, segmentFeature2);
		} 
		else {
			throw new UnsupportedTypeException(
						"ERROR: Similarity measure expects features of type" + supportedTypesString() );
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
		return "TotErrRateEvalMeasure";
	}

	@Override
	public Class getType() {
		return TotalErrorRateEvaluationMeasure.class;
	}	
}
