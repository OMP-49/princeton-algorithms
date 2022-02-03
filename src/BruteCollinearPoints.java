import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private ArrayList<LineSegment> lineSegements;
    private int numberOfSegments;

    public BruteCollinearPoints(Point[] points1) {

        Point[] points = Arrays.copyOf(points1, points1.length);
        lineSegements = new ArrayList<LineSegment>();
        numberOfSegments = 0;
        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }
        Arrays.sort(points);

        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i - 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    if (Double.compare(points[i].slopeTo(points[j]), points[i].slopeTo(points[k])) == 0) {
                        for (int l = k + 1; l < points.length; l++) {
                            if (Double.compare(points[i].slopeTo(points[j]), points[i].slopeTo(points[l])) == 0) {
                                lineSegements.add(new LineSegment(points[i], points[l]));
                                numberOfSegments++;
                            }
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return numberOfSegments;
    }

    public LineSegment[] segments() {
        LineSegment[] segments = new LineSegment[numberOfSegments];
        for (int i = 0; i < numberOfSegments; i++) {
            segments[i] = lineSegements.get(i);
        }
        return segments;
    }

}
