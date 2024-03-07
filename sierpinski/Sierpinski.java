public class Sierpinski {

    // Height of an equilateral triangle with the specified side length.
    public static double height(double length) {
        double height = length * (Math.sqrt(3) / 2);
        return height;
    }

    // Draws a filled equilateral triangle with the specified side length
    // whose bottom vertex is (x, y).
    public static void filledTriangle(double x, double y, double length) {
        double[] xCoords = { x, x + length / 2, x - length / 2 };
        double[] yCoords = { y, y + height(length), y + height(length) };
        StdDraw.filledPolygon(xCoords, yCoords);
    }

    // Draws a Sierpinski triangle of order n, such that the largest filled
    // triangle has the specified side length and bottom vertex (x, y).
    public static void sierpinski(int n, double x, double y, double length) {
        if (n == 0) return;
        filledTriangle(x, y, length);

        double x0 = x - length / 2;
        double y0 = y;
        double x1 = x + length / 2;
        double y1 = y;
        double x2 = x;
        double y2 = y + height(length);

        sierpinski(n - 1, x0, y0, length / 2);
        sierpinski(n - 1, x1, y1, length / 2);
        sierpinski(n - 1, x2, y2, length / 2);
    }

    // Takes an integer command-line argument n;
    // draws the outline of an upwards equilateral triangle of length 1
    // whose bottom-left vertex is (0, 0) and bottom-right vertex is (1, 0);
    // and draws a Sierpinski triangle of order n that fits inside the outline.
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        double length = 1.0;

        // Draw the outline of the initial triangle
        double[] xCoords = { 0, 1, 0.5 };
        double[] yCoords = { 0, 0, height(length) };
        StdDraw.polygon(xCoords, yCoords);


        // Draw the Sierpinski triangles
        sierpinski(n, 0.5, 0, length / 2);
    }
}
