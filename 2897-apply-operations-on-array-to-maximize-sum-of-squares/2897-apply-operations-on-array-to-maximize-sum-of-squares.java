class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bits = new int[32];

        for(int num : nums){
            for(int i = 0; i < 32; i ++){
                if((num & (1 << i)) != 0){
                    bits[i] ++;
                }
            }
        }

        long MOD = 1_000_000_007;
        long totalSum = 0;

        for(int i = 0; i < k; i ++){
            long currentNum = 0;
            for(int b = 31; b >= 0; b --){
                if(bits[b] > 0){
                    currentNum |= (1L << b);
                    bits[b] --;
                }
            }
            totalSum = (totalSum + (currentNum * currentNum) % MOD) % MOD;
        }

        return (int) totalSum;
    }
}