package programmers.n131703;

class Solution {
    /*
    앞뒤(각 0, 1)가 존재하는 동전이 놓인 격자에
    초기상태와 목표상태가 주어진다.
    초기상태에서 '최소' 몇번 뒤집어야 목표가 되는가? (실패시 -1)
    
    격자의 각 변의 길이는 10이하

    */
    int[][] b;
    int[][] targetTable;
    public int solution(int[][] beginning, int[][] target) {
        int answer = -1;
        int n = beginning.length;
        int m = beginning[0].length;

        targetTable = target;
        int cnt = 0;

        dfs(0, 0, n, m, beginning);

        return answer;
    }

    void dfs(int r, int c, int n, int m, int[][] table) {
        if (r == n && c == m) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (table[i][j] == targetTable[i][j]) {
                        // false
                    }
                }
            }
            return; // true
        }

        for (int i = r; i < n ;i++) {
            for (int j = 0; j < m; j++) {
                table[i][j] = (table[i][j] + 1) % 2;
                dfs(r+1, c+1, n, m, table);
                table[i][j] = (table[i][j] + 1) % 2;
            }
        }

        for (int i = c; i < m; i++) {
            for (int j = 0; j < m; j++) {
                table[j][i] = (table[i][j] + 1) % 2;
                dfs(r+1, c+1, n, m, table);
                table[j][i] = (table[i][j] + 1) % 2;
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
                new int[][] {
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
                new int[][] {
                        {1, 0, 1},
                        {0, 0, 0},
                        {0, 0, 0},
                }
        ));
    }

}