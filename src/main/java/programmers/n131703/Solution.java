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

        dfs(0, n, m, beginning, 0);

        if (min == Integer.MAX_VALUE) {
            return -1;
        }

        return min;
    }


    void invertHorizontal(int[][] table, int r, int n) {
        for (int i = 0; i < n; i++) {
            table[r][i] = (table[r][i] + 1) % 2;
        }
    }

    void check (int n, int m, int[][] table, int[][] targetTable, int cnt) {
        int colFlipCount = 0;

        // 각 열마다 첫 번째 행의 상태를 보고 이 열을 뒤집어야 할지 결정
        for (int j = 0; j < m; j++) {
            boolean needFlip = (table[0][j] != targetTable[0][j]);
            if (needFlip) {
                colFlipCount++;
            }

            // 해당 열의 모든 행이 target과 일치하는지 검증
            for (int i = 0; i < n; i++) {
                int current = table[i][j];
                if (needFlip) {
                    current = (current + 1) % 2;
                }
                if (current != targetTable[i][j]) {
                    return; // 목표 상태를 만들 수 없는 경우 검증 중단
                }
            }
        }

        // 모든 검증을 통과했다면 최소 횟수 갱신
        min = Math.min(min, cnt + colFlipCount);
    }

    void dfs(int r, int n, int m, int[][] table, int cnt) {
        // 더이상 반전하지 않아도 된다면.
//        if ((r == n - 1 && c == m) || (r == n && c == m - 1)) {
        if (r == n) {
            check(n, m, table, table, cnt);
            return;
        }

        if (cnt > min) {
            return;
        }

        invertHorizontal(table, r + 1, m);
        dfs(r + 1, n, m, table, cnt + 1);
        invertHorizontal(table, r + 1, m);

        dfs(r + 1, n, m, table, cnt );
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