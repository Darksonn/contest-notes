static class SSC {
  int N;
  ArrayList<Integer>[] edges;
  int[] assignment;
  public SSC(int N) {
    this.N = N;
    this.edges = new ArrayList[N];
    for (int i = 0; i < N; ++i)
      edges[i] = new ArrayList<>();
    visited = new boolean[N];
    assignment = new int[N];
    for (int i = 0; i < N; ++i)
      assignment[i] = -1;
  }
  public void addEdge(int from, int to) {
    edges[from].add(to);
  }
  ArrayList<Integer> L = new ArrayList<>();
  boolean[] visited;

  // Returns the number of components.
  public int kosaraju() {
    int num = 0;
    for (int i = 0; i < N; ++i) visit(i);
    for (int i : L) num += assign(i, i);
    return num;
  }
  private void visit(int n) {
    if (visited[n]) return;
    visited[n] = true;
    for (int neigh : edges[n]) visit(neigh);
    L.add(n);
  }
  private int assign(int n, int r) {
    if (assignment[n] != -1) return 0;
    assignment[n] = r;
    for (int neigh : edges[n]) assign(neigh, r);
    return 1;
  }
}
