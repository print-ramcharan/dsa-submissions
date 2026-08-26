class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        return dfs(0, 0, s, k, 0);
    }

    private String dfs(int i, int j, String s, int k, int count) {
        if (j > s.length()) return "";

        if (count == k) {
            String candidate = s.substring(i, j);
            String rest = dfs(i + 1, i + 1, s, k, 0);
            return getBest(candidate, rest);
        }

        if (j == s.length()) {
            return dfs(i + 1, i + 1, s, k, 0);
        }

        int nextCount = count + (s.charAt(j) == '1' ? 1 : 0);
        return dfs(i, j + 1, s, k, nextCount);
    }

    private String getBest(String a, String b) {
        if (a.isEmpty()) return b;
        if (b.isEmpty()) return a;

        if (a.length() < b.length()) return a;
        if (b.length() < a.length()) return b;

        return a.compareTo(b) <= 0 ? a : b;
    }
}