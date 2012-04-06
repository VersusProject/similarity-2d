
Similarity Metric Implementations

=================================================
Using Metric Implementations in Eclipse
=================================================
1. Ensure Eclipse is installed
2. Create new project in Eclipse (File->New->Java Project)
3. Copy in lib/ dir (with jars)
4. Add in jars (R-click projectName -> Properties -> Java Build Path -> Libraries tab; foreach jar, Add External JARs -> select jar in lib/ )
5. Copy in (and/or extract from zip) code inside src/ directory (should create comparisons/ directory tree)
6. Should be ready to edit or use with Eclipse

=================================================
Running Various Metric Tests
=================================================
1. Select tests.comparisons.measure.impl directory in eclipse browser (usually in left panel in "Java Browsing Perspective"). 
2. Right-click tests.comparisons.measure.impl directory -> Right-click "Run As" -> Right-click "JUnit Test"
3. All the unit tests should run and display output results in the console window.

   The test data files are in test/data/ and test/data/math_ops/ subdirectories. 

=================================================
Release History / Release Notes
=================================================

12/2011	 similarity v 1.0
	Benjamin Long	Initial upload of similarity metric code (unofficially called similarity metric code, v 1.0)
		- Quick initial prototyping of metrics, no major testing done yet
		
12/23/2011 similarity v 1.1
	Benjamin Long	First major update (a.k.a., similarity v. 1.1 )
		- Refactored some of the code, replacing comparisons.ImageOps.java with comparisons.ImageData.java
		  as the primary data structure for performing logical/relational operations on pixel/matrix representations
		  of images (primarily used with TEE, TET, and ARI metrics, currently).
		  
		  This removed a former more complex and uglier design which tried to make "Editable" arrays.
		  
		  This current release honors the policy of keeping features "read-only".
		  By moving the transformations into ImageData.java, underlying implementation was better encapsulated from
		  the operational API/interface.
		  
		- Redesigned the basic flow for so-called "labeled-images" by small extensions to
		  Versus core classes, making:
		    comparisons.adapter.HasLabels,
		    comparisons.adapter.impl.LabeledImageObjectAdapter,
			comparisons.extract.impl.LabeledArrayFeatureExtractor,
			comparisons.descriptor.impl.LabeledThreeDimensionalDoubleArrayFeature,
			comparisons.measure.LabeledMeasure,
			
			and then having each of the related "label-supporting" metrics (TEE, TET, and ARI)
			implement the "LabeledMeasure" interface, which allows them to automatically calculate
			and report a series of measures, 1 for each label, which is just a mask of a particular
			pixel value in a given image. Related such pixel-valued-labels are then compared to
			produce the corresponding list of measures.
			
		- Created JUnit test cases for each metric and placed them in the
		    test.comparisons.measure.impl subdirectory. Instructions for running them are above.
			
		- Finally, added the META-INF/services/ subdirectory tree and related set of files for
		  plugging the implemented metrics into the Versus web application.
			
01/03/2012 similarity v 1.1
	Benjamin Long	Small cleanup update
		- removed an unused library from metric import lists
		- updated Javadoc tag syntax to remove javadoc warnings		  

01/26/2012 similarity v 1.2
	Benjamin Long	Architecture synchronization update
		- removed old *versus*bjl*.jar file
		- added project standard jars for versus-core and versus-image from Antoine
		- implemented N-to-1 relationship of N-features-per-measure by implementing expanded Measure interface method: 
		  supportedFeaturesTypes() for all metric classes
		- extended LabledArrayFeatureExtractor to comply with extended versus base class, Extractor, which had added two
		  new abstract methods, hasPreview() and previewName(). These are not currently used by may be at some later time.
		- modified measure display names to fit better on web interface UI
		- centralized metric code for ARI, TEE, and TET, into MathOps as is the case for all other current metric implementations
		  for uniformity of construction