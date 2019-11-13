// Returns two times the signed area of the polygon
public static long polygonArea(Point[] points) {
  int len = points.length;
  long area = points[len-1].det(points[0]);
  for (int i = 1; i < len; ++i) {
    area += points[i-1].det(points[i]);
  }
  return area;
}
public static class Point {
  long x, y;
  public Point(long x, long y) {
    this.x = x; this.y = y;
  }
  public long det(Point other) {
    return this.x * other.y - other.x * this.y;
  }
}
