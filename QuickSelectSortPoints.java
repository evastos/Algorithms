/**
 * Created by Eva on 18.2.2015..
 */
public class QuickSelectSortPoints {

    /**
     * Given an array of points, find K closest point to the origin in a 2D plane.
     * http://www.careercup.com/question?id=4751976126480384
     */

    public static String testKClosestPoints() {
        return java.util.Arrays.toString(findKClosestPoints(POINTS, new Point(0.0, 0.0), K));
    }

    private static int K = 7;

    private static final Point[] POINTS = new Point[]{new Point(0, 0), new Point(1, 2), new Point(3, 4), new Point(5, -1), new Point(3, 7), new Point(100, 140), new Point(-3, -2), new Point(0.5, -10), new Point(40, 55), new Point(-1, 0), new Point(2, 3)};

    static class Point {
        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double distanceTo(Point point) {
            return Math.sqrt(Math.pow(point.getX() - x, 2) + Math.pow(point.getY() - y, 2));
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}' + " distance to center: " + distanceTo(new Point(0, 0));
        }

    }

    public static Point[] findKClosestPoints(Point[] points, Point center, int k) {

        if (k >= points.length) {
            return points;
        }

        Point[] closestPoints = new Point[k];
        int start = 0, end = points.length - 1;

        while (end - start >= k) {

            int pivotIdx = (start + end) / 2;
            Point pivot = points[pivotIdx];

            int pivotStoreIdx = end;

            swapPoints(points, pivotIdx, pivotStoreIdx);
            end--;

            while (start < end) {
                while (start <= points.length - 1 && points[start].distanceTo(center) <= pivot
                        .distanceTo(center)) {
                    start++;
                }

                while (end >= 0 && points[end].distanceTo(center) > pivot.distanceTo(center)) {
                    end--;
                }

                if (start <= points.length - 1 && end >= 0 && start < end) {
                    swapPoints(points, start, end);
                }
            }

            swapPoints(points, end + 1, pivotStoreIdx);

            start = 0;

            if (end - start < k) {
                // found first k elements yay
                for (int i = 0; i < k; i++) {
                    closestPoints[i] = points[i];
                }
                return closestPoints;
            }
        }
        return closestPoints;
    }

    private static void swapPoints(Point[] points, int i, int j) {
        if (i < 0 || i >= points.length || j < 0 || j >= points.length || i == j) {
            return;
        }
        Point temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

}
