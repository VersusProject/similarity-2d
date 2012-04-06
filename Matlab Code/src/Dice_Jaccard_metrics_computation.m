

function [dice jaccard] = Dice_Jaccard_metrics_computation(Reference_Image, Compare_Image)

% Compute the sum of the two images
I = (Reference_Image>0) + (Compare_Image>0);

% compute overlap pixels between the two images
overlap = I == 2;

% Compute entire cell area in both images
I = I>0;

% Compute dice and jaccard metric
dice = 2*sum(overlap(:))/(sum(I(:))+sum(overlap(:)));
jaccard = sum(overlap(:))/sum(I(:));




