static final int MOD = 1000000007;
static ArrayList<int[]> buf = new ArrayList<>();
static int binom(int n, int m) {
  while (buf.size() <= n) {
    buf.add(binomNext(buf.get(buf.size() - 1)));
  }
  int[] row = buf.get(n);
  if (m < 0 || m >= row.length) return 0;
  return row[m];
}
static int[] binomNext(int[] prev) {
  int[] next = new int[prev.length + 1];
  next[0] = 1;
  next[prev.length] = 0;
  for (int i = 1; i < prev.length; ++i) {
    next[i] = (prev[i-1] + prev[i]) % MOD;
  }
  return next;
}
