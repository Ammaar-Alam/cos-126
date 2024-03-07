import java.awt.Color;

public class ImageClassifier {

    // Converts a grayscale image into a one-dimensional array
    // suitable for use with the MultiPerceptron.
    public static double[] extractFeatures(Picture picture) {
        int width = picture.width();
        int height = picture.height();
        double[] features = new double[width * height];

        int index = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color color = picture.get(col, row);
                // grayscale has equal RGB values, so color.get[any of RGB] would work
                double grayscale = (color.getRed());
                features[index++] = grayscale;
            }
        }
        return features;
    }

    // Main method to execute the ImageClassifier
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println(
                    "Usage: java ImageClassifier <training-data-file> "
                            + "<testing-data-file>");
            return;
        }

        String trainingFile = args[0];
        String testingFile = args[1];

        In inTraining = new In(trainingFile);

        int numClasses = inTraining.readInt();
        int width = inTraining.readInt();
        int height = inTraining.readInt();

        MultiPerceptron mp = new MultiPerceptron(numClasses, width * height);

        // Training process
        while (!inTraining.isEmpty()) {
            String imagePath = inTraining.readString();
            int label = inTraining.readInt();
            Picture pic = new Picture(imagePath);
            double[] features = extractFeatures(pic);
            mp.trainMulti(features, label);
        }

        In inTesting = new In(testingFile);

        int correctPredictions = 0;
        int totalPredictions = 0;
        inTesting.readInt(); // discard m
        inTesting.readInt(); // discard width
        inTesting.readInt(); // discard hieght
        while (!inTesting.isEmpty()) {
            String imagePath = inTesting.readString();
            int trueLabel = inTesting.readInt();
            Picture pic = new Picture(imagePath);
            double[] features = extractFeatures(pic);
            int predictedLabel = mp.predictMulti(features);

            if (predictedLabel == trueLabel) {
                correctPredictions++;
            }
            else {
                System.out.println(
                        imagePath + ", label = " + trueLabel + ", "
                                + "predict = " + predictedLabel);
            }

            totalPredictions++;
        }

        double errorRate = 1.0 - (double) correctPredictions / totalPredictions;
        System.out.println("test error rate = " + errorRate);
    }
}
