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
  if (a == 0) return b;
  while (b != 0) {
    long t = a;
    a = b % a; b = t;
  }
  return a;
}
