class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;

        comb = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;

            for (int j = 1; j < i; j++) {
                comb[i][j] =
                    (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        return (int) ((ways(nums) - 1 + MOD) % MOD);
    }

    private long ways(int[] nums) {
        int n = nums.length;

        if (n <= 2) return 1;

        int root = nums[0];
        int leftSize = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < root) leftSize++;
        }

        int rightSize = n - 1 - leftSize;

        int[] left = new int[leftSize];
        int[] right = new int[rightSize];

        int l = 0, r = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < root) {
                left[l++] = nums[i];
            } else {
                right[r++] = nums[i];
            }
        }

        long leftWays = ways(left);
        long rightWays = ways(right);

        long interleavings = comb[n - 1][leftSize];

        return (((interleavings * leftWays) % MOD) * rightWays) % MOD;
    }
}
