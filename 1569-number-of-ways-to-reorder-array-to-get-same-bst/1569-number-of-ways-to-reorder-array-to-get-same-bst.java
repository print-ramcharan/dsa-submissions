class Solution {
    private static final long MOD = 1000000007L;
    public int numOfWays(int[] nums) {
        int n = nums.length;

        long[][] comb = new long[n + 1][n + 1];

        for(int i = 0; i <= n; i ++){
            comb[i][0] = comb[i][i] = 1;
            for(int j = 1; j < i; j ++){
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>(n);
        for(int num : nums){
            list.add(num);
        }
        return (int) ((ways(list, comb) - 1 + MOD) % MOD);
    }

    private long ways(List<Integer> list, long[][] comb){
        int n = list.size();
        
        if(n <= 2){
            return 1;
        }

        int root = list.get(0);

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for(int i = 1; i < n; i ++){
            int val = list.get(i);
            if(val < root){
                left.add(val);
            }else{
                right.add(val);
            }
        }

        long lWays = ways(left, comb);
        long rWays = ways(right, comb);

        long preserved = comb[left.size() + right.size()][left.size()];

        return (((preserved * lWays) % MOD) * rWays) % MOD;
    }
}