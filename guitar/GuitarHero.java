public class GuitarHero {

    //
    public static void main(String[] args) {

        // create two guitar strings, for concert A and concert C
        double CONCERT_A = 440.0;
        String keyboardString = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        GuitarString[] strings = new GuitarString[keyboardString.length()];

        // initialize keyboard mapping and the array of guitar strings
        for (int i = 0; i < keyboardString.length(); i++) {
            double frequency = CONCERT_A * Math.pow(2, (i - 24.0) / 12);
            strings[i] = new GuitarString(frequency);
        }

        // the main input loop
        Keyboard keyboard = new Keyboard();

        while (true) {

            // check if the user has played a key; if so, process it
            if (keyboard.hasNextKeyPlayed()) {

                // the key the user played
                char key = keyboard.nextKeyPlayed();

                // pluck the corresponding string
                int index = keyboardString.indexOf(key);
                if (index != -1)
                    strings[index].pluck();
            }

            // compute the superposition of the samples
            double sample = 0;
            for (int i = 0; i < keyboardString.length(); i++)
                sample += strings[i].sample();


            // play the sample on standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for (int i = 0; i < keyboardString.length(); i++)
                strings[i].tic();
        }
    }
}
