
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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Collections;
import edu.illinois.ncsa.versus.adapter.impl.ImageObjectAdapter;
import edu.illinois.ncsa.versus.descriptor.impl.ThreeDimensionalDoubleArrayFeature;
import ncsa.im2learn.core.datatype.ImageObject;
import edu.illinois.ncsa.versus.descriptor.Descriptor;
import gov.nist.itl.versus.similarity.comparisons.descriptor.impl.*;

public class ImageData
{
  // internal data structures to mirror input from received features
	private double[][][] values;
	private int rows = 0;
	private int cols = 0;
	private int bands = 0;
	private boolean debug = false;
	
	
  // construction
	public ImageData() {
		this(new double[1][1][1],false);
	}
	
	public ImageData(double[][][] values) {
		this(values,false);
	}

	public ImageData(double[][][] values, boolean _debug ) {
		this.values = values;
		this.rows = values.length;
		this.cols = values[0].length;
		this.bands = values[0][0].length;
				
		if ( _debug==true ) {
			this.debug = true;
		}
	}
	
	public void setValues( double[][][] values ) { this.values = values; }
	public void setRows( int rows ) { this.rows = rows; }
	public void setCols( int cols ) { this.cols = cols; }
	public void setBands( int bands ) { this.bands = bands; }
	public boolean getDebug() { return debug; }
	public void setDebug( boolean d ) { debug=d; }
	
	public boolean equals( ImageData a ) 
	{
		if ( rows != a.getNumRows() ) return false;
		if ( cols != a.getNumCols() ) return false;
		if ( bands != a.getNumBands() ) return false;
		
		double[][][] avals = a.getValues();
		int len1 = values.length;
		int len2 = values[0].length;
		int len3 = values[0][0].length;
		for (int i=0; i < len1; i++)
			for (int j=0; j < len2; j++)
				for (int k=0; k < len3; k++)
					if ( values[i][j][k] != avals[i][j][k] )
						return false;
		return true;
	}
	
  // methods	
  
	public ImageData clone()
	{
		ImageData o = new ImageData();
		o.setValues( values );
		o.setRows( rows );
		o.setCols( cols );
		o.setBands( bands );
		o.setDebug( debug );
		return o;
	}  
  
	public double[][][] getValues() {
		return values;
	}
	
	public int[][][] getIntValues() 
	{
		int[][][] v = new int[rows][cols][bands];
		for (int i=0; i < rows; i++) {
			for (int j=0; j < cols; j++) {
				for (int k=0; k < bands; k++) {
					v[rows][cols][bands] = getInt(i,j,k);
				}
			}
		}
		return v;
	}
	
	public double getValue(int row, int col, int band) {
		return values[row][col][band];
	}
	
	public void set( int row, int col, int band, double value ) 
	{
		values[row][col][band] = value;
	}
	
	public void set( int row, int col, int band, int value ) 
	{
		set(row, col, band, (double)value );
	}

	public int getInt(int row, int col, int band) {
			return (int)values[row][col][band];
	}
	
	public int getNumBands() {
		return values[0][0].length;
	}
	
	public int getHeight() {
		return values.length;
	}
	
	public int getWidth() {
		return values[0].length;
	}
	
	public int getNumCols() { return getWidth(); }
	public int getNumRows() { return getHeight();}	
	
  // custom operations needed for metric calculations

	/*
	 * Load two input files and use adapters to obtain corresponding ImageObject objects.
	 * These objects are the basis of the metric calculations.
	 * 
	 * Thus, we could say that these metrics operate upon "pixel features".
	 * 
	 * There does not yet seem to be a "pixel adapter" (which would amount to a kind of
	 * "pixel 'pass-through'). To conform to the adapter/descriptor/measure pattern, we
	 * will implement such a minimal adapter and also relocate relevant parts of this code into
	 * those respective components.
	 */
	public Vector load(File f1, File f2)
	{
		Vector<ImageObject> v = new Vector();
		ImageObjectAdapter a1 = new ImageObjectAdapter();
		ImageObjectAdapter a2 = new ImageObjectAdapter();

		try {
			a1.load(f1);
			a2.load(f2);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		v.addElement( a1.getImageObject() );
		v.addElement( a2.getImageObject() );
		
		return v;
	}	
	
	/*
	 * Determine whether or not 2 ImageObject objects are the "same size"
	 * based on whether their respective dimensions are equal (e.g., height and width,
	 * in the terminology of "rows" and "columns").
	 * 
	 */
	public boolean sameSize(ImageData o1, ImageData o2)
	{
		int o1_cols = o1.getNumCols();
		int o1_rows = o1.getNumRows();
		int o2_cols = o2.getNumCols();
		int o2_rows = o2.getNumRows();

		if ( o1_cols!=o2_cols || o1_rows!=o2_rows) {
			return false;
		}
		return true;
	}

	/*
	 * Converts an input image object to a binary one (with each pixel value taking on only 1 or 0).
	 * @param o
	 * 		input image object to be converted
	 */
	public ImageData logical(ImageData o)
	{
		ImageData r = new ImageData();
		int pixel;

		try {
			r = (ImageData)o.clone();
		} catch (Exception e) {
			System.out.println( e.getMessage() );
			return null;
		}
		
		//System.out.println("logical:");
		for (int row=0; row < r.getNumRows(); row++) {
			
			for(int col=0; col < r.getNumCols(); col++) {
				for(int band=0; band < r.getNumBands(); band++) {
					pixel = r.getInt(row,col,band);
					//System.out.print(""+pixel + " ");
					if ( pixel > 0 ) {
						r.set(row,col,band,1);	// if pixel > 0, set it to 1
					}
					else {
						r.set(row,col,band,0);	// otherwise, set it to 0
					}
				}
			}
			//System.out.println("");
		}
	//	System.out.println(">logical.");
		return r;
	}
	
	
	/*
	 * Returns the sum of the pixel values of a given ImageData.
	 * @param o
	 * 		The ImageData providing pixels to be counted
	 */		
	public double sum(ImageData o)
	{
		double sum = 0;				
		
		for (int row=0; row < o.getNumRows(); row++) {
			for(int col=0; col < o.getNumCols(); col++) {
				for(int band=0; band < o.getNumBands(); band++) {
					sum += o.getValue(row,col,band);
				}
			}
		}			
		return sum;
	}

	/*
	 * Given an input ImageData, return a list of its matrix contents as a
	 * single 1D list.
	 * 
	 * Do this by traversing the ImageData in row-major order.
	 * 
	 * @param o
	 * 		The ImageData we've input for "vectorization" (a.k.a., "flattening")
	 */
	
	public ArrayList vectorize(ImageData o)
	{
		ArrayList list = new ArrayList();	

		for (int row=0; row < o.getNumRows(); row++) {
			for(int col=0; col < o.getNumCols(); col++) {
				for(int band=0; band < o.getNumBands(); band++) {				
					list.add( new Double(o.getValue(row,col,band)) );
				}
			}
		}	

		return list;
	}		
	
	/*
	 * Implements a "logical and-not" operation for a pair of ImageData objects.
	 * Functionally, it is composed as follows: result = not(and(o1,o2))
	 * @param o1
	 * 		The first ImageData object
	 * @param o2
	 * 		The second ImageData object
	 */	
	public ImageData andNot(ImageData o1, ImageData o2)
	{
		ImageData r = and(o1,o2);		
		return not(r);				// invert before returning
	}
	
	
	/*
	 * Implements a "logical and" operation for a pair of ImageData objects.
	 * @param o1
	 * 		The first ImageData object
	 * @param o2
	 * 		The second ImageData object
	 */	
	public ImageData and(ImageData o1, ImageData o2)
	{
		int pixel_o1;
		int pixel_o2;
		ImageData r = new ImageData();		
		ImageData bin_o1 = logical(o1); // BJL: We convert them to logical/binary form by default. Helpful if not unconverted. Otherwise, this action is redundant. Covered both cases here.
		ImageData bin_o2 = logical(o2);
		
		try {
			r = (ImageData)o1.clone();	// BJL: This algorithm assumes the input images are the same size, so can choose either one as a template for the result.
		} catch (Exception e) {
			System.out.println( e.getMessage() );
			return null;
		}				
		
		for (int row=0; row < r.getNumRows(); row++) {
			for(int col=0; col < r.getNumCols(); col++) {
				for(int band=0; band < r.getNumBands(); band++) {
					pixel_o1 = bin_o1.getInt(row,col,band);
					pixel_o2 = bin_o2.getInt(row,col,band);
					if ( (pixel_o1==1) && (pixel_o2==1) ) {
						r.set(row,col,band,1);	// if pixels are both 1, set it to 1
					}
					else {
						r.set(row,col,band,0);	// otherwise, set it to 0
					}
				}
			}
		}

		return r;
	}
	
	
	/*
	 * Implements a "logical negation" operation for a single ImageData object.
	 * @param o
	 * 		The ImageData object to be negated.
	 */
	public ImageData not(ImageData o)
	{

		int pixel;
		ImageData r = new ImageData();		
		ImageData bin_o = logical(o);
		
		try {
			r = (ImageData)bin_o.clone();	// BJL: This algorithm assumes the input images are the same size, so can choose either one as a template for the result.
		} catch (Exception e) {
			System.out.println( e.getMessage() );
			return null;
		}				
	
		//System.out.println("not:");
		for (int row=0; row < r.getNumRows(); row++) {
			for(int col=0; col < r.getNumCols(); col++) {
				for(int band=0; band < r.getNumBands(); band++) {
					pixel = bin_o.getInt(row,col,band);
					//System.out.print(""+pixel+" ");
					
					if ( pixel == 1 ) {
						r.set(row,col,band,0);	// if pixel = 1, set it to 0
					}
					else {
						r.set(row,col,band,1);	// otherwise, set it to 1
					}
				}
			}
			//System.out.println();
		}
		//System.out.println(">not.");
		
		return r;
	}
	
	
	/*
	 * Implements a "logical or" operation for a pair of ImageData objects.
	 * @param o1
	 * 		The first ImageData object
	 * @param o2
	 * 		The second ImageData object
	 */
	public ImageData or(ImageData o1, ImageData o2)
	{	

		int pixel_o1;
		int pixel_o2;
		ImageData r = new ImageData();		
		ImageData bin_o1 = logical(o1);	// BJL: We convert them to logical/binary form by default. Helpful if not unconverted. Otherwise, this action is redundant. Covered both cases here.
		ImageData bin_o2 = logical(o2);	
		
		try {
			r = (ImageData)o1.clone();	// BJL: This algorithm assumes the input images are the same size, so can choose either one as a template for the result.
		} catch (Exception e) {
			System.out.println( e.getMessage() );
			return null;
		}				
		
		for (int row=0; row < r.getNumRows(); row++) {
			for(int col=0; col < r.getNumCols(); col++) {
				for(int band=0; band < r.getNumBands(); band++) {
					pixel_o1 = bin_o1.getInt(row,col,band);
					pixel_o2 = bin_o2.getInt(row,col,band);
					if ( (pixel_o1==1) || (pixel_o2==1) ) {
						r.set(row,col,band,1);	// if either pixel is 1, set it to 1
					}
					else {
						r.set(row,col,band,0);	// otherwise, set it to 0
					}
				}
			}
		}

		return r;
	}
	
	/*
	 * Implements a "logical negation" operation for a single ImageData object.
	 * @param o
	 * 		The ImageData object to be negated.
	 */
	public ImageData match(ImageData o, double value)
	{
		int pixel;
		ImageData r = new ImageData();		
		
		try {
			r = (ImageData)o.clone();	// BJL: This algorithm assumes the input images are the same size, so can choose either one as a template for the result.
		} catch (Exception e) {
			System.out.println( e.getMessage() );
			return null;
		}				
		
		for (int row=0; row < r.getNumRows(); row++) {
			for(int col=0; col < r.getNumCols(); col++) {
				for(int band=0; band < r.getNumBands(); band++) {
					pixel = o.getInt(row,col,band);
					if ( pixel == (int)value ) {
						r.set(row,col,band,1);	// if pixel = value, set it to 1
					}
					else {
						r.set(row,col,band,0);	// otherwise, set it to 0
					}
				}
			}
		}

		return r;
	}
	
	
	/*
	 * Implements an element-wise addition operation for a pair of ImageData objects.
	 * @param o1
	 * 		The first ImageData object
	 * @param o2
	 * 		The second ImageData object
	 */	
	public ImageData add(ImageData o1, ImageData o2)
	{

		double pixel1	= 0.0;
		double pixel2	= 0.0;
		double value	= 0.0;
		ImageData r = new ImageData();		
		
		try {
			r = (ImageData)o1.clone();	// BJL: This algorithm assumes the input images are the same size, so can choose either one as a template for the result.
		} catch (Exception e) {
			System.out.println( e.getMessage() );
			return null;
		}				
		
		for (int row=0; row < r.getNumRows(); row++) {
			for(int col=0; col < r.getNumCols(); col++) {
				for(int band=0; band < r.getNumBands(); band++) {
					pixel1 = o1.getInt(row,col,band);
					pixel2 = o2.getInt(row,col,band);
					value = pixel1 + pixel2;
					r.set(row,col,band,value);	
				}
			}
		}
		return r;
	}
	
	
	/* Returns the unique set of pixel values present in the given ImageData.
	 * @param
	 * 		o	the ImageData explored for this set
	 */
	public ArrayList unique(ImageData o) 
	{
		int pixel_values[]	= new int[256];		// use for counting pixel values actually seen
		ArrayList<Double> list = new ArrayList();		// use for storing only those pixel values that are non-zero when it's all said and done
				
		// initialize pixel value array
		//for (int i=0; i < 256; i++) { pixel_values[i] = 0; }
		for (int i=0; i < 255; i++) { pixel_values[i] = 0; }

		// walk and count pixel values
		for (int row=0; row < o.getNumRows(); row++) {
			for(int col=0; col < o.getNumCols(); col++) {
				for(int band=0; band < o.getNumBands(); band++) {
					pixel_values[ o.getInt(row, col, band) ]++;
				}
			}
		}				
		
		// identify and collect only unique pixel values (i.e., those with non-zero counts)
		int total_unique = 0;
		//for (int i=0; i < 256; i++) {
		for (int i=0; i < 255; i++) {
			if ( pixel_values[i] != 0 ) {
				list.add(new Double(i));
			}
		}

		return list;
	}
	
	
	/*
	 * General use:
	 * 	Given an input list, sort it and provide a list of only its unique (non-duplicate)
	 * 	elements (still in sorted order).
	 * 
	 * Specific use:
	 * 	Given an unordered list of non-zero pixel values in an image, provide a sorted
	 *  unique list of pixel-values that occur in that image.
	 *  
	 * Comment:
	 * 	While this is generic, it is used specifically to process a list of pixel values
	 *  to provide a unique list of pixel values (usually for an image that has been
	 *  pre-processed by mask() and unique() above.
	 *  For example:
	 *  	im1_nonZeroEntries = mask(originalImage, imageMask);
	 *  	img_unsorted_pixel_value_list = unique(image). 
	 *  
	 *  Then, one would run that list through this method to obtain the list of uniquely-occuring pixel values
	 *  in that image.
	 *  
	 * @param inputList
	 * 		The list of unordered, non-zero integer pixel values from an image.
	 */
	public ArrayList uniqueSort( ArrayList inputList )
	{
		ArrayList a = (ArrayList)inputList.clone();
		ArrayList uniqueList = new ArrayList();
		Collections.sort(a);
		for ( Object o: a ) {		
			if ( !uniqueList.contains(o) ) {
				uniqueList.add(o);
			}
		}
		return uniqueList;
	}
	
	/*
	 * Traverses the original and mask images in tandem.
	 * The mask should be identical to the original except it
	 * should have been preselected to be a binary image.
	 * 
	 * Wherever the mask has a 1 value, add that pixel value to 
	 * the list.
	 * 
	 * Return the list unsorted and only containing pixel values of
	 * the original matching a corresponding mask 1-value.
	 * 
	 * @param orig
	 * 		The original ImageData image
	 * @param mask
	 * 		The corresponding ImageData mask (having identical dimensions and containing binary values)
	 * 
	 */
	public ArrayList mask(ImageData orig, ImageData mask)
	{
		ArrayList list = new ArrayList();	// a column-wise traversal of the input images populates this 1D list

		for (int col=0; col < orig.getNumCols(); col++) {
			for(int row=0; row < orig.getNumRows(); row++) {
				for(int band=0; band < orig.getNumBands(); band++) {
					if ( mask.getValue(row,col,band) == 1 ){
						list.add( new Double(orig.getValue(row,col,band)) );
					}
				}
			}
		}		
		return list;
	}



	/*
	 * Create and return a 2D integer matrix where all entries contain the given value.
	 * 
	 * This will be frequently be used to initialize "zero-ized" arrays.
	 * 
	 * @param rows
	 * 		Number of rows of the matrix
	 * @param cols
	 * 		Number of columns of the matrix
	 * @param value
	 * 		The initialization value to assign to each cell location in the matrix
	 */
	
	public double[][] makeZeroMatrix2D( int rows, int cols, double value ) 
	{
		double m[][] = new double[rows][cols];
		for (int i=0,j=0; i<rows && j<cols; i++,j++) {
			m[i][j] = value;
		}
		return m;
	}
	
	/*
	 * Given a list of values (often a vectorized sequence of non-zero entries
	 * from an image matrix) return a binary list annotated with 1-entries where the
	 * given value is found in the list and 0-entries everywhere else.
	 * 
	 * @param originalContent
	 * 		The input list of non-zero integer values
	 * @param value
	 * 		The specific integer value we're searching for in that list
	 */
	public ArrayList getMatches( ArrayList originalContent, Double value )
	{
		ArrayList r = new ArrayList();
		double val = value.doubleValue();
		double k   = 0.0;
		
		for (Object o: originalContent ) {
			k = ((Double)o).doubleValue();
			if ( k == val ) {
				r.add( new Double(1));
			}
			else {
				r.add( new Double(0));
			}					
		}		
		return r;
	}
	
	/*
	 * Perform a "binary and" operation on two input binary-valued integer lists of the same length.
	 * Return a list of equal length where each entry is annotated with 1 (if they both
	 * have a 1) or 0 otherwise.
	 * 
	 * @param a1
	 * 		The first binary-valued input integer list
	 * @param a2
	 * 		The 2nd binary-valued input integer list
	 */
	public ArrayList and(ArrayList a1, ArrayList a2)
	{
		ArrayList r = new ArrayList();
		int length = a1.size();
		double a1_val = 0;
		double a2_val = 0;
		
		if ( a1.size() != a2.size() ) {
			output("error: input lists must be the same size");
			return null;
		}
		
		for (int i=0; i < length; i++) {
			a1_val = ((Double)a1.get(i)).intValue();
			a2_val = ((Double)a2.get(i)).intValue();
			if ( (a1_val==1) && (a2_val==1) ) {
				r.add( new Double(1));
			}
			else {
				r.add( new Double(0));
			}
		}

		return r;
	}
	
	/*
	 * Given a binary-valued input list, return the sum of its entries.
	 * 
	 * @param a
	 * 		The binary-valued input list
	 */
	public double sum(ArrayList a)
	{
		double sum = 0;
		double val = 0;
		for (Object o: a) {
			val = ((Double)o).doubleValue();
			sum += val;
		}	
		return sum;
	}
	
	/*
	 * Given a 2D integer matrix as input, provide a list of the designated column's entries.
	 * 
	 * @param m
	 * 		2D integer matrix from which to extract and return a column	
	 * @param rows
	 * 		The number of rows in the matrix for the given column
	 * @param col
	 * 		The specific column index to extract
	 */
	public ArrayList getColumn(double M [][], int rows, int col)
	{
		ArrayList r = new ArrayList();
		for (int i=0; i < rows; i++ ) {
			r.add(new Double(M[i][col]));
		}
		return r;
	}

	/*
	 * Given a 2D integer matrix as input, provide a list of the designated row's entries.
	 * 
	 * @param m
	 * 		2D integer matrix from which to extract and return a column	
	 * @param cols
	 * 		The number of cols in the matrix for the given row
	 * @param row
	 * 		The specific column index to extract
	 */
	public ArrayList getRow(double M [][], int cols, int row)
	{
		ArrayList r = new ArrayList();
		for (int i=0; i < cols; i++ ) {
			r.add(new Double(M[row][i]));		
		}
		return r;
	}
	
	
	/*
	 * Returns the simplified "n choose 2" form of the "n choose r" calculation.
	 * 
	 * @param n
	 * 		Size of the set on which we perform this calculation
	 */
	public double nChoose2( double n )
	{
		double result = ((n*n) - n)/2;
		return result;
	}

	
	/*
	 * Outputs a string message to standard output.
	 * @param msg
	 * 		The message to be output.
	 */
	public void output( String msg ) { System.out.println(msg); }
	
	public int d2b(double d) {
		return (d > 0) ? 1 : 0;
	}		  
}
