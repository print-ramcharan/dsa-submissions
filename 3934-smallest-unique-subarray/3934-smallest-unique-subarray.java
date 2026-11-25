class Solution {
    private static final long BASE1 = 1_000_003L;
    private static final long MOD1 = 1_000_000_007L;
    private static final long BASE2 = 1_000_033L;
    private static final long MOD2 = 1_000_000_009L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        long[] pow1 = new long[n + 1];
        long[] pow2 = new long[n + 1];
        pow1[0] = 1;
        pow2[0] = 1;

        for (int i = 1; i <= n; i++) {
            pow1[i] = (pow1[i - 1] * BASE1) % MOD1;
            pow2[i] = (pow2[i - 1] * BASE2) % MOD2;
        }

        int low = 1, high = n, ans = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUnique(nums, mid, pow1, pow2)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean hasUnique(int[] nums, int len, long[] pow1, long[] pow2) {
        int n = nums.length;
        long h1 = 0, h2 = 0;

        for (int i = 0; i < len; i++) {
            h1 = (h1 * BASE1 + nums[i]) % MOD1;
            h2 = (h2 * BASE2 + nums[i]) % MOD2;
        }

        Map<Long, Integer> freq = new HashMap<>();
        freq.put(combine(h1, h2), 1);

        long drop1 = pow1[len - 1];
        long drop2 = pow2[len - 1];

        for (int i = len; i < n; i++) {
            long out = nums[i - len];
            long in = nums[i];

            h1 = (h1 - (out * drop1) % MOD1 + MOD1) % MOD1;
            h1 = (h1 * BASE1 + in) % MOD1;

            h2 = (h2 - (out * drop2) % MOD2 + MOD2) % MOD2;
            h2 = (h2 * BASE2 + in) % MOD2;

            long key = combine(h1, h2);
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }
        return false;
    }

    private long combine(long h1, long h2) {
        return (h1 << 32) ^ (h2 & 0xffffffffL);
    }
}