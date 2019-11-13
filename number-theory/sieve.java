public static class Sieve {
  byte[] primes;
  public Sieve(int len) {
    primes = new byte[len / 24 + 1];
    for (int i = 5; i < 8*primes.length; i += 2) {
      if (!isPrime(i)) continue;
      for (int j = 5*i; j < 8*primes.length; j += 2*i) {
        if (j % 3 != 0) setComposite(j);
      }
    }
  }
  public boolean isPrime(int n) {
    if (n <= 1) return false;
    if (n <= 3) return true;
    if (n % 2 == 0 || n % 3 == 0) return false;
    n = n / 3;
    int idx = n >> 3;
    int bit = n & 7;
    return ((primes[idx] >> bit) & 1) == 0;
  }
  private void setComposite(int n) {
    n = n / 3;
    int idx = n >> 3;
    int bit = n & 7;
    primes[idx] |= 1 << bit;
  }
}
