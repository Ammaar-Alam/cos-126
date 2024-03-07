public class MultiPerceptron {

    private Perceptron[] perceptrons; // Array of perceptrons

    // Creates a multi-perceptron object with m classes and n inputs.
    public MultiPerceptron(int m, int n) {
        perceptrons = new Perceptron[m];
        for (int i = 0; i < m; i++) {
            perceptrons[i] = new Perceptron(n);
        }
    }

    // Returns the number of classes m.
    public int numberOfClasses() {
        return perceptrons.length;
    }

    // Returns the number of inputs n (length of the feature vector).
    public int numberOfInputs() {
        if (perceptrons.length > 0) {
            return perceptrons[0].numberOfInputs();
        }
        return 0;
    }

    // Returns the predicted label (between 0 and m-1) for the given input.
    public int predictMulti(double[] x) {
        int bestIndex = -1;
        double highestSum = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < perceptrons.length; i++) {
            double currentSum = perceptrons[i].weightedSum(x);
            if (currentSum > highestSum) {
                highestSum = currentSum;
                bestIndex = i;
            }
        }
        return bestIndex;
    }

    // Trains this multi-perceptron on the labeled (between 0 and m-1) input.
    public void trainMulti(double[] x, int label) {
        for (int i = 0; i < perceptrons.length; i++) {
            if (i == label) {
                perceptrons[i].train(x, 1);
            }
            else {
                perceptrons[i].train(x, -1);
            }
        }
    }

    // Returns a String representation of this MultiPerceptron, with
    // the string representations of the perceptrons separated by commas
    // and enclosed in parentheses.
    // Example with m = 2 and n = 3: ((2.0, 0.0, -2.0), (3.0, 4.0, 5.0))
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < perceptrons.length; i++) {
            sb.append(perceptrons[i].toString());
            if (i < perceptrons.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        int m = 2;
        int n = 3;

        double[] training1 = { 3.0, 4.0, 5.0 };  // class 1
        double[] training2 = { 2.0, 0.0, -2.0 };  // class 0
        double[] training3 = { -2.0, 0.0, 2.0 };  // class 1
        double[] training4 = { 5.0, 4.0, 3.0 };  // class 0

        MultiPerceptron perceptron = new MultiPerceptron(m, n);

        // Call the public methods directly from main
        int numClasses = perceptron.numberOfClasses();
        int numInputs = perceptron.numberOfInputs();
        int predictionForTraining1 = perceptron.predictMulti(training1);

        StdOut.println("Number of Classes: " + numClasses);
        StdOut.println("Number of Inputs: " + numInputs);
        StdOut.println("Prediction for training1: " + predictionForTraining1);

        StdOut.println(perceptron);
        perceptron.trainMulti(training1, 1);
        StdOut.println(perceptron);
        perceptron.trainMulti(training2, 0);
        StdOut.println(perceptron);
        perceptron.trainMulti(training3, 1);
        StdOut.println(perceptron);
        perceptron.trainMulti(training4, 0);
        StdOut.println(perceptron);
    }
}
