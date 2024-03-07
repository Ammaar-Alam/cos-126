// represents a tour for the traveling salesperson problem.
public class Tour {

    // reference to the first node in the tour
    private Node first;

    // constructs an empty Tour
    public Tour() {
        first = null;
    }

    // constructs a Tour from four points
    public Tour(Point a, Point b, Point c, Point d) {
        first = new Node();
        first.p = a;
        first.next = new Node();
        first.next.p = b;
        first.next.next = new Node();
        first.next.next.p = c;
        first.next.next.next = new Node();
        first.next.next.next.p = d;
        first.next.next.next.next = first; // Complete the circular link
    }

    // nested Node class
    private class Node {
        private Point p;  // The point
        private Node next;  // The next node in the tour
    }

    // returns the number of points in this tour
    public int size() {
        if (first == null) return 0;

        int count = 0;
        Node current = first;
        do {
            count++;
            current = current.next;
        } while (current != first);

        return count;
    }

    // returns the length of this tour
    public double length() {
        if (first == null) return 0.0;

        double length = 0.0;
        Node current = first;
        do {
            length += current.p.distanceTo(current.next.p);
            current = current.next;
        } while (current != first);

        return length;
    }

    // returns a string representation of this tour
    public String toString() {
        if (first == null) return "";

        StringBuilder sb = new StringBuilder();
        Node current = first;
        do {
            sb.append(current.p.toString()).append('\n');
            current = current.next;
        } while (current != first);

        return sb.toString();
    }

    // Draws this tour to standard drawing
    public void draw() {
        if (first == null) return;

        Node current = first;
        do {
            current.p.drawTo(current.next.p);
            current = current.next;
        } while (current != first);
    }

    // inserts a point into the tour using the nearest neighbor heuristic
    public void insertNearest(Point p) {
        if (first == null) {
            first = new Node();
            first.p = p;
            first.next = first;
            return;
        }

        Node nearest = first;
        double nearestDistance = Double.POSITIVE_INFINITY;
        Node current = first;
        do {
            double distance = current.p.distanceTo(p);
            if (distance < nearestDistance) {
                nearest = current;
                nearestDistance = distance;
            }
            current = current.next;
        } while (current != first);

        Node newNode = new Node();
        newNode.p = p;
        newNode.next = nearest.next;
        nearest.next = newNode;
    }

    // inserts a point into the tour using the smallest increase heuristic
    public void insertSmallest(Point p) {
        if (first == null) {
            first = new Node();
            first.p = p;
            first.next = first;
            return;
        }

        Node smallest = null;
        double smallestIncrease = Double.POSITIVE_INFINITY;
        Node current = first;
        do {
            double increase = current.p.distanceTo(p) + p.distanceTo(current.next.p)
                    - current.p.distanceTo(current.next.p);
            if (increase < smallestIncrease) {
                smallest = current;
                smallestIncrease = increase;
            }
            current = current.next;
        } while (current != first);

        Node newNode = new Node();
        newNode.p = p;
        newNode.next = smallest.next;
        smallest.next = newNode;
    }

    // Main method for testing
    public static void main(String[] args) {

        // define 4 points that are the corners of a tour
        Point a = new Point(100.0, 100.0);
        Point b = new Point(500.0, 100.0);
        Point c = new Point(500.0, 500.0);
        Point d = new Point(100.0, 500.0);

        // create the tour a -> b -> c -> d -> a
        Tour testTour1 = new Tour(a, b, c, d);

        // print the size to standard output
        StdOut.printf("Test #1 - check size - should be %d\n", 4);
        StdOut.printf("**** Number of points is %d\n", testTour1.size());

        StdOut.print("Pause - press the enter key to continue");
        StdIn.readChar();
        StdOut.println("Test #5 - check empty tour");
        Tour testTour2 = new Tour();
        StdOut.printf("          - check size - should be %d\n", 0);
        StdOut.printf("          *** size is %d\n", testTour2.size());
        StdOut.printf("          - check length - should be %.2f\n", 0.0);
        StdOut.printf("          *** length is %.2f\n", testTour2.length());
        StdOut.println("         - check toString - should be empty");
        StdOut.println(testTour2);
        StdOut.println("         - check draw");

        // create a tour usnig the 4-point constructor
        Tour tour = new Tour(a, b, c, d);
        System.out.println("Initial tour size: " + tour.size());
        System.out.println("Initial tour length: " + tour.length());

        // test insertNearest method
        Point e = new Point(250.0, 250.0);
        tour.insertNearest(e);
        System.out.println("After insertNearest: " + tour);

        // test insertSmallest method
        Point f = new Point(300.0, 300.0);
        tour.insertSmallest(f);
        System.out.println("After insertSmallest: " + tour);

        StdDraw.clear(); // clear the old drawing
        testTour2.draw();
    }
}
