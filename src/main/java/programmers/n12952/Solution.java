package programmers.n12952;

public class Solution {
    /*
    N-Queen, n  < 12이하 자연수

    한 행에 하나의 퀸만 놓을 수 있다.
    12개의 퀸은 모든 열에 배치하는 조합을 구한다. O(n!)
     */
//    static int answer;
//    public static int solution(int n) {
//        answer = 0;
//        backtracking(n, new boolean[n][n], new boolean[n],0);
//        return answer;
//    }
//    static void backtracking(int n, boolean[][] board, boolean[] visited, int depth) {
//
//        if (depth == n) {
//            answer++;
//            return;
//        }
//
//        for (int i = 0; i < n; i++) {
//            if (!visited[i]) {
//                if (depth > 0 && !validateDigonal(n, board, depth, i)) {
//                    continue;
//                }
//                board[depth][i] = true;
//                visited[i] = true;
//                backtracking(n, board, visited, depth + 1);
//                board[depth][i] = false;
//                visited[i] = false;
//            }
//        }
//    }
//    static boolean validateDigonal(int n, boolean[][] board, int row, int col) {
//        int r = row - 1;
//        int lc = col - 1;
//        while(r >= 0 && lc >= 0) {
//            if (board[r][lc])
//                return false;
//            r -= 1;
//            lc -= 1;
//        }
//        r = row - 1;
//        int rc = col + 1;
//        while(r >= 0 && rc < n) {
//            if (board[r][rc])
//                return false;
//            r -= 1;
//            rc += 1;
//        }
//        return true;
//    }
    static int answer;
    static boolean[] visited;  // 열 체크
    static boolean[] diag1;    // 우상향 대각선 체크 (row + col)
    static boolean[] diag2;    // 좌상향 대각선 체크 (row - col + n)

    public static int solution(int n) {
        answer = 0;
        visited = new boolean[n];
        diag1 = new boolean[2 * n]; // 합의 최대치는 2n-2
        diag2 = new boolean[2 * n]; // 차의 최솟값은 -(n-1)이므로 +n 보정

        backtracking(n, 0);
        return answer;
    }

    static void backtracking(int n, int depth) {
        if (depth == n) {
            answer++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i] && !diag1[depth + i] && !diag2[depth - i + n]) {
                visited[i] = diag1[depth + i] = diag2[depth - i + n] = true;
                backtracking(n, depth + 1);
                visited[i] = diag1[depth + i] = diag2[depth - i + n] = false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(4));
        System.out.println(solution(12));
    }
}
