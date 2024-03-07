public class Transform2D {

    // Returns a new array object that is an exact copy of the given array.
    // The given array is not mutated.
    public static double[] copy(double[] array) {
        int n = array.length;
        double[] copiedArray = new double[n];
        for (int i = 0; i < n; i++)
            copiedArray[i] = array[i];
        return copiedArray;
    }

    // Scales the polygon by the factor alpha.
    public static void scale(double[] x, double[] y, double alpha) {
        int n = x.length;
        int m = y.length;
        for (int i = 0; i < n; i++)
            x[i] = x[i] * alpha;
        for (int i = 0; i < m; i++)
            y[i] = y[i] * alpha;
    }

    // Translates the polygon by (dx, dy).
    public static void translate(double[] x, double[] y, double dx, double dy) {
        int n = x.length;
        int m = y.length;
        for (int i = 0; i < n; i++)
            x[i] = x[i] + dx;
        for (int i = 0; i < m; i++)
            y[i] = y[i] + dy;
    }


    // Rotates the polygon theta degrees counterclockwise, about the origin.
    public static void rotate(double[] x, double[] y, double theta) {
        // creates a copy of the x-coord array to be used in calculations
        int n = x.length;
        double[] xCopy = new double[n];
        for (int i = 0; i < n; i++)
            xCopy[i] = x[i];

        // creates a copy of the y-coord array to be used in calculations
        int m = y.length;
        double[] yCopy = new double[m];
        for (int i = 0; i < m; i++)
            yCopy[i] = y[i];

        // converts the given theta from deg to rad
        double thetaRad = Math.toRadians(theta);

        // rotates the x/y arrays of the polygon uses the given formulas
        for (int i = 0; i < n; i++)
            x[i] = xCopy[i] * Math.cos(thetaRad) - yCopy[i] * Math.sin(thetaRad);

        for (int i = 0; i < m; i++)
            y[i] = yCopy[i] * Math.cos(thetaRad) + xCopy[i] * Math.sin(thetaRad);
    }

    // Tests each of the API methods by directly calling them.
    public static void main(String[] args) {
        // Set the x- and y-scale
        StdDraw.setScale(-5.0, 5.0);

        // Create original polygon
        double[] x = { 0, 1, 1, 0 };
        double[] y = { 0, 0, 2, 1 };

        // Copy original polygon
        double[] cx = copy(x);
        double[] cy = copy(y);

        // Rotate and translate the copy
        rotate(cx, cy, -45.0);
        translate(cx, cy, 1.0, 2.0);
        scale(cx, cy, 0.5);

        // Draw the copy in blue
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.polygon(cx, cy);

        // Draw the original polygon in red
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.polygon(x, y);
    }
}
