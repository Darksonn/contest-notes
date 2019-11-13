final long mod = 17; // prime
long modPow(long b, long e) {
  long ans = 1;
  while (e > 0) {
    if ((e & 1) != 0) ans = ans * b % mod;
    b = b * b % mod; e /= 2;
  }
  return ans;
}
