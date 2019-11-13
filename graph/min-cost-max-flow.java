static class Edge {
  long flow, cap;
  double cost;
  int from, to, index;
  public Edge(int f, int t, long cap, double cost, int i) {
    this.from = f; this.to = t;
    this.cap = cap; this.cost = cost;
    this.index = i;
  }
}
static class Result {
  long flow;
  double cost;
  public Result(long flow, double cost) {
    this.flow = flow; this.cost = cost;
  }
}
static class QueueItem implements Comparable<QueueItem> {
  int item;
  double cost;
  public QueueItem(int item, double cost) {
    this.item = item;
    this.cost = cost;
  }
  public int compareTo(QueueItem o) {
    return Double.compare(this.cost, o.cost);
  }
}

static class MinCostMaxFlow {
  ArrayList<Edge>[] edges;
  double[] pi;
  Edge[] dad;
  boolean[] used;
  double[] dist;

  MinCostMaxFlow(int N) {
    edges = new ArrayList[N];
    for (int i = 0; i < N; ++i)
      edges[i] = new ArrayList<>();
    pi = new double[N];
    dad = new Edge[N];
    used = new boolean[N];
    dist = new double[N];
  }

  void addEdge(int from, int to, long cap, double cost) {
    Edge f = new Edge(from, to, cap, cost, edges[to].size());
    Edge b = new Edge(to, from, 0, -cost, edges[from].size());
    edges[from].add(f);
    edges[to].add(b);
    if (from == to) f.index += 1;
  }
  void bellmanFord(int s) {
    for (int i = 0; i < pi.length; ++i)
      pi[i] = Double.POSITIVE_INFINITY;
    pi[s] = 0;
    for (int j = 0; j < edges.length; ++j)
      for (int n = 0; n < edges.length; ++n)
        for (Edge edge : edges[n])
          if (edge.cap > 0)
            pi[edge.to] = Math.min(
                pi[edge.to],
                pi[edge.from] + edge.cost
            );
  }
  Result findShortestPath(int s, int t) {
    for (int i = 0; i < edges.length; ++i) {
      dad[i] = null;
      used[i] = false;
      dist[i] = Double.POSITIVE_INFINITY;
    }
    PriorityQueue<QueueItem> q = new PriorityQueue<>();
    q.add(new QueueItem(s, 0));
    dist[s] = 0;
    while (!q.isEmpty()) {
      QueueItem top = q.poll();
      int node = top.item;
      if (used[node]) continue;
      used[node] = true;
      for (Edge edge : edges[node]) {
        if (used[edge.to] || edge.cap - edge.flow == 0)
          continue;
        double tmp = dist[edge.from]
            + edge.cost
            + pi[edge.from]
            - pi[edge.to];
        if (tmp < dist[edge.to]) {
          dist[edge.to] = tmp;
          dad[edge.to] = edge;
          q.add(new QueueItem(edge.to, tmp));
        }
      }
    }
    if (!used[t]) return null;
    for (int i = 0; i < edges.length; ++i) {
      pi[i] += dist[i];
    }

    long flow = Long.MAX_VALUE;
    for (Edge e = dad[t]; e != null; e = dad[e.from]) {
      flow = Math.min(flow, e.cap - e.flow);
    }

    double cost = 0;
    for (Edge e = dad[t]; e != null; e = dad[e.from]) {
      e.flow += flow;
      edges[e.to].get(e.index).flow -= flow;
      cost += flow * e.cost;
    }

    return new Result(flow, cost);
  }

  Result minCostMaxFlow(int s, int t) {
    long maxF = 0;
    long minC = 0;
    bellmanFord(s);
    while (true) {
      Result path = findShortestPath(s, t);
      if (path == null) break;
      maxF += path.flow;
      minC += path.cost;
    }
    return new Result(maxF, minC);
  }
}
