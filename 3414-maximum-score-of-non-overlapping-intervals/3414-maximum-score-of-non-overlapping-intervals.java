class Solution {
    private List<List<Integer>> intervals;
    private int[] nextNonOverlap;
    private Long[][] memoWeight;
    private List<Integer>[][] memoIndices;

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[][] sorted = new int[n][4]; 
        for (int i = 0; i < n; i++) {
            sorted[i][0] = intervals.get(i).get(0);
            sorted[i][1] = intervals.get(i).get(1);
            sorted[i][2] = intervals.get(i).get(2);
            sorted[i][3] = i;
        }

        Arrays.sort(sorted, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[3], b[3]);
        });

        nextNonOverlap = new int[n];
        for (int i = 0; i < n; i++) {
            int targetR = sorted[i][1];
            int low = i + 1, high = n, ans = n;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (sorted[mid][0] > targetR) {
                    ans = mid;
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            nextNonOverlap[i] = ans;
        }

        this.intervals = intervals;
        memoWeight = new Long[n][5];
        memoIndices = new List[n][5];

        List<Integer> bestList = solve(0, 4, sorted);
        int[] result = new int[bestList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = bestList.get(i);
        }
        return result;
    }

    private List<Integer> solve(int idx, int count, int[][] sorted) {
        if (idx >= sorted.length || count == 0) {
            return new ArrayList<>();
        }

        if (memoIndices[idx][count] != null) {
            return memoIndices[idx][count];
        }

        List<Integer> skipList = solve(idx + 1, count, sorted);
        long skipWeight = getWeight(skipList);

        List<Integer> takeList = new ArrayList<>();
        takeList.add(sorted[idx][3]);
        takeList.addAll(solve(nextNonOverlap[idx], count - 1, sorted));
        Collections.sort(takeList); 
        long takeWeight = getWeight(takeList);

        
        List<Integer> result;
        if (takeWeight > skipWeight) {
            result = takeList;
        } else if (skipWeight > takeWeight) {
            result = skipList;
        } else {
            result = isSmaller(takeList, skipList) ? takeList : skipList;
        }

        memoIndices[idx][count] = result;
        return result;
    }

    private long getWeight(List<Integer> indices) {
        long sum = 0;
        for (int idx : indices) {
            sum += intervals.get(idx).get(2);
        }
        return sum;
    }

    private boolean isSmaller(List<Integer> a, List<Integer> b) {
        if (b.isEmpty()) return true;
        if (a.isEmpty()) return false;
        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }
        return a.size() < b.size();
    }
}