static final double eps = 1e-12;
static int solveLinear(
    double[][] A, double[] b, double[] x) {
  int n = A.length, m = x.length, rank = 0;
  int br = 0, bc = 0;
  int[] col = new int[m];
  for (int i = 0; i < m; ++i) col[i] = i;
  for (int i = 0; i < n; ++i) {
    double v, bv = 0;
    for (int r = i; r < n; ++r)
      for (int c = i; c < m; ++c)
        if ((v = Math.abs(A[r][c])) > bv)
        { br = r; bc = c; bv = v; }
    if (bv <= eps) {
      for (int j = i; j < n; ++j)
        if (Math.abs(b[j]) > eps) return -1;
      break;
    }
    swapA(A, i, br);
    swapD(b, i, br);
    swapI(col, i, bc);
    for (int j = 0; j < n; ++j)
      swapD(A[j], i, bc);
    bv = 1 / A[i][i];
    for (int j = i+1; j < n; ++j) {
      double fac = A[j][i] * bv;
      b[j] -= fac * b[i];
      for (int k = i+1; k < m; ++k)
        A[j][k] -= fac*A[i][k];
    }
    rank += 1;
  }

  for (int i = 0; i < m; ++i) x[i] = 0;
  for (int i = rank; i-- > 0; ) {
    b[i] /= A[i][i];
    x[col[i]] = b[i];
    for (int j = 0; j < i; ++j)
      b[j] -= A[j][i] * b[i];
  }
  return rank;
}
static void swapA(double[][] arr, int i, int j) {
  double[] t = arr[i]; arr[i] = arr[j]; arr[j] = t;
}
static void swapD(double[] arr, int i, int j) {
  double t = arr[i]; arr[i] = arr[j]; arr[j] = t;
}
static void swapI(int[] arr, int i, int j) {
  int t = arr[i]; arr[i] = arr[j]; arr[j] = t;
}
