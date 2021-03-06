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
 * name          Jaccard 
 * description   
 * @author       Benjamin Long
 * @version      1.4
 * date          
 */
package gov.nist.itl.versus.similarity.comparisons.measure.impl;

import gov.nist.itl.versus.similarity.comparisons.MathOpsE;
import edu.illinois.ncsa.versus.UnsupportedTypeException;
import edu.illinois.ncsa.versus.descriptor.Descriptor;
import edu.illinois.ncsa.versus.descriptor.impl.GrayscaleHistogramDescriptor;
import edu.illinois.ncsa.versus.descriptor.impl.PixelHistogramDescriptor;
import edu.illinois.ncsa.versus.descriptor.impl.RGBHistogramDescriptor;
import edu.illinois.ncsa.versus.measure.Measure;
import edu.illinois.ncsa.versus.measure.Similarity;
import edu.illinois.ncsa.versus.measure.SimilarityNumber;
import edu.illinois.ncsa.versus.measure.SimilarityPercentage;
import edu.illinois.ncsa.versus.utility.HasCategory;
import edu.illinois.ncsa.versus.utility.HasHelp;
import edu.illinois.ncsa.versus.utility.HelpProvider;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import gov.nist.itl.versus.similarity.comparisons.exception.*;

public class JaccardMeasure implements Measure, HasCategory, HasHelp
{
	private MathOpsE ops = new MathOpsE();

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
							throw new ImageCompatibilityException("Features must have the same length");
					}
					
					Double[] normHist1 = ops.normalizeRgbHistogram( feature1 );
					
						if ( normHist1 == null ) 
							throw new MathCompatibilityException("Histogram normalization failed for RGBHistogramDescriptor histogram1");					
					
					Double[] normHist2 = ops.normalizeRgbHistogram( feature2 );
					
						if ( normHist2 == null ) 
							throw new MathCompatibilityException("Histogram normalization failed for RGBHistogramDescriptor histogram2");

					Double measurement  = ops.histogram_measure_jaccard( normHist1, normHist2 );
									
						if ( measurement == null ) 
							throw new SingularityTreatmentException("Received null measurement value");	
							
					SimilarityNumber result = new SimilarityNumber(measurement.doubleValue());
				
						if ( result == null )
							throw new SingularityTreatmentException("Received null SimilarityNumber comparison result");							
						
					return result;
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
                        throw new ImageCompatibilityException("Features must have the same length");
                }

                Double[] normHist1 = ops.normalizeGrayscaleHistogram( feature1 );
				
					if ( normHist1 == null ) 
						throw new MathCompatibilityException("Histogram normalization failed for GrayscaleHistogramDescriptor histogram1");
				
                Double[] normHist2 = ops.normalizeGrayscaleHistogram( feature2 );
				
					if ( normHist2 == null ) 
						throw new MathCompatibilityException("Histogram normalization failed for GrayscaleHistogramDescriptor histogram2");				

                Double measurement  = ops.histogram_measure_jaccard( normHist1, normHist2 );
				
					if ( measurement == null ) 
						throw new SingularityTreatmentException("Received null measurement value");	

				SimilarityNumber result = new SimilarityNumber(measurement.doubleValue());
				
					if ( result == null )
						throw new SingularityTreatmentException("Received null SimilarityNumber comparison result");
				
                return result;
        }

        /**
         * Compares two Pixel Histograms.
         *
         * @param feature1 PixelHistogramDescriptor
         * @param feature2 PixelHistogramDescriptor
         * @return SimilarityNumber
         * @throws Exception
         */
        public SimilarityNumber compare(PixelHistogramDescriptor feature1, PixelHistogramDescriptor feature2) throws Exception {

                // Check feature lengths, they must be equal
                if( feature1.getNumBins() != feature2.getNumBins() ) {
                        throw new ImageCompatibilityException("Features must have the same length");
                }

                Double[] normHist1 = ops.normalizePixelHistogram( feature1 );
				
					if ( normHist1 == null ) 
						throw new MathCompatibilityException("Histogram normalization failed for GrayscaleHistogramDescriptor histogram1");
				
                Double[] normHist2 = ops.normalizePixelHistogram( feature2 );
				
					if ( normHist2 == null ) 
						throw new MathCompatibilityException("Histogram normalization failed for GrayscaleHistogramDescriptor histogram2");				

                Double measurement  = ops.histogram_measure_jaccard( normHist1, normHist2 );
				
					if ( measurement == null ) 
						throw new SingularityTreatmentException("Received null measurement value");	

				SimilarityNumber result = new SimilarityNumber(measurement.doubleValue());
				
					if ( result == null )
						throw new SingularityTreatmentException("Received null SimilarityNumber comparison result");
				
                return result;
        }
        
		@Override
		public SimilarityNumber compare(Descriptor feature1, Descriptor feature2)	throws Exception {
				if (feature1 instanceof RGBHistogramDescriptor && feature2 instanceof RGBHistogramDescriptor) {

						RGBHistogramDescriptor histogramFeature1 = (RGBHistogramDescriptor) feature1;
						
							if ( histogramFeature1 == null )
								throw new SWIndependenceException("Feature extraction failed for RGBHistogramDescriptor feature1");
						
						RGBHistogramDescriptor histogramFeature2 = (RGBHistogramDescriptor) feature2;
						
							if ( histogramFeature2 == null )
								throw new SWIndependenceException("Feature extraction failed for RGBHistogramDescriptor feature2");
								
						SimilarityNumber result = compare(histogramFeature1, histogramFeature2);		
						
							if ( result == null )
								throw new SingularityTreatmentException("Received null RGBHistogramDescriptor comparison value");
						
						return result;
				}
				else if( feature1 instanceof GrayscaleHistogramDescriptor && feature2 instanceof GrayscaleHistogramDescriptor) {

						GrayscaleHistogramDescriptor histogramFeature1 = (GrayscaleHistogramDescriptor) feature1;
						
							if ( histogramFeature1 == null )
								throw new SWIndependenceException("Feature extraction failed for GrayscaleHistogramDescriptor feature1");						
						
						GrayscaleHistogramDescriptor histogramFeature2 = (GrayscaleHistogramDescriptor) feature2;
						
							if ( histogramFeature2 == null )
								throw new SWIndependenceException("Feature extraction failed for GrayscaleHistogramDescriptor feature2");

						SimilarityNumber result = compare(histogramFeature1, histogramFeature2);
						
							if ( result == null )
								throw new SingularityTreatmentException("Received null SimilarityNumber comparison result");
						
						return result;
				}
				else if( feature1 instanceof PixelHistogramDescriptor && feature2 instanceof PixelHistogramDescriptor) {

					PixelHistogramDescriptor histogramFeature1 = (PixelHistogramDescriptor) feature1;
					
						if ( histogramFeature1 == null )
							throw new SWIndependenceException("Feature extraction failed for GrayscaleHistogramDescriptor feature1");						
					
					PixelHistogramDescriptor histogramFeature2 = (PixelHistogramDescriptor) feature2;
					
						if ( histogramFeature2 == null )
							throw new SWIndependenceException("Feature extraction failed for GrayscaleHistogramDescriptor feature2");

					SimilarityNumber result = compare(histogramFeature1, histogramFeature2);
					
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
			features.add( RGBHistogramDescriptor.class );
			features.add( GrayscaleHistogramDescriptor.class );
			features.add( PixelHistogramDescriptor.class );
			return features;
		}
	
		public String getFeatureType() {
			return RGBHistogramDescriptor.class.getName();
		}

		@Override
		public String getName() {
			return "Jaccard Similarity";
		}

		@Override
		public Class getType() {
			return JaccardMeasure.class;
		}	
		
		@Override
		public String getCategory() {
			return "Inner Product Family";
		}

		@Override
		public InputStream getHelpZipped() {
			return HelpProvider.getHelpZipped(JaccardMeasure.class);
		}

		@Override
		public String getHelpSHA1() {
			return HelpProvider.getHelpSHA1(JaccardMeasure.class);
		}
}