package programmers.n60061;

import java.util.*;

public class Solution {
    /*
    길이가 1인 기둥과 보를 설치한다.
    기둥은 바닥위나, 보위
    보의 한쪽 끝 부분이 기둥위에 있거나, 보의 양쪽 끝부분이 기둥 다른 보
    보와 기둥은 추가, 삭제가능.
    보는 바닥에 설치x

    xyab 배열
    x,y는 기둥과, 보의 각각 가로좌표, 세로좌표
    a: 0은 기둥, 1은 보
    b: 0은 삭제, 1은 설치

    규칙에 맞지않는 명령은 무시된다.
    기둥과 보의 정보를 xyb로 나타내어 오름차순 정렬하여 출력하라.
    기둥은 반드시 위, 보는 반드시 오른쪽으로 설치한다.
    */
    public static int[][] solution(int n, int[][] buildFrame) {
        boolean[][] beam = new boolean[n][n];
        boolean[][] pillar = new boolean[n][n + 1];

        for (int i = 0; i < n; i++) { // 최대 1000개
            int x = buildFrame[i][0], y = buildFrame[i][1];
            int a = buildFrame[i][2], b = buildFrame[i][3];

            if (a == 0 && b == 0) {

            }
            if (a == 0 && b == 1) {

            }
            if (a == 0 && b == 1) {
                if (y == 0 || pillar[x][y - 1] || x > 0 && beam[x - 1][y])
                    pillar[x][y] = true;
            }
            if (a == 1 && b == 1) {
                if (pillar[x][y - 1] || (x == 0 && beam[x + 1][y]) || (beam[x - 1][y] && beam[x + 1][y]))
                    beam[x][y] = true;
            }

        }
        List<int[]> list = new ArrayList<>();
        for (int y = 0; y < n; y++) {
            for (int x = 0; x <= n; x++) {
                if (x < n && beam[x][y]) list.add(new int[]{x, y, 1});
                if (pillar[x][y]) list.add(new int[]{x, y, 0});
            }
            list.sort(Comparator.comparingInt((int[] a) -> a[0])
                    .thenComparingInt(a -> a[1])
                    .thenComparingInt(a -> a[2]));

            int[][] answer = list.toArray(new int[list.size()][3]);
            return answer;
        }
    }

    public static  void main(String[] args) {
        Arrays.stream(solution(5,
                new int[][] {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}}
        )).forEach(row -> System.out.println(Arrays.toString(row)));
        Arrays.stream(solution(5,
                new int[][] {{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}}
        )).forEach(row -> System.out.println(Arrays.toString(row)));
    }
}
