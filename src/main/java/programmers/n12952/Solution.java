package programmers.n12952;

public class Solution {
    /*
    N-Queen, n  < 12이하 자연수

    한 행에 하나의 퀸만 놓을 수 있다.
    12개의 퀸은 모든 열에 배치하는 조합을 구한다. O(n!)
     */
    static int answer;
    public static int solution(int n) {
        answer = 0;
        backtracking(n, new boolean[n][n], new boolean[n],0);
        return answer;
    }
    static void backtracking(int n, boolean[][] board, boolean[] visited, int depth) {

        if (depth == n) {
            answer++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (depth > 0) {
                    if (!validateDigonal(n, board, depth, i)) continue;
                }
                board[depth][i] = true;
                visited[i] = true;
                backtracking(n, board, visited, depth + 1);
                board[depth][i] = false;
                visited[i] = false;
            }
        }
    }
    static boolean validateDigonal(int n, boolean[][] board, int row, int col) {
        int r = row - 1;
        int lc = col - 1;
        while(r >= 0 && lc >= 0) {
            if (board[r][lc])
                return false;
            r -= 1;
            lc -= 1;
        }
        r = row - 1;
        int rc = col + 1;
        while(r >= 0 && rc < n) {
            if (board[r][rc])
                return false;
            r -= 1;
            rc += 1;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution(4));
//        System.out.println(solution(12));
    }
}
