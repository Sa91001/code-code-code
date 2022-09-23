class Solution {
    public int concatenatedBinary(int n) {
        final long modulo = (long) (1e9 + 7);
        long res = 0;
        int binaryDigits = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) binaryDigits++;
            res = ((res << binaryDigits) + i) % modulo;
        }
        return (int) res;
    }
}