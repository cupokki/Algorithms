package programmers.n340211;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /*
    r, c 2차원에 물류센터 로봇이 자동운송
    평면 상 1부터 n번호의 포인트 존재.
    - 로봇은 경로 m개 존재, 천포인트에서 할당된 포인트로 순서대로 방문 (routes)
    - 로봇은 x대. 0초에 동시 출발, 초마다 상하좌우 한칸씩 이동
    - 항상 최단경로로 이동. 상하움직임을 우선으로 움직인다.
    - 마지막포인트 도달시 물류센터 이탈(순간이동)
    - 로봇은 충돌가능.
    - 현재 설정에서 몇번의 충돌이 일어나는지 구한다.

    - 센터 크기는 100by 100
    - 포인트는 100이하 자연수.
    - 루트 및 로봇의 수는 100이하 자연수.

    그래프탐색은 적절하지 않을 듯. 두점을 지나는 직선공식 활용같다.
     */
    static class Robot {
        int r, c;
        int[] route;
        int current;

        public Robot(int r, int c, int[] route) {
            this.r = r;
            this.c = c;
            this.route = route;
            this.current = 0;
        }

    }
    public static int solution(int[][] points, int[][] routes) {
        int answer = 0;

        int[][] currentPos = new int[8][8];

        List<Robot> robots = new ArrayList<>();
        for (int i = 0; i < routes.length; i++) {
            int[] p = points[routes[i][0] - 1]; // 0-based convert
            robots.add(new Robot(p[0], p[1], routes[i]));
            currentPos[p[0]][p[1]] = i + 1; // id(i) 대로 기록 temp
        }

        while (robots.size() > 0){
            // 중복 확인
            int[] collided = new int[robots.size()];
            int cnt = 0;
            for (int i = 0; i < robots.size(); i++) {
                Robot roA = robots.get(i);

                if (collided[i] != 0)
                    continue;

                for (int j = i + 1; j < robots.size(); j++) {
                    Robot roB = robots.get(j);
                    if (roA.r == roB.r && roA.c == roB.c) {
                        if (collided[i] == 0) {
                            cnt++;
                        }
                        collided[j] = collided[i] = cnt;
                    }
                }
            }
            answer += cnt;

            List<Robot> removeList = new ArrayList<>();
            // 로봇 이동
            for (int i = 0; i < robots.size(); i++) {
                Robot ro = robots.get(i);
                currentPos[ro.r][ro.c] = 0;
                if (ro.current == ro.route.length - 1) {
                    removeList.add(ro);
                    continue;
                }
                int nextRouteIdx = ro.current + 1;
                int[] next = points[ro.route[nextRouteIdx] - 1];
                if (ro.r != next[0]) {
                    ro.r += ro.r > next[0] ? -1 : 1;
                } else if (ro.c != next[1]){
                    ro.c += ro.c > next[1] ? -1 : 1;
                }
                currentPos[ro.r][ro.c] = i + 1;
                if (ro.r == next[0] && ro.c == next[1]) {
                    ro.current += 1;
                }
            }


            for (Robot r : removeList) {
                robots.remove(r);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        var p1 = new int[][] {
                {3, 2}, {6, 4}, {4, 7}, {1, 4}
        };
        var r1 = new int[][] {
                {4, 2}, {1, 3}, {2, 4}
        };
        System.out.println(solution(p1, r1));

        var p2 = new int[][] {
                {3, 2}, {6, 4}, {4, 7}, {1, 4}
        };
        var r2 = new int[][] {
                {4, 2}, {1, 3}, {4, 2}, {4, 3}
        };
        System.out.println(solution(p2, r2));

        var p3 = new int[][] {
                {2, 2}, {2, 3}, {2, 7}, {6, 6}, {5, 2}
        };
        var r3 = new int[][] {
                {2, 3, 4, 5}, {1, 3, 4, 5}
        };
        System.out.println(solution(p3, r3));
    }
}
