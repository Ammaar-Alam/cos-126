public class MarkovModel {
    private static final int ASCII = 128; // ASCII characters count

    private final int order; // Order of the Markov model
    private final String circularText; // Circular text for model creation
    private final ST<String, Integer> kgramFrequency; // Frequency of each kgram
    // Frequency of each character following a kgram, indexed by ASCII character code
    private final ST<String, int[]> charFollowingKgramFrequency;

    // Constructor
    public MarkovModel(String text, int k) {
        if (text == null || k < 0 || k > text.length()) {
            throw new IllegalArgumentException("Invalid text or order");
        }

        this.order = k;
        this.circularText = text + text.substring(0, k);
        this.kgramFrequency = new ST<>();
        this.charFollowingKgramFrequency = new ST<>();

        buildModel();
    }

    // Build the Markov model
    private void buildModel() {
        for (int i = 0; i < circularText.length() - order; i++) {
            String kgram = circularText.substring(i, i + order);
            char nextChar = circularText.charAt(i + order);

            // Update kgram frequency
            if (!kgramFrequency.contains(kgram)) {
                kgramFrequency.put(kgram, 0);
            }
            kgramFrequency.put(kgram, kgramFrequency.get(kgram) + 1);

            // Update character following kgram frequency
            if (!charFollowingKgramFrequency.contains(kgram)) {
                charFollowingKgramFrequency.put(kgram, new int[ASCII]);
            }
            int[] freqArray = charFollowingKgramFrequency.get(kgram);
            freqArray[nextChar]++;
        }
    }

    // Returns the order of the model
    public int order() {
        return order;
    }

    // Returns a String representation of the model
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String kgram : kgramFrequency.keys()) {
            sb.append(kgram).append(": ");
            int[] frequencies = charFollowingKgramFrequency.get(kgram);
            for (int i = 0; i < frequencies.length; i++) {
                if (frequencies[i] > 0) {
                    sb.append((char) i).append(" ").append(frequencies[i]).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // returns the number of times 'kgram' appeared in the input text
    public int freq(String kgram) {
        if (kgram == null || kgram.length() != order) {
            throw new IllegalArgumentException("Kgram length must be equal to order"
                                                       + " of the model");
        }

        if (kgramFrequency.contains(kgram)) {
            return kgramFrequency.get(kgram);
        }
        else {
            return 0;
        }
    }

    // returns the number of times 'c' followed 'kgram' in the input text
    public int freq(String kgram, char c) {
        if (kgram == null || kgram.length() != order) {
            throw new IllegalArgumentException("Kgram length must be equal to order"
                                                       + " of the model");
        }

        if (charFollowingKgramFrequency.contains(kgram)) {
            return charFollowingKgramFrequency.get(kgram)[c];
        }
        else {
            return 0;
        }
    }


    // validates the kgram length
    private void validateKgram(String kgram) {
        if (kgram == null || kgram.length() != order) {
            throw new IllegalArgumentException("Kgram length must be equal "
                                                       + "to order of the model");
        }
    }

    // returns a random character following 'kgram' in the input text
    public char random(String kgram) {
        validateKgram(kgram);
        if (!charFollowingKgramFrequency.contains(kgram)) {
            throw new IllegalArgumentException("Kgram not found in the model");
        }

        int[] frequencies = charFollowingKgramFrequency.get(kgram);
        int total = 0;
        for (int freq : frequencies) {
            total += freq;
        }

        int index = StdRandom.uniformInt(total);
        int sum = 0;
        for (int i = 0; i < frequencies.length; i++) {
            sum += frequencies[i];
            if (sum > index) {
                return (char) i;
            }
        }
        throw new IllegalStateException("Random character generation failed");
    }

    // main method for testing
    public static void main(String[] args) {
        String text1 = "banana";
        MarkovModel model1 = new MarkovModel(text1, 2);
        StdOut.println("Order: " + model1.order());
        StdOut.println("Random character following 'na': " + model1.random("na"));
        StdOut.println("freq(\"an\", 'a')    = " + model1.freq("an", 'a'));
        StdOut.println("freq(\"na\", 'b')    = " + model1.freq("na", 'b'));
        StdOut.println("freq(\"na\", 'a')    = " + model1.freq("na", 'a'));
        StdOut.println("freq(\"na\")         = " + model1.freq("na"));
        StdOut.println();

        String text3 = "one fish two fish red fish blue fish";
        MarkovModel model3 = new MarkovModel(text3, 4);
        StdOut.println("Order: " + model3.order());
        StdOut.println("Random character following 'fish': " + model3.random("fish"));
        StdOut.println("freq(\"ish \", 'r') = " + model3.freq("ish ", 'r'));
        StdOut.println("freq(\"ish \", 'x') = " + model3.freq("ish ", 'x'));
        StdOut.println("freq(\"ish \")      = " + model3.freq("ish "));
        StdOut.println("freq(\"tuna\")      = " + model3.freq("tuna"));
    }
}
