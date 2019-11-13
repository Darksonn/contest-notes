public static ArrayList<Point> convexHull(Point[] points) {
  Arrays.sort(points);

  // remove duplicates
  int len = 1;
  for (int i = 1; i < points.length; ++i) {
    if (!points[i].equals(points[len-1])) {
      len += 1;
    }
    points[len-1] = points[i];
  }
  ArrayList<Point> stack = new ArrayList<>(len);
  if (len <= 2) {
    for (int i = 0; i < len; ++i)
      stack.add(points[i]);
    return stack;
  }
  // upper half
  for (int i = 0; i < len; ++i) {
    while (stack.size() >= 2 && ccw(
          stack.get(stack.size() - 2),
          stack.get(stack.size() - 1),
          points[i]
    ) <= 0) {
      stack.remove(stack.size() - 1);
    }
    stack.add(points[i]);
  }
  stack.remove(stack.size() - 1);
  int min = stack.size() + 2;
  // lower half
  for (int i = len-1; i >= 0; --i) {
    while (stack.size() >= min && ccw(
          stack.get(stack.size() - 2),
          stack.get(stack.size() - 1),
          points[i]
    ) <= 0) {
      stack.remove(stack.size() - 1);
    }
    stack.add(points[i]);
  }
  stack.remove(stack.size() - 1);

  return stack;
}
// Replace every long with double for floats
public static long ccw(Point a, Point b, Point c) {
  return (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
}
static class Point implements Comparable<Point> {
  long x, y;
  public Point(long x, long y) {
    this.x = x; this.y = y;
  }
  public boolean equals(Object obj) {
    // let eclipse auto-generate this
  }
  public int compareTo(Point o) {
    if (this.x == o.x) return Long.compare(this.y, o.y);
    return Long.compare(this.x, o.x);
  }
}
