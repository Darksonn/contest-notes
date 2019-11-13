public static class ShortestPath {
  int N;
  ArrayList<Edge>[] edges;
  long[] distance; // or double

  public ShortestPath(int N) {
    this.N = N;
    this.distance = new long[N];
    this.edges = new ArrayList[N];
    for (int i = 0; i < N; ++i)
      this.edges[i] = new ArrayList<>();
  }

  public void addEdge(int from, int to, long cost) {
    edges[from].add(new Edge(from, to, cost));
  }
  public void shortestPath(int source) {
    // or Double.POSITIVE_INFINITY
    for (int i = 0; i < N; ++i)
      distance[i] = Long.MAX_VALUE;
    distance[source] = 0;
    ArrayDeque<Integer> queue = new ArrayDeque<>();
    boolean[] inStack = new boolean[N];
    int[] timesSeen = new int[N];
    queue.add(source);
    inStack[source] = true;
    Stack<Integer> infiniteDistance = new Stack<>();
    while (!queue.isEmpty()) {
      int node = queue.pollLast();
      if (timesSeen[node]++ > N) {
        infiniteDistance.add(node);
        continue;
      }
      inStack[node] = false;
      long dist = distance[node];
      for (Edge e : edges[node]) {
        int neigh = e.to;
        // or distance[neigh] - eps
        if (dist + e.cost < distance[neigh]) {
          distance[neigh] = dist + e.cost;
          if (!inStack[neigh]) {
            inStack[neigh] = true;
            queue.addLast(neigh);
          }
        }
      }
    }

    // Mark negative loops with -inf
    for (int i : infiniteDistance) inStack[i] = true;
    while (!infiniteDistance.isEmpty()) {
      int node = infiniteDistance.pop();
      // or Double.NEGATIVE_INFINITY
      distance[node] = Long.MIN_VALUE;
      for (Edge e : edges[node]) {
        if (!inStack[e.to]) {
          infiniteDistance.add(e.to);
          inStack[e.to] = true;
        }
      }
    }
  }

  public static class Edge {
    int from, to;
    long cost; // or double
    public Edge(int from, int to, long cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }
}
