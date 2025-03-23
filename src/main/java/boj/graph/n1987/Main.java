package boj.graph.n1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 알파벳
 * https://www.acmicpc.net/problem/1987
 * 37분
 *
 * 보드 각칸에 알파벳하나씩
 * 좌상단 칸에 말이 있다.
 * 말은 상하좌우 이동가능
 * 새로 이동한 칸의 알파벳은 지나온 알파벳이 아니어야한다.
 * 말이 최대 몇간 지나갈 수 있는지 구하여라
 *
 * dp 로 발전 가능할것같은데? 고저차 문제
 */
public class Main {
    static int R;
    static int C;
    static char[][] board;
    static int max = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        visited[board[0][0] - 'A'] = true;
        dfs(1, 0, 0);

        System.out.println(max);
    }
    static boolean[] visited = new boolean[26];
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static void dfs(int cnt, int r, int c) {
        max = Math.max(cnt, max);
        if (cnt == 26) {
            return;
        }

        for (int d = 0; d < 4; d++) {

            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C)
                continue;

            int currentAlphabet = board[nr][nc] - 'A';
            if(!visited[currentAlphabet]) {
                visited[currentAlphabet] = true;
                dfs(cnt + 1, nr, nc);
                visited[currentAlphabet] = false;
            }
        }
    }
}
