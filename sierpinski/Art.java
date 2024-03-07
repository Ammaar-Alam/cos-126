public class Art {

    // Helper function to draw a segmented hexagon
    private static void drawSegmentedHexagon(double x, double y, double size,
                                             int[][] colors) {
        double[] xCoords = new double[6];
        double[] yCoords = new double[6];

        for (int i = 0; i < 6; i++) {
            xCoords[i] = x + size * Math.cos(Math.PI / 3 * i);
            yCoords[i] = y + size * Math.sin(Math.PI / 3 * i);
        }

        // Draw three faces to simulate a 3D cube
        for (int i = 0; i < 3; i++) {
            int[] color = colors[i];
            StdDraw.setPenColor(color[0], color[1], color[2]);
            StdDraw.filledPolygon(new double[] {
                                          x, xCoords[i * 2],
                                          xCoords[(i * 2 + 1) % 6],
                                          xCoords[(i * 2 + 2) % 6]
                                  },
                                  new double[] {
                                          y, yCoords[i * 2],
                                          yCoords[(i * 2 + 1) % 6],
                                          yCoords[(i * 2 + 2) % 6]
                                  });
        }
    }

    // Main drawing function
    private static void draw3DCubeHexagon(double x, double y, double size,
                                          int n) {
        if (n <= 0) return;

        // Dynamic color based on recursion level
        int dynamicColor = 10 * n;
        int[][] outerColors = {
                { dynamicColor, 0, 150 }, { 130, 0, 180 },
                { 160, 0, 210 }
        };
        drawSegmentedHexagon(x, y, size, outerColors);

        // Draw text to indicate the recursion level
        // You can comment out these lines by removing the first "//" below
        // that will allow you to see the fractal without any numbers


        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(x, y, Integer.toString(n));
        // **/

        double[] xCoords = new double[6];
        double[] yCoords = new double[6];

        for (int i = 0; i < 6; i++) {
            xCoords[i] = x + size * Math.cos(Math.PI / 3 * i);
            yCoords[i] = y + size * Math.sin(Math.PI / 3 * i);
        }

        // Make recursive calls to draw nested hexagons
        for (int i = 0; i < 6; i++) {
            draw3DCubeHexagon(xCoords[i], yCoords[i], size / 3, n - 1);
        }

    }

    // Main function to initiate the drawing
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);  // Depth of recursion

        StdDraw.setScale(-1, 1);
        StdDraw.clear(StdDraw.BLACK);

        // Initiate the drawing with the main hexagon
        draw3DCubeHexagon(0, 0, 0.5, n);
    }
}
