class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int x : nums) {
            int toNext = dp.getOrDefault(x, 0) + 1;
            dp.put(x + 1, toNext);
            ans = Math.max(ans, toNext);

            int toSame = dp.getOrDefault(x - 1, 0) + 1;
            dp.put(x, toSame);
            ans = Math.max(ans, toSame);
        }

        return ans;
    }
}