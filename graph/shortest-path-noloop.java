public static class BellmanFord {
  int N;
  ArrayList<Edge>[] edges;
  long[] distance;

  public BellmanFord(int N) {
    this.N = N;
    this.distance = new long[N];
    this.edges = new ArrayList[N];
    for (int i = 0; i < N; ++i)
      this.edges[i] = new ArrayList<>();
  }

  public void addEdge(int from, int to, long cost) {
    edges[from].add(new Edge(from, to, cost));
  }
  public void bellmanFord(int source) {
    for (int i = 0; i < N; ++i)
      distance[i] = Long.MAX_VALUE;
    distance[source] = 0;
    ArrayDeque<Integer> queue = new ArrayDeque<>();
    boolean[] inStack = new boolean[N];
    queue.add(source);
    inStack[source] = true;
    while (!queue.isEmpty()) {
      int node = queue.pollLast();
      inStack[node] = false;
      long dist = distance[node];
      for (Edge e : edges[node]) {
        int neigh = e.to;
        if (dist + e.cost < distance[neigh]) {
          distance[neigh] = dist + e.cost;
          if (!inStack[neigh]) {
            inStack[neigh] = true;
            queue.addLast(neigh);
          }
        }
      }
    }
  }

  public static class Edge {
    int from, to;
    long cost;
    public Edge(int from, int to, long cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }
}
