// Returns long[] { x, lcm(m,n) }
boolean crt2(long a, long m, long b, long n, long[] out) {
  if (b > a) return crt2(b, n, a, m, out);
  long[] arr = euclid(m, n); long u=arr[0],gcd=arr[2];
  if ((a - b) % gcd != 0) return false; // no solution
  long x = (b - a) % n * u % n / gcd * m + a;
  out[1] = (n / gcd) * m; out[0] = x < 0 ? x + out[1] : x;
  return true;
}

boolean crt(long[] a, long[] mod, long[] out) {
  out[0] = a[0]; out[1] = mod[0];
  // Notice that i starts from 1.
  for (int i = 1; i < a.length; ++i)
    if (!crt2(a[i], mod[i], out[0], out[1], out))
      return false; // no solution
  return true;
}
