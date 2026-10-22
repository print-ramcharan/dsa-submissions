class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        if (n == 1)
            return 0;

        int targetType = -1;
        int targetShift = -1;

        int s0 = nums[0];
        boolean isType0 = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != (i + s0) % n) {
                isType0 = false;
                break;
            }
        }

        if (isType0) {
            targetType = 0;
            targetShift = s0;
        } else {
            int s1 = (n - 1 - nums[0] + n) % n;
            boolean isType1 = true;

            for (int i = 0; i < n; i++) {
                int expected = (n - 1 - i - s1 + n) % n;
                if (nums[i] != expected) {
                    isType1 = false;
                    break;
                }
            }

            if (isType1) {
                targetType = 1;
                targetShift = s1;
            } else {
                return -1;
            }
        }

        int startState = targetType * n + targetShift;
        int targetState = 0;
        int totalStates = 2 * n;
        int[] dist = new int[totalStates];
        Arrays.fill(dist, -1);

        Queue<Integer> queue = new LinkedList<>();
        dist[startState] = 0;
        queue.offer(startState);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (curr == targetState) {
                return dist[curr];
            }

            int currType = curr / n;
            int currShift = curr % n;

            int nextType1 = currType;
            int nextShift1 = (currShift + 1) % n;
            int nextState1 = nextType1 * n + nextShift1;
            if (dist[nextState1] == -1) {
                dist[nextState1] = dist[curr] + 1;
                queue.offer(nextState1);
            }

            int nextType2 = 1 - currType;
            int nextShift2 = (n - currShift) % n;
            int nextState2 = nextType2 * n + nextShift2;
            if (dist[nextState2] == -1) {
                dist[nextState2] = dist[curr] + 1;
                queue.offer(nextState2);
            }
        }

        return dist[targetState];
    }
}