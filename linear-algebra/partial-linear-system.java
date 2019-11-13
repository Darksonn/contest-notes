// Replace
for (int j = i+1; j < n; ++j) {
  ...
}
// with
for (int j = 0; j < n; ++j)
  if (j != i) {
    ...
  }

// And replace the last part with:
for (int i = 0; i < m; ++i) x[i] = Double.NaN;
outer:
for (int i = 0; i < rank; ++i) {
  for (int j = rank; j < m; ++j) {
    if (Math.abs(A[i][j]) > eps)
      continue outer;
  }
  x[col[i]] = b[i] / A[i][i];
}
