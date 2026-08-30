class Solution {
    public int minimumDeletions(int[] nums) {

        if(nums.length == 1) return 1;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int minIndex = 0;
        int maxIndex = 0;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] < min){
                min = nums[i];
                minIndex = i;
            }

            if(nums[i] > max){
                max = nums[i];
                maxIndex = i;
            }
        }

        // Correct distance from closest edge for each individual element
        int minFront = minIndex + 1;
        int minBack = nums.length - minIndex;
        int minDist = Math.min(minFront, minBack);

        int maxFront = maxIndex + 1;
        int maxBack = nums.length - maxIndex;
        int maxDist = Math.min(maxFront, maxBack);

        int bothFront = Math.max(minFront, maxFront);

        int bothBack = Math.max(minBack, maxBack);

        int split = minDist + maxDist;

        return Math.min(bothFront, Math.min(bothBack, split));
    }
}