

% sensitivity Analysis

% Set the number of examples
nb_examples = 8;

% Create the images_path and the file_name as cell structures
images_path = cell(nb_examples,1);
file_name = cell(nb_examples,1);
binary = zeros(nb_examples,1); binary(1:4) = 1;
path_to_folders = 'C:\Joe\Programming\CS-BioMet\Similarity Metric\';

% Define paths and filenames for each example
i = 1;
images_path{i} = [path_to_folders 'Translation Simulation\'];
file_name{i} = 'Translation';

i = i+1;
images_path{i} = [path_to_folders 'Rotation Simulation\'];
file_name{i} = 'Rotation';

i = i+1;
images_path{i} = [path_to_folders 'Scaling Simulation\'];
file_name{i} = 'Scaling';

i = i+1;
images_path{i} = [path_to_folders 'ellipse\'];
file_name{i} = 'Ellipse';

i = i+1;
images_path{i} = [path_to_folders 'fourColorBox\Blur\'];
file_name{i} = 'fourColorBox_Blur';

i = i+1;
images_path{i} = [path_to_folders 'fourColorBox\Gamma\'];
file_name{i} = 'fourColorBox_Gamma';

i = i+1;
images_path{i} = [path_to_folders 'checkerGranularity\'];
file_name{i} = 'checkerGranularity';

i = i+1;
images_path{i} = [path_to_folders 'checkerOrientation\'];
file_name{i} = 'checkerOrientation';

% Create the sensitivity matrix for each metric: "-1" is ineligible, "0" is low sensitivity, "1" is medium sensitivity, "2" is high sensitivity
metrics_name = {'dice'; 'jaccard'; 'ARI'; 'RI'; 'Euclidian'; 'City_block'; 'Chebyshev'; 'Intersection'; 'Divergence'};
nb_metrics = length(metrics_name);
metrics_computation = cell(nb_examples,2);
metrics_derivative = cell(nb_examples,2);
sensitivity_matrix = cell(nb_metrics,2);
for i = 1:nb_metrics
    sensitivity_matrix{i,1} = metrics_name{i};
    sensitivity_matrix{i,2} = zeros(nb_examples,3);
end

% Loop through the examples and compute sensitivity of each metric
for j = 1:nb_examples
    % Read images and the reference image
    images_name = dir([images_path{j} '*.tif']);
    image1 = round(double(imread([images_path{j} images_name(1).name])));
    nb_images = length(images_name)-1;
    
    % Initialize metrics
    metrics_computation{j,1} = file_name{j};
    metrics_computation{j,2} = cell(nb_metrics,2);
    metrics_derivative{j,1} = file_name{j};
    metrics_derivative{j,2} = cell(nb_metrics,2);
    for i = 1:nb_metrics
        metrics_computation{j,2}{i,1} = metrics_name{i};
        metrics_computation{j,2}{i,2} = zeros(nb_images,1);
        
        metrics_derivative{j,2}{i,1} = metrics_name{i};
    end
    
    % Normalize the X axis
    X_norm = (1:nb_images)'/nb_images;
    
    % compute metrics
    for i = 1:nb_images
        
        % Read image2
        image2 = round(double(imread([images_path{j} images_name(i+1).name])));
        
        % if image is binary, compute dice, jaccard, ARI and RI
        if binary(j)
            [metrics_computation{j,2}{1,2}(i) metrics_computation{j,2}{2,2}(i)] = Dice_Jaccard_metrics_computation(image1, image2);
            
            [metrics_computation{j,2}{3,2}(i) metrics_computation{j,2}{4,2}(i)] = Adjusted_Rand_Index_computation(image1, image2);
        end
        
        [metrics_computation{j,2}{5,2}(i) metrics_computation{j,2}{6,2}(i) metrics_computation{j,2}{7,2}(i) ...
            metrics_computation{j,2}{8,2}(i) metrics_computation{j,2}{9,2}(i)] = histogram_metrics_computation(image1, image2);
    end
    
    % Compute the slope for each plot
    for i = 1:nb_metrics
        metrics_derivative{j,2}{i,2} = abs(diff(metrics_computation{j,2}{i,2})./diff(X_norm));
    end
    
    % Plot the data
    h = figure; set(h, 'visible', 'off');
    hold on
    plot(X_norm, metrics_computation{j,2}{5,2}, 'b', 'LineWidth',3)
    plot(X_norm, metrics_computation{j,2}{5,2}, 'bs', 'LineWidth',2)
    set(gca, 'Box', 'on')
    axis([0 1 0 1])
    print(h, [file_name{j} '.tif'], '-dtiff')
    close(h)
    
    h = figure; set(h, 'visible', 'off');
    hold on
    plot(180*atan(metrics_derivative{j,2}{5,2})/pi, 'b', 'LineWidth',3)
    plot(180*atan(metrics_derivative{j,2}{5,2})/pi, 'bs', 'LineWidth',2)
    set(gca, 'Box', 'on')
    axis([1 nb_images -180 180])
    print(h, ['derivative_' file_name{j} '.tif'], '-dtiff')
    close(h)
    
    % Get the three numbers by matrix, Low, Medium, High
    step = round(nb_images/3);
    
    % Compute sensitivity matrix
    for i = 1:nb_metrics
        sensitivity_matrix{i,2}(j,1) = mean(metrics_derivative{j,2}{i,2}(1:step));
        sensitivity_matrix{i,2}(j,2) = mean(metrics_derivative{j,2}{i,2}(step+1:2*step));
        sensitivity_matrix{i,2}(j,3) = mean(metrics_derivative{j,2}{i,2}(2*step:end));
        if i<5 && ~binary(j)
            sensitivity_matrix{i,2}(j,:) = -1;
        end
    end
end

% Create color legend
color_legend = zeros(9,3);
j = 1;
color_legend(j,3) = 1; % Blue Color
j = j+1;
color_legend(j,2) = 0.5; color_legend(j,3) = 1; % Azur
j = j+1;
color_legend(j,2) = 1; color_legend(j,3) = 1; % Cyan
j = j+1;
color_legend(j,2) = 0.75; color_legend(j,3) = 0.25; % Dark Pastel Green
j = j+1;
color_legend(j,2) = 1; % Green
j = j+1;
color_legend(j,1) = 0.68; color_legend(j,2) = 1; color_legend(j,3) = 0.18; % Green Yellow
j = j+1;
color_legend(j,1) = 0.94; color_legend(j,2) = 0.8; % Yellow Munsel
j = j+1;
color_legend(j,1) = 0.95; color_legend(j,2) = 0.54; % Orange Tangerine
j = j+1;
color_legend(j,1) = 1; % Red

color_legend1 = zeros(900,100,3);
for j = 1:9
    color_legend1((j-1)*100+1:j*100,:,1) = color_legend(j,1);
    color_legend1((j-1)*100+1:j*100,:,2) = color_legend(j,2);
    color_legend1((j-1)*100+1:j*100,:,3) = color_legend(j,3);
end
imwrite(color_legend1, 'Legend.tif')

% Create color images
nb_bins = 18;
for i = 1:nb_metrics
        M = sensitivity_matrix{i,2};
        color_matrix = zeros(nb_examples*3,3);
        for j = 1:numel(M)
            if M(j) <0, color_matrix(j,:) = 1; end
            if M(j) >=0 && M(j)<tan(pi/nb_bins), color_matrix(j,3) = 1; end
            if M(j) >= tan(1*pi/nb_bins) && M(j)<tan(2*pi/nb_bins), color_matrix(j,2) = 0.5; color_matrix(j,3) = 1; end
            if M(j) >= tan(2*pi/nb_bins) && M(j)<tan(3*pi/nb_bins), color_matrix(j,2) = 1; color_matrix(j,3) = 1; end
            if M(j) >= tan(3*pi/nb_bins) && M(j)<tan(4*pi/nb_bins), color_matrix(j,2) = 0.75; color_matrix(j,3) = 0.25; end
            if M(j) >= tan(4*pi/nb_bins) && M(j)<tan(5*pi/nb_bins), color_matrix(j,2) = 1; end
            if M(j) >= tan(5*pi/nb_bins) && M(j)<tan(6*pi/nb_bins), color_matrix(j,1) = 0.68; color_matrix(j,2) = 1; color_matrix(j,3) = 0.18; end
            if M(j) >= tan(6*pi/nb_bins) && M(j)<tan(7*pi/nb_bins), color_matrix(j,1) = 0.94; color_matrix(j,2) = 0.8; end
            if M(j) >= tan(7*pi/nb_bins) && M(j)<tan(8*pi/nb_bins), color_matrix(j,1) = 0.95; color_matrix(j,2) = 0.54; end
            if M(j) >= tan(8*pi/nb_bins), color_matrix(j,1) = 1; end
        end
        color_matrix = reshape(color_matrix, nb_examples,3,3);
        
        % Expand matrix
        color_matrix1 = zeros(nb_examples*100,900,3);
        for j = 1:nb_examples
            for k = 1:3
                color_matrix1((j-1)*100+1:j*100,(k-1)*300+1:k*300,1) = color_matrix(j,k,1);
                color_matrix1((j-1)*100+1:j*100,(k-1)*300+1:k*300,2) = color_matrix(j,k,2);
                color_matrix1((j-1)*100+1:j*100,(k-1)*300+1:k*300,3) = color_matrix(j,k,3);
            end
        end
        imwrite(color_matrix1, [metrics_name{i} '_sensitivity.tif'])
end

