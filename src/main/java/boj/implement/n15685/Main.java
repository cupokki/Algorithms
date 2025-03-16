package boj.implement.n15685;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 드래곤 커브
 * https://www.acmicpc.net/problem/15685
 * 드래곤 커브의 3속성
 *  1. 시작점
 *  2. 시작방향
 *  3. 세대
 *  (드래곤 커브의 세대의 길이는 1)
 *
 *  세대간 끝점을 축으로 90도 회전하여 연결
 *  100x100의 격자 위에 드래곤 커브가 N개 있다
 *  각 드래곤 커브들이 격자위에 그려질때 만들어지는 1x1크기의 정사각형의 수를 구하라.
 *  N과 x,y,d,g가 순서대로 주어진다.
 *  d :
 *      0: x좌표가 증가하는 방향 (→)
 *      1: y좌표가 감소하는 방향 (↑)
 *      2: x좌표가 감소하는 방향 (←)
 *      3: y좌표가 증가하는 방향 (↓)

 */
public class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[101][101];

        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            List<Integer> dirs = new ArrayList<>();
            dirs.add(d);
            int gen = 1;
            while (G >= gen) {
                int len = dirs.size();
                for (int i = len - 1; i >=0; i--) {
                    dirs.add((dirs.get(i) + 1) % 4);
                }
                gen++;
            }
            drawByDirections(map, x, y, dirs);
        }

        System.out.println(calcSquareUnitCnt(map));
    }
    static void drawByDirections(boolean[][] map, int x, int y, List<Integer> dirs) {
        map[x][y] = true;
        for (int d : dirs) {
            x += dx[d];
            y += dy[d];
            map[x][y] = true;
        }
    }
    static int calcSquareUnitCnt(boolean[][] map) {
        int cnt = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++)
                if (map[i - 1][j - 1] && map[i - 1][j] && map[i][j - 1] && map[i][j])
                    cnt++;
        }
        return cnt;
    }
}
