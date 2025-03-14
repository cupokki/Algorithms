package boj.n15685;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 드래곤 커브
 * https://www.acmicpc.net/problem/15685
 * 드래곤 커브의 3속성
 *  1. 시작점
 *  2. 시작방향
 *  3. 세대
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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[100][100];
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            int[] attibutes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] zeroCurve = Arrays.copyOf(attibutes, 3);

            int gen = 0;
            // 0세대
            stack.push(attibutes);
            int[] nextCurve = getNextCurveByCurCurve(zeroCurve[0], zeroCurve[1], (zeroCurve[2] * gen) % 4);
            drawLine(map, attibutes[0], attibutes[1], nextCurve[0], nextCurve[1]);
            while (attibutes[3] > gen) {
                int[] curCurve = stack.pop();
//                int[] nextCurve = getNextCurveByCurCurve(curCurve[0], curCurve[1], (curCurve[2] * gen) % 4);
                drawLine(map, curCurve[0], curCurve[1], nextCurve[0], nextCurve[1]);

                gen++;
            }
        }

        System.out.println(calcSquareUnitCnt(map));
    }
    static int[] getNextCurveByCurCurve(int x, int y, int d) {
        if (d == 0) {

        } else if (d == 1) {

        } else if (d == 2) {

        } else {

        }
    }
    static void drawLine(boolean[][] map, int sx, int sy, int ex, int ey) {
        if (sx == ex) {
            for (int y = sy; y <= ey; y++)
                map[sx][y] = true;
        }else {
            for (int x = sx; x <= ex; x++)
                map[x][sy] = true;
        }
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
