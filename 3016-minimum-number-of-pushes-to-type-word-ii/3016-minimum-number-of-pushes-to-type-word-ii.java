import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        Arrays.sort(freq);

        int totalPresses = 0;
        int distinctLettersCount = 0;

        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) break;

            int pressCost = (distinctLettersCount / 8) + 1;
            totalPresses += freq[i] * pressCost;
            distinctLettersCount++;
        }

        return totalPresses;
    }
}