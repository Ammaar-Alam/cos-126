public class TextGenerator {

    // generates random text based on a Markov model.
    // matches the start of the output with the input text for k up to 16
    public static void main(String[] args) {
        // Read command-line arguments for order k and text length textLength
        int k = Integer.parseInt(args[0]);
        int textLength = Integer.parseInt(args[1]);

        // Read input text from standard input
        String inputText = StdIn.readAll();

        // Create a Markov model of order k
        MarkovModel markovModel = new MarkovModel(inputText, k);

        // Generate and print textLength characters using the Markov model
        generateAndPrintText(k, textLength, inputText, markovModel);
    }

    // genrerates and prints text of specified length using the Markov model
    // handles special cases for k = 0 and short input text
    private static void generateAndPrintText(int k, int textLength,
                                             String inputText,
                                             MarkovModel markovModel) {
        StringBuilder outputText = new StringBuilder();
        String kgram;

        if (k == 0 || inputText.length() < k) {
            kgram = "";
        }
        else {
            kgram = inputText.substring(0, k);
            outputText.append(kgram);
        }

        for (int i = outputText.length(); i < textLength; i++) {
            char nextChar = getNextChar(kgram, markovModel);
            outputText.append(nextChar);
            if (k > 0 && kgram.length() == k) {
                kgram = kgram.substring(1) + nextChar;
            }
        }

        StdOut.println(outputText.toString());
    }

    // returns the next character based on the current kgram using the Markov model
    private static char getNextChar(String kgram, MarkovModel markovModel) {
        return markovModel.random(kgram);
    }
}
