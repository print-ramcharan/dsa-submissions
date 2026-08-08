class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] lastPos = new int[m + 1];
        Arrays.fill(lastPos, -1);
        lastPos[m] = n; 

        int idx = n - 1;
        for (int j = m - 1; j >= 0; j--) {
            while (idx >= 0 && word1.charAt(idx) != word2.charAt(j)) {
                idx--;
            }
            lastPos[j] = idx;
            if (idx >= 0) {
                idx--; 
            }
        }

        int[] result = new int[m];
        boolean changed = false;
        int j = 0;

        for (int i = 0; i < n && j < m; i++) {
            if (word1.charAt(i) == word2.charAt(j)) {
                result[j] = i;
                j++;
            } else if (!changed && lastPos[j + 1] > i) {
                
                result[j] = i;
                changed = true;
                j++;
            }
        }

        return j == m ? result : new int[0];
    }
}