package boj.simulation.n1347;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 미로 만들기
 * 미로 안 한칸에 남쪽을 보며 서있다.
 * 각 칸은 벽이거나 길
 */
public class Main {
    // 시계 방향, 동 남 서 북
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] moves = br.readLine().toCharArray();
        int d = 1;
        int[][] map = new int[n * 2 + 1][n * 2 + 1];
        int r, c;
        r = c = n;
        map[r][c] = 1;
        int sr, sc;
        int er, ec;
        sr = er = r;
        sc = ec = c;
        for (int i = 0; i < moves.length; i++) {

            if (moves[i] == 'R') {
                d = (d - 1 + 4) % 4;
            } else if (moves[i] == 'L') {
                d = (d + 1) % 4;

            } else { // 'F'
                r = r + dr[d];
                sr = Math.min(sr, r);
                er = Math.max(er, r);
                c = c + dc[d];
                sc = Math.min(sc, c);
                ec = Math.max(ec, c);
                map[r][c] = 1;
            }

        }

        for (int i = sr; i <= er; i++) {
            for (int j = sc; j <= ec; j++) {
                System.out.print(map[i][j] == 1?'.':'#');
            }
            System.out.println();
        }

    }
}
