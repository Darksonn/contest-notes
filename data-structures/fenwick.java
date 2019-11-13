static int lsb(int n) { return n & -n; }
static class Fenwick {
  int[] arr;
  public Fenwick(int[] arr) {
    this.arr = arr;
    for (int i = 0; i < arr.length; ++i) {
      int j = i + lsb(i+1);
      if (j < arr.length) arr[j] += arr[i];
    }
  }
  // inclusive sum: x[0] + x[1] + ... + x[k]
  public int sum(int k) {
    int sum = 0;
    for (int i = k+1; i > 0; i -= lsb(i))
      sum += arr[i-1];
    return sum;
  }
  // add v to x[k]
  public void add(int k, int v) {
    for (int i = k+1; i <= arr.length; i += lsb(i))
      arr[i-1] += v;
  }
}
