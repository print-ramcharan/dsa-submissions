class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k];
        
        for (int num : nums) {
            long[] nextDp = new long[k];
            int rem = (int)(num % k);
            
            nextDp[rem]++;
            
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRem = (int)((r * (long)rem) % k);
                    nextDp[newRem] += dp[r];
                }
            }
            
            dp = nextDp;
            
            for (int r = 0; r < k; r++) {
                result[r] += dp[r];
            }
        }
        
        return result;
    }
}