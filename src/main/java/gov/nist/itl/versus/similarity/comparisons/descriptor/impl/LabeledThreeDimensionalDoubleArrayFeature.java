
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

package gov.nist.itl.versus.similarity.comparisons.descriptor.impl;


import edu.illinois.ncsa.versus.descriptor.impl.ThreeDimensionalDoubleArrayFeature;
import gov.nist.itl.versus.similarity.comparisons.ImageData;

public class LabeledThreeDimensionalDoubleArrayFeature extends ThreeDimensionalDoubleArrayFeature
{
	public final String type = this.getClass().toString();
	private ImageData label;
	private ImageData[] labels;
	
	public LabeledThreeDimensionalDoubleArrayFeature() {
		label = null;
		labels = null;
	}

	public LabeledThreeDimensionalDoubleArrayFeature(ImageData label) {
		this.label = label;
	}

	public LabeledThreeDimensionalDoubleArrayFeature(ImageData[] labels) {
		this.labels = labels;
	}
	
	public LabeledThreeDimensionalDoubleArrayFeature(double[][][] values) {
		super(values);
	}
	
	public ImageData getLabel() 
	{
		return label;
	}
	
	public ImageData[] getLabels() 
	{
		return labels;
	}

	public void setLabel( ImageData label ) 
	{
		this.label = label;
	}
	
	public void setLabels(ImageData[] labels ) 
	{
		this.labels = labels;
	}	
	
	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getName() {
		return "Labeled3DArray";
	}
	
	public LabeledThreeDimensionalDoubleArrayFeature clone() {
		LabeledThreeDimensionalDoubleArrayFeature f = new LabeledThreeDimensionalDoubleArrayFeature();
		f.setLabel(label);
		f.setLabels(labels);
		return f;
	}
}
