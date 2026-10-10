class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        Map<Integer, Long> map = new HashMap<>();
        long totalOps = (long) k1 + k2;
        for(int i = 0; i < nums1.length; i ++){
            int diff = Math.abs(nums1[i] - nums2[i]);
            if(diff > 0){
                map.put(diff, map.getOrDefault(diff, 0L) + 1);
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        for(int diff : map.keySet()){
            pq.add(diff);
        }
        while(!pq.isEmpty() && totalOps > 0){

            int curr = pq.poll();
            long count = map.get(curr);

            long opsNeeded = count;

            if(totalOps >= opsNeeded){
                totalOps -= opsNeeded;
                map.remove(curr);

                int nextDiff = curr - 1;
                if(nextDiff > 0){
                    map.put(nextDiff, map.getOrDefault(nextDiff, 0L) + count);
                    if(!map.containsKey(nextDiff) || map.get(nextDiff) == count){
                        pq.add(nextDiff);
                    }
                }
            }else{
                map.put(curr, count - totalOps);
                int nextDiff = curr - 1;
                if(nextDiff > 0){
                    map.put(nextDiff, map.getOrDefault(nextDiff, 0L) + totalOps);
                    if(!pq.contains(nextDiff)){
                        pq.add(nextDiff);
                    }
                }
                totalOps = 0;
            }

        }

        long totalSum = 0;
        for(Map.Entry<Integer, Long> entry : map.entrySet()){
            long diff = entry.getKey();
            long count = entry.getValue();
            totalSum += count * diff * diff;
        }

        return totalSum;
    }
}