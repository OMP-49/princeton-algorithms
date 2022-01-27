import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lineSegments;

    public FastCollinearPoints(Point[] points) {
        lineSegments = new ArrayList<>();

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
            if (points[i] == points[i - 1]) {
                throw new IllegalArgumentException();
            }
        }

        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        for (int i = 0; i < pointsCopy.length; i++) {
            Point origin = pointsCopy[i];
            Arrays.sort(points, origin.slopeOrder());
            Point start = origin;
            Point end = origin;

            double prevSlope = origin.slopeTo(points[0]);
            int count = 1;
            for (int j = 1; j < points.length; j++) {
                if (Double.compare(origin.slopeTo(points[j]), prevSlope) == 0) {
                    if (start.compareTo(points[j]) == 1) {
                        start = points[j];
                    }
                    if (end.compareTo(points[j]) == -1) {
                        end = points[j];
                    }
                    count++;
                } else {
                    if (count > 3) {
                        LineSegment line = new LineSegment(start, end);
                        if (!lineSegments.contains(line)) {
                            lineSegments.add(line);
                        }
                    }
                    if (points[j].compareTo(origin) == -1) {
                        start = points[j];
                        end = origin;
                    } else {
                        start = origin;
                        end = points[j];
                    }

                    prevSlope = origin.slopeTo(points[j]);
                    count = 2;
                }
            }
            if (count > 3) {
                LineSegment line = new LineSegment(start, end);
                if (!lineSegments.contains(line)) {
                    lineSegments.add(line);
                }

                count = 1;
            }
//            Arrays.sort(points);

        }

    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] segments = new LineSegment[lineSegments.size()];
        for (int i = 0; i < lineSegments.size(); i++) {
            segments[i] = lineSegments.get(i);
        }
        return segments;

    }

}
