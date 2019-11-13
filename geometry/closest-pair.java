static Point[] strip = new Point[0];
public static PointPair closestPair(Point[] points) {
  Arrays.sort(points);
  if (strip.length < points.length)
    strip = new Point[len];
  return cpAux(points, 0, points.length);
}
static PointPair cpAux(Point[] points, int off, int end) {
  if (end - off <= 6) return bf(points, off, end);
  int mid = (off + end) / 2;
  PointPair best = cpAux(points, off, mid);
  PointPair right = cpAux(points, mid, end);
  if (right.dist < best.dist) best = right;

  int stripLen = 0;
  double midX = points[mid].x;
  for (int i = off; i < end; ++i) {
    double dx = points[i].x - midX;
    if (dx*dx < best.dist)
      strip[stripLen++] = points[i];
  }

  Arrays.sort(strip, 0, stripLen, new YComparator());
  // This is O(n) because of the break
  for (int i = 0; i < stripLen; ++i) {
    for (int j = i+1; j < stripLen; ++j) {
      double dy = strip[i].y - strip[j].y;
      if (dy * dy > best.dist) break;
      best.offer(strip[i], strip[j]);
    }
  }
  return best;
}
static PointPair bf(Point[] points, int off, int end) {
  PointPair best = new PointPair(points[off], points[off+1]);
  for (int i = off; i < end; ++i) {
    for (int j = i+1; j < end; ++j) {
      best.offer(points[i], points[j]);
    }
  }
  return best;
}

static class Point implements Comparable<Point> {
  double x, y;
  public Point(double x, double y) {
    this.x = x; this.y = y;
  }
  public int compareTo(Point o) {
    return Double.compare(this.x, o.x);
  }
}
static class YComparator implements Comparator<Point> {
  public int compare(Point o1, Point o2) {
    return Double.compare(o1.y, o2.y);
  }
}
static class PointPair {
  Point a, b;
  double dist; // squared
  public PointPair(Point a, Point b) {
    this.a = a; this.b = b;
    this.dist = (a.x - b.x)*(a.x - b.x)
      + (a.y - b.y)*(a.y - b.y);
  }
  public void offer(Point a, Point b) {
    double dist = (a.x - b.x)*(a.x - b.x)
      + (a.y - b.y)*(a.y - b.y);
    if (dist < this.dist) {
      this.a = a; this.b = b;
      this.dist = dist;
    }
  }
}
