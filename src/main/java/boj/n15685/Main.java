package boj.n15685;

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
 *
 *
 *  상대적인 방향만 기록하면? 스택같은 구조로 해결할 수 있지않을까?
 *  0 : 스택서 선분을 꺼낸다. 끝점을 계산한다. 끝점을 시작점으로 바꾼 걸, 방향을 수정(+1 * 스택깊이)해서 넣는다.
 *  1 : 스택서 선분을 꺼낸다. 끝점을 계산한다. 끝점을 시작점으로 바꾼다.
 *
 *  선분을 꺼낸다.
 *  꺼내진 좌표는 시작점이 된다.
 *  꺼내진 좌표와 방향을 기반으로 새로운 끝점을 만든다.
 *  끝점을 스택에 담는다.
 */
public class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[101][101];
        Stack<int[]> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            map[x][y] = true;
            Queue<Integer> directions = new LinkedList<>();
            directions.offer(d);
            // 0세대
            int gen = 1; // 현재

            while (G > gen) { // == directions.size() == gen
                int curD = (directions.poll() - gen + 4) % 4;
                x += dx[curD];
                y += dy[curD];
                map[x][y] = true;
                directions.offer(curD);
                gen++;
            }
        }

        System.out.println(calcSquareUnitCnt(map));
    }

    static void drawLine(boolean[][] map, int x, int y, int d) {
        map[x + dx[d]][y + dy[d]] = true;
    }

    static int calcSquareUnitCnt(boolean[][] map) {
        int cnt = 0;
        for (int i = 1; i < 100; i++) {
            for (int j = 1; j < 100; j++)
                if (map[i - 1][j - 1] || map[i - 1][j] || map[i][j - 1] || map[i][j])
                    cnt++;
        }
        return cnt;
    }
}
