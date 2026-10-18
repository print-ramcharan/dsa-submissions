class Solution {
    private static List<String> res;
    public List<String> restoreIpAddresses(String s) {
        res = new ArrayList<String>();

        if(s.length() < 4 || s.length() > 12) return res;
        dfs(0, 0, "", s);

        return res;
    }
    private void dfs(int start, int pointNum, String currIP,  String s){


        if(pointNum == 3){
            if(isValid(s, start, s.length() - 1)){
                res.add(currIP + s.substring(start));
            }
            return;
        }
        
        for(int i = start; i < Math.min(start + 3, s.length()); i ++){
            if(isValid(s, start, i)){
                String sub = s.substring(start, i + 1);
                dfs(i + 1, pointNum + 1, currIP + sub + ".", s);
            }
        }
    }
    private boolean isValid(String s, int left, int right) {
        if (left > right) return false;
        if (s.charAt(left) == '0' && left != right) return false;

        int num = 0;
        for (int i = left; i <= right; i++) {
            num = num * 10 + (s.charAt(i) - '0');
            
            if (num > 255) return false;
        }
        return true;
    }
}