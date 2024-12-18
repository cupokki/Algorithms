package boj.simulation.n14499;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * https://www.acmicpc.net/problem/14499
 * 주사위 굴리기
 * N by M인 지도가 존재한다. 지도 위 주사위(x, y) 한 개, 모든 면은 0
 * 지도의 칸에 정수가 쓰여 있다.
 * 주사위를 굴려 이동한 칸의 수가
 *  0-> 주사위 바닥에 있는 수가 지도로 복사됨
 *  else -> 지도의 수가 바닥으로, 칸은 0이됨
 *  주사위를 놓은 곳의 좌표 이동명령
 *  이동시 상단에 적힌 값을 출력
 *  (지도 밖으로 이동시에 출력x)
 *  1 : 동, 2 : 서, 3 : 북, 4 : 남
 */
public class Main {
    static int N, M; //  (1 ≤ N, M ≤ 20)
    static int r, c;
    static int K;
    static int[][] map;
    static int[] dice = new int[6];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken()); // x : 북쪽에서 x만큼 떨어져 있다.
        c = Integer.parseInt(st.nextToken()); // y
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int n = 0; n < N; n++) {
            map[n] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < K; k++) {
            int dir = Integer.parseInt(st.nextToken());
            if(moveDice(dir)) bw.write(dice[5]+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static boolean moveDice(int dir){
        // rotate
        // 규칙이 없는 배열을 순회

        final int[][] directions = new int[][]{
                {},
                {0, 2, 5, 3}, //동쪽으로 굴림(방향과 역으로 배열 순회할 것)
                {0, 3, 5, 2},
                {0, 1, 5, 4},
                {0, 4, 5, 1}
        };

        if (dir == 1) { // 2차원 배열로 dx,dy적용 가능함
            if (c + 1 >= M) return false; // 동
            c++;
        } else if (dir == 2) {
            if (c - 1 < 0) return false; // 서
            c--;
        } else if (dir == 3) {
            if (r - 1 < 0) return false; // 북
            r--;
        } else {
            if (r + 1 >= N) return false; // 남
            r++;
        }
        int[] temp = new int[4];
        for(int i = 0; i < 4; i++) {
            temp[i] = dice[directions[dir][(i + 1) % 4]];
        }
        for (int i = 0; i < 4; i++) {
            dice[directions[dir][i]] = temp[i];
        }


        if (map[r][c] == 0){// 밑면이 0이면 지도로 복사
            map[r][c] = dice[0];
        } else { // 0이 아니라면 지도가 주사위로 복사 후, 지도는 0
            dice[0] = map[r][c];
            map[r][c] = 0;
        }

        return true;
    }
}
