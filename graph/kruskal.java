static class Edge implements Comparable<Edge> {
  int length, from, to;
  Edge(int from, int to, int length) {
    this.length = length;
    this.from = from; this.to = to;
  }
  int compareTo(Edge o) {
    return Integer.compare(this.length, o.length);
  }
}
static class Kruskal {
  int N;
  ArrayList<Edge> edges = new ArrayList<>();
  UnionFind uf;
  Kruskal(int N) { this.N = N; }
  void addEdge(int from, int to, int length) {
    edges.add(new Edge(from, to, length));
  }
  // Adds edges in minimal tree to given list and
  // return total length of edges.
  int spanningTree(ArrayList<Edge> tree) {
    uf = new UnionFind(N);
    Collections.sort(edges);
    int length = 0;
    for (Edge edge : edges) {
      int left = uf.find(edge.from);
      int right = uf.find(edge.to);
      if (left == right) continue;
      uf.union(left, right);
      tree.add(edge);
      length += edge.length;
    }
    return length;
  }
  boolean isConnected() {
    return uf.sz[uf.find(0)] == N;
  }
  int numComponents() {
    int c = 0;
    for (int i = 0; i < N; ++i)
      if (uf.p[i] == i)
        c += 1;
    return c;
  }
}
