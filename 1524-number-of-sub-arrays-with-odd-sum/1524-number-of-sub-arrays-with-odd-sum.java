class Solution {
    public int numOfSubarrays(int[] arr) {
        int odd = 0, even = 1;
        long sum = 0, count = 0;
        int mod = 1_000_000_007;

        for(int x : arr){
            sum += x;

            if(sum % 2 == 0){
                count = (count + odd) % mod;
                even ++;
            }else{
                count = (count + even) % mod;
                odd ++;
            }
        }

        return (int) count;
    }
}