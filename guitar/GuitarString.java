public class GuitarString {

    private RingBuffer buffer;
    private int SAMPLING_RATE;
    private double DECAY;


    // Creates a guitar string of the specified frequency,
    // using a sampling rate of 44,100.
    public GuitarString(double frequency) {
        SAMPLING_RATE = 44100;
        int n = (int) Math.ceil(SAMPLING_RATE / frequency);
        buffer = new RingBuffer(n);
        for (int i = 0; i < n; i++)
            buffer.enqueue(0.0);
    }

    // Creates a guitar string whose length and initial values
    // are given by the specified array.
    public GuitarString(double[] init) {
        int n = init.length;
        buffer = new RingBuffer(n);
        for (int i = 0; i < n; i++) {
            buffer.enqueue(init[i]);
        }
    }

    // Returns the number of samples in the ring buffer.
    public int length() {
        return buffer.size();
    }

    // Returns the current sample.
    public double sample() {
        return buffer.peek();
    }

    // Plucks this guitar string by replacing the ring buffer with white noise.
    public void pluck() {
        while (!buffer.isEmpty())
            buffer.dequeue();
        for (int i = 0; i < buffer.capacity(); i++)
            buffer.enqueue(StdRandom.uniformDouble(-0.5, 0.5));
    }

    // Advances the Karplus-Strong simulation one time step.
    public void tic() {
        DECAY = 0.996;
        double first = buffer.dequeue();
        double second = buffer.peek();
        double newValue = ((first + second) / 2) * DECAY;
        buffer.enqueue(newValue);
    }

    // Tests this class by directly calling both constructors
    // and all instance methods.
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        GuitarString gs1 = new GuitarString(n);
        StdOut.printf("Test #1 - check length based on given n == %d\n", n);
        StdOut.printf("**** Length is %d\n", gs1.length());

        StdOut.printf("Test #2 - check sample is %.1f\n", 0.0);
        StdOut.printf("**** Sample is %.1f\n", gs1.sample());

        double[] samples = { -0.7, +0.8, -0.9, +0.6 };
        GuitarString gs2 = new GuitarString(samples);
        int len = samples.length;
        StdOut.printf("Test #3 - check length based on given samples == %d\n",
                      len);
        StdOut.printf("**** Length is %d\n", gs2.length());

        StdOut.printf("Test #4 - check sample is %.2f\n", samples[0]);
        StdOut.printf("**** Sample is %.2f\n", gs2.sample());

        gs2.pluck();
        StdOut.printf("Test #5 - check length after pluck is still == %d\n",
                      len);
        StdOut.printf("**** Length is %d\n", gs2.length());

        StdOut.printf("Test #6 - check sample is range [-0.5..+0.5)\n");
        StdOut.printf("**** Sample is %.2f\n", gs2.sample());

        GuitarString gs3 = new GuitarString(samples);
        StdOut.printf("Test #7 - check sample is %.2f\n", samples[0]);
        StdOut.printf("**** Sample is %.1f\n", gs3.sample());
        gs3.tic();
        StdOut.printf("Test #8 - check sample is %.2f\n", samples[1]);
        StdOut.printf("**** Sample is %.2f\n", gs3.sample());

        int m = 25; // number of tics
        double[] moreSamples = { 0.2, 0.4, 0.5, 0.3, -0.2, 0.4, 0.3, 0.0, -0.1, -0.3 };
        StdOut.printf("Test #9 - test %d tics\n", m);
        GuitarString gs4 = new GuitarString(moreSamples);
        for (int i = 0; i < m; i++) {
            double sample = gs4.sample();
            StdOut.printf("%6d %8.4f\n", i, sample);
            gs4.tic();
        }

    }
}