class Solution {
    public int minimumPushes(String word) {
        int totalPresses = 0;
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            int pressCost = (i / 8) + 1; 
            totalPresses += pressCost;
        }

        return totalPresses;
    }
}