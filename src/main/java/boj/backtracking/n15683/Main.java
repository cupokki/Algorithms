package boj.backtracking.n15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/15683
 * 감시
 * 한쪽, 양쪽, 직각. 양쪽과 한쪽,모든 방향
 * 5종의 cctv가 존재할 수 있고, 각각은 회전 가능함
 * 0은 빈칸, 6은 벽
 * 적절히 배치하여 사각지대의 최솟값을 구한다.
 * 카메라는 벽 취급하지 않는다!!!
 *
 * 카메라의 방향별로 모두 확인해야한다.
 * dfs 백트래킹
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
        min = blindspot;
        backtracking(0);
        System.out.println(min);
    }


    static void backtracking(int depth){
        //blindsopt이 0인 경우를 발견했다면 더이상 진행할 필요없음
        if(blindspot == 0){
            min = 0;
            return;
        }
        if(depth == cctv.size()){
            min = Math.min(min, blindspot);
            return;
        }

        // cctv들 순회
//        for (int i = 0; i < cctv.size(); i++ ){

        int[] pos = cctv.get(depth);
        int type = room[pos[0]][pos[1]] - 1; // 인덱스값 때문에

        // cctv종류별 방향 순회
        for(int[] dirs : camera[type]) {
            List<int[]> covered = applyCCTVCoverage(pos, dirs); // 변경된 위치 저장
            backtracking(depth + 1);
            for(int[] p : covered){ // 복구
                room[p[0]][p[1]] = 0;
                blindspot++;
            }
        }
    }

    static List<int[]> applyCCTVCoverage(int[] pos, int[] dirs) {
        List<int[]> covered = new ArrayList<>();
        for(int dir : dirs) {
            int x = pos[0];
            int y = pos[1];
            while (true) {
                x += directions[dir][0];
                y += directions[dir][1];
                if (x < 0 || x >= n || y < 0 || y >= m)
                    break;
                if (room[x][y] == 6) //카메라도 되나? -> 벽취급x 라고 문제에 되어있음
                    break;
                if (room[x][y] == 0) { //이미 -1인 건 건들면 안된다. 지우는 순서가 꼬일수도 있으니
                    room[x][y] = -1;
                    blindspot--;
                    covered.add(new int[]{x, y});//복구용으로 보관
                }
            }
        }
        return covered;
    }
}
