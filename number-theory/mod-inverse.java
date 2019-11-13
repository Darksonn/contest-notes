long modInvert(long a, long mod) {
  long arr = euclid(a, mod);
  if (arr[2] != 1) return -1; // not coprime
  return (arr[0] + mod) % mod;
}
