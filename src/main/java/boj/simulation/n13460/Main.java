package boj.simulation.n13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/13460
 * 구슬 탈출 2
 * 직사각형 보드에 빨간 구슬, 파란구슬
 * 빨강, 파랑 하나씩 넣고 빨간 구슬을 구멍을 통해 빼는 게임
 * (파란 구슬을 빠져나가면 안됨, 동시에 나가도 안됨)
 * 중력으로 판을 기울여 구슬을 움직인다.
 * 10번 이하로 움직임, 최소 몇번의 기울임(4방향)으로 빨간 구슬을 뺄 수 있나(불가능시 -1)
 * . 빈칸, # 벽, o 구멍, R,B
 */
public class Main {
    static int N, M;
    static char[][] board;
    static int min = Integer.MAX_VALUE;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        int rr = 0, rc = 0, br = 0, bc = 0;
        for (int i = 0; i < N; i++) {
            String line = bufferedReader.readLine();
            board[i] = line.toCharArray();
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'R') {
                    rr = i;
                    rc = j;
                } else if (board[i][j] == 'B') {
                    br = i;
                    bc = j;
                }
            }
        }

        dfs(0, rr, rc, br, bc);
        System.out.println(min != Integer.MAX_VALUE ? min : -1);
    }

    static void dfs(int depth, int rr, int rc, int br, int bc) {
        if (depth >= 10 || depth >= min) return;

        for (int d = 0; d < 4; d++) {
            int[] nextRed = move(rr, rc, d);
            int[] nextBlue = move(br, bc, d);

            int nrr = nextRed[0], nrc = nextRed[1];
            int nbr = nextBlue[0], nbc = nextBlue[1];

            if (board[nbr][nbc] == 'O') continue; // 파란 구슬이 구멍에 빠지면 무효
            if (board[nrr][nrc] == 'O') { // 빨간 구슬이 구멍에 빠지면 성공
                min = Math.min(min, depth + 1);
                return;
            }

            // 두 구슬이 같은 위치에 있을 경우
            if (nrr == nbr && nrc == nbc) {
                if (nextRed[2] > nextBlue[2]) { // 더 많이 이동한 구슬을 뒤로
                    nrr -= dr[d];
                    nrc -= dc[d];
                } else {
                    nbr -= dr[d];
                    nbc -= dc[d];
                }
            }

            dfs(depth + 1, nrr, nrc, nbr, nbc);
        }
    }

    static int[] move(int r, int c, int d) {
        int steps = 0;
        while (board[r + dr[d]][c + dc[d]] != '#' && board[r][c] != 'O') {
            r += dr[d];
            c += dc[d];
            steps++;
        }
        return new int[]{r, c, steps};
    }
}
