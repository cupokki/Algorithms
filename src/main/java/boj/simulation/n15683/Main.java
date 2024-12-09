package boj.simulation.n15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/15683
 * 감시
 * 한쪽, 양쪽, 직각. 양쪽과 한쪽,모든 방향
 * 5종의 cctv가 존재할 수 있고, 각각은 회전 가능함
 * 0은 빈칸, 6은 벽
 * 적절히 배치하여 사각지대의 최솟값을 구한다.
 * 카메라는 벽 취급하지 않는다!!!
 *
 *
 * 시뮬레이션, 브루트포스 해법
 */
public class Main {
    static int n;
    static int m;
    static int[][] room;
    static List<int[]> cctv = new ArrayList<>();
    static int blindspot = 0;
    static int min;
    static int[][] directions = {
            {-1,0}, // 상
            {1, 0}, // 하
            {0, 1}, // 우
            {0, -1} // 좌
    };
    static int[][][] camera = { // [type], [dir]
            {{0}, {1}, {2}, {3}},
            {{0, 1}, {2, 3}},
            {{0, 2}, {1, 2}, {1, 3}, {0, 1}},
            {{0, 2, 3}, {0, 1, 2}, {0, 1, 3}, {1, 2, 3}},
            {{0, 1, 2, 3}}

    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        room = new int[n][m];
        cctv = new ArrayList<>();
        //맵 초기화
        for(int i = 0; i < n; i++ ){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if(room[i][j] >= 1 && room[i][j] <= 5)
                    cctv.add(new int[]{i,j});
                if(room[i][j] == 0)
                    blindspot++;
            }
        }
        /*

        구현


         */

    }
}
