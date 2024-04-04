public class AutoGuitar {

    private String keyboardString = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private GuitarString[] strings = new GuitarString[keyboardString.length()];
    private char[] melodyKeys = {
            'r', 'r', 'u', 'u', 'i', 'i', 'u', 'f', 'f', 'd', 'd', 'e', 'e', 'r'
    };
    private int[] noteDurations = {
            4000, 4000, 4000, 4000, 4000, 4000, 8000, 4000, 4000, 4000, 4000, 4000, 4000, 8000
    };

    public void playAutoGuitar() {

        // Initialize the guitar strings
        double CONCERT_A = 440.0;
        for (int i = 0; i < keyboardString.length(); i++) {
            double frequency = CONCERT_A * Math.pow(2, (i - 24.0) / 12);
            strings[i] = new GuitarString(frequency);
        }

        // Play the melody in a loop to make the song longer
        for (int loop = 0; loop < 4; loop++) {
            playMelody();
        }
    }

    private void playMelody() {
        for (int n = 0; n < melodyKeys.length; n++) {
            int index = keyboardString.indexOf(melodyKeys[n]);
            strings[index].pluck();

            // Play the note for its duration
            for (int t = 0; t < noteDurations[n]; t++) {
                // Compute the superposition of the samples
                double sample = 0;
                for (int i = 0; i < keyboardString.length(); i++)
                    sample += strings[i].sample();

                StdAudio.play(sample);

                // Advance the simulation of each guitar string by one step
                for (int i = 0; i < keyboardString.length(); i++)
                    strings[i].tic();
            }
        }
    }

    public static void main(String[] args) {
        AutoGuitar guitar = new AutoGuitar();
        guitar.playAutoGuitar();
    }
}
