long[] euclid(long a, long b) {
  long x = 1, yy = x;
  long y = 0, xx = y;
  while (b != 0) {
    long q = a / b, t = b;
    b = a % b; a = t;
    t = xx; xx = x - q*xx; x = t;
    t = yy; yy = y - q*yy; y = t;
  }
  return new long[] { x, y, a };
}
long gcd(long a, long b) {
  while (b != 0) {
    long bb = b;
    b = a % b; a = bb;
  }
  return a;
}
