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
 *    Date: Mon Jan 23 07:24:30 EST 2012
 */


package gov.nist.itl.versus.similarity.comparisons.measure.impl;

import edu.illinois.ncsa.versus.UnsupportedTypeException;
import edu.illinois.ncsa.versus.descriptor.Descriptor;
import edu.illinois.ncsa.versus.descriptor.impl.GrayscaleHistogramDescriptor;
import edu.illinois.ncsa.versus.descriptor.impl.PixelHistogramDescriptor;
import edu.illinois.ncsa.versus.descriptor.impl.RGBHistogramDescriptor;
import edu.illinois.ncsa.versus.descriptor.impl.ThreeDimensionalDoubleArrayFeature;
import edu.illinois.ncsa.versus.measure.Measure;
import edu.illinois.ncsa.versus.measure.Similarity;
import edu.illinois.ncsa.versus.measure.SimilarityNumber;
import edu.illinois.ncsa.versus.measure.SimilarityPercentage;
import gov.nist.itl.versus.similarity.comparisons.MathOps;
import gov.nist.itl.versus.similarity.comparisons.descriptor.impl.LabeledThreeDimensionalDoubleArrayFeature;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class CanberraMeasure implements Measure
{
	private MathOps ops = new MathOps();

		@Override
		public SimilarityPercentage normalize(Similarity similarity) {
			return null;
		}	

        /**
         * Compares two RGB Histograms.
         *
         * @param feature1 RGBHistogramDescriptor
         * @param feature2 RGBHistogramDescriptor
         * @return SimilarityNumber
         * @throws Exception
         */	
		public SimilarityNumber compare(RGBHistogramDescriptor feature1, RGBHistogramDescriptor feature2) throws Exception 
		{
					// Check feature lengths, they must be equal
					if( feature1.getNumBins() != feature2.getNumBins() ) {
							throw new Exception("Features must have the same length");
					}
					
					double[] normHist1 = ops.rgbNormHistogram2Double( feature1 );
					double[] normHist2 = ops.rgbNormHistogram2Double( feature2 );
					double measurement  = ops.d_canberra( normHist1, normHist2 );
					return new SimilarityNumber(measurement);
		}

        /**
         * Compares two Grayscale Histograms.
         *
         * @param feature1 GrayscaleHistogramDescriptor
         * @param feature2 GrayscaleHistogramDescriptor
         * @return SimilarityNumber
         * @throws Exception
         */
        public SimilarityNumber compare(GrayscaleHistogramDescriptor feature1, GrayscaleHistogramDescriptor feature2) throws Exception {

                // Check feature lengths, they must be equal
                if( feature1.getNumBins() != feature2.getNumBins() ) {
                        throw new Exception("Features must have the same length");
                }

                double[] normHist1 = ops.grayScaleNormHistogram2Double( feature1 );
                double[] normHist2 = ops.grayScaleNormHistogram2Double( feature2 );
                double measurement  = ops.d_canberra( normHist1, normHist2 );
                return new SimilarityNumber(measurement);
        }
	
		@Override
		public SimilarityNumber compare(Descriptor feature1, Descriptor feature2)	throws Exception {
				if (feature1 instanceof RGBHistogramDescriptor && feature2 instanceof RGBHistogramDescriptor) {

						RGBHistogramDescriptor histogramFeature1 = (RGBHistogramDescriptor) feature1;
						RGBHistogramDescriptor histogramFeature2 = (RGBHistogramDescriptor) feature2;
						return compare(histogramFeature1, histogramFeature2);
				}
				else if( feature1 instanceof GrayscaleHistogramDescriptor && feature2 instanceof GrayscaleHistogramDescriptor) {

						GrayscaleHistogramDescriptor histogramFeature1 = (GrayscaleHistogramDescriptor) feature1;
						GrayscaleHistogramDescriptor histogramFeature2 = (GrayscaleHistogramDescriptor) feature2;
						return compare(histogramFeature1, histogramFeature2);
				}
				else {
						throw new UnsupportedTypeException(
										"Similarity measure expects features of type" + supportedTypesString() );
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
			features.add( RGBHistogramDescriptor.class );
			features.add( GrayscaleHistogramDescriptor.class );
			return features;
		}
	
		public String getFeatureType() {
			return RGBHistogramDescriptor.class.getName();
		}

		@Override
		public String getName() {
			return "CanberraMeasure";
		}

		@Override
		public Class getType() {
			return CanberraMeasure.class;
		}	
}
