class Solution {
    int[][][] memo;
    public boolean hasValidPath(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        memo = new int[n][m][n + m + 1];
        return dfs(0,0, 0, grid);
    }

    private boolean dfs(int i, int j, int count, char[][] grid){
        int n = grid.length;
        int m = grid[0].length;

        if(grid[i][j] == '('){
            count ++;
        }else{
            count --;
        }

        if(count < 0){
            return false;
        }
        if(i == n - 1 &&  j == m - 1){
            return count == 0;
        }

        if(memo[i][j][count] != 0){
            return memo[i][j][count] == 1;
        }

        int[][] dirs = {{0, 1}, {1, 0}};


        for(int[] dir : dirs){

            int nr = dir[0] + i;
            int nc = dir[1] + j;

            if(nr < n && nc < m){

                if(dfs(nr, nc, count, grid)){
                    memo[i][j][count] = 1;
                    return true;
                }
            }
        }
        memo[i][j][count] = - 1;
        return false;
    }

}