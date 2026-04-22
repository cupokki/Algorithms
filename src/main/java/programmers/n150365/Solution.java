package programmers.n150365;

import java.util.Arrays;

public class Solution {
    /*
    nm 크기 격자 미로
    xy에서 출발해 rc로 이동
    총 이동거리가 k이어야하며, 출발지와 도착지를 포함해 같은 격자를 두번이상 방문해도된다.
    미로에서 탈출한 경로를 문자열로 나타냈을때, 문자열이 사전 순으로 가장 빠른 경로를 출력한다.

    미로는 각 변이 50 이하
    k는 2500이하 자연수
    탈출 불가시 impossible 출력
     */
    static int[] dx = {1, 0, 0, -1}; //dlru : 아래 왼 오 위 순
    static int[] dy = {0, -1, 1, 0};
    static char[] initials = {'d', 'l', 'r', 'u'};
    static int N, M;
    static int R, C;
    static int K;
    static String answer;
    public static String solution (int n, int m, int x, int y, int r, int c, int k) {
        N = n; M = m;
        R = r; C = c;
        K = k;
        answer = "";
        int minDist = Math.abs(R - x) + Math.abs(C - y);
        // 이동횟수가 최단거리보다 같거나 커야하고
        // 최단거리이동 후 남은 거리가 짝수 이어야함
        if (minDist <= k && (k - minDist) % 2 == 0)
            dfs(x, y, 0, new StringBuilder());
        return answer.isBlank() ? "impossible" : answer;
    }
    static void dfs(int x, int y, int depth, StringBuilder sb) {
        if (!answer.isBlank()) return;

        int remain = Math.abs(R - x) + Math.abs(C - y);
        if (remain > K - depth) return;

        if (depth == K) { // k == depth일때, remain == 0;
            answer =sb.toString();
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 1 || nx >= N + 1 || ny < 1 || ny >= M + 1) continue;
            sb.append(initials[i]);
            dfs(nx, ny, depth + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    public static void main(String[] args) {
        System.out.println(solution(3, 4, 2, 3, 3, 1, 5));
        System.out.println(solution(2, 2, 1, 1, 2,2, 2));
        System.out.println(solution(2, 3, 1, 2, 3,3, 4));
    }
}
