package programmers.n150365;

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
        answer = "impossible";
        N = n; M = m;
        R = r; C = c;
        K = k;
        dfs(x, y, 0, new char[k]);
        return answer;
    }
    static void dfs(int x, int y, int k, char[] result) {
        String str = String.valueOf(result);
        if (k == K) {
            if(x == R && y == C && str.compareTo(answer) < 0) answer = str;
            return;
        }
        if (answer.compareTo(str) < 0) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            result[k] = initials[i];
            dfs(nx, ny, k + 1, result);
            result[k] = 'z';
        }

    }
    public static void main(String[] args) {
        System.out.println(solution(3, 4, 2, 3, 3, 1, 5));
        System.out.println(solution(2, 2, 1, 1, 2,2, 2));
        System.out.println(solution(2, 3, 1, 2, 3,3, 4));
    }
}
