class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int best = 0;

        for (int i = 0; i < n; i++) {
            long prod = 1;
            int gcdVal = 0;
            long lcmVal = 1;

            for (int j = i; j < n; j++) {
                int val = nums[j];
                if (prod > Long.MAX_VALUE / val) {
                    break;
                }
                prod *= val;
                gcdVal = (j == i) ? val : gcd(gcdVal, val);
                lcmVal = (j == i) ? val : lcm(lcmVal, val);

                if (isProductEquivalent(prod, gcdVal, lcmVal)) {
                    best = Math.max(best, j - i + 1);
                }
            }
        }

        return best;
    }

    private boolean isProductEquivalent(long prod, int gcdVal, long lcmVal) {
        if (lcmVal > Long.MAX_VALUE / gcdVal) {
            return false;
        }
        return prod == lcmVal * gcdVal;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        long g = gcd((int) Math.min(a, b), (int) (Math.max(a, b) % Math.min(a, b)));
        if (a / g > Long.MAX_VALUE / b) {
            return Long.MAX_VALUE;
        }
        return (a / g) * b;
    }
}