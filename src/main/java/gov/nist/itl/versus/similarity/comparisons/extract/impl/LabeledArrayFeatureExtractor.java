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
 *  @version 1.2
 *    Date: Mon Jan 23 07:24:45 EST 2012
 *    			Updated with abstract methods hasPreview() and hasPreviewName() to
 *               sync with updates to base class Extractor in versus-core.
 */
package gov.nist.itl.versus.similarity.comparisons.extract.impl;

import java.util.HashSet;
import java.util.Set;

import edu.illinois.ncsa.versus.UnsupportedTypeException;
import edu.illinois.ncsa.versus.adapter.Adapter;
import edu.illinois.ncsa.versus.descriptor.Descriptor;
import edu.illinois.ncsa.versus.extract.Extractor;
import edu.illinois.ncsa.versus.utility.HasCategory;
import gov.nist.itl.versus.similarity.comparisons.adapter.HasLabels;
import gov.nist.itl.versus.similarity.comparisons.adapter.impl.LabeledImageObjectAdapter;
import gov.nist.itl.versus.similarity.comparisons.descriptor.impl.LabeledThreeDimensionalDoubleArrayFeature;

public class LabeledArrayFeatureExtractor implements Extractor, HasCategory {

    private LabeledThreeDimensionalDoubleArrayFeature extract(HasLabels adapter) {
        return new LabeledThreeDimensionalDoubleArrayFeature(adapter.getLabels());
    }

    @Override
    public LabeledImageObjectAdapter newAdapter() {
        return new LabeledImageObjectAdapter();
    }

    @Override
    public String getName() {
        return "Pixels2LabeledArray";
    }

    @Override
    public Descriptor extract(Adapter adapter) throws UnsupportedTypeException {
        if (adapter instanceof HasLabels) {
            HasLabels hasLabels = (HasLabels) adapter;
            return extract(hasLabels);
        } else {
            throw new UnsupportedTypeException();
        }
    }

    @Override
    public Set<Class<? extends Adapter>> supportedAdapters() {
        Set<Class<? extends Adapter>> adapters = new HashSet<Class<? extends Adapter>>();
        adapters.add(HasLabels.class);
        return adapters;
    }

    @Override
    public Class<? extends Descriptor> getFeatureType() {
        return LabeledThreeDimensionalDoubleArrayFeature.class;
    }

    @Override
    public boolean hasPreview() {
        return false;
    }

    @Override
    public String previewName() {
        return null;
    }

    @Override
    public String getCategory() {
        return "2D";
    }
}
