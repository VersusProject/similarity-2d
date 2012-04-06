

function [ARI RI] = Adjusted_Rand_Index_computation(Reference_Image, Compare_Image)

% Get the number of objects in both images
nb1 = unique(Reference_Image)+1;
nb2 = unique(Compare_Image)+1;

% Initialize the overlap_matrix.
overlap_matrix = zeros(nb1(end), nb2(end));

% Start the search for overlaps
for i = 1:numel(Reference_Image)
    overlap_matrix(Reference_Image(i)+1, Compare_Image(i)+1) = overlap_matrix(Reference_Image(i)+1, Compare_Image(i)+1) + 1;
end
% overlap_matrix = [1 1 0; 1 2 1; 0 0 4];
% Compute elements of contingency table for ARI calculation
A = sum(overlap_matrix,2);
B = sum(overlap_matrix);
overlap_matrix = overlap_matrix(:);
N = sum(overlap_matrix);
Nij = sum(overlap_matrix .* (overlap_matrix -1))/2;
A2 = sum(A .* (A -1))/2;
B2 = sum(B .* (B -1))/2;
N2 = N*(N -1)/2;

ARI = (Nij - A2*B2/N2) / (0.5*(A2+B2) - A2*B2/N2);

RI = 1+(2*Nij-A2-B2)/N2;





