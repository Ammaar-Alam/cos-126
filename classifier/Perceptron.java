public class Perceptron {

    // Array to hold the weights for each input
    private double[] weights;

    // Constructor to initialize the perceptron with n inputs
    public Perceptron(int n) {
        weights = new double[n];
        for (int i = 0; i < n; i++) {
            weights[i] = 0;
        }
    }

    // Calculate the weighted sum of inputs and weights
    public double weightedSum(double[] inputs) {
        double sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * inputs[i];
        }
        return sum;
    }

    // Predict the output based on inputs
    public int predict(double[] inputs) {
        double sum = weightedSum(inputs);
        if (sum > 0) {
            return 1;
        }
        else {
            return -1;
        }
    }

    // Train the perceptron by adjusting weights based on input and target
    public void train(double[] inputs, int target) {
        int prediction = predict(inputs);

        // False positive: predicted +1 but actual is -1
        if (prediction == 1 && target == -1) {
            for (int i = 0; i < weights.length; i++) {
                weights[i] -= inputs[i];
            }
        }

        // False negative: predicted -1 but actual is +1
        else if (prediction == -1 && target == 1) {
            for (int i = 0; i < weights.length; i++) {
                weights[i] += inputs[i];
            }
        }
        // Correct prediction: Do nothign
    }

    /**
     * Previous training function used:
     * public void train(double[] inputs, int target) {
     * int prediction = predict(inputs);
     * int error = target - prediction;
     * double LEARNING_RATE = 0.5;
     * for (int i = 0; i < weights.length; i++) {
     * weights[i] += error * inputs[i] * LEARNING_RATE;
     * }
     * }
     * The learning rate was required as otherwise my vectors would be twice
     * what the expected output was?
     */


    // Return the number of inputs the perceptron takes
    public int numberOfInputs() {
        return weights.length;
    }

    // Convert weights to string representation
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < weights.length; i++) {
            sb.append(weights[i]);
            if (i < weights.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    // Main method for testing purposes
    public static void main(String[] args) {
        int n = 3;

        double[] training1 = { 3.0, 4.0, 5.0 };  // yes
        double[] training2 = { 2.0, 0.0, -2.0 };  // no
        double[] training3 = { -2.0, 0.0, 2.0 };  // yes
        double[] training4 = { 5.0, 4.0, 3.0 };  // no

        Perceptron perceptron = new Perceptron(n);

        // Call the public methods directly from main
        int numInputs = perceptron.numberOfInputs();
        int prediction1 = perceptron.predict(training1);
        double weightedSum1 = perceptron.weightedSum(training1);

        StdOut.println("Number of Inputs: " + numInputs);
        StdOut.println("Prediction for training1: " + prediction1);
        StdOut.println("Weighted Sum for training1: " + weightedSum1);

        StdOut.println(perceptron);

        perceptron.train(training1, +1);
        StdOut.println(perceptron);
        perceptron.train(training2, -1);
        StdOut.println(perceptron);
        perceptron.train(training3, +1);
        StdOut.println(perceptron);
        perceptron.train(training4, -1);
        StdOut.println(perceptron);
    }
}
