class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> origAdj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            origAdj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            origAdj.get(parent[i]).add(i);
        }

        int[] newParent = Arrays.copyOf(parent, n);
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        findNewParents(0, origAdj, s, lastSeen, newParent);

        List<List<Integer>> finalAdj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            finalAdj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            finalAdj.get(newParent[i]).add(i);
        }

        int[] ans = new int[n];
        computeSizes(0, finalAdj, ans);
        return ans;
    }

    private void findNewParents(int node, List<List<Integer>> origAdj, String s, int[] lastSeen, int[] newParent) {
        int idx = s.charAt(node) - 'a';
        if (lastSeen[idx] != -1) {
            newParent[node] = lastSeen[idx];
        }

        int prev = lastSeen[idx];
        lastSeen[idx] = node;

        for (int child : origAdj.get(node)) {
            findNewParents(child, origAdj, s, lastSeen, newParent);
        }

        lastSeen[idx] = prev;
    }

    private int computeSizes(int node, List<List<Integer>> finalAdj, int[] ans) {
        int size = 1;
        for (int child : finalAdj.get(node)) {
            size += computeSizes(child, finalAdj, ans);
        }
        ans[node] = size;
        return size;
    }
}