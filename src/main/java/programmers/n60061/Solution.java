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
        List<int[]> list = new ArrayList<>();

        boolean[][] beam = new boolean[n][n];
        boolean[][] pillar = new boolean[n][n + 1];

        for (int i = 0; i < n; i++) { // 최대 1000개
            int[] b = buildFrame[i];

            if (b[3] == 0) { // 삭제
                if (b[2] == 0) { // 기둥
                    // 기둥을 지울때, 내 위에 보,기둥이 없으며, 내 왼쪽 위의 보도 없어야한다.

                } else { // 보
                    // 보를 지울때, 내 양쪽 보가 기둥을 보유하는지를 확인해야한다.

                }
            }
            if (b[3] == 1) { // 추가
                if (b[2] == 0) { // 기둥
                    // 기둥을 추가할때, 내 아래, 보나 기둥이 있어야한다.

                } else { // 보
                    // 보를 추가할때, 내 아래 기둥이 있거나, 내 양쪽에 보가 있는지 확인.


                }
            }
        }


        int[][] answer = null;
        return answer;
    }


    public static void main(String[] args) {
        Arrays.stream(solution(5,
                new int[][] {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}}
        )).forEach(row -> System.out.println(Arrays.toString(row)));
        Arrays.stream(solution(5,
                new int[][] {{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}}
        )).forEach(row -> System.out.println(Arrays.toString(row)));
    }
}
