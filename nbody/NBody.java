public class NBody {
    // program that draws a stdin, and then uses loops/nested loops to simulate
    // the solar system, using and calculating the respective variables
    public static void main(String[] args) {

        // Step 1: Parse command-line arguments
        double stoppingTime = Double.parseDouble(args[0]);
        double deltaT = Double.parseDouble(args[1]);

        // Step 2: Read universe from standard input
        int n = StdIn.readInt();
        double radius = StdIn.readDouble();

        double[] px = new double[n];
        double[] py = new double[n];
        double[] vx = new double[n];
        double[] vy = new double[n];
        double[] mass = new double[n];
        String[] image = new String[n];

        for (int i = 0; i < n; i++) {
            px[i] = StdIn.readDouble();
            py[i] = StdIn.readDouble();
            vx[i] = StdIn.readDouble();
            vy[i] = StdIn.readDouble();
            mass[i] = StdIn.readDouble();
            image[i] = StdIn.readString();
        }

        // Step 3: Initialize standard drawing
        StdDraw.setXscale(-radius, +radius);
        StdDraw.setYscale(-radius, +radius);
        StdDraw.enableDoubleBuffering();

        // Step 4: Play music on standard audio
        StdAudio.playInBackground("2001.wav");

        double G = 6.67e-11;  // made into capital G as this is a constant

        // Step 5: Simulate the universe (the big time loop)
        for (double t = 0.0; t < stoppingTime; t += deltaT) {
            // Step 5A: Calculate the forces
            double[] fx = new double[n];
            double[] fy = new double[n];

            for (int i = 0; i < n; i++) {
                fx[i] = 0.0;  // Initialize net forces to 0 for each body
                fy[i] = 0.0;

                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        double dx = px[j] - px[i];
                        double dy = py[j] - py[i];

                        double distance = Math.sqrt(dx * dx + dy * dy);
                        double force = (G * mass[j] * mass[i]) / (distance * distance);

                        fx[i] += (force * dx) / (distance);  // force components
                        fy[i] += (force * dy) / (distance);
                    }
                }
            }

            // Step 5B: Update velocities and positions
            for (int i = 0; i < n; i++) {
                double ax = fx[i] / mass[i];  // acceleration in x-direction
                double ay = fy[i] / mass[i];  // acceleration in y-direction

                vx[i] += ax * deltaT;  // update velocity using acceleration
                vy[i] += ay * deltaT;

                px[i] += vx[i] * deltaT;  // update position
                py[i] += vy[i] * deltaT;
            }

            // 5C. Draw universe to standard drawing
            StdDraw.picture(0, 0, "starfield.jpg");
            for (int i = 0; i < n; i++) {
                StdDraw.picture(px[i], py[i], image[i]);
            }
            StdDraw.show();
            StdDraw.pause(20);

        }

        // Step 6: Print universe to standard output
        StdOut.printf("%d\n", n);
        StdOut.printf("%.2e\n", radius);

        // print information for each body
        for (int i = 0; i < n; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          px[i], py[i], vx[i], vy[i], mass[i], image[i]);
        }
    }
}
