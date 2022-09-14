class Solution {
  public int distinctSubseqII(String s) {
    final int kMod = 1_000_000_007;
    
    long[] endsWith = new long[26];

    for (final char c : s.toCharArray())
      endsWith[c - 'a'] = (Arrays.stream(endsWith).sum() + 1) % kMod;

    return (int) (Arrays.stream(endsWith).sum() % kMod);
  }
}