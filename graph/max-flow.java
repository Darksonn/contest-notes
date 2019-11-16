static class MaxFlow {
  ArrayList<Edge>[] edges;
  boolean[] vis;
  long maxCap = 0;
  public MaxFlow(int N) {
    this.edges = new ArrayList[N];
    for (int i = 0; i < N; ++i) edges[i] = new ArrayList<>();
    this.level = new int[N];
    this.vis = new boolean[N];
  }
  void addEdge(int from, int to, long cap) {
    if (from == to) return;
    maxCap = Math.max(maxCap, cap);
    edges[from].add(new Edge(from, to, cap, edges[to].size()));
    edges[to].add(new Edge(to, from, 0, edges[from].size() - 1));
  }
  long findFlow(int node, int t, long flow, long limit) {
    if (node == t) return flow;
    if (vis[node]) return 0;
    vis[node] = true;
    for (Edge edge : edges[node]) {
      if (edge.cap >= limit) {
        long aug = findFlow(edge.to, t,
            Math.min(flow, edge.cap), limit);
        if (aug != 0) {
          edge.cap -= aug;
          edges[edge.to].get(edge.index).cap += aug;
          return aug;
        }
      }
    }
    return 0;
  }
  long maxFlow(int s, int t) {
    long totFlow = 0;
    long lim = maxCap;
    while (lim > 0) {
      long res = 0;
      do {
        totFlow += res;
        for (int i = 0; i < vis.length; ++i)
          vis[i] = false;
      } while ((res = findFlow(s, t,
              Long.MAX_VALUE, lim)) > 0);
      lim /= 2;
    }
    return totFlow;
  }
}
static class Edge {
  // cap is the residual capacity
  // base is the original capacity
  long cap, base;
  int from, to, index;
  public Edge(int from, int to, long cap, int index) {
    this.from = from;
    this.to = to;
    this.cap = cap;
    this.base = cap;
    this.index = index;
  }
  public long getFlow() {
    if (base == 0)
      return 0;
    return base - cap;
  }
}
