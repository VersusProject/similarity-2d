
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

package gov.nist.itl.versus.similarity.comparisons.adapter.impl;

import ncsa.im2learn.core.datatype.ImageObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import edu.illinois.ncsa.versus.adapter.impl.ImageObjectAdapter;
import gov.nist.itl.versus.similarity.comparisons.ImageData;
import gov.nist.itl.versus.similarity.comparisons.adapter.HasLabels;

/**
 * Simple adapter encapsulating Im2Learn ImageObject.
 * 
 */
public class LabeledImageObjectAdapter extends ImageObjectAdapter implements HasLabels 
{
	protected ImageObject o;
	private ImageData ops = new ImageData();
	private static Log log = LogFactory.getLog(LabeledImageObjectAdapter.class);

	/**
	 * Wrap an Im2Learn ImageObject.
	 * 
	 * @param imageObject
	 */
	public LabeledImageObjectAdapter( ImageObject imageObject ) 
	{
		super(imageObject);
		o = getImageObject();
	}

	/**
	 * Create an empty instance of the adapter.
	 */
	public LabeledImageObjectAdapter() {
		super();
		o = null; 
	}

	/*
	 *  Provides access to a given label of a segmented image.
	 */
	@Override
	public ImageData getLabel( double pixel_label_value ) 
	{
		ImageData r = null;	
		o = getImageObject();
		int numBands 	= o.getNumBands();
		int numRows 	= o.getNumRows();
		int numCols 	= o.getNumCols();
		double[][][] pixels = new double[numRows][numCols][numBands];
		
		for (int band = 0; band < numBands; band++) {
			for (int row = 0; row < numRows; row++) {
				for (int col = 0; col < numCols; col++) {
					pixels[row][col][band] = o.getDouble(row, col, band);
				}
			}
		}
		
		r = new ImageData(pixels);
		r = ops.match(r, pixel_label_value );
		return r;
	}
	
	/*
	 *  Provides access to a the set of possible pixel-value labels of a segmented image.
	 */
	@Override
	public ImageData[] getLabels() 
	{
		ImageData[] r = new ImageData[256];	// BJL: Need to revisit this assumption of 256 pixel values
		for (int i=0; i < r.length; i++) {
			r[i] = getLabel((double)i);
		}
		return r;
	}	

	@Override
	public String getName() {
		return "Labeled Image Object Adapter";
	}

}
