public static class FloydWarshall {
  int N;
  long[][] dist;
  static final long INF = Long.MAX_VALUE / 4;
  public FloydWarshall(int N) {
    this.N = N;
    dist = new long[N][N];
    for (int i = 0; i < N; ++i)
      for (int j = 0; j < N; ++j)
        dist[i][j] = INF;
    for (int i = 0; i < N; ++i)
      dist[i][i] = 0;
  }
  public void addEdge(int from, int to, long weight) {
    dist[from][to] = Math.min(weight, dist[from][to]);
  }
  public void floydWarshall() {
    for (int i = 0; i < N; ++i) {
      for (int j = 0; j < N; ++j) {
        for (int k = 0; k < N; ++k) {
          if (dist[j][i] == INF || dist[i][k] == INF)
            continue;
          long sum = dist[j][i] + dist[i][k];
          if (sum < -INF) sum = -INF;
          if (dist[j][k] > sum) dist[j][k] = sum;
        }
      }
    }
  }
  // Return true if graph has negative cycle
  public boolean hasNegative() {
    for (int i = 0; i < N; ++i) {
      if (dist[i][i] < 0) return true;
    }
    return false;
  }
  // Mark all paths through a negative cycle with -INF
  public void markNegative() {
    for (int k = 0; k < N; ++k) {
      if (dist[k][k] >= 0) continue;
      for (int i = 0; i < N; ++i)
        for (int j = 0; j < N; ++j)
          if (dist[i][k] != INF && dist[k][j] != INF)
            dist[i][j] = -INF;
    }
  }
}
