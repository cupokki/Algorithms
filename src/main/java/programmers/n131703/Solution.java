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

    void dfs(int r, int c, int n, int m, int[][] table, int depth) {
        // 더이상 반전하지 않아도 된다면.
        if (r == n && c == m) {
            min = Math.min(min, depth);
            return;
        }

        if (depth > min) {
            return;
        }

        for (int i = r; i < n; i++) {
            // 한번 열을 뒤집었을때, 각 행이 목표와 다르면 그 열은 반드시 뒤집어야한다.
            for (int j = 0; j < m; j++) {
                table[i][j] = (table[i][j] + 1) % 2; // 반전
            }

            for (int j = 0; j < m; j++) {
                if (targetTable[i][j] != table[i][j]) {
                    dfs(r + 1, c + 1, n, m, table, depth + 1);
                }
            }

            for (int j = 0; j < m; j++) {
                table[i][j] = (table[i][j] + 1) % 2; // 복구
            }
        }

        for (int i = c; i < m; i++) {
            for (int j = 0; j < m; j++) {
                table[j][i] = (table[i][j] + 1) % 2; // 반전
            }
            for (int j = 0; j < m; j++) {
                // 한번 행을 뒤집었을때, 각 열이 목표와 다르면 그 열은 반드시 뒤집어야한다.
                if (targetTable[j][i] != table[j][i]) {
                    dfs(r + 1, c + 1, n, m, table, depth + 1);
                }
            }
            for (int j = 0; j < m; j++) {
                table[j][i] = (table[i][j] + 1) % 2; // 복구
            }
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
                        {9, 0, 0, 0, 1},
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