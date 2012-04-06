

function [Euclidean City_block Chebyshev Intersection Divergence] = histogram_metrics_computation(Reference_Image, Compare_Image)

% Get the histogram for both images
max_bin = max(length(unique(Reference_Image)), length(unique(Compare_Image)));
P = hist(Reference_Image(:), max_bin);
Q = hist(Compare_Image(:), max_bin);

% Normalize histograms
P = P(:)/numel(Reference_Image);
Q = Q(:)/numel(Reference_Image);

% Delete rows with both zeros in them
r = (P==0 & Q==0);
P(r) = [];
Q(r) = [];

Euclidean = sqrt(sum((P-Q).^2));
City_block = sum(abs(P-Q));
Chebyshev = max(abs(P-Q));
Intersection = sum(min(P,Q));
Divergence = 2*sum(((P-Q)./(P+Q)).^2);

