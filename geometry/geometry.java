static class Point {
  double x, y;
  Point(double x, double y) { this.x = x; this.y = y; }
  Point add(Point p) { return new Point(x+p.x, y+p.y); }
  Point sub(Point p) { return new Point(x-p.x, y-p.y); }
  Point mul(double f) { return new Point(f*x, f*y); }
  Point div(double f) { return new Point(x/f, y/f); }
  double dot(Point p) { return x*p.x + y*p.y; }
  double cross(Point p) { return x*p.y - y*p.x; }
  double cross(Point a, Point b) {
    return a.sub(this).cross(b.sub(this));
  }
  double dist2() { return x*x + y*y; }
  double dist() { return Math.hypot(x, y); }
  // angle to x−axis in interval [−pi , pi ]
  double angle() { return Math.atan2(y, x); }
  Point unit() { return this.div(dist()); }
  // rotate +90 deg
  Point perp() { return new Point(-y, x); }
  Point normal() { return perp().unit(); }
  // rotate a radians around origin
  Point rotate(double a) {
    double cos = Math.cos(a), sin = Math.sin(a);
    return new Point(x*cos - y*sin, x*sin + y*cos);
  }
  boolean equals(Point o) { return x==o.x && y==o.y; }
}

// distance from p to line through a and b
double lineDist(Point a, Point b, Point p) {
  return a.cross(b, p) / b.sub(a).dist();
}
// distance from p to line segment from a to b
double segDist(Point a, Point b, Point p) {
  if (a.equals(b)) return b.sub(p).dist();
  double d = a.sub(b).dist2();
  double t = Math.min(d, Math.max(0, p.sub(a).dot(b.sub(a))));
  return p.sub(a).mul(d).sub(b.sub(a).mul(t)).dist() / d;
}
int sgn(double x) { return (int) Math.signum(x); }
// Returns intersection of line segments.
// If line segments overlap fully, returns endpoints
// contained in both line segments.
ArrayList<Point> segInter(Point a, Point b, Point c, Point d) {
  double oa = c.cross(d, a), ob = c.cross(d, b),
         oc = a.cross(b, c), od = a.cross(b, d);
  ArrayList<Point> s = new ArrayList<>();
  if (sgn(oa) * sgn(ob) < 0 && sgn(oc) * sgn(od) < 0) {
    Point i = a.mul(ob).sub(b.mul(oa)).div(ob - oa);
    s.add(i);
    return s;
  }
  if (onSegment(c, d, a)) s.add(a);
  if (onSegment(c, d, b)) s.add(b);
  if (onSegment(a, b, c)) s.add(c);
  if (onSegment(a, b, d)) s.add(d);
  return s;
}
// Is a point on a line segment?
boolean onSegment(Point s, Point e, Point p) {
  // for integer coordinates:
  // return p.cross(s, e) == 0 &&
  //    s.sub(p).dot(e.sub(p)) <= 0;
  return segDist(s, e, p) < 1e-10;
}
// All intersections of the circles.
Point[] circleInter(Point a, Point b, double r1, double r2) {
  if (a.equals(b)) return new Point[0]; // ignoring r1 == r2
  Point vec = b.sub(a);
  double d2 = vec.dist2(), sum = r1+r2, dif = r1-r2,
    p = (d2 + r1*r1 - r2*r2)/(d2*2), h2 = r1*r1 - p*p*d2;
  if (sum*sum < d2 || dif*dif > d2) return new Point[0];
  Point mid = a.add(vec.mul(p));
  if (h2 < 0) return new Point[] { mid };
  Point per = vec.perp().mul(Math.sqrt(h2 / d2));
  return new Point[] { mid.add(per), mid.sub(per) };
}
// Is the given point contained in the given polygon?
static final int OUTSIDE = -1;
static final int ONEDGE = 0;
static final int INSIDE = 1;
static int inPolygon(Point[] p, Point a) {
  boolean cnt = false;
  int n = p.length;
  for (int i = 0; i < n; ++i) {
    Point q = p[(i+1) % n];
    if (onSegment(p[i], q, a)) return ONEDGE;
    int l = (a.y < p[i].y) ? 1 : 0;
    int r = (a.y < q.y) ? 1 : 0;
    if ((l - r) * a.cross(p[i], q) > 0)
      cnt = !cnt;
  }
  return cnt ? INSIDE : OUTSIDE;
}
