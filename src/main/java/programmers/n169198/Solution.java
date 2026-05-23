package programmers.n169198;

import java.util.Arrays;

public class Solution {
    /*
    mn당구대, 원쿠션연습.
    맞춰야 하는 당구공 위치가 주어진다.
    큣대로 칠 공은 항상 같은 위치

    목표한 공에 맞을때 까지 최소 얼마의 거리를 굴러가야 하는가

    각 회마다 친 공이 굴러간 거리의 최솟값을 제곱하여 배열에 담에 반환한다.
    - 입사각과 반사각은 동일
    - 꼭짓점에 부딪힐 경우 진입 방향의 반대 방향으로 공이 진행
    - 공의 크기는 무시 (일직선상에 있어도 괜찮나봄)
    - 두 좌표가 일치시 "맞음"

    두변반 확인하면 나머지는 대칭이네?
    {1, -1}
    {-1, 1}
    */

    public static int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        // reflect to each axis. and then calculate distance between A to B.
        Arrays.fill(answer, Integer.MAX_VALUE);
        for (int i = 0; i < balls.length; i++) {
            int[] pos = balls[i];
            // 당구대 4변 모든 변을 확인해야한다.

            if (!(startX == pos[0] && startY > pos[1])) // 하단
                answer[i] = Math.min(answer[i], calcDist(startX, -startY, pos[0], pos[1]));
            if (!(startY == pos[1] && startX > pos[0])) // 좌측
                answer[i] = Math.min(answer[i], calcDist(-startX, startY, pos[0], pos[1]));
            if (!(startX == pos[0] && startY < pos[1])) // 상단
                answer[i] = Math.min(answer[i], calcDist(startX, n + (n - startY), pos[0], pos[1]));
            if (!(startY == pos[1] && startX < pos[0])) // 우측
                answer[i] = Math.min(answer[i], calcDist(m + (m - startX), startY, pos[0], pos[1]));
        }
        return answer;
    }

    static int calcDist(int a, int b, int c, int d) {
        return (a - c) * (a - c) + (b - d) * (b - d);
    }

    public static void main(String[] args) {
        Arrays.stream(solution(10, 10, 3, 7, new int[][]{{7, 7}, {2, 7}, {7, 3}})).forEach(i -> System.out.println(i + " "));
        System.out.println();
    }
}