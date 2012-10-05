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
 */
package gov.nist.itl.versus.similarity.comparisons.measure.impl;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.illinois.ncsa.versus.adapter.Adapter;
import edu.illinois.ncsa.versus.extract.Extractor;
import edu.illinois.ncsa.versus.measure.Measure;
import edu.illinois.ncsa.versus.registry.CompareRegistry;
import edu.illinois.ncsa.versus.utility.HasHelp;

/**
 *
 * @author antoinev
 */
public class HasHelpTest {

    public HasHelpTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAdapterActuallyHasHelp() {
        List<Adapter> adapters = CompareRegistry.getAdapters();
        for (Adapter adapter : adapters) {
            if (!(adapter instanceof HasHelp)) {
                continue;
            }

            HasHelp hasHelp = (HasHelp) adapter;
            assertNotNull("Adapter " + adapter + " has no sha1 help but implement HasHelp", hasHelp.getHelpSHA1());
            assertNotNull("Adapter " + adapter + " has no help but implement HasHelp", hasHelp.getHelpZipped());
        }
    }

    @Test
    public void testExtractorActuallyHasHelp() {
        List<Extractor> extractors = CompareRegistry.getExtractors();
        for (Extractor extractor : extractors) {
            if (!(extractor instanceof HasHelp)) {
                continue;
            }

            HasHelp hasHelp = (HasHelp) extractor;
            assertNotNull("Extractor " + extractor + " has no sha1 help but implement HasHelp", hasHelp.getHelpSHA1());
            assertNotNull("Extractor " + extractor + " has no help but implement HasHelp", hasHelp.getHelpZipped());
        }
    }

    @Test
    public void testMeasureActuallyHasHelp() {
        List<Measure> measures = CompareRegistry.getMeasures();
        for (Measure measure : measures) {
            if (!(measure instanceof HasHelp)) {
                continue;
            }

            HasHelp hasHelp = (HasHelp) measure;
            assertNotNull("Measure " + measure + " has no sha1 help but implement HasHelp", hasHelp.getHelpSHA1());
            assertNotNull("Measure " + measure + " has no help but implement HasHelp", hasHelp.getHelpZipped());
        }
    }
}
