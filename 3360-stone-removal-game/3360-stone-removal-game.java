class Solution {
    public boolean canAliceWin(int n) {
        return canRemove(n, 10);
    }
    private boolean canRemove(int stones, int remove){
        if(stones < remove) return false;

        return !canRemove(stones - remove, remove - 1);
    }
}