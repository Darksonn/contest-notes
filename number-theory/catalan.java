static BigInteger[] catalan(int len) {
  BigInteger[] res = new BigInteger[len];
  res[0] = BigInteger.valueOf(1);
  for (int i = 1; i < len; ++i) {
    res[i] = res[i-1]
      .multiply(BigInteger.valueOf(2 * (2*i - 1)))
      .divide(BigInteger.valueOf(i + 1));
  }
  return res;
}
