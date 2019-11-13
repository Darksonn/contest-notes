static class UnionFind {
  int N;
  int[] p, sz;
  UnionFind(int N) {
    this.N = N;
    p = new int[N];
    sz = new int[N];
    for (int i = 0; i < N; ++i) {
      p[i] = i; sz[i] = 1;
    }
  }
  int find(int x) {
    if (x == p[x])
      return x;
    else
      return p[x] = find(p[x]);
  }
  void union(int x, int y) {
    x = find(x); y = find(y);
    if (x == y) return;
    if (sz[x] < sz[y]) {
      p[x] = y; sz[y] += sz[x];
    } else {
      p[y] = x; sz[x] += sz[y];
    }
  }
}
