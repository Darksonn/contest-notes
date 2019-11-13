static long f(long a, long b) {
  // insert your associative function here
  return a + b;
}
// a value such that f(a, unit) = f(unit, a) = a
static final long unit = 0;
static class SegmentTree {
  long[] s; int N;
  // Pick the constructor that fits your use case
  SegmentTree(int N) {
    this.N = N;
    s = new long[2*N];
    for (int i = 0; i < 2*N; ++i)
      s[i] = unit;
  }
  SegmentTree(int[] init) {
    this.N = init.length;
    s = new long[2*N];
    for (int i = 0; i < N; ++i)
      s[N+i] = init[i];
    for (int i = N-1; i >= 0; --i)
      s[i] = f(s[2*i], s[2*i+1]);
  }
  void set(int pos, long val) {
    for (s[pos += N] = val; (pos /= 2) > 0;)
      s[pos] = f(s[2*pos], s[2*pos + 1]);
  }
  long query(int b, int e) { // query [b, e)
    long ra = unit, rb = unit;
    for (b += N, e += N; b < e; b /= 2, e /= 2) {
      if (b % 2 == 1) ra = f(ra, s[b++]);
      if (e % 2 == 1) rb = f(s[--e], rb);
    }
    return f(ra, rb);
  }
}
