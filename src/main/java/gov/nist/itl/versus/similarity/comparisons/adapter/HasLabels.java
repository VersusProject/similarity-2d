
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

package gov.nist.itl.versus.similarity.comparisons.adapter;

import edu.illinois.ncsa.versus.adapter.HasPixels;
import gov.nist.itl.versus.similarity.comparisons.ImageData;

/**
 *  This interface allows one to manipulate anything that has "labels". 
 *  'Label' is a term used frequently by staff on the CS-BIO-MET project. 
 *  For a given binary-segmented-image, there may exist 1+ levels/labels/regions
 *  where the corresponding pixel values may range from 0 to some number (usually pixel values
 *  are low in these cases, say, from 0 to 3, for example).
 * 
 *  The purpose of this interface is to select a particular subset of pixel values (i.e., the
 *  corresponding layer or label) from an image where all the pixel values are the same. 
 *  
 *  Thus, a user of this interface gives the desired label pixel value along with the input 
 *  image segmentation. They are then provided with a binary image (i.e., having only 1- 
 *  and 0- pixel values) where the 1s identify all pixel locations where that pixel value 
 *  occurred and all other pixel values are 0s.
 * 
 *  This is an extension of the HasPixels interface. As such, it should implement methods
 *  so that anyone using this interface can process pixels to manipulate labels no matter the source. 
 * 
 *  Where pixel methods are implemented, the first dimension is rows, second is columns, and third is bands.
 * 
 */
public interface HasLabels extends HasPixels 
{	
	public ImageData getLabel( double pixel_label_value );
	public ImageData[] getLabels();
}