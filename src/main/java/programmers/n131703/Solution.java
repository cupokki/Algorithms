package programmers.n131703;

class Solution {
    /*
    앞뒤(각 0, 1)가 존재하는 동전이 놓인 격자에
    초기상태와 목표상태가 주어진다.
    초기상태에서 '최소' 몇번 뒤집어야 목표가 되는가? (실패시 -1)
    
    격자의 각 변의 길이는 10이하

    */
    int[][] targetTable;
    int min;

    public int solution(int[][] beginning, int[][] target) {
        min = Integer.MAX_VALUE;
        int n = beginning.length;
        int m = beginning[0].length;

        targetTable = target;

        dfs(0, 0, n, m, beginning, 0);

        if (min == Integer.MAX_VALUE) {
            return -1;
        }

        return min;
    }

    void invertColumn(int[][] table, int c, int m) {
        for (int i = 0; i < m; i++) {
            table[i][c] = (table[i][c] + 1) % 2;
        }
    }

    void invertHorizontal(int[][] table, int r, int n) {
        for (int i = 0; i < n; i++) {
            table[r][i] = (table[r][i] + 1) % 2;
        }
    }

    void dfs(int r, int c, int n, int m, int[][] table, int cnt) {
        // 더이상 반전하지 않아도 된다면.
//        if ((r == n - 1 && c == m) || (r == n && c == m - 1)) {
        if (r == n && c == m) {
            min = Math.min(min, cnt);
            return;
        }

        if (cnt > min) {
            return;
        }

        if (r < n && c < m) {
            if (table[r][c] != targetTable[r][c]) {
                invertHorizontal(table, r, n);
                dfs(r + 1, c, n, m, table, cnt + 1);
                invertHorizontal(table, r, n);
            }
            dfs(r + 1, c, n, m, table, cnt);

            if (table[r][c] != targetTable[r][c]) {
                invertColumn(table, c, m); // 반전
                dfs(r, c + 1, n, m, table, cnt + 1);
                invertColumn(table, c, m); // 복구
            }
            dfs(r, c + 1, n, m, table, cnt);
        }
    }


    public static void main(String[] args) {
        Solution sol = new Solution();

        // 5
        System.out.println(sol.solution(
                new int[][]{
                        {0, 1, 0, 0, 0},
                        {1, 0, 1, 0, 1},
                        {0, 1, 1, 1, 0},
                        {1, 0, 1, 1, 0},
                        {0, 1, 0, 1, 0}
                },
                new int[][]{
                        {0, 0, 0, 1, 1},
                        {0, 0, 0, 0, 1},
                        {0, 0, 1, 0, 1},
                        {0, 0, 0, 1, 0},
                        {0, 0, 0, 0, 1}
                }
        ));

        // -1
        System.out.println(sol.solution(
                new int[][]{
                        {0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}
                },
                new int[][]{
                        {1, 0, 1},
                        {0, 0, 0},
                        {0, 0, 0},
                }
        ));
    }

}